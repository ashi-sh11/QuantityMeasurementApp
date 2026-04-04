package Com.apps.QuantityMeasurementApp.UseCase16;

public class MainApp {
    static void main() {
        QuantityMeasurementController controller =
                new QuantityMeasurementController();

        System.out.println(controller.getPoolStats());
    }
}