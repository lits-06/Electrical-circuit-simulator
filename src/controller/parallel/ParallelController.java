package controller.parallel;

import controller.CircuitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ParallelController extends CircuitController {

    public void changeCircuitScene(MouseEvent event) throws Exception {
        super.changeCircuitScene(event, "/fxml/serial/Serial.fxml");
    }

    public void handleSubmit() throws Exception {
        try {
            super.handleSubmit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/parallel/ParallelResult.fxml"));
            Parent newRoot = loader.load();

            ParallelResultController controller = loader.getController();

            // Truyền danh sách các thành phần vào controller của ParallelResult
            controller.setupComponentTable();
            controller.setComponents(getComponents());

            Scene currentScene = getBtnSubmit().getScene();
            currentScene.setRoot(newRoot);
        } catch (Exception e) {
            System.err.println("Error: invalid value" + e.getMessage());
        }
    }

}
