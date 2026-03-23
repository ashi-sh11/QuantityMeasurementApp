package UseCase11;

import java.util.Objects;

public class Quantity<T extends IMeasurable> {

    private final double value;
    private final T unit;

    public Quantity(double value, T unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }

    // 🔹 CONVERT
    public Quantity<T> convertTo(T targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    // 🔹 ADD (same unit result)
    public Quantity<T> add(Quantity<T> other) {
        validate(other);

        double sum = unit.convertToBaseUnit(value)
                + other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), unit);
    }

    // 🔹 ADD (target unit)
    public Quantity<T> add(Quantity<T> other, T targetUnit) {
        validate(other);

        double sum = unit.convertToBaseUnit(value)
                + other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), targetUnit);
    }

    private void validate(Quantity<T> other) {
        if (other == null)
            throw new IllegalArgumentException("Null quantity");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different categories");
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}