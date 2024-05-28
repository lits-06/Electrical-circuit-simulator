package controller.serial;

import controller.CircuitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SerialController extends CircuitController {

    public void changeCircuitScene(MouseEvent event) throws Exception {
        super.changeCircuitScene(event, "/fxml/parallel/Parallel.fxml");
    }

    public void handleSubmit() throws IOException {

        super.handleSubmit();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serial/SerialResult.fxml"));
        Parent newRoot = loader.load();

        SerialResultController controller = loader.getController();

        // Truyền danh sách các thành phần vào controller của ParallelResult
        controller.setupComponentTable();
        controller.setComponents(getComponents());

        Scene currentScene = getBtnSubmit().getScene();
        currentScene.setRoot(newRoot);
    }
}