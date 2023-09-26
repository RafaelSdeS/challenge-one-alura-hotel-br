package com.alura.hotel.views;

import com.alura.hotel.jdbc.controller.GuestController;
import com.alura.hotel.jdbc.model.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class EditarHospede extends JFrame {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtBirthDate;
    private JComboBox<String> cmbNationality;
    private JTextField txtPhoneNumber;
    private JButton btnSaveChanges;

    private GuestController guestController;

    // List of nationalities
    private final List<String> nationalities = Arrays.asList(
        "alemão", "andorrano", "angolano", "antiguano", "saudita", "argelino", "argentino", "armênio", "australiano", "austríaco", "azerbaijano", "bahamense", "bangladês, bangladense", "barbadiano", "bahreinita", "belga", "belizenho", "beninês", "belarusso", "boliviano", "bósnio", "botsuanês", "brasileiro", "bruneíno", "búlgaro", "burkineonse, burkinabé", "burundês", "butanês", "cabo-verdiano", "camerounês", "cambojano", "catariano", "canadense", "cazaque", "chadiano", "chileno", "chinês", "cipriota", "colombiano", "comoriano", "congolês", "congolês", "sul-coreano", "norte-coreano", "costa-marfinense, marfinense", "costa-ricense", "croata", "cubano", "dinamarquês", "djiboutiano", "dominiquense", "egípcio", "salvadorenho", "emiradense, emirático", "equatoriano", "eritreu", "eslovaco", "esloveno", "espanhol", "estadunidense, (norte-)americano", "estoniano", "etíope", "fijiano", "filipino", "finlandês", "francês", "gabonês", "gambiano", "ganês ou ganense", "georgiano", "granadino", "grego", "guatemalteco", "guianês", "guineense", "guineense, bissau-guineense", "equato-guineense", "haitiano", "hondurenho", "húngaro", "iemenita", "cookiano", "marshallês", "salomonense", "indiano", "indonésio", "iraniano", "iraquiano", "irlandês", "islandês", "34", "jamaicano", "japonês", "jordaniano", "kiribatiano", "kuwaitiano", "laosiano", "lesotiano", "letão", "libanês", "liberiano", "líbio", "liechtensteiniano", "lituano", "luxemburguês", "macedônio", "madagascarense", "malásio37", "malawiano", "maldivo", "maliano", "maltês", "marroquino", "mauriciano", "mauritano", "mexicano", "myanmarense", "micronésio", "moçambicano", "moldovo", "monegasco", "mongol", "montenegrino", "namibiano", "nauruano", "nepalês", "nicaraguense", "nigerino", "nigeriano", "niuiano", "norueguês", "neozelandês", "omani", "neerlandês", "palauano", "palestino", "panamenho", "papua, papuásio", "paquistanês", "paraguaio", "peruano", "polonês, polaco", "português", "queniano", "quirguiz", "britânico", "centro-africano", "tcheco", "dominicano", "romeno", "ruandês", "russo", "samoano", "santa-lucense", "são-cristovense", "samarinês", "santomense", "são-vicentino", "seichelense", "senegalês", "sérvio", "singapurense", "sírio", "somaliano, somali", "sri-lankês", "suázi", "sudanês", "sul-sudanês", "sueco", "suíço", "surinamês", "tajique", "tailandês", "tanzaniano", "timorense", "togolês", "tonganês", "trinitário", "tunisiano", "turcomeno", "turco", "tuvaluano", "ucraniano", "ugandês", "uruguaio", "uzbeque", "vanuatuense", "vaticano", "venezuelano", "vietnamita", "zambiano", "zimbabueano"
    );

    public EditarHospede(int guestId) {
        setTitle("Editar Hóspede");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        guestController = new GuestController();

        JLabel lblFirstName = new JLabel("Primeiro Nome:");
        add(lblFirstName);
        txtFirstName = new JTextField();
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Sobrenome:");
        add(lblLastName);
        txtLastName = new JTextField();
        add(txtLastName);

        JLabel lblBirthDate = new JLabel("Data de Nascimento (yyyy-MM-dd):");
        add(lblBirthDate);
        txtBirthDate = new JTextField();
        add(txtBirthDate);

        JLabel lblNationality = new JLabel("Nacionalidade:");
        add(lblNationality);
        cmbNationality = new JComboBox<>(nationalities.toArray(new String[0]));
        add(cmbNationality);

        JLabel lblPhoneNumber = new JLabel("Número de Telefone:");
        add(lblPhoneNumber);
        txtPhoneNumber = new JTextField();
        add(txtPhoneNumber);

        btnSaveChanges = new JButton("Salvar Alterações");
        add(btnSaveChanges);

        // Load guest data based on guestId and populate the fields
        Guest guest = guestController.getGuestById(guestId);
        if (guest != null) {
            txtFirstName.setText(guest.getFirstName());
            txtLastName.setText(guest.getLastName());
            txtBirthDate.setText(guest.getBirthDate().toString()); // Assuming date format is yyyy-MM-dd
            cmbNationality.setSelectedItem(guest.getNationality());
            txtPhoneNumber.setText(guest.getPhoneNumber());
        }

        btnSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve user input from text fields and combo box
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                Date birthDate = Date.valueOf(txtBirthDate.getText()); // Assuming date format is yyyy-MM-dd
                String nationality = cmbNationality.getSelectedItem().toString();
                String phoneNumber = txtPhoneNumber.getText();

                // Update the guest's data in the database
                if (guest != null) {
                    guest.setFirstName(firstName);
                    guest.setLastName(lastName);
                    guest.setBirthDate(birthDate);
                    guest.setNationality(nationality);
                    guest.setPhoneNumber(phoneNumber);

                    guestController.updateGuest(guest);

                    // Close the edit guest window
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Guest not found.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditarHospede(1).setVisible(true);
            }
        });
    }
}
