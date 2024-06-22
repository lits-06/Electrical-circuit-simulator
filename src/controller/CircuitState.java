package controller;

import java.util.List;

import models.CircuitComponent;

public class CircuitState {
    private String sourceType;
    private CircuitComponent source;
    private List<CircuitComponent> components;

    public CircuitState(String sourceType, CircuitComponent source, List<CircuitComponent> components) {
        this.sourceType = sourceType;
        this.components = components;
        this.source = source;
    }

    public String getSourceType() {
        return sourceType;
    }

    public CircuitComponent getSource() {
        return source;
    }

    public List<CircuitComponent> getComponents() {
        return components;
    }
}
