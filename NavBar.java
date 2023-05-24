/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.navbar;

/**
 *
 * @author Simoun
 */
import javax.swing.*;
import java.awt.*;

public class NavBar extends JFrame {
    public NavBar() {
        setTitle("Bank System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        getContentPane().setBackground(Color.decode("#FFFFFF"));

        // Color Theme
        Color primaryColor = Color.decode("#005176");
        Color secondaryColor = Color.decode("#4F93D2");
        Color tertiaryColor = Color.decode("#5CBFE9");
        Color whiteColor = Color.decode("#FDFFFF");
        Color backgroundColor = Color.decode("#FFFFFF");

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(primaryColor);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Navigation");
        titleLabel.setForeground(whiteColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        sidebarPanel.add(titleLabel);

        // Create navigation labels
        String[] navLabels = {"Client", "Send/Receive", "Checking Accounts", "Transactions",
                "Loans", "Credit Cards", "Bill Payments", "Investments"};

        // Creating a navBox for "Checking Accounts" to get its preferred size
        JPanel referenceBox = new JPanel();
        referenceBox.setLayout(new BorderLayout());
        referenceBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel referenceLabel = new JLabel("Checking Accounts");
        referenceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        referenceBox.add(referenceLabel, BorderLayout.CENTER);
        Dimension referenceSize = referenceBox.getPreferredSize();

        for (String label : navLabels) {
            JPanel navBox = new JPanel();
            navBox.setBackground(secondaryColor);
            navBox.setLayout(new BorderLayout());
            navBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            navBox.setPreferredSize(referenceSize); // Set the preferred size to the referenceSize

            JLabel navLabel = new JLabel(label);
            navLabel.setForeground(whiteColor);
            navLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            navBox.add(navLabel, BorderLayout.CENTER);

            JPanel boxContainer = new JPanel();
            boxContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
            boxContainer.setBackground(primaryColor);
            boxContainer.add(navBox);

            sidebarPanel.add(boxContainer);
        }

        JScrollPane scrollPane = new JScrollPane(sidebarPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(scrollPane, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NavBar().setVisible(true);
            }
        });
    }
}










