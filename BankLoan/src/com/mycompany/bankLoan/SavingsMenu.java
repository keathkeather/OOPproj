package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsMenu extends JPanel {
    private JTextArea balanceTextArea;

    public SavingsMenu() {
        setLayout(new BorderLayout());

        // Create the components
        balanceTextArea = new JTextArea();
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");

        // Add components to the panel
        add(balanceTextArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(transferButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the withdraw action
                performWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the deposit action
                performDeposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the transfer action
                performTransfer();
            }
        });
    }

    private void performWithdraw() {
        // Implement your withdraw logic here
        // You can access the balance from the database and update the balanceTextArea
        // accordingly
        // Example:
        double withdrawalAmount = 100.0; // Replace with the actual withdrawal amount from your logic
        double currentBalance = Double.parseDouble(balanceTextArea.getText().replace("Savings Balance: ", ""));
        double newBalance = currentBalance - withdrawalAmount;
        balanceTextArea.setText("Savings Balance: " + newBalance);
    }

    private void performDeposit() {
        // Implement your deposit logic here
        // You can access the balance from the database and update the balanceTextArea
        // accordingly
        // Example:
        double depositAmount = 200.0; // Replace with the actual deposit amount from your logic
        double currentBalance = Double.parseDouble(balanceTextArea.getText().replace("Savings Balance: ", ""));
        double newBalance = currentBalance + depositAmount;
        balanceTextArea.setText("Savings Balance: " + newBalance);
    }

    private void performTransfer() {
        // Implement your transfer logic here
        // You can access the balance from the database and update the balanceTextArea
        // accordingly
        // Example:
        double transferAmount = 300.0; // Replace with the actual transfer amount from your logic
        double currentBalance = Double.parseDouble(balanceTextArea.getText().replace("Savings Balance: ", ""));
        double newBalance = currentBalance - transferAmount;
        balanceTextArea.setText("Savings Balance: " + newBalance);
    }
}
