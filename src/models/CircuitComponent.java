package models;

public class CircuitComponent {
    private String type;
    private String name;
    private String unit;
    private String value;
    private String value2 = null;
    private String unit2 = null;

    private String resistance;
    private String voltage;
    private String current;

    public CircuitComponent(String type, String name, String unit, String value) {
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public CircuitComponent(String type, String name, String unit, String value, String unit2, String value2) {
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.value = value;
        this.value2 = value2;
        this.unit2 = unit2;
    }

    public String getValue2() {
        return value2;
    }

    public String getUnit2() {
        return unit2;
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
