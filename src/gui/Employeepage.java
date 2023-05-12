package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import database.DBManager;

public class Employeepage {

    private DBManager dbM;

    private JFrame frame;
    private JPanel panel;
    private JButton addButton;
    private JButton addSoldButton;
    private JButton logoutButton;
    private JButton viewAvailableButton;
    private JButton viewSoldLeasedButton;

    public Employeepage() {
        createGUI();
    }

    private void createGUI() {
        dbM = DBManager.getDBManager();
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

        addSoldButton = new JButton("Sell/Lease Car");
        addSoldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for adding a new car
                JFrame addCarFrame = new JFrame("Sell/Lease Car");
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

                JLabel customerIDLabel = new JLabel("Customer ID:");
                JTextField customerIDField = new JTextField();
                formPanel.add(customerIDLabel);
                formPanel.add(customerIDField);

                JLabel purchaseTypeLabel = new JLabel("Purchase Type:");
                JComboBox<String> typeDropdown = new JComboBox<>(new String[] { "Sell", "Lease" });
                formPanel.add(purchaseTypeLabel);
                formPanel.add(typeDropdown);

                JLabel quantityLabel = new JLabel("Quantity:");
                JTextField quantityField = new JTextField();
                formPanel.add(quantityLabel);
                formPanel.add(quantityField);

                // Add the form panel to the JFrame
                addCarFrame.add(formPanel, BorderLayout.CENTER);

                // Add a button for submitting the form
                JButton submitButton = new JButton("Confirm");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the values from the form fields
                        String vin = vinField.getText();
                        String customerID = customerIDField.getText();
                        // Integer buyPrice = Integer.parseInt(buyPriceField.getText());
                        // Integer leasePrice = Integer.parseInt(leasePriceField.getText());
                        String quantity = quantityField.getText();

                        // Get the car information from Car and put it into Invoice.
                        ResultSet rs = dbM.query(String.format("SELECT * FROM Car WHERE VIN = %d;", Integer.valueOf(vin)));
                        boolean success = false;
                        try {
                            while(rs.next()) {
                                int price = 0;
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = dateFormat.format(new Date());
                                if(typeDropdown.getSelectedItem() == "Sold") {
                                    price = rs.getInt("buy_price");
                                }
                                else {
                                    price = rs.getInt("lease_price");
                                }
                                dbM.queryQuiet(String.format("INSERT INTO Invoice(customerID_FK, purchase_type, quantity, unit_price, date)" +
                                " values(%d,'%s',%d,%d,'%s');", 
                                Integer.valueOf(customerID), typeDropdown.getSelectedItem(), Integer.valueOf(quantity), price, dateString));
                                success = true;
                                // Update Car table
                                dbM.queryQuiet(String.format("UPDATE Car SET customerID_FK = %d WHERE VIN = '%s';", Integer.valueOf(customerID), vin));
                            }

                            if(success) {
                                //Success message
                                JOptionPane.showMessageDialog(addCarFrame, "Transaction succeeded!");
                            }
                            else
                                // Display a failure message
                                JOptionPane.showMessageDialog(addCarFrame, "Transaction failed!");
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

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

        panel.add(addSoldButton);

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
                frame = new JFrame("Car Browsing Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); // Center the window on the screen

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
        String[] columnNames = { "Invoice ID", "Customer ID", "Purchase Type", "Quantity", "Unit Price", "Invoice Date" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        ResultSet rs = dbM.query("SELECT * FROM Invoice;");
        try {
            while(rs.next()) {
                int invoiceID = rs.getInt("invoiceID");
                int custID = rs.getInt("customerID_FK");
                String type = rs.getString("purchase_type");
                int quan = rs.getInt("quantity");
                int unit = rs.getInt("unit_price");
                String date = rs.getString("date");
                Object[] data = {invoiceID, custID, type, quan, unit, date};
                model.addRow(data);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

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