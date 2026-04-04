package Com.apps.QuantityMeasurementApp.UseCase16;


public class QuantityMeasurementController {

    private final QuantityMeasurementDatabaseRepository repository;

    public QuantityMeasurementController() {
        this.repository = QuantityMeasurementDatabaseRepository.getInstance();
    }

    public String getPoolStats() {
        return repository.getPoolStatistics();
    }
}