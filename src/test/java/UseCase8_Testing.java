
import UseCase8.Length;
import UseCase8.LengthUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase8_Testing {
//
    @Test
    void testLengthUnit_Feet() {
        assertEquals(12.0, LengthUnit.FEET.getFactor());
    }

    @Test
    void testLengthUnit_Inches() {
        assertEquals(1.0, LengthUnit.INCHES.getFactor());
    }

    @Test
    void testLengthUnit_Yards() {
        assertEquals(36.0, LengthUnit.YARDS.getFactor());
    }

    @Test
    void testLengthUnit_Centimeters() {
        assertEquals(0.393701, LengthUnit.CENTIMETERS.getFactor(), 0.0001);
    }


    @Test
    void testConvertToBaseUnit_Feet() {
        assertEquals(12.0, LengthUnit.FEET.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_Inches() {
        assertEquals(12.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_Yards() {
        assertEquals(36.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    void testConvertToBaseUnit_Centimeters() {
        assertEquals(30.48 * 0.393701, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.01);
    }


    @Test
    void testConvertFromBaseUnit_ToFeet() {
        assertEquals(1.0, LengthUnit.FEET.convertFromBaseUnit(12.0), 0.0001);
    }

    @Test
    void testConvertFromBaseUnit_ToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(12.0), 0.0001);
    }

    @Test
    void testConvertFromBaseUnit_ToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(36.0), 0.0001);
    }

    @Test
    void testConvertFromBaseUnit_ToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(30.48 * 0.393701), 0.1);
    }


    @Test
    void testEquality_FeetToInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToInches() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_DifferentValues() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }


    @Test
    void testConvertTo() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length result = l1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.0001);
    }


    @Test
    void testAddition_SameUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_WithTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.addAndConvert(l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.0001);
    }


    @Test
    void testAddition_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }


    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }
}