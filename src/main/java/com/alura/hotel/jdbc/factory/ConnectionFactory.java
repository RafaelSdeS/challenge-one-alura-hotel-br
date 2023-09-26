package com.alura.hotel.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_db");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing the connection pool.", e);
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a database connection.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
