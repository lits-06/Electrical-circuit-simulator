package controller.serial;

import controller.CircuitResultController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.CircuitComponent;

public class SerialResultController extends CircuitResultController {

    @FXML
    private void handleBackClick() throws Exception {
        super.handleBackClick("/fxml/serial/Serial.fxml", this);
    }

    public void updateComponentVisibility() {
        super.updateComponentVisibility();
        getLineEndControl().getChildren().get(getComponents().size() - 1).setVisible(true);
    }

    public void calculateComponentValues() {
        double voltageSource = Double.parseDouble(super.getSource().getValue());

        if ("dcSource".equals(super.getSource().getType())) {
            if (super.getCapacitorCount() > 0) {
                for (CircuitComponent component : super.getComponents()) {
                    switch (component.getType()) {
                        case "Resistor" -> {
                            double resistance = Double.parseDouble(component.getValue());
                            double voltage = 0;
                            double current = 0;

                            component.setResistance(String.valueOf(resistance));
                            component.setVoltage(String.valueOf(voltage));
                            component.setCurrent(String.valueOf(current));
                        }
                        case "Capacitor" -> {
                            double Ceq = calculateEquivalentCapacitor();
                            double C = Double.parseDouble(component.getValue()) / Math.pow(10, 6);

                            String resistance = "∞";
                            double voltage = voltageSource / Ceq / C;
                            double current = 0;

                            component.setResistance(resistance);
                            component.setVoltage(String.valueOf(voltage));
                            component.setCurrent(String.valueOf(current));
                        }
                        case "Inductor" -> {
                            double resistance = 0;
                            double voltage = 0;
                            double current = 0;

                            component.setResistance(String.valueOf(resistance));
                            component.setVoltage(String.valueOf(voltage));
                            component.setCurrent(String.valueOf(current));
                        }
                    }
                }
            } else {
                double Req = 0;
                for (CircuitComponent component : super.getComponents()) {
                    if ("Resistor".equals(component.getType())) {
                        Req += Double.parseDouble(component.getValue());
                    }
                }

                double I = voltageSource / Req;

                for (CircuitComponent component : super.getComponents()) {
                    double current;
                    switch (component.getType()) {
                        case "Resistor" -> {
                            double resistance = Double.parseDouble(component.getValue());
                            double voltage = voltageSource / Req * resistance;
                            current = I;

                            component.setResistance(String.valueOf(resistance));
                            component.setVoltage(String.valueOf(voltage));
                            component.setCurrent(String.valueOf(current));
                        }
                        case "Inductor" -> {
                            double resistance = 0;
                            double voltage = 0;
                            current = I;

                            component.setResistance(String.valueOf(resistance));
                            component.setVoltage(String.valueOf(voltage));
                            component.setCurrent(String.valueOf(current));
                        }
                    }
                }
            }
        } else if ("acSource".equals(super.getSource().getType())) {
            double omega = 2 * 3.14 * Double.parseDouble(super.getSource().getValue2());
            double Req = calculateEquivalentResistance();
            double I = voltageSource / Req;

            for (CircuitComponent component : super.getComponents()) {
                double current;
                switch (component.getType()) {
                    case "Resistor" -> {
                        double resistance = Double.parseDouble(component.getValue());
                        double voltage = voltageSource / Req * resistance;
                        current = I;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                    case "Capacitor" -> {
                        double C = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                        double resistance = 1 / (omega * C);
                        double voltage = voltageSource / Req * resistance;
                        current = I;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                    case "Inductor" -> {
                        double L = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                        double resistance = omega * L;
                        double voltage = voltageSource / Req * resistance;
                        current = I;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                }
            }
        }
    }

    private double calculateEquivalentCapacitor() {
        double Ceq = 0;
        for (CircuitComponent component : super.getComponents()) {
            if ("Capacitor".equals(component.getType())) {
                double C = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                Ceq += 1 / C;
            }
        }

        return Ceq;
    }

    public double calculateEquivalentResistance() {
        double omega = 2 * 3.14 * Double.parseDouble(super.getSource().getValue2());
        double Req;
        double R = 0;
        double Zc = 0;
        double Zl = 0;

        for (CircuitComponent component : super.getComponents()) {
            switch (component.getType()) {
                case "Resistor" -> {
                    double Ri = Double.parseDouble(component.getValue());
                    R += Ri;
                }
                case "Capacitor" -> {
                    double C = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                    double Zci = 1 / (omega * C);
                    Zc += Zci;
                }
                case "Inductor" -> {
                    double L = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                    double Zli = omega * L;
                    Zl += Zli;
                }
            }
        }

        Req = Math.sqrt(Math.pow(R, 2) + Math.pow(Zl - Zc, 2));
        return Req;
    }

    public void displayComponentValues() {
        calculateComponentValues();

        ObservableList<CircuitComponent> componentList = FXCollections.observableArrayList(super.getComponents());
        super.getComponentTable().setItems(componentList);
    }
}