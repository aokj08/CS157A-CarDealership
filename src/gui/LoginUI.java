package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.DBManager;

public class LoginUI extends JFrame {

    private DBManager dbM = DBManager.getDBManager();

    private JLabel titleLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String password;

    public LoginUI() {
         // Set the frame properties
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        // Create the title label
        titleLabel = new JLabel("Welcome to My App!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.gridwidth = 2;
        titleLabelConstraints.insets = new Insets(20, 0, 20, 0);
        panel.add(titleLabel, titleLabelConstraints);

        // Create the password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints();
        passwordLabelConstraints.gridx = 0;
        passwordLabelConstraints.gridy = 1;
        passwordLabelConstraints.insets = new Insets(0, 20, 10, 0);
        panel.add(passwordLabel, passwordLabelConstraints);

        // Create the password field
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints();
        passwordFieldConstraints.gridx = 1;
        passwordFieldConstraints.gridy = 1;
        passwordFieldConstraints.insets = new Insets(0, 0, 10, 20);
        panel.add(passwordField, passwordFieldConstraints);

        // Create the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        GridBagConstraints loginButtonConstraints = new GridBagConstraints();
        loginButtonConstraints.gridx = 0;
        loginButtonConstraints.gridy = 2;
        loginButtonConstraints.gridwidth = 2;
        panel.add(loginButton, loginButtonConstraints);

        // Add the panel to the frame
        add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Check if the entered password matches the expected password
        String password = new String(passwordField.getPassword());
        if (password.equals("mypassword")) {
            // If the passwords match, close the login window and display the home page
            setVisible(false);
            dispose();
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        } else {
            // If the passwords don't match, show an error message
            JOptionPane.showMessageDialog(this, "Incorrect password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public String getPassword() {
        return password;
    }
}

