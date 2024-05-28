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
        super.changeCircuitScene(event);
        FXMLLoader loader;
        if (event.getSource() == getHboxParallel()) {
            loader = new FXMLLoader(getClass().getResource("/fxml/parallel/Parallel.fxml"));
        } else {
            return;
        }

        Parent newRoot = loader.load();

        Stage stage = (Stage) ((HBox) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newRoot);
        stage.setScene(newScene);
        stage.show();
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