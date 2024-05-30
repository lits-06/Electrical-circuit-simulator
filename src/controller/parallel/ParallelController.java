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
            checkShortCircuit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/parallel/ParallelResult.fxml"));
            Parent newRoot = loader.load();

            ParallelResultController controller = loader.getController();

            // Truyền danh sách các thành phần vào controller của ParallelResult
            controller.setupComponentTable();
            controller.setComponents(getComponents());
            controller.setSource(getSource());
            controller.setComponentCounts(getComponentCounts());

            controller.displayComponentValues();

            Scene currentScene = getBtnSubmit().getScene();
            currentScene.setRoot(newRoot);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void checkShortCircuit() throws Exception {
        if ("dcSource".equals(super.getSource().getType()) && super.getInductorCount() > 0) {
            super.showError("Short circuit");
            throw new Exception("short circuit");
        }
    }
}
