package Com.apps.QuantityMeasurementApp.UseCase15.entity;

import java.io.Serializable;
public class QuantityMeasurementEntity implements Serializable {

    private double value1;
    private double value2;
    private String operation;
    private double result;

    public QuantityMeasurementEntity(double value1, double value2, String operation, double result) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}