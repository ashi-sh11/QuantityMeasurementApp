import UseCase5.Length.LengthUnit;
import org.junit.jupiter.api.Test;
import static UseCase5.Length.convert;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase5_Testing {
    private static final double EPS = 1e-6;
    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0, convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0, convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0, convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), EPS);
    }

    @Test
    void testConversion_RoundTrip() {
        double value = 5.0;
        double converted = convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double back = convert(converted, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(value, back, EPS);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            convert(1.0, null, LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaN() {
        assertThrows(IllegalArgumentException.class, () -> {
            convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }
}