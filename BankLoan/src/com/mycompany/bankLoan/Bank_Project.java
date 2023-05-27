package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Bank_Project extends JFrame {
    private JLabel nameLabel, accNumberLabel, pictureLabel;
    private JMenuItem createItem, historyItem, viewProfileItem, bankTransferItem, payBillItem, performTransactionItem;
    private JButton loanItem, savingsItem;

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
        savingsItem = new JButton("Savings");

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
        savingsItem.setFont(font);

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
        constraints.gridx++;
        mainPanel.add(savingsItem, constraints);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        loanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoanMenu loanMenu = new LoanMenu();
                getContentPane().removeAll();
                getContentPane().add(topPanel, BorderLayout.NORTH);
                getContentPane().add(loanMenu, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        savingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavingsMenu savingsMenu = new SavingsMenu();
                getContentPane().removeAll(); // Remove all components from the content pane
                getContentPane().setLayout(new BorderLayout()); // Set the desired layout for the content pane
                getContentPane().add(topPanel, BorderLayout.NORTH);
                getContentPane().add(savingsMenu, BorderLayout.CENTER);
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
                    "jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println(e);
        }

        Bank_Project frame = new Bank_Project();
        frame.setSize(1080, 720); // Set window size to 1080 x 720 pixels
        frame.setVisible(true);
    }

}
