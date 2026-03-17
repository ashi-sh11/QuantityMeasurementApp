import UseCase4.Length;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthTest {
    @Test
    void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }
    @Test
    void testEquality_YardToYard_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }
    @Test
    void testEquality_YardToFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }
    @Test
    void testEquality_YardToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }
    @Test
    void testEquality_CmToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }
    @Test
    void testEquality_CmToFeet_NotEqual() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }
    @Test
    void testEquality_Transitive() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }
    @Test
    void testEquality_NullComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);

        assertFalse(l1.equals(null));
    }
    @Test
    void testEquality_SameReference() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);

        assertTrue(l1.equals(l1));
    }
    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }
}