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
import models.ACSource;
import models.CircuitComponent;
import models.DCSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitController {

    @FXML
    private HBox hboxParallel;

    @FXML
    private HBox hboxSerial;

    @FXML
    private Label parallelLabel;

    @FXML
    private Label serialLabel;

    @FXML
    private HBox sourceInfo;

    @FXML
    private VBox elementInfo;

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
    List<CircuitComponent> components = new ArrayList<>();
    List<ACSource> acSources = new ArrayList<>();
    List<DCSource> dcSources = new ArrayList<>();

    private final int MAX_ELEMENTS = 5;
    private boolean alertMaxElementsShown = false;

    @FXML
    void initialize() {
        sourceItems = FXCollections.observableArrayList("AC", "DC");

        sourceType.setItems(sourceItems);

        sourceType.setValue("DC");
        dcVoltage.setVisible(true);
        voltageUnit.setVisible(true);
    }

    public List<CircuitComponent> getComponents() {
        return components;
    }

    public List<ACSource> getAcSources() {
        return acSources;
    }

    public List<DCSource> getDcSources() {
        return dcSources;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public HBox getHboxParallel() {
        return hboxParallel;
    }

    public HBox getHboxSerial() {
        return hboxSerial;
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
        addElement("Resistor", "R" + resistorCount, "Ω");
    }

    @FXML
    private void addCapacitor() {
        capacitorCount++;
        addElement("Capacitor", "C" + capacitorCount, "µF");
    }

    @FXML
    private void addInductor() {
        inductorCount++;
        addElement("Inductor", "L" + inductorCount, "µH");
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

        HBox newElement = new HBox(10);
        Label nameLabel = new Label(elementName);
        TextField parameterField = new TextField();
        Label itemUnit = new Label(elementUnit);
        CircuitComponent component = new CircuitComponent(elementType, elementName, elementUnit, ""); // Tạo đối tượng
                                                                                                      // mới
        components.add(component); // Thêm vào danh sách

        parameterField.textProperty().addListener((observable, oldValue, newValue) -> {
            component.setValue(newValue); // Cập nhật giá trị của thành phần khi giá trị thay đổi
        });

        newElement.getChildren().addAll(nameLabel, parameterField, itemUnit);
        elementContainer.getChildren().add(newElement);
    }

    @FXML
    public void handleSubmit() throws IOException {
        if ("AC".equals(sourceType.getValue())) {
            String acVoltageValue = acVoltage.getText();
            String acFrequencyValue = acFrequency.getText();
            CircuitComponent component = new CircuitComponent("acSource", "U", "V", acVoltageValue, "Hz",
                    acFrequencyValue);
            components.add(component);
        }
        if ("DC".equals(sourceType.getValue())) {
            String dcVoltageValue = dcVoltage.getText();
            CircuitComponent component = new CircuitComponent("dcSource", "U", "V", dcVoltageValue);
            components.add(component);
        }
    }
}
