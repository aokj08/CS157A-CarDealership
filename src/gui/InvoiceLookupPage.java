package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.DBManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceLookupPage {

    private JFrame frame;
    private JTable table;
    private JButton backButton;
    private JTextField searchField;

    public static int focusInvoice;

    private DBManager dbM;

    public InvoiceLookupPage() {

        dbM = DBManager.getDBManager();
        // Create a JFrame
        frame = new JFrame("Invoice Lookup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel for the header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Invoices Menu");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a JTable to display all invoice info
        String[] columns = { "Invoice ID", "Customer ID", "Purchase Type", "Quantity", "Unit Price", "Invoice Date" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        // Add some data to the table
        ResultSet rs = dbM.query("SELECT * FROM Invoice;");
        try {
            while(rs.next()) {
                int invoiceID = rs.getInt("invoiceID");
                int custID = rs.getInt("customerID_FK");
                String type = rs.getString("purchase_type");
                String date = rs.getString("date");
                int quantity = rs.getInt("quantity");
                int unit = rs.getInt("unit_price");
                Object[] data = {invoiceID, custID, type, quantity, unit, date};
                model.addRow(data);
                
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } 
        
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a JPanel for the search bar
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search By Specific Invoice ID");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusInvoice = Integer.parseInt(searchField.getText());
                new InvoiceSamplePage();
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Create a JPanel for the back button
        JPanel backPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main dealership list
                // Clear the search field
                searchField.setText("");
                // Update the table with all the dealerships
                model.setRowCount(0); // clear the table
                // Open the carbrowsing tab
                new Carbrowsingpage();
                // Close the current page
                frame.dispose();
            }
        });
        backPanel.add(backButton);
        mainPanel.add(backPanel, BorderLayout.SOUTH);

        // Add the header and main content to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
