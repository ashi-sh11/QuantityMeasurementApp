package UseCase11;

public enum WeightUnit implements IMeasurable {
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592);
    private final double factor;
    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return this.name();
    }
}