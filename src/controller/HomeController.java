package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private HBox hboxParallel;

    @FXML
    private HBox hboxSerial;

    @FXML
    private Label parallelLabel;

    @FXML
    private Label serialLabel;

    @FXML
    private void handleHBoxClick(MouseEvent event) throws Exception {
        FXMLLoader loader;
        if (event.getSource() == hboxParallel) {
            loader = new FXMLLoader(getClass().getResource("/fxml/Parallel.fxml"));
        } else if (event.getSource() == hboxSerial) {
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
}
