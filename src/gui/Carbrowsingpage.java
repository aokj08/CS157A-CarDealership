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

        ResultSet rs = dbM.query("SELECT * FROM Car WHERE customerID_FK IS NULL;");
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