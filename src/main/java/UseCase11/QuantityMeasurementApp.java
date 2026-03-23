package UseCase11;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = QuantityFactory.length(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = QuantityFactory.length(12, LengthUnit.INCHES);

        System.out.println(l1.equals(l2));
        System.out.println(l1.convertTo(LengthUnit.INCHES));
        System.out.println(l1.add(l2, LengthUnit.FEET));

        Quantity<WeightUnit> w1 = QuantityFactory.weight(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = QuantityFactory.weight(1000, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));
    }
}