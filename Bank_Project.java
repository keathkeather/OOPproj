/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bank_project;

/**
 *
 * @author Simoun
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;

//Menu Page wla pa kaau ni design pero ga use kog gridbag para ma sa mga menuItems na layout.
public class Bank_Project extends JFrame {
    private JLabel nameLabel, accNumberLabel, pictureLabel;
    private JMenuItem createItem, historyItem, viewProfileItem, bankTransferItem, payBillItem, performTransactionItem;

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

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

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








