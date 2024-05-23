package controller.parallel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.ACSource;
import models.CircuitComponent;
import models.DCSource;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ParallelResultController implements Initializable {

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

//    @FXML
//    private VBox circuitContainer;

    @FXML
    private Group circuit;

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

    private int count;
    private int countPosition = 1;
    private boolean isCountSet = false;
    private List<CircuitComponent> components;
    private List<ACSource> acSources;
    private List<DCSource> dcSources;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (isCountSet) {
            updateVisibility();
        }
        if (components != null) {
            displayComponents();
        }

        if (components != null) {
            displayComponentValues();
        }
    }


    public void setupComponentTable() {
        componentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
        resistanceColumn.setCellValueFactory(new PropertyValueFactory<>("resistance"));
    }


    private void displayComponentValues() {
        ObservableList<CircuitComponent> componentList = FXCollections.observableArrayList(components);
        componentTable.setItems(componentList);
    }

    @FXML
    private void handleBackClick(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent newRoot = loader.load();

        Scene currentScene = btnBack.getScene();
        currentScene.setRoot(newRoot);
    }

    public void setCount(int count) {
        this.count = count;
        isCountSet = true;
        if (resistorControl != null && capacitorControl != null && inductorControl != null) {
            updateVisibility();
        }
    }

    public void setComponents(List<CircuitComponent> components) {
        this.components = components;
        if (circuit != null) {
            displayComponents();
        }
    }

    public void setAcSources(List<ACSource> acSources) {
        this.acSources = acSources;
        if (circuit != null)
            displayAcSources();
    }

    public void setDcSources(List<DCSource> dcSources) {
        this.dcSources = dcSources;
        if (circuit != null)
            displayDcSources();
    }

    private void updateVisibility() {
        for (CircuitComponent component : components) {

            if (component.getType().equals("Resistor")) {

                switch (countPosition) {
                    case 1:
                        for (int j = 0; j < resistorControl.getChildren().size(); j++) {
                            if (resistorControl.getChildren().get(j).getId().equals("resister1")) {
                                resistorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int j = 0; j < resistorControl.getChildren().size(); j++) {
                            if (resistorControl.getChildren().get(j).getId().equals("resister2")) {
                                resistorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int j = 0; j < resistorControl.getChildren().size(); j++) {
                            if (resistorControl.getChildren().get(j).getId().equals("resister3")) {
                                resistorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 4:
                        for (int j = 0; j < resistorControl.getChildren().size(); j++) {
                            if (resistorControl.getChildren().get(j).getId().equals("resister4")) {
                                resistorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int j = 0; j < resistorControl.getChildren().size(); j++) {
                            if (resistorControl.getChildren().get(j).getId().equals("resister5")) {
                                resistorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                }
            } else if (component.getType().equals("Capacitor")) {

                switch (countPosition) {
                    case 1:
                        for (int j = 0; j < capacitorControl.getChildren().size(); j++) {
                            if (capacitorControl.getChildren().get(j).getId().equals("capacitor1")) {
                                capacitorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int j = 0; j < capacitorControl.getChildren().size(); j++) {
                            if (capacitorControl.getChildren().get(j).getId().equals("capacitor2")) {
                                capacitorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int j = 0; j < capacitorControl.getChildren().size(); j++) {
                            if (capacitorControl.getChildren().get(j).getId().equals("capacitor3")) {
                                capacitorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 4:
                        for (int j = 0; j < capacitorControl.getChildren().size(); j++) {
                            if (capacitorControl.getChildren().get(j).getId().equals("capacitor4")) {
                                capacitorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int j = 0; j < capacitorControl.getChildren().size(); j++) {
                            if (capacitorControl.getChildren().get(j).getId().equals("capacitor5")) {
                                capacitorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                }
            } else if (component.getType().equals("Inductor")) {
                switch (countPosition) {
                    case 1:
                        for (int j = 0; j < inductorControl.getChildren().size(); j++) {
                            if (inductorControl.getChildren().get(j).getId().equals("inductor1")) {
                                inductorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int j = 0; j < inductorControl.getChildren().size(); j++) {
                            if (inductorControl.getChildren().get(j).getId().equals("inductor2")) {
                                inductorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int j = 0; j < inductorControl.getChildren().size(); j++) {
                            if (inductorControl.getChildren().get(j).getId().equals("inductor3")) {
                                inductorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 4:
                        for (int j = 0; j < inductorControl.getChildren().size(); j++) {
                            if (inductorControl.getChildren().get(j).getId().equals("inductor4")) {
                                inductorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (int j = 0; j < inductorControl.getChildren().size(); j++) {
                            if (inductorControl.getChildren().get(j).getId().equals("inductor5")) {
                                inductorControl.getChildren().get(j).setVisible(true);
                                countPosition++;
                                break;
                            }
                        }
                        break;
                }
            }
        }
    }

    private void displayComponents() {
        int temp = 1;

        for (CircuitComponent component : components) {
            switch (temp) {
                case 1:
                    HBox componentBox1 = new HBox(1);
                    Label nameLabel1 = new Label(component.getName());
                    Label valueLabel1 = new Label(component.getValue());
                    Label unitLabel1 = new Label(component.getUnit());

                    nameLabel1.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    valueLabel1.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    unitLabel1.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

                    componentBox1.getChildren().addAll(valueLabel1, unitLabel1);

                    component1.getChildren().addAll(nameLabel1, componentBox1);
                    temp++;
                    break;
                case 2:
                    HBox componentBox2 = new HBox(1);
                    Label nameLabel2 = new Label(component.getName());
                    Label valueLabel2 = new Label(component.getValue());
                    Label unitLabel2 = new Label(component.getUnit());

                    nameLabel2.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    valueLabel2.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    unitLabel2.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

                    componentBox2.getChildren().addAll(valueLabel2, unitLabel2);

                    component2.getChildren().addAll(nameLabel2, componentBox2);
                    temp++;
                    break;
                case 3:
                    HBox componentBox3 = new HBox(1);
                    Label nameLabel3 = new Label(component.getName());
                    Label valueLabel3 = new Label(component.getValue());
                    Label unitLabel3 = new Label(component.getUnit());

                    nameLabel3.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    valueLabel3.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    unitLabel3.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

                    componentBox3.getChildren().addAll(valueLabel3, unitLabel3);

                    component3.getChildren().addAll(nameLabel3, componentBox3);
                    temp++;
                    break;
                case 4:
                    HBox componentBox4 = new HBox(1);
                    Label nameLabel4 = new Label(component.getName());
                    Label valueLabel4 = new Label(component.getValue());
                    Label unitLabel4 = new Label(component.getUnit());

                    nameLabel4.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    valueLabel4.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    unitLabel4.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

                    componentBox4.getChildren().addAll(valueLabel4, unitLabel4);

                    component4.getChildren().addAll(nameLabel4, componentBox4);
                    temp++;
                    break;
                case 5:
                    HBox componentBox5 = new HBox(1);
                    Label nameLabel5 = new Label(component.getName());
                    Label valueLabel5 = new Label(component.getValue());
                    Label unitLabel5 = new Label(component.getUnit());

                    nameLabel5.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    valueLabel5.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
                    unitLabel5.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

                    componentBox5.getChildren().addAll(valueLabel5, unitLabel5);

                    component5.getChildren().addAll(nameLabel5, componentBox5);
                    temp++;
                    break;
            }
        }
    }

    private void displayAcSources() {
        for (ACSource acSource : acSources) {
            Label voltageLabel = new Label(acSource.getAcVoltage() + " " + acSource.getAcVoltageUnit());
            Label frequencyLabel = new Label(acSource.getAcFrequency() + " " + acSource.getAcFrequencyUnit());
            voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
            frequencyLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

            componentPowerSource.getChildren().addAll(voltageLabel, frequencyLabel);
        }
    }

    private void displayDcSources() {
        for (DCSource dcSource : dcSources) {
            Label voltageLabel = new Label(dcSource.getAcVoltage() + " " + dcSource.getAcVoltageUnit());
            voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");

            componentPowerSource.getChildren().addAll(voltageLabel);
        }
    }
}
