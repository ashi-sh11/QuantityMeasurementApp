package UseCase10;

import java.util.Objects;
public class Quantity<T extends IMeasurable> {
    private double value;
    private T unit;

    public Quantity(double value , T unit){
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value=value;
        this.unit=unit;
    }

    public T getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    public Quantity<T> convertTo(T targetUnit){
       double base = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round( convertedValue),targetUnit);
    }

    public Quantity<T> add(Quantity<T> other){
        validateSameCategory(other);
        double first = unit.convertToBaseUnit(value);
        double second = other.unit.convertToBaseUnit(other.value);
        double res = first + second;
        double ans = unit.convertFromBaseUnit(res);
        return new Quantity<>(round(ans),unit);
    }

    public Quantity<T> add(Quantity<T> other, T targetUnit) {
        validateSameCategory(other);
        double first = unit.convertToBaseUnit(value);
        double second = other.unit.convertToBaseUnit(other.value);
        double sumBase = first + second;
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), targetUnit);
    }

    private void validateSameCategory(Quantity<T> other) {
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Categories are Different ");
        }
    }
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (this.unit.getClass() != other.unit.getClass()) return false;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(baseValue, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}

