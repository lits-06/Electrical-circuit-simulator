package controller.serial;

import controller.CircuitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class SerialController extends CircuitController {

    public void changeCircuitScene(MouseEvent event) throws Exception {
        super.changeCircuitScene(event, "/fxml/parallel/Parallel.fxml");
    }

    public void handleSubmit() {
        try {
            super.handleSubmit();
            checkShortCircuit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serial/SerialResult.fxml"));
            Parent newRoot = loader.load();

            SerialResultController controller = loader.getController();

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
        if ("dcSource".equals(super.getSource().getType())
                && super.getInductorCount() == super.getComponents().size()) {
            super.showError("Short circuit");
            throw new Exception("short circuit");
        }
    }
}