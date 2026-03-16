import quantitymeasurement.QuantityMeasurementApp.Feet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase1_Testing {

    @Test
    public void testFeetEquality_SameValue(){
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        assertEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_DifferentValue(){
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(4.0);
        assertNotEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_WithNull(){
        Feet f1 = new Feet(1.0);
        assertNotEquals(f1,null);
    }

    @Test
    public void testFeetEquality_WithDifferentType(){
        Feet f1 = new Feet(1.0);
        String s = "1.0";
        assertNotEquals(f1,s);
    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet f1 = new Feet(1.0);
        assertEquals(f1,f1);
    }
}