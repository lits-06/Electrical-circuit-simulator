package controller.components;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class PowerSourceController {

    @FXML
    private HBox count3;

    @FXML
    private HBox count4;

    private int count;

    @FXML
    public void initialize(int count, HBox a, HBox b) {
        this.count = count;
        this.count3 = a;
        this.count4 = b;
        updateVisibility();
    }

    public void setCount3(HBox count3) {
        this.count3 = count3;
    }

    public void setCount4(HBox count4) {
        this.count4 = count4;
    }

    public HBox getCount3() {
        return count3;
    }

    public HBox getCount4() {
        return count4;
    }

    public void setCount(int count) {
        this.count = count;
        updateVisibility();
    }

    public void updateVisibility() {
        if (count3 == null || count4 == null) {
            return;
        }

        if (count == 3) {
            count3.setVisible(true);
            count4.setVisible(false);
        } else if (count == 4) {
            count3.setVisible(false);
            count4.setVisible(true);
        } else {
            count3.setVisible(false);
            count4.setVisible(false);
        }
    }
}
