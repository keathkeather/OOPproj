package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Bank_Project extends JFrame {
    private JLabel nameLabel, accNumberLabel, pictureLabel;
    private JMenuItem createItem, historyItem, viewProfileItem, bankTransferItem, payBillItem, performTransactionItem;
    private JButton loanItem;

    public Bank_Project() {
        setTitle("Bankrupt Bank App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel userInfoPanel = new JPanel(new GridLayout(3, 1));
        nameLabel = new JLabel("John Wick", SwingConstants.RIGHT);
        accNumberLabel = new JLabel("Account Number: 0912345678", SwingConstants.RIGHT);
        pictureLabel = new JLabel(new ImageIcon("user_picture.jpg"), SwingConstants.RIGHT);
        userInfoPanel.add(pictureLabel);
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(accNumberLabel);
        topPanel.add(userInfoPanel, BorderLayout.EAST);

        createItem = new JMenuItem("Create New Account");
        historyItem = new JMenuItem("History of Transactions");
        viewProfileItem = new JMenuItem("View Profile Account");
        bankTransferItem = new JMenuItem("Bank Transfer");
        payBillItem = new JMenuItem("Pay Bill");
        performTransactionItem = new JMenuItem("Perform Transactions");
        loanItem = new JButton("Loan");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.33;
        constraints.weighty = 0.33;

        Font font = new Font("Arial", Font.PLAIN, 12);
        createItem.setFont(font);
        historyItem.setFont(font);
        viewProfileItem.setFont(font);
        bankTransferItem.setFont(font);
        payBillItem.setFont(font);
        performTransactionItem.setFont(font);
        loanItem.setFont(font);

        mainPanel.add(createItem, constraints);
        constraints.gridx++;
        mainPanel.add(historyItem, constraints);
        constraints.gridx++;
        mainPanel.add(viewProfileItem, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        mainPanel.add(bankTransferItem, constraints);
        constraints.gridx++;
        mainPanel.add(payBillItem, constraints);
        constraints.gridx++;
        mainPanel.add(performTransactionItem, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        mainPanel.add(loanItem, constraints);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        loanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankLoan loanPanel = new BankLoan();
                getContentPane().removeAll();
                getContentPane().add(topPanel, BorderLayout.NORTH);
                getContentPane().add(loanPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankrupt", "username", "password");
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println(e);
        }

        new Bank_Project();
    }
}
