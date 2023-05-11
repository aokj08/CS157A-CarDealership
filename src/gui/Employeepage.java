package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.DBManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Employeepage {
    private JFrame frame;
    private JPanel panel;
    private JButton addButton;
    private JButton logoutButton;
    private JButton viewAvailableButton;
    private JButton viewSoldLeasedButton;

    public Employeepage() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Employee Page");
        frame.setSize(600, 400);
        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("Welcome back, Employee!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(welcomeLabel, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        addButton = new JButton("Add New Cars");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for adding a new car
                JFrame addCarFrame = new JFrame("Add New Car");
                addCarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addCarFrame.setSize(400, 300);
                // Center the frame on the screen
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) ((screenSize.getWidth() - addCarFrame.getWidth()) / 2);
                int centerY = (int) ((screenSize.getHeight() - addCarFrame.getHeight()) / 2);
                addCarFrame.setLocation(centerX, centerY);

                // Create a JPanel for the form fields
                JPanel formPanel = new JPanel();
                formPanel.setLayout(new GridLayout(6, 2));

                // Add form fields to the panel
                JLabel vinLabel = new JLabel("VIN:");
                JTextField vinField = new JTextField();
                formPanel.add(vinLabel);
                formPanel.add(vinField);

                JLabel colorLabel = new JLabel("Color:");
                JTextField colorField = new JTextField();
                formPanel.add(colorLabel);
                formPanel.add(colorField);

                JLabel buyPriceLabel = new JLabel("Buy Price:");
                JTextField buyPriceField = new JTextField();
                formPanel.add(buyPriceLabel);
                formPanel.add(buyPriceField);

                JLabel leasePriceLabel = new JLabel("Lease Price:");
                JTextField leasePriceField = new JTextField();
                formPanel.add(leasePriceLabel);
                formPanel.add(leasePriceField);

                JLabel producerLabel = new JLabel("Producer:");
                JTextField producerField = new JTextField();
                formPanel.add(producerLabel);
                formPanel.add(producerField);

                // Add the form panel to the JFrame
                addCarFrame.add(formPanel, BorderLayout.CENTER);

                // Add a button for submitting the form
                JButton submitButton = new JButton("Add Car");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the values from the form fields
                        String vin = vinField.getText();
                        String color = colorField.getText();
                        Integer buyPrice = Integer.parseInt(buyPriceField.getText());
                        Integer leasePrice = Integer.parseInt(leasePriceField.getText());
                        String producer = producerField.getText();

                        // Add the new car to the database
                        DBManager.getDBManager().queryQuiet(String.format(
                                "INSERT INTO Car(VIN, color, buy_price, lease_price, producer) values('%s', '%s', %d, %d, '%s');",
                                vin, color, buyPrice, leasePrice, producer));

                        // Display a success message
                        JOptionPane.showMessageDialog(addCarFrame, "Car added successfully!");

                        // Dispose of the current frame
                        addCarFrame.dispose();
                    }
                });
                // Add a button for cancelling the form
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Dispose of the current frame and show the Employee page
                        addCarFrame.dispose();
                        frame.setVisible(true);
                    }
                });

                // Add the form panel, submit button, and cancel button to a new JPanel
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout());
                buttonPanel.add(submitButton);
                buttonPanel.add(cancelButton);

                // Add the form panel and button panel to the frame
                addCarFrame.add(formPanel, BorderLayout.CENTER);
                addCarFrame.add(buttonPanel, BorderLayout.SOUTH);

                // Make the JFrame visible
                addCarFrame.setVisible(true);

            }
        });

        panel.add(addButton);

        viewAvailableButton = new JButton("View Available Cars");
        viewAvailableButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Create new instance of DealershipLookupPage
                CarBrowsingPage Carbrowsingpage = new CarBrowsingPage();
                // Close current frame
                frame.dispose();
            }
        });
        panel.add(viewAvailableButton);

        viewSoldLeasedButton = new JButton("View Sold/Leased Cars");
        viewSoldLeasedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame
                JFrame soldLeasedFrame = new JFrame("Sold/Leased Cars");
                soldLeasedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                soldLeasedFrame.setSize(600, 400);
                // Center the frame on the screen
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) ((screenSize.getWidth() - soldLeasedFrame.getWidth()) / 2);
                int centerY = (int) ((screenSize.getHeight() - soldLeasedFrame.getHeight()) / 2);
                soldLeasedFrame.setLocation(centerX, centerY);
                // Create a new JTable with the relevant columns
                String[] columnNames = { "Invoice ID", "Customer ID", "Purchase Type", "Quantity", "Unit Price",
                        "Invoice Date" };
                Object[][] data = {
                        { 1, 101, "Lease", 2, 58000.00, "2023-05-01" },
                        { 2, 102, "Sold", 1, 48000.99, "2022-04-28" },
                        { 3, 103, "Sold", 3, 78000.45, "2019-04-15" },
                        { 4, 104, "Lease", 1, 42000.79, "2023-03-30" }
                };
                JTable table = new JTable(data, columnNames);

                // Add the JTable to a JScrollPane and add the scroll pane to the JFrame
                JScrollPane scrollPane = new JScrollPane(table);
                soldLeasedFrame.add(scrollPane);

                // Add a back button to the frame
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Dispose of the current frame
                        soldLeasedFrame.dispose();

                        // Create a new EmployeePage and make it visible
                        createGUI();
                    }
                });
                soldLeasedFrame.add(backButton, BorderLayout.SOUTH);

                // Make the JFrame visible
                soldLeasedFrame.setVisible(true);
            }
        });

        panel.add(viewSoldLeasedButton);

        // Create a JButton for logging out
        logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Homepage JFrame
                new Homepage();
                // Close the Carbrowsingpage JFrame
                frame.dispose();
            }
        });

        panel.add(logoutButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Employeepage employeePage = new Employeepage();
    }
}