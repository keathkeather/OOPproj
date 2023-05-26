/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AssignedFeature;

/**
 *
 * @author Simoun Tompar
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;


public class CreditCard extends JFrame {

    private CreditCardDatabaseManager dbManager = new CreditCardDatabaseManager();

    public CreditCard() {
        setTitle("Credit Card Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        getContentPane().setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Card ID");
        tableModel.addColumn("Card Number");
        tableModel.addColumn("Credit Limit");
        tableModel.addColumn("Interest Rate");
        tableModel.addColumn("Payment Due Date");

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        StyledButton addCardButton = new StyledButton("Add New Credit Card");
        addCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddCreditCardDialog(tableModel);
            }
        });
        buttonPanel.add(addCardButton);

        StyledButton updateCardButton = new StyledButton("Update Credit Card");
        updateCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int cardID = (int) table.getValueAt(selectedRow, 0);
                    showUpdateCreditCardDialog(cardID, tableModel);
                } else {
                    JOptionPane.showMessageDialog(CreditCard.this, "Please select a credit card to update.", "No Credit Card Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(updateCardButton);

        StyledButton deleteCardButton = new StyledButton("Delete Credit Card");
        deleteCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int cardID = (int) table.getValueAt(selectedRow, 0);
                    deleteCreditCard(cardID, tableModel);
                } else {
                    JOptionPane.showMessageDialog(CreditCard.this, "Please select a credit card to delete.", "No Credit Card Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteCardButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        fetchCreditCardData(tableModel);

        NavBar navBar = new NavBar();
        JScrollPane navBarScrollPane = new JScrollPane(navBar);
        navBarScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(navBarScrollPane, BorderLayout.WEST);
    }

    private void fetchCreditCardData(DefaultTableModel tableModel) {
        try {
            ResultSet resultSet = dbManager.fetchCreditCardData();
            while (resultSet.next()) {
                int cardID = resultSet.getInt("cardID");
                String cardNumber = resultSet.getString("cardNumber");
                double creditLimit = resultSet.getDouble("creditLimit");
                double interestRate = resultSet.getDouble("interestRate");
                String paymentDueDate = resultSet.getString("paymentDueDate");

                Object[] row = {cardID, cardNumber, creditLimit, interestRate, paymentDueDate};
                tableModel.addRow(row);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAddCreditCardDialog(DefaultTableModel tableModel) {
        JDialog dialog = new JDialog(this, "Add New Credit Card", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField();
        JLabel creditLimitLabel = new JLabel("Credit Limit:");
        JTextField creditLimitField = new JTextField();
        JLabel interestRateLabel = new JLabel("Interest Rate:");
        JTextField interestRateField = new JTextField();
        JLabel paymentDueDateLabel = new JLabel("Payment Due Date:");
        JTextField paymentDueDateField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String cardNumber = cardNumberField.getText();
            double creditLimit;
            double interestRate;
            try {
                creditLimit = Double.parseDouble(creditLimitField.getText());
                interestRate = Double.parseDouble(interestRateField.getText());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(dialog, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (creditLimit < 0 || interestRate < 0) {
                JOptionPane.showMessageDialog(dialog, "Credit Limit and Interest Rate cannot be negative.", "Invalid input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String paymentDueDate = paymentDueDateField.getText();
            addCreditCard(cardNumber, creditLimit, interestRate, paymentDueDate, tableModel);
            dialog.dispose();
        }
    });

        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(creditLimitLabel);
        panel.add(creditLimitField);
        panel.add(interestRateLabel);
        panel.add(interestRateField);
        panel.add(paymentDueDateLabel);
        panel.add(paymentDueDateField);
        panel.add(new JLabel()); 
        panel.add(addButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showUpdateCreditCardDialog(int cardID, DefaultTableModel tableModel) {
    try {
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM creditcard WHERE cardID = ?");
        statement.setInt(1, cardID);
        ResultSet resultSet = dbManager.getCreditCard(cardID);

        if (resultSet.next()) {
            String cardNumber = resultSet.getString("cardNumber");
            double creditLimit = resultSet.getDouble("creditLimit");
            double interestRate = resultSet.getDouble("interestRate");
            String paymentDueDate = resultSet.getString("paymentDueDate");

            JDialog dialog = new JDialog(this, "Update Credit Card", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel cardNumberLabel = new JLabel("Card Number:");
            JTextField cardNumberField = new JTextField(cardNumber);
            JLabel creditLimitLabel = new JLabel("Credit Limit:");
            JTextField creditLimitField = new JTextField(String.valueOf(creditLimit));
            JLabel interestRateLabel = new JLabel("Interest Rate:");
            JTextField interestRateField = new JTextField(String.valueOf(interestRate));
            JLabel paymentDueDateLabel = new JLabel("Payment Due Date:");
            JTextField paymentDueDateField = new JTextField(paymentDueDate);

            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String newCardNumber = cardNumberField.getText();
                    double newCreditLimit;
                double newInterestRate;
                try {
                    newCreditLimit = Double.parseDouble(creditLimitField.getText());
                    newInterestRate = Double.parseDouble(interestRateField.getText());
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(dialog, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }
                String newPaymentDueDate = paymentDueDateField.getText();

                updateCreditCard(cardID, newCardNumber, newCreditLimit, newInterestRate, newPaymentDueDate, tableModel);

                dialog.dispose();
            }
        });

            panel.add(cardNumberLabel);
            panel.add(cardNumberField);
            panel.add(creditLimitLabel);
            panel.add(creditLimitField);
            panel.add(interestRateLabel);
            panel.add(interestRateField);
            panel.add(paymentDueDateLabel);
            panel.add(paymentDueDateField);
            panel.add(new JLabel()); 
            panel.add(updateButton);

            dialog.add(panel);
            dialog.setVisible(true);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void addCreditCard(String cardNumber, double creditLimit, double interestRate, String paymentDueDate, DefaultTableModel tableModel) {
        dbManager.addCreditCard(cardNumber, creditLimit, interestRate, paymentDueDate);
        tableModel.setRowCount(0);
        fetchCreditCardData(tableModel);
    }

    private void updateCreditCard(int cardID, String cardNumber, double creditLimit, double interestRate, String paymentDueDate, DefaultTableModel tableModel) {
        dbManager.updateCreditCard(cardID, cardNumber, creditLimit, interestRate, paymentDueDate);
        tableModel.setRowCount(0);
        fetchCreditCardData(tableModel);
    }

    private void deleteCreditCard(int cardID, DefaultTableModel tableModel) {
        dbManager.deleteCreditCard(cardID);
        tableModel.setRowCount(0);
        fetchCreditCardData(tableModel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreditCard().setVisible(true);
            }
        });
    }
}