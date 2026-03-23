package UseCase11;


public class QuantityFactory {

    public static Quantity<LengthUnit> length(double value, LengthUnit unit) {
        return new Quantity<>(value, unit);
    }

    public static Quantity<WeightUnit> weight(double value, WeightUnit unit) {
        return new Quantity<>(value, unit);
    }
}