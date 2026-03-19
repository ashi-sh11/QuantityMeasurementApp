package UseCase9;

import UseCase9.Length;
import UseCase9.Length;


public class QuantityMeasurementapp {
     static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length converted = l1.convertTo(LengthUnit.INCHES);

        System.out.println("Convert 1 FEET to INCHES → " + converted);


        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("1 FEET == 12 INCHES → " + l1.equals(l2));


        Length l3 = new Length(1.0, LengthUnit.YARDS);
        Length l4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("1 YARD == 36 INCHES → " + l3.equals(l4));


        Length l5 = new Length(1.0, LengthUnit.FEET);
        Length l6 = new Length(12.0, LengthUnit.INCHES);
        Length result1 = l5.add(l6);

        System.out.println("1 FEET + 12 INCHES → " + result1);


        Length result2 = l5.addAndConvert(l6, LengthUnit.INCHES);

        System.out.println("1 FEET + 12 INCHES (IN INCHES) → " + result2);


        Length l7 = new Length(1.0, LengthUnit.YARDS);
        Length l8 = new Length(3.0, LengthUnit.FEET);

        System.out.println("1 YARD + 3 FEET → " + l7.add(l8));


        Length l9 = new Length(2.54, LengthUnit.CENTIMETERS);
        System.out.println("2.54 CM to INCHES → " + l9.convertTo(LengthUnit.INCHES));


        Length l10 = new Length(5.0, LengthUnit.FEET);
        Length l11 = new Length(0.0, LengthUnit.INCHES);

        System.out.println("5 FEET + 0 INCHES → " + l10.add(l11));


        Length l12 = new Length(5.0, LengthUnit.FEET);
        Length l13 = new Length(-2.0, LengthUnit.FEET);

        System.out.println("5 FEET + (-2 FEET) → " + l12.add(l13));


        double base = LengthUnit.INCHES.convertToBaseUnit(12.0);
        System.out.println("12 INCHES to FEET (base) → " + base);

        double back = LengthUnit.INCHES.convertFromBaseUnit(1.0);
        System.out.println("1 FEET to INCHES → " + back);
    }
}