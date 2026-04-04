package Com.apps.QuantityMeasurementApp.UseCase16;

import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository {

    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

    private static QuantityMeasurementDatabaseRepository instance;

    private ConnectionPool connectionPool;

    private QuantityMeasurementDatabaseRepository() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized QuantityMeasurementDatabaseRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementDatabaseRepository();
        }
        return instance;
    }

    public String getPoolStatistics() {
        try {
            return connectionPool.getPoolStats();
        } catch (Exception e) {
            logger.severe("Error fetching pool stats: " + e.getMessage());
            throw new DatabaseException("Failed to fetch pool stats", e);
        }
    }
}