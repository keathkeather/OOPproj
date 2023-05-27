/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankLoan;

/**
 *
 * @author Simoun
 */
import javax.swing.*;
import java.awt.*;

public class NavBar extends JPanel {
    public NavBar() {
        setBackground(Color.decode("#005176"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Navigation");
        titleLabel.setForeground(Color.decode("#FDFFFF"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        add(titleLabel);

        String[] navLabels = { "Client", "Send/Receive", "Checking Accounts", "Transactions",
                "Loans", "Credit Cards", "Bill Payments", "Investments" };

        JPanel referenceBox = new JPanel();
        referenceBox.setLayout(new BorderLayout());
        referenceBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel referenceLabel = new JLabel("Checking Accounts");
        referenceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        referenceBox.add(referenceLabel, BorderLayout.CENTER);
        Dimension referenceSize = referenceBox.getPreferredSize();
        referenceSize.width += 35;

        for (String label : navLabels) {
            JPanel navBox = new JPanel();
            navBox.setBackground(Color.decode("#4F93D2"));
            navBox.setLayout(new BorderLayout());
            navBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            navBox.setPreferredSize(referenceSize);

            JButton navButton = new JButton(label);
            navButton.setForeground(Color.decode("#FDFFFF"));
            navButton.setFont(new Font("Arial", Font.PLAIN, 14));
            navButton.setBackground(Color.decode("#4F93D2"));
            navButton.setBorderPainted(false);
            navButton.setFocusPainted(false);
            navBox.add(navButton, BorderLayout.CENTER);

            JPanel boxContainer = new JPanel();
            boxContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
            boxContainer.setBackground(Color.decode("#005176"));
            boxContainer.add(navBox);

            add(boxContainer);
        }

        setPreferredSize(new Dimension(200, 600));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new NavBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
