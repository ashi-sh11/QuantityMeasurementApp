package Com.apps.QuantityMeasurementApp.UseCase16;
import java.sql.*;
import java.util.*;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final Queue<Connection> availableConnections = new LinkedList<>();
    private final Queue<Connection> usedConnections = new LinkedList<>();

    private static final int INITIAL_POOL_SIZE = 10;
    Class.forName("org.postgresql.Driver");
    private static final String URL = "jdbc:postgresql://localhost:5432/DemoJDBC";
    private static final String USER = "postgres";
    private static final String PASSWORD = "forDemoOnly";

    private ConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");

            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                availableConnections.add(createConnection());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public synchronized Connection getConnection() {
        if (availableConnections.isEmpty()) {
            throw new RuntimeException("No available connections");
        }
        Connection conn = availableConnections.poll();
        usedConnections.add(conn);
        return conn;
    }

    public synchronized void releaseConnection(Connection conn) {
        if (conn != null) {
            usedConnections.remove(conn);
            availableConnections.add(conn);
        }
    }

    public synchronized String getPoolStats() {
        return "Connection Pool Stats -> " +
                "Available: " + availableConnections.size() +
                ", Used: " + usedConnections.size() +
                ", Total: " + (availableConnections.size() + usedConnections.size());
    }
}