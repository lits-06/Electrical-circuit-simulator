package controller.parallel;

import controller.CircuitResultController;

public class ParallelResultController extends CircuitResultController {

//    @FXML
//    private HBox resistorControl;
//
//    @FXML
//    private HBox capacitorControl;
//
//    @FXML
//    private HBox inductorControl;
//
//    @FXML
//    private Button btnBack;
//
//    @FXML
//    private VBox componentPowerSource;
//
//    @FXML
//    private VBox component1;
//
//    @FXML
//    private VBox component2;
//
//    @FXML
//    private VBox component3;
//
//    @FXML
//    private VBox component4;
//
//    @FXML
//    private VBox component5;
//
//    @FXML
//    private Group circuit;
//
//    @FXML
//    private TableView<CircuitComponent> componentTable;
//
//    @FXML
//    private TableColumn<CircuitComponent, String> componentNameColumn;
//
//    @FXML
//    private TableColumn<CircuitComponent, String> voltageColumn;
//
//    @FXML
//    private TableColumn<CircuitComponent, String> currentColumn;
//
//    @FXML
//    private TableColumn<CircuitComponent, String> resistanceColumn;
//    private int countPosition = 0;
//    private boolean isCountSet = false;
//    private List<CircuitComponent> components;
//    private List<ACSource> acSources;
//    private List<DCSource> dcSources;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        if (isCountSet) {
//            updateVisibility();
//        }
//        if (components != null) {
//            displayComponentValues();
//        }
//    }
//
//
//    public void setupComponentTable() {
//        componentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
//        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
//        resistanceColumn.setCellValueFactory(new PropertyValueFactory<>("resistance"));
//    }
//
//
//    private void displayComponentValues() {
//        ObservableList<CircuitComponent> componentList = FXCollections.observableArrayList(components);
//        componentTable.setItems(componentList);
//    }
//
//    @FXML
//    private void handleBackClick() throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
//        Parent newRoot = loader.load();
//
//        Scene currentScene = btnBack.getScene();
//        currentScene.setRoot(newRoot);
//    }
//
//    public void setComponents(List<CircuitComponent> components) {
//        this.components = components;
//        isCountSet = true;
//        updateVisibility();
//    }
//
//    public void setAcSources(List<ACSource> acSources) {
//        this.acSources = acSources;
//        if (circuit != null)
//            displayAcSources();
//    }
//
//    public void setDcSources(List<DCSource> dcSources) {
//        this.dcSources = dcSources;
//        if (circuit != null)
//            displayDcSources();
//    }
//
//    private void updateVisibility() {
//        for (CircuitComponent component : components) {
//            HBox componentBox = new HBox(1);
//            Label nameLabel = new Label(component.getName());
//            Label valueLabel = new Label(component.getValue());
//            Label unitLabel = new Label(component.getUnit());
//            nameLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//            valueLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//            unitLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//            componentBox.getChildren().addAll(valueLabel, unitLabel);
//            switch (countPosition) {
//                case 0 -> {
//                    component1.getChildren().addAll(nameLabel, componentBox);
//                }
//                case 1 -> {
//                    component2.getChildren().addAll(nameLabel, componentBox);
//                }
//                case 2 -> {
//                    component3.getChildren().addAll(nameLabel, componentBox);
//                }
//                case 3 -> {
//                    component4.getChildren().addAll(nameLabel, componentBox);
//                }
//                case 4 -> {
//                    component5.getChildren().addAll(nameLabel, componentBox);
//                }
//            }
//
//            switch (component.getType()) {
//                case "Resistor" -> {
//                    resistorControl.getChildren().get(countPosition).setVisible(true);
//                }
//                case "Capacitor" -> {
//                    capacitorControl.getChildren().get(countPosition).setVisible(true);
//                }
//                case "Inductor" -> {
//                    inductorControl.getChildren().get(countPosition).setVisible(true);
//                }
//            }
//            countPosition++;
//        }
//    }
//
//    private void displayAcSources() {
//        for (ACSource acSource : acSources) {
//            Label voltageLabel = new Label(acSource.getAcVoltage() + " " + acSource.getAcVoltageUnit());
//            Label frequencyLabel = new Label(acSource.getAcFrequency() + " " + acSource.getAcFrequencyUnit());
//            voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//            frequencyLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//
//            componentPowerSource.getChildren().addAll(voltageLabel, frequencyLabel);
//        }
//    }
//
//    private void displayDcSources() {
//        for (DCSource dcSource : dcSources) {
//            Label voltageLabel = new Label(dcSource.getAcVoltage() + " " + dcSource.getAcVoltageUnit());
//            voltageLabel.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 20px;");
//
//            componentPowerSource.getChildren().addAll(voltageLabel);
//        }
//    }
}
