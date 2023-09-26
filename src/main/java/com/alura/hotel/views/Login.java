package com.alura.hotel.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    int xMouse, yMouse;
    private JLabel labelExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        initializeUI();
        setLocationRelativeTo(null);
    }

    private void initializeUI() {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        // Create the panel for the right-side image
        JPanel panel_1 = createImagePanel();
        panel.add(panel_1);

        // Create the exit button
        JPanel btnexit = createExitButton();
        panel_1.add(btnexit);

        // Create the username and password fields
        createFields(panel);

        // Create the login button
        JPanel btnLogin = createLoginButton();
        panel.add(btnLogin);

        // Create the header panel
        JPanel header = createHeaderPanel(panel);
        panel.add(header);
    }

    private JPanel createImagePanel() {
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setBounds(484, 0, 304, 527);
        panel_1.setLayout(null);

        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        imgHotel.setIcon(new ImageIcon(Login.class.getResource("../imagens/img-hotel-login-.png")));
        panel_1.add(imgHotel);

        return panel_1;
    }

    private JPanel createExitButton() {
        JPanel btnexit = new JPanel();
        btnexit.setBounds(251, 0, 53, 36);
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setLayout(null);
        btnexit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        labelExit.setForeground(SystemColor.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        btnexit.add(labelExit);

        // Add action listeners for the exit button
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.RED);
                labelExit.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(new Color(12, 138, 199));
                labelExit.setForeground(Color.WHITE);
            }
        });

        return btnexit;
    }

    private void createFields(JPanel panel) {
        // Username Field
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("Digite seu nome de usuário");
        txtUsuario.setBorder(BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtUsuario.setBounds(65, 256, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);

        // Password Field
        txtSenha = new JPasswordField();
        txtSenha.setText("********");
        txtSenha.setForeground(SystemColor.activeCaptionBorder);
        txtSenha.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSenha.setBorder(BorderFactory.createEmptyBorder());
        txtSenha.setBounds(65, 353, 324, 32);
        panel.add(txtSenha);

        JLabel LabelUsuario = new JLabel("USUÁRIO");
        LabelUsuario.setForeground(SystemColor.textInactiveText);
        LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 219, 107, 26);
        panel.add(LabelUsuario);

        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setForeground(SystemColor.textInactiveText);
        lblSenha.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        lblSenha.setBounds(65, 316, 140, 26);
        panel.add(lblSenha);

        // Add mouse listeners to handle text field placeholders
        txtUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleUsernameFieldClick();
            }
        });

        txtSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePasswordFieldClick();
            }
        });
    }

    private JPanel createLoginButton() {
        JPanel btnLogin = new JPanel();
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 431, 122, 44);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblEntrar = new JLabel("ENTRAR");
        lblEntrar.setBounds(0, 0, 122, 44);
        lblEntrar.setForeground(SystemColor.controlLtHighlight);
        lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntrar.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnLogin.add(lblEntrar);

        // Add mouse listeners for the login button
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handleLogin();
            }
        });

        return btnLogin;
    }

    private JPanel createHeaderPanel(JPanel panel) {
        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleHeaderMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleHeaderMousePressed(e);
            }
        });
        header.setBackground(SystemColor.window);
        header.setBounds(0, 0, 784, 36);
        panel.add(header);
        header.setLayout(null);

        return header;
    }

    private void handleUsernameFieldClick() {
        if (txtUsuario.getText().equals("Digite seu nome de usuário")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(Color.BLACK);
        }
        if (String.valueOf(txtSenha.getPassword()).isEmpty()) {
            txtSenha.setText("********");
            txtSenha.setForeground(Color.GRAY);
        }
    }

    private void handlePasswordFieldClick() {
        if (String.valueOf(txtSenha.getPassword()).equals("********")) {
            txtSenha.setText("");
            txtSenha.setForeground(Color.BLACK);
        }
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.setText("Digite seu nome de usuário");
            txtUsuario.setForeground(Color.GRAY);
        }
    }

    private void handleLogin() {
        String Usuario = "admin";
        String Senha = "admin";

        String senha = new String(txtSenha.getPassword());

        if (txtUsuario.getText().equals(Usuario) && senha.equals(Senha)) {
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou Senha não válidos");
        }
    }

    private void handleHeaderMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void handleHeaderMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
