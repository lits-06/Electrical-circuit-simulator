package models;

public class CircuitComponent {
    private String type;
    private String name;
    private String unit;
    private String value;

    public CircuitComponent(String type, String name, String unit, String value) {
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
