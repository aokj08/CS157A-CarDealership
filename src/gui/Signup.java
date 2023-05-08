package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener {
    private JLabel nameLabel, emailLabel, passwordLabel, addressLabel, zipLabel, stateLabel, countryLabel, phoneLabel;
    private JTextField nameField, emailField, addressField, zipField, stateField, countryField, phoneField;
    private JPasswordField passwordField;
    private JButton signupButton, cancelButton;

    public Signup() {
        setTitle("Sign Up");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the center of the screen
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;

        // Calculate the top-left corner of the frame
        int frameX = centerX - (getWidth() / 2);
        int frameY = centerY - (getHeight() / 2);

        // Set the location of the frame
        setLocation(frameX, frameY);

        setLayout(new GridLayout(9, 2));

        nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);

        emailLabel = new JLabel("Email:");
        add(emailLabel);
        emailField = new JTextField();
        add(emailField);

        passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);

        addressLabel = new JLabel("Home Address:");
        add(addressLabel);
        addressField = new JTextField();
        add(addressField);

        zipLabel = new JLabel("Zip Code:");
        add(zipLabel);
        zipField = new JTextField();
        add(zipField);

        stateLabel = new JLabel("State:");
        add(stateLabel);
        stateField = new JTextField();
        add(stateField);

        countryLabel = new JLabel("Country:");
        add(countryLabel);
        countryField = new JTextField();
        add(countryField);

        phoneLabel = new JLabel("Phone Number:");
        add(phoneLabel);
        phoneField = new JTextField();
        add(phoneField);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        add(signupButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        add(cancelButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            char[] password = passwordField.getPassword();
            String address = addressField.getText();
            String zip = zipField.getText();
            String state = stateField.getText();
            String country = countryField.getText();
            String phone = phoneField.getText();

            // Perform sign up action here
            // ...

            JOptionPane.showMessageDialog(this,
                    "Your account has been successfully created!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}