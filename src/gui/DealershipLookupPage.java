package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealershipLookupPage {

    private JFrame frame;
    private JTable table;
    private JButton backButton;
    private JTextField searchField;
    private Object[] row1, row2, row3;

    public DealershipLookupPage() {
        // Create a JFrame
        frame = new JFrame("Dealerships in Bay Area");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel for the header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Dealerships in the Bay Area");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a JTable to display the dealerships
        String[] columns = { "Dealership ID", "Street Address", "City", "Zip Code", "State", "Country" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add some sample data to the table
        row1 = new Object[] { "1", "123 Main St", "San Francisco", "94102", "CA", "USA" };
        row2 = new Object[] { "2", "456 Oak St", "San Jose", "95134", "CA", "USA" };
        row3 = new Object[] { "3", "789 Elm St", "Oakland", "94601", "CA", "USA" };
        model.addRow(row1);
        model.addRow(row2);
        model.addRow(row3);

        // Create a JPanel for the search bar
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search ZIP Code");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zipCode = searchField.getText();
                // Update the table with the search results
                model.setRowCount(0); // clear the table
                Object[] row = { "4", "789 Maple St", "Sunnyvale", "94086", "CA", "USA" };
                model.addRow(row);
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
                model.addRow(row1);
                model.addRow(row2);
                model.addRow(row3);
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
