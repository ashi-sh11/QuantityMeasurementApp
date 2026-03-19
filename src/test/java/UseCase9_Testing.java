
import UseCase9.Length;
import UseCase9.LengthUnit;
import UseCase9.Weight;
import UseCase9.WeightUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase9_Testing {
    private static final double EPSILON = 1e-4;
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



    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(w2));
    }


    @Test
    void testEquality_KilogramToGram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_GramToKilogram() {
        Weight w1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToPound() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.20462, WeightUnit.POUND);

        assertTrue(Math.abs(
                w1.getUnit().convertToBaseUnit(w1.getValue()) -
                        w2.getUnit().convertToBaseUnit(w2.getValue())
        ) < EPSILON);
    }

    @Test
    void testEquality_GramToPound() {
        Weight w1 = new Weight(453.592, WeightUnit.GRAM);
        Weight w2 = new Weight(1.0, WeightUnit.POUND);

        assertTrue(Math.abs(
                w1.getUnit().convertToBaseUnit(w1.getValue()) -
                        w2.getUnit().convertToBaseUnit(w2.getValue())
        ) < EPSILON);
    }


    @Test
    void testConversion_KgToGram() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKg() {
        Weight w = new Weight(2.20462, WeightUnit.POUND);
        Weight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        Weight w = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_Zero() {
        Weight w = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_Negative() {
        Weight w = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit2() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_KgPlusGram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKg() {
        Weight w1 = new Weight(2.20462, WeightUnit.POUND);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(4.40924, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithTargetUnit2() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutative() {

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result1 = w1.add(w2);
        Weight result2 = w2.add(w1);

        Weight r1 = result1.convertTo(WeightUnit.KILOGRAM);
        Weight r2 = result2.convertTo(WeightUnit.KILOGRAM);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithZero2() {
        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(0.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues2() {
        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(-2000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_LargeValues() {
        Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testNullUnit2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1.0, null);
        });
    }

    @Test
    void testInvalidValue2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(Double.NaN, WeightUnit.KILOGRAM);
        });
    }

    @Test
    void testNullComparison() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);

        assertFalse(w.equals(null));
    }

    @Test
    void testSameReference() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w.equals(w));
    }
}