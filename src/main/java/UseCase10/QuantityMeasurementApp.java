package UseCase10;

public class QuantityMeasurementApp {

    public static <T extends IMeasurable> boolean demonstrateEquality(
            Quantity<T> q1, Quantity<T> q2) {
            boolean result = q1.equals(q2);
            System.out.println(q1 + " == " + q2 + " → " + result);
            return result;
    }

    public static <T extends IMeasurable> Quantity<T> demonstrateConversion(
            Quantity<T> q, T targetUnit) {
            Quantity<T> result = q.convertTo(targetUnit);
            System.out.println(q + " → " + result);
            return result;
    }

    public static <T extends IMeasurable> Quantity<T> demonstrateAddition(
            Quantity<T> q1, Quantity<T> q2) {
            Quantity<T> result = q1.add(q2);
            System.out.println(q1 + " + " + q2 + " → " + result);
            return result;
    }
    public static <T extends IMeasurable> Quantity<T> demonstrateAddition(
            Quantity<T> q1, Quantity<T> q2, T targetUnit) {
            Quantity<T> result = q1.add(q2, targetUnit);
            System.out.println(q1 + " + " + q2 + " → " + result);
            return result;
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);
        demonstrateEquality(l1, l2);
        demonstrateConversion(l1, LengthUnit.INCHES);
        demonstrateAddition(l1, l2, LengthUnit.FEET);
        System.out.println();

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);
        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit.GRAM);
        demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);
    }
}
