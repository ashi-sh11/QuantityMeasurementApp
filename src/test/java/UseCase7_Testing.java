import UseCase7.Length;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthTest {
    @Test
    void testAddition_TargetSameAsFirstOperand() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.0001);
    }
    @Test
    void testAddition_TargetSameAsSecondOperand() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.0001);
    }
    @Test
    void testAddition_TargetDifferentUnit() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_TargetUnitConsistency() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.YARDS);
        assertEquals(Length.LengthUnit.YARDS, result.convertTo(Length.LengthUnit.YARDS).unit);
    }


    @Test
    void testAddition_Commutative() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result1 = l1.add(l2, Length.LengthUnit.FEET);
        Length result2 = l2.add(l1, Length.LengthUnit.FEET);
        assertEquals(result1.getValue(), result2.getValue(), 0.0001);
    }

    @Test
    void testAddition_NullTargetUnit() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> {l1.add(l2, null);
        });
    }
    @Test
    void testAddition_MultipleTargetUnits() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length feetResult = l1.add(l2, Length.LengthUnit.FEET);
        Length inchResult = l1.add(l2, Length.LengthUnit.INCHES);
        assertEquals(2.0, feetResult.getValue(), 0.0001);
        assertEquals(24.0, inchResult.getValue(), 0.0001);
    }

    @Test
    void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_LargeValues() {
        Length l1 = new Length(1e6, Length.LengthUnit.FEET);
        Length l2 = new Length(1e6, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(2e6, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_SmallValues() {
        Length l1 = new Length(0.001, Length.LengthUnit.FEET);
        Length l2 = new Length(0.002, Length.LengthUnit.FEET);
        Length result = l1.add(l2, Length.LengthUnit.FEET);
        assertEquals(0.003, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CmToInch() {
        Length l1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);
        assertEquals(5.08, result.getValue(), 0.01);
    }
}