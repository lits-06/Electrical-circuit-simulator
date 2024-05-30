package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.CircuitComponent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CircuitResultController {
    @FXML
    private HBox resistorControl;

    @FXML
    private HBox capacitorControl;

    @FXML
    private HBox inductorControl;

    @FXML
    private Button btnBack;

    @FXML
    private VBox componentPowerSource;

    @FXML
    private VBox component1;

    @FXML
    private VBox component2;

    @FXML
    private VBox component3;

    @FXML
    private VBox component4;

    @FXML
    private VBox component5;

    @FXML
    private HBox lineEndControl;

    @FXML
    private TableView<CircuitComponent> componentTable;

    @FXML
    private TableColumn<CircuitComponent, String> componentNameColumn;

    @FXML
    private TableColumn<CircuitComponent, String> voltageColumn;

    @FXML
    private TableColumn<CircuitComponent, String> currentColumn;

    @FXML
    private TableColumn<CircuitComponent, String> resistanceColumn;
    private int countPosition = 0;
    private int resistorCount;
    private int capacitorCount;
    private int inductorCount;
    private List<CircuitComponent> components;
    private CircuitComponent source;

    public TableView<CircuitComponent> getComponentTable() {
        return componentTable;
    }

    public List<CircuitComponent> getComponents() {
        return components;
    }

    public CircuitComponent getSource() {
        return source;
    }

    public HBox getLineEndControl() {
        return lineEndControl;
    }

    public int getCapacitorCount() {
        return capacitorCount;
    }

    public void setupComponentTable() {
        componentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
        resistanceColumn.setCellValueFactory(new PropertyValueFactory<>("resistance"));
    }

    public void setComponents(List<CircuitComponent> components) {
        this.components = components;
        updateComponentVisibility();
    }

    public void setSource(CircuitComponent source) {
        this.source = source;
        updateSourceVisibility();
    }

    public void setComponentCounts(int[] counts) {
        this.resistorCount = counts[0];
        this.capacitorCount = counts[1];
        this.inductorCount = counts[2];
    }

    private void displayComponentValues() {
        ObservableList<CircuitComponent> componentList = FXCollections.observableArrayList(components);
        componentTable.setItems(componentList);
    }

    @FXML
    private void handleBackClick() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent newRoot = loader.load();

        Scene currentScene = btnBack.getScene();
        currentScene.setRoot(newRoot);
    }

    public void updateComponentVisibility() {
        for (CircuitComponent component : components) {
            HBox componentBox = new HBox(1);
            Label nameLabel = new Label(component.getName());
            Label valueLabel = new Label(component.getValue());
            Label unitLabel = new Label(component.getUnit());
            nameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
            valueLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
            unitLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
            componentBox.getChildren().addAll(valueLabel, unitLabel);

            switch (component.getType()) {
                case "Resistor" -> {
                    resistorControl.getChildren().get(countPosition).setVisible(true);
                }
                case "Capacitor" -> {
                    capacitorControl.getChildren().get(countPosition).setVisible(true);
                }
                case "Inductor" -> {
                    inductorControl.getChildren().get(countPosition).setVisible(true);
                }
            }

            switch (countPosition) {
                case 0 -> {
                    component1.getChildren().addAll(nameLabel, componentBox);
                }
                case 1 -> {
                    component2.getChildren().addAll(nameLabel, componentBox);
                }
                case 2 -> {
                    component3.getChildren().addAll(nameLabel, componentBox);
                }
                case 3 -> {
                    component4.getChildren().addAll(nameLabel, componentBox);
                }
                case 4 -> {
                    component5.getChildren().addAll(nameLabel, componentBox);
                }
            }

            countPosition++;
        }
    }

    public void updateSourceVisibility() {
        HBox sourceBox = new HBox(1);
        Label nameLabel = new Label(source.getName());
        Label valueLabel = new Label(source.getValue());
        Label unitLabel = new Label(source.getUnit());
        nameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
        valueLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
        unitLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
        sourceBox.getChildren().addAll(valueLabel, unitLabel);

        switch (source.getType()) {
            case "dcSource" -> {
                Label voltageLabel = new Label(source.getValue() + " " + source.getUnit());
                voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                componentPowerSource.getChildren().addAll(voltageLabel);
            }
            case "acSource" -> {
                Label voltageLabel = new Label(source.getValue() + " " + source.getUnit());
                Label frequencyLabel = new Label(source.getValue2() + " " + source.getUnit2());
                voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                frequencyLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                componentPowerSource.getChildren().addAll(voltageLabel, frequencyLabel);
            }
        }
    }
}
