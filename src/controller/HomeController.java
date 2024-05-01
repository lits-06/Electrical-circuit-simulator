package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HomeController {

    @FXML
    private HBox hboxParallel;

    @FXML
    private HBox hboxSerial;

    @FXML
    private Label parallelLabel;

    @FXML
    private Label serialLabel;

    private void resetDefaults() {
        parallelLabel.setTextFill(Color.BLACK);
        serialLabel.setTextFill(Color.BLACK);
        hboxParallel.setStyle("-fx-background-color: white;");
        hboxSerial.setStyle("-fx-background-color: white;");
    }

    @FXML
    private void handleParallelClick(MouseEvent event) {
        resetDefaults();

        parallelLabel.setTextFill(Color.RED);
        hboxParallel.setStyle("-fx-background-color: #fffafa;");
    }

    @FXML
    private void handleSerialClick(MouseEvent event) {
        resetDefaults();

        serialLabel.setTextFill(Color.RED);
        hboxSerial.setStyle("-fx-background-color: #fffafa;");
    }
}
