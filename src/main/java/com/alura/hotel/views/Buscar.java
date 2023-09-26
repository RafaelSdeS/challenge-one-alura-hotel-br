package com.alura.hotel.views;

import com.alura.hotel.jdbc.controller.GuestController;
import com.alura.hotel.jdbc.controller.ReservationController;
import com.alura.hotel.jdbc.model.Guest;
import com.alura.hotel.jdbc.model.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.List;

public class Buscar extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbGuests;
    private JTable tbReservations;
    private DefaultTableModel guestTableModel;
    private DefaultTableModel reservationTableModel;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;

    private GuestController guestController;
    private ReservationController reservationController;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Buscar frame = new Buscar();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Buscar() throws SQLException {
        guestController = new GuestController();
        reservationController = new ReservationController();

        List<Guest> allGuests = guestController.getAllGuests();
        List<Reservation> allReservations = reservationController.getAllReservations();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("../imagens/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservations = new JTable();
        tbReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservations.setFont(new Font("Roboto", Font.PLAIN, 16));
        reservationTableModel = (DefaultTableModel) tbReservations.getModel();
        reservationTableModel.addColumn("Numero de Reserva");
        reservationTableModel.addColumn("Data Check In");
        reservationTableModel.addColumn("Data Check Out");
        reservationTableModel.addColumn("Valor");
        reservationTableModel.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservations);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("../imagens/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        tbGuests = new JTable();
        tbGuests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbGuests.setFont(new Font("Roboto", Font.PLAIN, 16));
        guestTableModel = (DefaultTableModel) tbGuests.getModel();
        guestTableModel.addColumn("Numero de Hóspede");
        guestTableModel.addColumn("Nome");
        guestTableModel.addColumn("Sobrenome");
        guestTableModel.addColumn("Data de Nascimento");
        guestTableModel.addColumn("Nacionalidade");
        guestTableModel.addColumn("Telefone");
        guestTableModel.addColumn("Numero de Reserva");
        JScrollPane scroll_tableGuests = new JScrollPane(tbGuests);
        panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("../imagens/pessoas.png")), scroll_tableGuests, null);
        scroll_tableGuests.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        final JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        final JPanel btnExit = new JPanel();
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnExit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnExit.setLayout(null);
        btnExit.setBackground(Color.WHITE);
        btnExit.setBounds(857, 0, 53, 36);
        header.add(btnExit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnExit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnSearch = new JPanel();
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = panel.getSelectedIndex();
                if (selectedIndex == 1) {
                    searchGuestsByName();
                } else {
                    searchReservationById();
                }
            }
        });
        btnSearch.setLayout(null);
        btnSearch.setBackground(new Color(12, 138, 199));
        btnSearch.setBounds(748, 125, 122, 35);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnSearch);

        JPanel btnRecarregar = new JPanel();
        btnRecarregar.setLayout(null);
        btnRecarregar.setBackground(new Color(12, 138, 199));
        btnRecarregar.setBounds(150, 508, 122, 35);
        btnRecarregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnRecarregar);
        btnRecarregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reloadAllData();
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRecarregar.setBackground(new Color(9, 105, 150)); // Change color when hovered
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                btnRecarregar.setBackground(new Color(12, 138, 199)); // Restore original color
            }
        });
        
        JLabel lblRecarregar = new JLabel("Recarregar");
        lblRecarregar.setHorizontalAlignment(SwingConstants.CENTER);
        lblRecarregar.setForeground(Color.WHITE);
        lblRecarregar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblRecarregar.setBounds(0, 0, 122, 35);
        btnRecarregar.add(lblRecarregar);

        JLabel lblSearch = new JLabel("BUSCAR");
        lblSearch.setBounds(0, 0, 122, 35);
        btnSearch.add(lblSearch);
        lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearch.setForeground(Color.WHITE);
        lblSearch.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEdit = new JPanel();
        btnEdit.setLayout(null);
        btnEdit.setBackground(new Color(12, 138, 199));
        btnEdit.setBounds(635, 508, 122, 35);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEdit);
        btnEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = panel.getSelectedIndex();
                if (selectedIndex == 1) {
                    editSelectedGuest();
                } else {
                    editSelectedReservation();
                }
            }
        });

        JLabel lblEdit = new JLabel("EDITAR");
        lblEdit.setHorizontalAlignment(SwingConstants.CENTER);
        lblEdit.setForeground(Color.WHITE);
        lblEdit.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEdit.setBounds(0, 0, 122, 35);
        btnEdit.add(lblEdit);

        JPanel btnDelete = new JPanel();
        btnDelete.setLayout(null);
        btnDelete.setBackground(new Color(12, 138, 199));
        btnDelete.setBounds(767, 508, 122, 35);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDelete);
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = panel.getSelectedIndex();
                if (selectedIndex == 1) {
                    deleteSelectedGuest();
                } else {
                    try {
                        deleteSelectedReservation();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JLabel lblDelete = new JLabel("DELETAR");
        lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
        lblDelete.setForeground(Color.WHITE);
        lblDelete.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblDelete.setBounds(0, 0, 122, 35);
        btnDelete.add(lblDelete);

        setResizable(false);

        // Display the loaded data in the table
        populateGuestTable(allGuests);
        populateReservationTable(allReservations);
    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
    private void populateGuestTable(List<Guest> guests) {
        guestTableModel.setRowCount(0);

        for (Guest guest : guests) {
            guestTableModel.addRow(new Object[]{
                    guest.getId(),
                    guest.getFirstName(),
                    guest.getLastName(),
                    guest.getBirthDate(),
                    guest.getNationality(),
                    guest.getPhoneNumber(),
                    guest.getReservationId()
            });
        }
    }

    private void searchGuestsByName() {
        String searchText = txtBuscar.getText().trim(); 

        if (!searchText.isEmpty()) {
            List<Guest> searchResults = guestController.getGuestsByName(searchText);

            // Display the search results in the table (update the table model)
            guestTableModel.setRowCount(0); // Clear existing rows

            for (Guest guest : searchResults) {
                guestTableModel.addRow(new Object[]{
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getBirthDate(),
                        guest.getNationality(),
                        guest.getPhoneNumber(),
                        guest.getReservationId()
                });
            }
        }
    }

    private void deleteSelectedGuest() {
        int selectedRow = tbGuests.getSelectedRow();
        if (selectedRow >= 0) {
            int guestId = (int) tbGuests.getValueAt(selectedRow, 0);
            guestController.deleteGuestById(guestId);
            // Refresh the table after deletion
            List<Guest> updatedGuests = guestController.getAllGuests();
            populateGuestTable(updatedGuests);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a guest to delete.");
        }
    }

    private void editSelectedGuest() {
        int selectedRow = tbGuests.getSelectedRow();
        if (selectedRow >= 0) {
            int guestId = (int) tbGuests.getValueAt(selectedRow, 0);
            EditarHospede editarHospede = new EditarHospede(guestId);
            editarHospede.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a guest to edit.");
        }
    }

    private void populateReservationTable(List<Reservation> reservations) {
        reservationTableModel.setRowCount(0);

        for (Reservation reservation : reservations) {
            reservationTableModel.addRow(new Object[]{
                    reservation.getId(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    reservation.getValue(),
                    reservation.getPaymentMethod()
            });
        }
    }

    private void searchReservationById() {
        String searchText = txtBuscar.getText().trim();
    
        if (!searchText.isEmpty()) {
            try {
                int reservationId = Integer.parseInt(searchText);
    
                // Get the reservation based on the parsed ID
                Reservation reservation = reservationController.getReservationById(reservationId);
    
                if (reservation != null) {
                    // Clear the existing rows in the reservation table
                    reservationTableModel.setRowCount(0);
    
                    // Add the retrieved reservation to the table
                    reservationTableModel.addRow(new Object[]{
                            reservation.getId(),
                            reservation.getCheckInDate(),
                            reservation.getCheckOutDate(),
                            reservation.getValue(),
                            reservation.getPaymentMethod()
                    });
                } else {
                    // Display a message if the reservation with the given ID is not found
                    JOptionPane.showMessageDialog(null, "Reservation with ID " + reservationId + " not found.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid reservation ID. Please enter a valid numeric ID.");
            }
        }
    }
    
    private void editSelectedReservation() {
        int selectedRow = tbReservations.getSelectedRow();
        if (selectedRow >= 0) {
            int reservationId = (int) tbReservations.getValueAt(selectedRow, 0);
            EditarReserva editarReserva = new EditarReserva(reservationId);
            editarReserva.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a reservation to edit.");
        }
    }

    private void deleteSelectedReservation() throws SQLException {
        int selectedRow = tbReservations.getSelectedRow();
        if (selectedRow >= 0) {
            int reservationId = (int) tbReservations.getValueAt(selectedRow, 0);
            reservationController.deleteReservationById(reservationId);
            // Refresh the table after deletion
            List<Reservation> updatedReservations = reservationController.getAllReservations();
            populateReservationTable(updatedReservations);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
        }
    }

    private void reloadAllData() {
        try {
            // Reload guest data
            List<Guest> allGuests = guestController.getAllGuests();
            populateGuestTable(allGuests);
    
            // Reload reservation data
            List<Reservation> allReservations = reservationController.getAllReservations();
            populateReservationTable(allReservations);
    
            // Clear the search field
            txtBuscar.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }
    
}
