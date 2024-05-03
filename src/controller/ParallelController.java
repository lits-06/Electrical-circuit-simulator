package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ParallelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private final int MAX_ELEMENTS = 5;
    private boolean alertMaxElementsShown = false;

    @FXML
    void initialize() {
        sourceItems = FXCollections.observableArrayList("AC", "DC");

        sourceType.setItems(sourceItems);
    }

    @FXML
    private void changeSerialScene(MouseEvent event) throws Exception {
        FXMLLoader loader;
        if (event.getSource() == hboxSerial) {
            loader = new FXMLLoader(getClass().getResource("/fxml/Serial.fxml"));
        } else {
            return;
        }

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
        addElement("Capacitor", "C" + capacitorCount, "F");
    }

    @FXML
    private void addInductor() {
        inductorCount++;
        addElement("Inductor", "L" + inductorCount, "H");
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

        newElement.getChildren().addAll(nameLabel, parameterField, itemUnit);
        elementContainer.getChildren().add(newElement);
    }

    @FXML
    private void handleSubmit() {
        sourceInfo.getChildren().clear();
        elementInfo.getChildren().clear();
        btnSubmit.setVisible(false);
    }
}
