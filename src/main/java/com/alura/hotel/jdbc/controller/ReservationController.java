package com.alura.hotel.jdbc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alura.hotel.jdbc.dao.ReservationDAO;
import com.alura.hotel.jdbc.factory.ConnectionFactory;
import com.alura.hotel.jdbc.model.Reservation;

public class ReservationController {
    private ReservationDAO reservationDAO;

    public ReservationController() {
        new ConnectionFactory();
        Connection connection = ConnectionFactory.getConnection();
        this.reservationDAO = new ReservationDAO(connection);
    }

    public void insertReservation(Reservation reservation) throws SQLException {
        this.reservationDAO.insertReservation(reservation);
    }

    public List<Reservation> getAllReservations() throws SQLException {
        return reservationDAO.getAllReservations();
    }

    public List<Reservation> getReservationsByGuestName(String guestName) {
        return reservationDAO.getReservationsByGuestName(guestName);
    }

    public Reservation getReservationById(int reservationId) {
        return reservationDAO.getReservationById(reservationId);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservationById(int id) {
        reservationDAO.deleteReservationById(id);
    }
}
