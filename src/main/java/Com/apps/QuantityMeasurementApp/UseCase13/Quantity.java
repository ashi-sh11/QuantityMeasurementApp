package Com.apps.QuantityMeasurementApp.UseCase13;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<T extends IMeasurable> {
    private final double value;
    private final T unit;

    public Quantity(double value, T unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }

    public enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {if (b == 0) throw new ArithmeticException("Divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }
        public double apply(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    private void validateArithmeticOperands(Quantity<T> other, T targetUnit, boolean requiredTarget) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different categories");
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid values");
        if (requiredTarget && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    private double performBaseArithmetic(Quantity<T> other, ArithmeticOperation op) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return op.apply(base1, base2);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    public Quantity<T> add(Quantity<T> other) {
        validateArithmeticOperands(other, null, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = unit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), unit);
    }

    public Quantity<T> add(Quantity<T> other, T targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<T> subtract(Quantity<T> other) {
        validateArithmeticOperands(other, null, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = unit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), unit);
    }

    public Quantity<T> subtract(Quantity<T> other, T targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<T> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public Quantity<T> convertTo(T targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (this.unit.getClass() != other.unit.getClass()) return false;
        double b1 = unit.convertToBaseUnit(value);
        double b2 = other.unit.convertToBaseUnit(other.value);
        return Double.compare(b1, b2) == 0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value), unit.getClass());
    }
}