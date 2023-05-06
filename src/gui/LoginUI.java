package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBManager;

public class LoginUI extends JFrame {

    private DBManager dbM;

    private JLabel titleLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String password;
    private JButton signUpButton;

    public LoginUI() {
        dbM = DBManager.getDBManager();
        initComponents();
    }

    private void initComponents() {
        // setTitle("Login");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);
        // getContentPane().setBackground(new Color(229, 229, 229));

        // titleLabel = new JLabel("Login");
        // titleLabel.setFont(new Font("Tahoma", 1, 24));

        // JLabel passwordLabel = new JLabel("Password:");
        // passwordField = new JPasswordField(20);

        // loginButton = new JButton("Login");
        // signUpButton = new JButton("Sign Up");

        // setLayout(new GridBagLayout());
        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridwidth = 2;
        // gbc.anchor = GridBagConstraints.CENTER;
        // gbc.insets.bottom = 20;
        // add(titleLabel, gbc);

        // gbc.gridx++;
        // gbc.anchor = GridBagConstraints.EAST;
        // add(passwordField, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // gbc.anchor = GridBagConstraints.WEST;
        // add(passwordLabel, gbc);

        // gbc.gridx++;
        // gbc.anchor = GridBagConstraints.EAST;
        // add(passwordField, gbc);

        // JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        // buttonPanel.add(loginButton);
        // buttonPanel.add(Box.createHorizontalStrut(10));
        // buttonPanel.add(signUpButton);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // gbc.gridwidth = 2;
        // gbc.insets.top = 20;
        // gbc.anchor = GridBagConstraints.CENTER;
        // add(buttonPanel, gbc);

        // pack();
        // setLocationRelativeTo(null);

         // Set the frame properties
         setTitle("Login");
         setSize(400, 300);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         // Create a JPanel to hold the components
         JPanel panel = new JPanel();
         panel.setLayout(new GridBagLayout());
         panel.setBackground(Color.WHITE);
 
         // Create the title label
         titleLabel = new JLabel("Welcome to B.E.S.T Car Dealership!");
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
         signUpButton = new JButton("Sign Up");
         signUpButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 signUpButtonActionPerformed(evt);
             }
         });
         GridBagConstraints signUpButtonConstraints = new GridBagConstraints();
         signUpButtonConstraints.gridx = 1;
         signUpButtonConstraints.gridy = 2;
         signUpButtonConstraints.insets = new Insets(0, 0, 20, 20);
         panel.add(signUpButton, signUpButtonConstraints);
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

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SignUpUI signUp = new SignUpUI();
        signUp.setVisible(true);
        setVisible(false);
        dispose();
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Check if the entered password matches the expected password
        String password = new String(passwordField.getPassword());
        ResultSet rs = dbM.query("SELECT password FROM Customer;");
        boolean found = false;
        try {
            while(rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    // If the passwords match, close the login window and display the home page
                    setVisible(false);
                    dispose();
                    HomePage homePage = new HomePage();
                    homePage.setVisible(true);
                    found = true;
                    break;
                }
            }
            if(!found) {
                JOptionPane.showMessageDialog(this, "Incorrect password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getPassword() {
        return password;
    }
}

