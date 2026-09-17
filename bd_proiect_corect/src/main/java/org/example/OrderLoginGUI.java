package org.example;

import javax.swing.*;
import java.awt.*;

public class OrderLoginGUI extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private OrderJDBC db;

    public OrderLoginGUI() {
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        db = new OrderJDBC();
        db.init();

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(new Color(134, 30, 30));

        userField = new JTextField();
        passField = new JPasswordField();

        //LOGIN UL
        JButton loginBtn = new JButton("Login");

        //strict doar UTILIZATOR
        JLabel userLabel = new JLabel("Utilizator:", JLabel.CENTER);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        userLabel.setOpaque(true);
        userLabel.setBackground(new Color(134, 30, 30));
        panel.add(userLabel);
        panel.add(userField);
        userField.setBackground(new Color(155, 130, 130));
        userField.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel paswordLabel = new JLabel("Parola:",   JLabel.CENTER);
        paswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        paswordLabel.setForeground(Color.WHITE);
        paswordLabel.setOpaque(true);
        paswordLabel.setBackground(new Color(134, 30, 30));
        panel.add(paswordLabel);
        panel.add(passField);
        passField.setBackground(new Color(155, 130, 130));
        passField.setFont(new Font("Arial", Font.BOLD, 14));

        add(panel, BorderLayout.CENTER);
        add(loginBtn, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> tryLogin());
        loginBtn.setBackground(new Color(68, 40, 40));
        loginBtn.setForeground(Color.WHITE);

        setVisible(true);
    }

    private void tryLogin() {
        UIManager.put("OptionPane.background", new Color(68, 40, 40));
        UIManager.put("Panel.background", new Color(68, 40, 40));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(134, 30, 30));
        UIManager.put("Button.foreground", Color.WHITE);

        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals("Alexia") && pass.equals("1234")) {
            dispose();
            new OrderGUI(db);
        } else {
            JOptionPane.showMessageDialog(this, "Utilizator sau parola gresite!");
        }
    }

    public static void main(String[] args) {
        new OrderLoginGUI();
    }
}
