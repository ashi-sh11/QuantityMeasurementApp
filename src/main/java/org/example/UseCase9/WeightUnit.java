package org.example.UseCase9;

public enum WeightUnit {

    //base unit (kg)
    MILLIGRAM(0.000001),
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592),
    TONNE(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}