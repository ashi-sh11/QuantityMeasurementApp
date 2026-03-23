package UseCase12;

public class QuantityMeasurementApp {

    public static <T extends IMeasurable> void demoAll(
            Quantity<T> q1, Quantity<T> q2, T targetUnit) {

        System.out.println("---- OPERATIONS ----");

        System.out.println("Equality: " + q1.equals(q2));

        System.out.println("Convert: " + q1.convertTo(targetUnit));

        System.out.println("Add: " + q1.add(q2));
        System.out.println("Add Target: " + q1.add(q2, targetUnit));

        System.out.println("Subtract: " + q1.subtract(q2));
        System.out.println("Subtract Target: " + q1.subtract(q2, targetUnit));
        System.out.println("Divide: " + q1.divide(q2));
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCHES);
        demoAll(l1, l2, LengthUnit.FEET);

        System.out.println();

        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GRAM);
        demoAll(w1, w2, WeightUnit.KILOGRAM);

        System.out.println();

        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);
        demoAll(v1, v2, VolumeUnit.LITRE);
    }
}