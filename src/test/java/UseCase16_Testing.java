
import Com.apps.QuantityMeasurementApp.UseCase16.QuantityMeasurementController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UseCase16_Testing {

    private QuantityMeasurementController controller;

    @Before
    public void setup() {
        controller = new QuantityMeasurementController();
    }

    @Test
    public void testGetPoolStatistics() {
        String stats = controller.getPoolStats();

        Assert.assertNotNull(stats);
        Assert.assertTrue(stats.contains("Available"));
        Assert.assertTrue(stats.contains("Used"));
        Assert.assertTrue(stats.contains("Total"));

        System.out.println(stats);
    }
}