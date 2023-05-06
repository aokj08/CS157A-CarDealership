package gui;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        // Set the frame properties
        setTitle("My App Home Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTabbedPane container to hold the tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create a JPanel for the invoices tab
        JPanel invoicePanel = new JPanel();
        invoicePanel.setLayout(new BorderLayout());
        invoicePanel.setBackground(Color.WHITE);
        JLabel invoiceLabel = new JLabel("This is the Invoices tab.");
        invoiceLabel.setHorizontalAlignment(JLabel.CENTER);
        invoicePanel.add(invoiceLabel, BorderLayout.CENTER);

        // Create a JPanel for the browsing tab
        JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());
        browsePanel.setBackground(Color.WHITE);
        JLabel browseLabel = new JLabel("This is the Browse tab.");
        browseLabel.setHorizontalAlignment(JLabel.CENTER);
        browsePanel.add(browseLabel, BorderLayout.CENTER);

        // Add the tabs to the tabbedPane
        tabbedPane.addTab("Invoices", invoicePanel);
        tabbedPane.addTab("Browse", browsePanel);

        // Add the tabbedPane to the frame
        add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

}
