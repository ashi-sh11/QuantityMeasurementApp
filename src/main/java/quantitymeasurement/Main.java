package quantitymeasurement;

public class Main {
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println(l1.equals(l2));
        Length l3 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l4 = new Length(1.0, Length.LengthUnit.INCHES);

        System.out.println(l3.equals(l4));
    }
}