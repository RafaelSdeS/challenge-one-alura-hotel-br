package com.alura.hotel.jdbc.factory;

import java.sql.Connection;

public class TestaConexao {

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
                ConnectionFactory.closeConnection(connection);
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
}
