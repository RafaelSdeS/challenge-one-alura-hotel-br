package com.alura.hotel.views;

import com.alura.hotel.jdbc.controller.ReservationController;
import com.alura.hotel.jdbc.model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditarReserva extends JFrame {

    private JPanel contentPane;
    private JTextField txtCheckIn;
    private JTextField txtCheckOut;
    private JTextField txtValor;
    private JComboBox<String> cbPaymentMethod;

    private ReservationController reservationController;
    private Integer reservationId;

    public EditarReserva(int reservationId) {
        this.reservationId = reservationId;
        reservationController = new ReservationController();
        Reservation reservation = reservationController.getReservationById(reservationId);

        setTitle("Editar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Labels and Text Fields
        JLabel lblCheckIn = new JLabel("Check-In:");
        lblCheckIn.setBounds(30, 30, 70, 14);
        contentPane.add(lblCheckIn);

        txtCheckIn = new JTextField();
        txtCheckIn.setBounds(110, 27, 130, 20);
        contentPane.add(txtCheckIn);
        txtCheckIn.setColumns(10);
        txtCheckIn.setText(formatDate(reservation.getCheckInDate()));

        JLabel lblCheckOut = new JLabel("Check-Out:");
        lblCheckOut.setBounds(30, 60, 70, 14);
        contentPane.add(lblCheckOut);

        txtCheckOut = new JTextField();
        txtCheckOut.setBounds(110, 57, 130, 20);
        contentPane.add(txtCheckOut);
        txtCheckOut.setColumns(10);
        txtCheckOut.setText(formatDate(reservation.getCheckOutDate()));

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(30, 90, 70, 14);
        contentPane.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(110, 87, 130, 20);
        contentPane.add(txtValor);
        txtValor.setColumns(10);
        txtValor.setText(String.valueOf(reservation.getValue()));

        JLabel lblPaymentMethod = new JLabel("Método de Pagamento:");
        lblPaymentMethod.setBounds(30, 120, 150, 14);
        contentPane.add(lblPaymentMethod);

        // Payment Method Combo Box
        cbPaymentMethod = new JComboBox<>();
        cbPaymentMethod.setBounds(180, 117, 130, 20);
        contentPane.add(cbPaymentMethod);
        cbPaymentMethod.addItem("Cartão de Crédito");
        cbPaymentMethod.addItem("Cartão de Débito");
        cbPaymentMethod.addItem("Dinheiro");
        cbPaymentMethod.setSelectedItem(reservation.getPaymentMethod());

        // Save Button
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        btnSalvar.setBounds(30, 160, 100, 23);
        contentPane.add(btnSalvar);

        // Cancel Button
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(150, 160, 100, 23);
        contentPane.add(btnCancelar);
    }

    // Save Changes Method
    private void saveChanges() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date checkIn = new Date(dateFormat.parse(txtCheckIn.getText()).getTime());
            Date checkOut = new Date(dateFormat.parse(txtCheckOut.getText()).getTime());
            BigDecimal value = new BigDecimal(txtValor.getText()); // Parse as BigDecimal
            String paymentMethod = cbPaymentMethod.getSelectedItem().toString();

            Reservation updatedReservation = new Reservation(reservationId, checkIn, checkOut, value, paymentMethod);
            reservationController.updateReservation(updatedReservation);

            JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!");
            dispose(); // Close the edit reservation window
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar reserva. Verifique os dados inseridos.");
        }
    }

    // Format Date Method
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
