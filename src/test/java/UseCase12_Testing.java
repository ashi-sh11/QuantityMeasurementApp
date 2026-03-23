import UseCase12.LengthUnit;
import UseCase12.Quantity;
import UseCase12.WeightUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase12_Testing {

    private static final double EPS = 0.01;

    @Test
    void testSubtraction_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        assertEquals(5.0, q1.subtract(q2).getValue(), EPS);
    }

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(9.5, q1.subtract(q2).getValue(), EPS);
    }

    @Test
    void testSubtraction_TargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(114.0,
                q1.subtract(q2, LengthUnit.INCHES).getValue(), EPS);
    }

    @Test
    void testSubtraction_Negative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10, LengthUnit.FEET);

        assertEquals(-5.0, q1.subtract(q2).getValue(), EPS);
    }

    @Test
    void testSubtraction_Zero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120, LengthUnit.INCHES);

        assertEquals(0.0, q1.subtract(q2).getValue(), EPS);
    }

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2), EPS);
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2), EPS);
    }

    @Test
    void testDivision_LessThanOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10, LengthUnit.FEET);

        assertEquals(0.5, q1.divide(q2), EPS);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testCrossCategory_Subtract() {
        Quantity<LengthUnit> l = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            l.subtract((Quantity) w);
        });
    }

    @Test
    void testCrossCategory_Divide() {
        Quantity<LengthUnit> l = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            l.divide((Quantity) w);
        });
    }

    @Test
    void testImmutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10, q1.getValue()); // unchanged
    }
}