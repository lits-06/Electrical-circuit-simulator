package controller.serial;

import controller.CircuitResultController;

public class SerialResultController extends CircuitResultController {
    public void updateVisibility() {
        super.updateVisibility();
        getLineEndControl().getChildren().get(getComponents().size() - 2).setVisible(true);
    }
}
