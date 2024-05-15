package connection;

import java.sql.*;

public class ConnectionFactory {
    static String DB_URL = "jdbc:postgresql://localhost:5432/ordersmanagement";
    static String DB_USER = "postgres";
    static String DB_PASSWORD = "1k2k3k4k";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

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
