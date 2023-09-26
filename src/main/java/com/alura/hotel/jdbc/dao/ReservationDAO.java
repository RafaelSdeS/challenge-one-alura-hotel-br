package com.alura.hotel.jdbc.dao;

import com.alura.hotel.jdbc.factory.ConnectionFactory;
import com.alura.hotel.jdbc.model.Reservation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private final Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservations (checkInDate, checkOutDate, value, paymentMethod) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, reservation.getCheckInDate());
            preparedStatement.setDate(2, reservation.getCheckOutDate());
            preparedStatement.setBigDecimal(3, reservation.getValue());
            preparedStatement.setString(4, reservation.getPaymentMethod());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve the generated reservation ID.");
                }
            }
        }
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String sql = "SELECT * FROM Reservations";
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    java.sql.Date checkInDate = resultSet.getDate("checkInDate");
                    java.sql.Date checkOutDate = resultSet.getDate("checkOutDate");
                    BigDecimal value = resultSet.getBigDecimal("value");
                    String paymentMethod = resultSet.getString("paymentMethod");

                    Reservation reservation = new Reservation(id, checkInDate, checkOutDate, value, paymentMethod);
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public List<Reservation> getReservationsByGuestName(String guestName) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservations WHERE guest_name LIKE ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + guestName + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reservation reservation = extractReservationFromResultSet(rs);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return reservations;
    }

    public Reservation getReservationById(int reservationId) {
        try {
            String sql = "SELECT * FROM Reservations WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, reservationId);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve data from the result set and create a Reservation object
                        int id = resultSet.getInt("id");

                        Date checkInDate = resultSet.getDate("checkInDate");
                        Date checkOutDate = resultSet.getDate("checkOutDate");
                        BigDecimal value = resultSet.getBigDecimal("value");
                        String paymentMethod = resultSet.getString("paymentMethod");

                        // Create and return the Reservation object
                        return new Reservation(id, checkInDate, checkOutDate, value, paymentMethod);
                    } else {
                        // No reservation with the given ID found
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE Reservations SET checkInDate = ?, checkOutDate = ?, value = ?, paymentMethod = ? WHERE id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setDate(1, reservation.getCheckInDate());
            pstmt.setDate(2, reservation.getCheckOutDate());
            pstmt.setBigDecimal(3, reservation.getValue());
            pstmt.setString(4, reservation.getPaymentMethod());
            pstmt.setInt(5, reservation.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReservationById(int id) {
        String sql = "DELETE FROM Reservations WHERE id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Reservation extractReservationFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date checkInDate = rs.getDate("checkInDate");
        Date checkOutDate = rs.getDate("checkOutDate");
        BigDecimal value = rs.getBigDecimal("value");
        String paymentMethod = rs.getString("paymentMethod");

        // Create and return a new Reservation object
        return new Reservation(id, checkInDate, checkOutDate, value, paymentMethod);
    }

}
