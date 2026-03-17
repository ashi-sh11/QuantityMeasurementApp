import org.junit.jupiter.api.Test;
import quantitymeasurement.Length;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase3_Testing {
  @Test
  void testEquality_FeetToInch() {
      Length l1 = new Length(1.0,Length.LengthUnit.FEET);
      Length l2 = new Length(12.0,Length.LengthUnit.INCHES);
      assertTrue(l1.equals(l2));
  }


    @Test
    void testEquality_DifferentValue() {
        Length q1 = new Length(1.0, Length.LengthUnit.FEET);
        Length q2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_Null() {
        Length q1 = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }

  }

