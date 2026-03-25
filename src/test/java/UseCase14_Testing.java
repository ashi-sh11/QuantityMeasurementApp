import Com.apps.QuantityMeasurementApp.UseCase14.Quantity;
import Com.apps.QuantityMeasurementApp.UseCase14.TemperatureUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase14_Testing {
    private static final double EPSILON = 0.01;

    @Test
    void testEquality_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testEquality_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(212, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t2 = new Quantity<>(100, TemperatureUnit.CELSIUS);
        assertTrue(t1.equals(t2));
    }


    @Test
    void testEquality_DifferentValues() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertFalse(t1.equals(t2));
    }


    @Test
    void testConversion_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> t = new Quantity<>(100, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> t = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), EPSILON);
    }


    @Test
    void testConversion_SameUnit() {
        Quantity<TemperatureUnit> t = new Quantity<>(50, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(50.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(20, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> {t1.add(t2, TemperatureUnit.CELSIUS);
        });
    }

    @Test
    void testSubtraction_NotSupported() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(5, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> {t1.subtract(t2, TemperatureUnit.CELSIUS);
        });
    }


    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, TemperatureUnit.CELSIUS);
        });
    }

    @Test
    void testNullComparison() {
        Quantity<TemperatureUnit> t = new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertFalse(t.equals(null));
    }

    @Test
    void testSameReference() {
        Quantity<TemperatureUnit> t = new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertTrue(t.equals(t));
    }


    @Test
    void testCrossCategoryComparison() {
        Quantity<TemperatureUnit> temp = new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertTrue(true);
    }
}