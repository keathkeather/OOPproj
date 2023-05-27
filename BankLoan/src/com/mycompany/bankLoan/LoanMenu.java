// Bataluna, Main Loan Page

package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoanMenu extends JPanel {
    private Rounded.RoundedTextArea remainingBalField;
    private Rounded.RoundedButton applyLoanButton, payLoanButton, moreDetailsButton;
    private JPanel contentPanel, buttonPanel, buttonPanel2, textareaPanel;
    private JLabel loanInfoLabel;
    private NavBar navBar;

    GridBagConstraints gbc;

    public LoanMenu() {
        setLayout(new BorderLayout());

        navBar = new NavBar();
        add(navBar, BorderLayout.WEST);

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.decode("#5cbfe9"));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        loanInfoLabel = new JLabel("Loan Information");
        loanInfoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(loanInfoLabel, gbc);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);

        moreDetailsButton = new Rounded.RoundedButton("More Details", 10);
        moreDetailsButton.addActionListener(new MoreDetailsButtonListener());
        buttonPanel.add(moreDetailsButton);

        moreDetailsButton.setPreferredSize(new Dimension(130, 30));
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        contentPanel.add(buttonPanel, gbc);

        textareaPanel = new JPanel(new BorderLayout());
        textareaPanel.setOpaque(false);

        remainingBalField = new Rounded.RoundedTextArea(4, 20, 20, 5);
        remainingBalField.setEditable(false);
        remainingBalField.setFont(new Font("Arial", Font.BOLD, 16));
        remainingBalField.setLineWrap(true);
        remainingBalField.setWrapStyleWord(true);

        textareaPanel.add(remainingBalField, BorderLayout.CENTER);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, -100, 10, -10);
        contentPanel.add(textareaPanel, gbc);

        displayRemainingBalance();

        buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel2.setOpaque(false);

        applyLoanButton = new Rounded.RoundedButton("Apply Now", 10);
        applyLoanButton.addActionListener(new ApplyLoanButtonListener());
        buttonPanel2.add(applyLoanButton);

        applyLoanButton.setPreferredSize(new Dimension(130, 30));
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;

        payLoanButton = new Rounded.RoundedButton("Pay Loan", 10);
        payLoanButton.addActionListener(new PayLoanButtonListener());
        buttonPanel2.add(payLoanButton);

        payLoanButton.setPreferredSize(new Dimension(130, 30));
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        contentPanel.add(buttonPanel2, gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    public void displayRemainingBalance() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT remainingBal FROM loan");

            if (resultSet.next()) {

                double remainingBal = resultSet.getDouble("remainingBal");

                String formattedRemainingBal = String.format("%.2f", remainingBal);

                String loanBalance = "\n                          " + formattedRemainingBal
                        + "\n                 Remaining Balance";

                remainingBalField.setText(loanBalance);
            } else {
                String noLoanMessage = "\n    You have no current loan. Apply \n     now with 3% interest rate only!";
                remainingBalField.setText(noLoanMessage);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class ApplyLoanButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT remainingBal FROM loan");

                // Retrieve the current accountID from the account table
                int currentCustomerID = getCurrentCustomerID(connection);

                // Check if the current accountID exists in the loan table
                boolean hasActiveLoan = checkActiveLoan(connection, currentCustomerID);

                if (!resultSet.next()) {
                    // No entry in the database, redirect to BankLoan so a loan can be applied
                    JFrame parentFrame = (JFrame) SwingUtilities.getRoot(LoanMenu.this);
                    parentFrame.getContentPane().removeAll();
                    parentFrame.getContentPane().add(new BankLoan(), BorderLayout.CENTER);
                    parentFrame.revalidate();
                    parentFrame.repaint();
                } else {
                    double remainingBal = resultSet.getDouble("remainingBal");
                    if (hasActiveLoan && remainingBal > 0) {
                        JOptionPane.showMessageDialog(LoanMenu.this,
                                "Sorry, you cannot apply for a loan yet. Please fully pay your current loan balance.",
                                "Loan Application Denied",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (hasActiveLoan && remainingBal <= 0) {
                        // Delete values in the loan table
                        deleteLoanRecords();

                        JFrame parentFrame = (JFrame) SwingUtilities.getRoot(LoanMenu.this);
                        parentFrame.getContentPane().removeAll();
                        parentFrame.getContentPane().add(new BankLoan(), BorderLayout.CENTER);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    } else {
                        JFrame parentFrame = (JFrame) SwingUtilities.getRoot(LoanMenu.this);
                        parentFrame.getContentPane().removeAll();
                        parentFrame.getContentPane().add(new BankLoan(), BorderLayout.CENTER);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    }
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        private int getCurrentCustomerID(Connection connection) throws SQLException {
            int customerID = -1; // Default value if accountID is not found

            String query = "SELECT customerID FROM account";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                customerID = resultSet.getInt("customerID");
            }

            resultSet.close();
            statement.close();

            return customerID;
        }

        private boolean checkActiveLoan(Connection connection, int customerID) throws SQLException {
            String query = "SELECT customerID FROM loan WHERE customerID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerID);

            ResultSet resultSet = statement.executeQuery();
            boolean hasActiveLoan = resultSet.next();

            resultSet.close();
            statement.close();

            return hasActiveLoan;
        }
    }

    private void deleteLoanRecords() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");

            String sql = "DELETE FROM loan";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class PayLoanButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT remainingBal FROM loan");

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(LoanMenu.this,
                            "You have no loan to pay. Please apply for a new one.",
                            "No Loan to Pay",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    double remainingBal = resultSet.getDouble("remainingBal");
                    if (remainingBal > 0) {
                        // Remaining balance > 0, paid, redirect to PayLoan page
                        JFrame parentFrame = (JFrame) SwingUtilities.getRoot(LoanMenu.this);
                        parentFrame.getContentPane().removeAll();
                        parentFrame.getContentPane().add(new PayLoan(), BorderLayout.CENTER);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    } else {
                        deleteLoanRecords();
                        // Remaining balance = 0
                        JOptionPane.showMessageDialog(LoanMenu.this,
                                "You have no loan to pay. Please apply for a new one.",
                                "No Loan to Pay",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class MoreDetailsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoreDetails moreDetails = new MoreDetails();
            moreDetails.displayLoanDetails();
        }
    }

}