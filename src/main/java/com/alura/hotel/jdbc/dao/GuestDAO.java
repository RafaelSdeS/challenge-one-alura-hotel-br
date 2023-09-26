package com.alura.hotel.jdbc.dao;

import com.alura.hotel.jdbc.model.Guest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    private final Connection connection;

    public GuestDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertGuest(Guest guest) {
    try {
        String sql = "INSERT INTO Guests (firstName, lastName, birthDate, nationality, phoneNumber, reservationId) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, guest.getFirstName());
            pstmt.setString(2, guest.getLastName());
            pstmt.setDate(3, guest.getBirthDate());
            pstmt.setString(4, guest.getNationality());
            pstmt.setString(5, guest.getPhoneNumber());
            pstmt.setInt(6, guest.getReservationId());

            pstmt.execute();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    guest.setId(generatedKeys.getInt(1));
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public List<Guest> getGuestsByName(String name) {
        List<Guest> guests = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Guests WHERE firstName LIKE ? OR lastName LIKE ?";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, "%" + name + "%");
                pstmt.setString(2, "%" + name + "%");
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Guest guest = new Guest(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("birthDate"), rs.getString("nationality"),rs.getString("phoneNumber"), rs.getInt("reservationId") );
                        
                        guests.add(guest);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guests;
    }

    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Guests";

            try (PreparedStatement pstmt = connection.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Guest guest = new Guest(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("birthDate"), rs.getString("nationality"),rs.getString("phoneNumber"), rs.getInt("reservationId") );

                    guests.add(guest);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guests;
    }

    public void deleteGuestById(int guestId) {
        try {
            String sql = "DELETE FROM Guests WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, guestId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Guest getGuestById(int guestId) {
        try {
            String sql = "SELECT * FROM Guests WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, guestId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return extractGuestFromResultSet(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Guest not found
    }
    
    public void updateGuest(Guest guest) {
        try {
            String sql = "UPDATE Guests SET firstName = ?, lastName = ?, birthDate = ?, nationality = ?, phoneNumber = ?, reservationId = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, guest.getFirstName());
                pstmt.setString(2, guest.getLastName());
                pstmt.setDate(3, guest.getBirthDate());
                pstmt.setString(4, guest.getNationality());
                pstmt.setString(5, guest.getPhoneNumber());
                pstmt.setInt(6, guest.getReservationId());
                pstmt.setInt(7, guest.getId());
    
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Guest extractGuestFromResultSet(ResultSet rs) throws SQLException {
        // Extract guest data from the result set and return a Guest object
        int id = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        Date birthDate = rs.getDate("birthDate");
        String nationality = rs.getString("nationality");
        String phoneNumber = rs.getString("phoneNumber");
        int reservationId = rs.getInt("reservationId");
    
        return new Guest(id, firstName, lastName, birthDate, nationality, phoneNumber, reservationId);
    }
    
}
