package gui;

import javax.swing.*;

import database.DBManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceSamplePage {

    private JFrame frame;
    private JButton backButton;

    private javax.swing.JLabel customerID;
    private javax.swing.JLabel add;
    private javax.swing.JLabel city;
    private javax.swing.JLabel state;
    private javax.swing.JLabel zip;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel email;
    private javax.swing.JLabel invoiceID;
    private javax.swing.JLabel invoiceDate;
    private javax.swing.JLabel amountDue;

    private javax.swing.JLabel billTo;
    private javax.swing.JLabel invoiceInfo;
    private javax.swing.JLabel carsLabel;

    private DBManager dbM;


    public InvoiceSamplePage() {

        dbM = DBManager.getDBManager();
        // Create a JFrame
        frame = new JFrame("Invoice Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a JPanel for the header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Invoice");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        //all the text fields to hold customer and invoice data
        billTo = new JLabel("Bill To");
        billTo.setFont(new Font("Arial", Font.BOLD, 18));
        billTo.setBounds(50, 25, 150, 50);

        customerID = new JLabel("Customer ID: ");
        customerID.setFont(new Font("Arial", Font.PLAIN, 18));
        customerID.setBounds(50, 50, 150, 50);

        add = new JLabel("Address: ");
        add.setFont(new Font("Arial", Font.PLAIN, 18));
        add.setBounds(50, 75, 150, 50);

        city = new JLabel("City: ");
        city.setFont(new Font("Arial", Font.PLAIN, 18));
        city.setBounds(50, 100, 150, 50);

        state = new JLabel("State: ");
        state.setFont(new Font("Arial", Font.PLAIN, 18));
        state.setBounds(50, 125, 150, 50);

        zip = new JLabel("Zip Code: ");
        zip.setFont(new Font("Arial", Font.PLAIN, 18));
        zip.setBounds(50, 150, 150, 50);

        phone = new JLabel("Phone Number: ");
        phone.setFont(new Font("Arial", Font.PLAIN, 18));
        phone.setBounds(50, 175, 150, 50);

        email = new JLabel("Email: ");
        email.setFont(new Font("Arial", Font.PLAIN, 18));
        email.setBounds(50, 200, 150, 50); 

        //labels for invoice information
        invoiceInfo = new JLabel("Invoice Information");
        invoiceInfo.setFont(new Font("Arial", Font.BOLD, 18));
        invoiceInfo.setBounds(300, 25, 300, 50);

        invoiceID = new JLabel("Invoice ID: ");
        invoiceID.setFont(new Font("Arial", Font.PLAIN, 18));
        invoiceID.setBounds(300, 75, 300, 50);

        invoiceDate = new JLabel("Invoice Date: ");
        invoiceDate.setFont(new Font("Arial", Font.PLAIN, 18));
        invoiceDate.setBounds(300, 125, 300, 50);

        amountDue = new JLabel("Amount Due: ");
        amountDue.setFont(new Font("Arial", Font.PLAIN, 18));
        amountDue.setBounds(300, 175, 300, 50); 

        //label and combo box for car information
        carsLabel = new JLabel("Cars");
        carsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        carsLabel.setBounds(600, 25, 150, 50);


        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a JTable to display all invoice info
        ArrayList<String> vins = new ArrayList<>();

        // Add some data to the variables
        ResultSet rs = dbM.query(String.format("SELECT VIN FROM Car WHERE invoiceID_FK = %d;", InvoiceLookupPage.focusInvoice));
                try {
                    while(rs.next()) {
                         String vin = rs.getString("VIN");
                        
                        vins.add(vin);
                        
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
        
        javax.swing.JComboBox<String> cars = new javax.swing.JComboBox<>();
        for (int i = 0; i < vins.size(); i++)
        {
            cars.addItem(vins.get(i));
        }
        cars.setBounds(600, 75, 100, 25);
        frame.add(cars);
       

        //edit the text in all the JLabels with the information from customer and invoice queries, then add them to the frame
        frame.add(billTo);
        frame.add(customerID);
        frame.add(add);
        frame.add(city);
        frame.add(state);
        frame.add(zip);
        frame.add(phone); 
        frame.add(email);
        frame.add(invoiceInfo); 
        frame.add(invoiceID); 
        frame.add(invoiceDate); 
        frame.add(amountDue);
        frame.add(carsLabel); 

        JPanel backPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

public static void main(String[] args)
{
    InvoiceSamplePage trial = new InvoiceSamplePage();
}

}
