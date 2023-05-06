package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class SignUpUI extends JFrame {

    private JLabel titleLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signUpButton;
    private JButton cancelButton;

    public SignUpUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(229, 229, 229));

        titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Tahoma", 1, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);

        signUpButton = new JButton("Sign Up");
        cancelButton = new JButton("Cancel");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.bottom = 20;
        add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets.bottom = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets.top = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        gbc.gridy++;
        gbc.insets.top = 5;
        add(cancelButton, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
