package models;

public class DCSource {
    private String dcVoltageUnit;
    private String dcVoltage;

    public DCSource(String dcVoltageUnit, String acVoltage) {
        this.dcVoltageUnit = dcVoltageUnit;
        this.dcVoltage = acVoltage;
    }

    public String getAcVoltageUnit() {
        return dcVoltageUnit;
    }

    public String getAcVoltage() {
        return dcVoltage;
    }
}
