package Com.apps.QuantityMeasurementApp.UseCase15.dto;

public class QuantityDTOUC15 {

    private double value;
    private String unitName;
    private String measurementType;

    public QuantityDTOUC15() {}

    public QuantityDTOUC15(double value, String unitName, String measurementType) {
        this.value = value;
        this.unitName = unitName;
        this.measurementType = measurementType;
    }

    public double getValue() { return value; }
    public String getUnitName() { return unitName; }
    public String getMeasurementType() { return measurementType; }

    public void setValue(double value) { this.value = value; }
    public void setUnitName(String unitName) { this.unitName = unitName; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
}