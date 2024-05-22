package connection;

import java.sql.*;

/**
 * This class provides utility methods to manage database connections.
 * It includes methods to open and close connections, statements, and result sets.
 */
public class ConnectionFactory {
    static String DB_URL = "jdbc:postgresql://localhost:5432/ordersmanagement";
    static String DB_USER = "postgres";
    static String DB_PASSWORD = "1k2k3k4k";

    /**
     * Obtains a connection to the database using predefined credentials.
     *
     * @return A Connection object to the database.
     * @throws RuntimeException if a database access error occurs or the url is null.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the database connection if it's not null.
     *
     * @param connection The database connection to close.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the statement if it's not null to free up resources.
     *
     * @param statement The SQL statement to close.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the result set if it's not null to prevent memory leaks.
     *
     * @param resultSet The result set to close.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
