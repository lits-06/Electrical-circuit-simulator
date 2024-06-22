package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CircuitComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class CircuitController {

    @FXML
    private TextField dcVoltage;

    @FXML
    private TextField acVoltage;

    @FXML
    private TextField acFrequency;

    @FXML
    private Label voltageUnit;

    @FXML
    private Label hzUnit;

    @FXML
    private VBox elementContainer;

    @FXML
    private ChoiceBox<String> sourceType;

    private ObservableList<String> sourceItems;

    @FXML
    private Button btnSubmit;

    private int resistorCount = 0;
    private int capacitorCount = 0;
    private int inductorCount = 0;
    private List<CircuitComponent> components = new ArrayList<>();
    private CircuitComponent source;

    private final int MAX_ELEMENTS = 5;
    private boolean alertMaxElementsShown = false;

    @FXML
    void initialize() {
        sourceItems = FXCollections.observableArrayList("DC", "AC");

        sourceType.setItems(sourceItems);

        sourceType.setValue("DC");
        dcVoltage.setVisible(true);
        voltageUnit.setVisible(true);
    }

    public List<CircuitComponent> getComponents() {
        return components;
    }

    public String getSourceType() {
        return sourceType.getValue();
    }

    public CircuitComponent getSource() {
        return source;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public int getInductorCount() {
        return inductorCount;
    }

    public int[] getComponentCounts() {
        int[] counts = new int[3];
        counts[0] = resistorCount;
        counts[1] = capacitorCount;
        counts[2] = inductorCount;
        return counts;
    }

    public void setSourceType(String type) {
        sourceType.setValue(type);
    }

    public void setSource(CircuitComponent source) {
        this.source = source;
    }

    public void setComponents(List<CircuitComponent> components) {
        this.components = components;
    }

    public void setComponentCounts(int[] counts) {
        this.resistorCount = counts[0];
        this.capacitorCount = counts[1];
        this.inductorCount = counts[2];
    }

    @FXML
    public void changeCircuitScene(MouseEvent event, String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent newRoot = loader.load();

        Stage stage = (Stage) ((HBox) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void handleSourceChange() {
        String selectedType = sourceType.getValue();

        if ("DC".equals(selectedType)) {
            dcVoltage.setVisible(true);

            voltageUnit.setVisible(true);

            acVoltage.setVisible(false);
            acFrequency.setVisible(false);
            hzUnit.setVisible(false);
        } else if ("AC".equals(selectedType)) {
            acVoltage.setVisible(true);
            acFrequency.setVisible(true);

            voltageUnit.setVisible(true);
            hzUnit.setVisible(true);

            dcVoltage.setVisible(false);
        }
    }

    @FXML
    private void addResistor() {
        resistorCount++;
        addElement("Resistor", "R", "Ω");
    }

    @FXML
    private void addCapacitor() {
        capacitorCount++;
        addElement("Capacitor", "C", "µF");
    }

    @FXML
    private void addInductor() {
        inductorCount++;
        addElement("Inductor", "L", "µH");
    }

    private void addElement(String elementType, String elementName, String elementUnit) {
        if (resistorCount + capacitorCount + inductorCount > MAX_ELEMENTS) {
            if (!alertMaxElementsShown) {
                HBox newElement = new HBox(10);
                Label nameLabel = new Label("Cannot add more than " + MAX_ELEMENTS + " elements.");
                newElement.getChildren().addAll(nameLabel);
                elementContainer.getChildren().add(newElement);
                alertMaxElementsShown = true;
            }
            return;
        }

        // Trong trường hợp người dùng bấm submit khi chưa nhập đến tối đa số phần tử,
        // sẽ hiện thông báo lỗi, nếu sau đó người dùng thêm phần tử điện thì phải xóa
        // thông báo này rồi mới thêm phần tử
        elementContainer.getChildren()
                .removeIf(node -> node instanceof HBox && "error".equals(node.getUserData()));

        HBox newElement = new HBox(10);
        Label nameLabel = new Label(elementName);
        TextField parameterField = new TextField();
        Label itemUnit = new Label(elementUnit);
        Button deleteButton = new Button("X");

        CircuitComponent component = new CircuitComponent(elementType, elementName, elementUnit, "");
        components.add(component);

        parameterField.textProperty().addListener((observable, oldValue, newValue) -> {
            component.setValue(newValue);
        });

        deleteButton.setOnAction(event -> {
            elementContainer.getChildren().remove(newElement);
            components.remove(component);
            switch (elementType) {
                case "Resistor" -> resistorCount--;
                case "Capacitor" -> capacitorCount--;
                case "Inductor" -> inductorCount--;
            }
        });

        newElement.getChildren().addAll(nameLabel, parameterField, itemUnit, deleteButton);
        elementContainer.getChildren().add(newElement);
    }

    @FXML
    public void handleReset() {
        elementContainer.getChildren().removeIf(node -> node instanceof HBox);
        components.clear();
        resistorCount = 0;
        capacitorCount = 0;
        inductorCount = 0;
    }

    @FXML
    public void handleSubmit() throws Exception {
        if ("AC".equals(sourceType.getValue())) {
            String acVoltageValue = acVoltage.getText();
            String acFrequencyValue = acFrequency.getText();

            // Kiểm tra giá trị của nguồn hợp lệ
            if (!checkValid(acVoltageValue) || !checkValid(acFrequencyValue)) {
                showError("AC voltage or frequency invalid");
                throw new Exception("valid error");
            }

            // Kiểm tra chưa có phần tử nào
            if (components.isEmpty()) {
                showError("No components added");
                throw new Exception("no components added");
            }

            // Kiểm tra từng phần tử, xem phần tử nào không hợp lệ
            for (CircuitComponent component : components) {
                String componentValue = component.getValue();
                if (!checkValid(componentValue)) {
                    showError(component.getName() + " invalid value");
                    throw new Exception("valid error");
                }
            }

            source = new CircuitComponent("acSource", "U", "V", acVoltageValue,
                    "Hz", acFrequencyValue);
        } else if ("DC".equals(sourceType.getValue())) {
            String dcVoltageValue = dcVoltage.getText();

            if (!checkValid(dcVoltageValue)) {
                showError("DC voltage invalid");
                throw new Exception("valid error");
            }

            if (components.isEmpty()) {
                showError("No components added");
                throw new Exception("No components added");
            }

            for (CircuitComponent component : components) {
                String componentValue = component.getValue();
                if (!checkValid(componentValue)) {
                    showError(component.getName() + " invalid value");
                    throw new Exception("valid error");
                }
            }

            source = new CircuitComponent("dcSource", "U", "V", dcVoltageValue);
        }
    }

    // Giá trị của DC, AC và frequency > 0
    private boolean checkValid(String value) {
        try {
            double numericValue = Double.parseDouble(value);
            return numericValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Hiển thị lỗi trong giao diện trước khi chuyển sang giao diện xử lí mạch
    protected void showError(String message) {
        // Xóa thông báo lỗi trước đó, rôi mới thêm thông báo lỗi mới vào.
        // Dùng getUserData và setUserData để gắn dữ liệu cho node báo lỗi
        // là "error"
        elementContainer.getChildren()
                .removeIf(node -> node instanceof HBox && "error".equals(node.getUserData()));

        HBox errorMessageBox = new HBox(10);
        errorMessageBox.setUserData("error");
        Label errorMessageLabel = new Label(message);

        errorMessageBox.getChildren().addAll(errorMessageLabel);
        elementContainer.getChildren().add(errorMessageBox);
    }

    public void updateUI() {
        handleSourceChange();

        if ("DC".equals(sourceType.getValue())) {
            dcVoltage.setText(source.getValue());
        } else if ("AC".equals(sourceType.getValue())) {
            acVoltage.setText(source.getValue());
            acFrequency.setText(source.getValue2());
        }

        resistorCount = 0;
        capacitorCount = 0;
        inductorCount = 0;
        elementContainer.getChildren().removeIf(node -> node instanceof HBox);
        for (CircuitComponent component : components) {
            switch (component.getType()) {
                case "Resistor" -> resistorCount++;
                case "Capacitor" -> capacitorCount++;
                case "Inductor" -> inductorCount++;
            }

            HBox newElement = new HBox(10);
            Label nameLabel = new Label(component.getName());
            TextField parameterField = new TextField(component.getValue());
            Label itemUnit = new Label(component.getUnit());
            Button deleteButton = new Button("X");

            deleteButton.setOnAction(event -> {
                elementContainer.getChildren().remove(newElement);
                components.remove(component);
                switch (component.getType()) {
                    case "Resistor" -> resistorCount--;
                    case "Capacitor" -> capacitorCount--;
                    case "Inductor" -> inductorCount--;
                }
            });

            newElement.getChildren().addAll(nameLabel, parameterField, itemUnit, deleteButton);
            elementContainer.getChildren().add(newElement);
        }

    }
}
