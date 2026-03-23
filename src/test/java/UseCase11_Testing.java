import UseCase11.LengthUnit;
import UseCase11.Quantity;
import UseCase11.VolumeUnit;
import UseCase11.WeightUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseCase11_Testing {

    private static final double EPSILON = 1e-4;

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_MillilitreToLitre() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToGallon() {
        Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> g = new Quantity<>(0.264172, VolumeUnit.GALLON);

        assertTrue(Math.abs(
                l.getUnit().convertToBaseUnit(l.getValue()) -
                        g.getUnit().convertToBaseUnit(g.getValue())
        ) < EPSILON);
    }

    @Test
    void testEquality_GallonToLitre() {
        Quantity<VolumeUnit> g = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> l = new Quantity<>(3.78541, VolumeUnit.LITRE);

        assertTrue(Math.abs(
                g.getUnit().convertToBaseUnit(g.getValue()) -
                        l.getUnit().convertToBaseUnit(l.getValue())
        ) < EPSILON);
    }

    @Test
    void testVolumeVsLength() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(v.equals(l));
    }

    @Test
    void testVolumeVsWeight() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(v.equals(w));
    }

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1000.0, q.convertTo(VolumeUnit.MILLILITRE).getValue(), EPSILON);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(1.0, q.convertTo(VolumeUnit.LITRE).getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(3.78541, q.convertTo(VolumeUnit.LITRE).getValue(), 0.01);
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> q = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(1.0, q.convertTo(VolumeUnit.GALLON).getValue(), 0.01);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> q = new Quantity<>(1.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result =
                q.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        assertEquals(3.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(2.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(2000.0,
                q1.add(q2, VolumeUnit.MILLILITRE).getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(q1.add(q2).equals(q2.add(q1)));
    }

    @Test
    void testZero() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        assertEquals(5.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testNegative() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);

        assertEquals(3.0, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testLargeValues() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1e6, VolumeUnit.LITRE);

        assertEquals(2e6, q1.add(q2).getValue(), EPSILON);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE));
    }

    @Test
    void testNullComparison() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(q.equals(null));
    }

    @Test
    void testSameReference() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q.equals(q));
    }

    @Test
    void testVolumeUnitConstants() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor());
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor());
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), 0.01);
    }

    @Test
    void testConvertToBaseUnit() {
        assertEquals(1.0,
                VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit() {
        assertEquals(1000.0,
                VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }
}