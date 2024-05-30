package controller.parallel;

import controller.CircuitResultController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.CircuitComponent;

public class ParallelResultController extends CircuitResultController {
    public void calculateComponentValues() {
        double voltageSource = Double.parseDouble(super.getSource().getValue());

        if ("dcSource".equals(super.getSource().getType())) {
            for (CircuitComponent component : super.getComponents()) {
                switch (component.getType()) {
                    case "Resistor" -> {
                        double resistance = Double.parseDouble(component.getValue());
                        double voltage = voltageSource;
                        double current = voltage / resistance;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                    case "Capacitor" -> {
                        String resistance = "∞";
                        double voltage = voltageSource;
                        String current = "0";

                        component.setResistance(resistance);
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(current);
                    }
                }
            }
        } else if ("acSource".equals(super.getSource().getType())) {
            double omega = 2 * 3.14 * Double.parseDouble(super.getSource().getValue2());

            for (CircuitComponent component : super.getComponents()) {
                switch (component.getType()) {
                    case "Resistor" -> {
                        double resistance = Double.parseDouble(component.getValue());
                        double voltage = voltageSource;
                        double current = voltage / resistance;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                    case "Capacitor" -> {
                        double C = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                        double resistance = 1 / (omega * C);
                        double voltage = voltageSource;
                        double current = voltage / resistance;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                    case "Inductor" -> {
                        double L = Double.parseDouble(component.getValue()) / Math.pow(10, 6);
                        double resistance = omega * L;
                        double voltage = voltageSource;
                        double current = voltage / resistance;

                        component.setResistance(String.valueOf(resistance));
                        component.setVoltage(String.valueOf(voltage));
                        component.setCurrent(String.valueOf(current));
                    }
                }
            }
        }
    }

    public void displayComponentValues() {
        calculateComponentValues();

        ObservableList<CircuitComponent> componentList = FXCollections.observableArrayList(super.getComponents());
        super.getComponentTable().setItems(componentList);
    }
}
