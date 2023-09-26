package com.alura.hotel.jdbc.controller;

import com.alura.hotel.jdbc.dao.GuestDAO;
import com.alura.hotel.jdbc.factory.ConnectionFactory;
import com.alura.hotel.jdbc.model.Guest;

import java.sql.Connection;
import java.util.List;

public class GuestController {

	private GuestDAO guestDAO;

	public GuestController() {
		new ConnectionFactory();
		Connection connection = ConnectionFactory.getConnection();
		this.guestDAO = new GuestDAO(connection);
	}

	public void insertGuest(Guest guest) {
		this.guestDAO.insertGuest(guest);
	}

	public Guest getGuestById(int guestId) {
		return guestDAO.getGuestById(guestId);
	}

	public List<Guest> getGuestsByName(String name) {
		return guestDAO.getGuestsByName(name);
	}

	public List<Guest> getAllGuests() {
		return guestDAO.getAllGuests();
	}

	public void updateGuest(Guest guest) {
		guestDAO.updateGuest(guest);
	}

	public void deleteGuestById(int guestId) {
		guestDAO.deleteGuestById(guestId);
	}

}
