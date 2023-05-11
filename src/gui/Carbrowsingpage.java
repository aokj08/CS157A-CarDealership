package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBManager;

public class CarBrowsingPage {

    private DBManager dbM;

    private JFrame frame;
    private JTable table;
    private JButton logoutButton;
    private JButton findDealershipButton;
    private JButton backButton;

    public CarBrowsingPage() {
        dbM = DBManager.getDBManager();
        // Create a JFrame
        frame = new JFrame("Car Browsing Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel for the header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Cars Available for Purchase or Lease");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a JTable to display the cars
        String[] columns = { "Vin", "Color", "Buy Price", "Lease Price", "Producer" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add some sample data to the table
        Object[] row1 = { "1", "Red", "$25,000", "$300/month", "Toyota" };
        Object[] row2 = { "2", "Blue", "$20,000", "$250/month", "Honda" };
        Object[] row3 = { "3", "Green", "$30,000", "$350/month", "Ford" };
        Object[] row4 = { "4", "Black", "$22,000", "$275/month", "Chevrolet" };
        Object[] row5 = { "5", "Silver", "$28,000", "$325/month", "BMW" };
        Object[] row6 = { "6", "White", "$24,000", "$290/month", "Mercedes-Benz" };
        model.addRow(row1);
        model.addRow(row2);
        model.addRow(row3);
        model.addRow(row4);
        model.addRow(row5);
        model.addRow(row6);

        ResultSet rs = dbM.query("SELECT * FROM Car;");
        try {
            while(rs.next()) { 
                Object[] row = {rs.getString("VIN"),
                                rs.getString("color"),
                                "$" + Integer.toString(rs.getInt("buy_price")),
                                "$" + Integer.toString(rs.getInt("lease_price")) + "/month",
                                rs.getString("producer")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

        // Create a JButton for finding dealership locations
        findDealershipButton = new JButton("Find Dealership");
        findDealershipButton.addActionListener(e -> {
            new DealershipLookupPage();
        });

        // Create a JButton for going back to the employeepage
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Employeepage();
            frame.dispose();
        });

        // Add the buttons to the buttonPanel
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(findDealershipButton);

        // Add the header, main content, and buttons to the frame
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(headerPanel, BorderLayout.NORTH);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}