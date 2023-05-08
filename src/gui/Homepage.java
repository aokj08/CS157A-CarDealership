package gui;

import javax.swing.*;

import database.DBManager;

import java.awt.*;
import java.sql.ResultSet;

public class Homepage {

    private DBManager dbM;

    public Homepage() {
        dbM = DBManager.getDBManager();
        JFrame frame = new JFrame("Dealership Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel for the header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Welcome to the Dealership!");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel mainLabel = new JLabel("Please log in or sign up below:");
        mainPanel.add(mainLabel, BorderLayout.NORTH);

        // Create a JPanel for the login form
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel accountTypeLabel = new JLabel("Account Type:");
        JComboBox<String> accountTypeDropdown = new JComboBox<>(new String[] { "Employee", "Customer" });
        JLabel usernameLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> {
            String email = usernameField.getName(), password = passwordField.getName();
            if (accountTypeDropdown.getSelectedItem() == "Employee") {
                ResultSet rs = dbM.query(
                        String.format("SELECT email, password FROM Employee WHERE email = '%s' AND password = '%s';",
                                email, password));
                if (rs != null) {
                    new Employeepage();
                } else {
                    // Display Error message
                }
            } else {
                ResultSet rs = dbM.query(
                        String.format("SELECT email, password FROM Customer WHERE email = '%s' AND password = '%s';",
                                email, password));
                if (rs != null) {
                    // implement closing current window if have time
                    new Carbrowsingpage(); // Customer page
                } else {
                    // Display Error message
                }
            }
        });
        loginPanel.add(accountTypeLabel);
        loginPanel.add(accountTypeDropdown);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Create a JPanel for the sign up link
        JPanel signupPanel = new JPanel();
        JLabel signupLabel = new JLabel("Don't have an account? ");
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(e -> {
            // Open Signup JFrame
            new Signup();
        });
        signupPanel.add(signupLabel);
        signupPanel.add(signupButton);
        mainPanel.add(signupPanel, BorderLayout.SOUTH);

        // Add the header and main content to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
    // public static void main(String[] args) {
    // // Create a JFrame
    // JFrame frame = new JFrame("Dealership Homepage");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setSize(500, 300);
    // frame.setLocationRelativeTo(null); // Center the window on the screen

    // // Create a JPanel for the header
    // JPanel headerPanel = new JPanel();
    // JLabel headerLabel = new JLabel("Welcome to the Dealership!");
    // headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
    // headerPanel.add(headerLabel);

    // // Create a JPanel for the main content
    // JPanel mainPanel = new JPanel(new BorderLayout());
    // JLabel mainLabel = new JLabel("Please log in or sign up below:");
    // mainPanel.add(mainLabel, BorderLayout.NORTH);

    // // Create a JPanel for the login form
    // JPanel loginPanel = new JPanel(new GridLayout(4, 2, 5, 5));
    // JLabel accountTypeLabel = new JLabel("Account Type:");
    // JComboBox<String> accountTypeDropdown = new JComboBox<>(new String[] {
    // "Employee", "Customer" });
    // JLabel usernameLabel = new JLabel("Email:");
    // JLabel passwordLabel = new JLabel("Password:");
    // JTextField usernameField = new JTextField();
    // JPasswordField passwordField = new JPasswordField();
    // JButton loginButton = new JButton("Log In");
    // loginButton.addActionListener(e -> {
    // String email = usernameField.getName(), password = passwordField.getName();
    // if(accountTypeDropdown.getSelectedItem() == "Employee") {
    // // ResultSet rs = dbM
    // }
    // new Carbrowsingpage(); //Customer page
    // });
    // loginPanel.add(accountTypeLabel);
    // loginPanel.add(accountTypeDropdown);
    // loginPanel.add(usernameLabel);
    // loginPanel.add(usernameField);
    // loginPanel.add(passwordLabel);
    // loginPanel.add(passwordField);
    // loginPanel.add(new JLabel(""));
    // loginPanel.add(loginButton);
    // mainPanel.add(loginPanel, BorderLayout.CENTER);

    // // Create a JPanel for the sign up link
    // JPanel signupPanel = new JPanel();
    // JLabel signupLabel = new JLabel("Don't have an account? ");
    // JButton signupButton = new JButton("Sign Up");
    // signupButton.addActionListener(e -> {
    // // Open Signup JFrame
    // new Signup();
    // });
    // signupPanel.add(signupLabel);
    // signupPanel.add(signupButton);
    // mainPanel.add(signupPanel, BorderLayout.SOUTH);

    // // Add the header and main content to the frame
    // frame.add(headerPanel, BorderLayout.NORTH);
    // frame.add(mainPanel, BorderLayout.CENTER);

    // // Make the frame visible
    // frame.setVisible(true);
    // }
}
