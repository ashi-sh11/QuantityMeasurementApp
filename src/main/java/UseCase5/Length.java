package UseCase5;

public class Length {
    private double value;
    private LengthUnit unit;
    public Length(double value, LengthUnit unit) {
        this.value = value;
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
    }
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private double value;
        LengthUnit(double value) {
            this.value = value;
        }
        public double getFactor() {
            return value;
        }
    }
    public double toFactor() {
        return value * unit.getFactor();
    }
    public boolean compare(Length thatLength) {
        return Double.compare(this.toFactor(), thatLength.toFactor()) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LengthUnit)) return false;
        Length other = (Length) obj;
        return Double.compare(this.toFactor(), other.toFactor()) == 0;
    }
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        double baseValue = value * source.getFactor();
        return baseValue / target.getFactor();
    }

    public Length convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Length(convertedValue, targetUnit);
    }
}
