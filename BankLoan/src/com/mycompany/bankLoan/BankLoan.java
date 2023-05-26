// Bataluna, Calculating and Applying for Loan

package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BankLoan extends JPanel {
    private JLabel amountLabel, durationLabel, inputDetailsLabel;
    private JPanel loanDetailsPanel;
    private Rounded.RoundedTextField amountField, durationField, interestRateField;
    private Rounded.RoundedButton calculateButton, applyLoanButton;
    private Rounded.RoundedTextArea loanInfoField;
    private CustomCalculateListener calculateListener;
    private ApplyLoanListener applyLoanListener;
    private Connection connection;
    private NavBar navBar;
    private Font labelFont, labelFont1;

    GridBagConstraints constraints;

    private String dbUrl = "jdbc:mysql://localhost:3306/oop";
    private String username = "root";
    private String password = "";

    public BankLoan() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));

        navBar = new NavBar();
        add(navBar, BorderLayout.WEST);

        labelFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 16);
        labelFont1 = new Font("Verdana", Font.BOLD, 20);

        loanDetailsPanel = new JPanel(new GridBagLayout());
        loanDetailsPanel.setBackground(Color.decode("#5cbfe9"));

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;

        inputDetailsLabel = new JLabel("Input Loan Details:");
        inputDetailsLabel.setFont(labelFont1);
        constraints.gridwidth = 2;
        loanDetailsPanel.add(inputDetailsLabel, constraints);

        constraints.gridy++;

        amountLabel = new JLabel("Loan Amount:");
        amountLabel.setFont(labelFont);
        constraints.gridwidth = 1;
        loanDetailsPanel.add(amountLabel, constraints);

        constraints.gridx++;

        amountField = new Rounded.RoundedTextField(20, 20);
        amountField.setPreferredSize(new Dimension(200, 30));
        amountField.setBackground(Color.WHITE);
        constraints.weightx = 0.0;
        loanDetailsPanel.add(amountField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;

        durationLabel = new JLabel("Loan Duration (in months):");
        durationLabel.setFont(labelFont);
        loanDetailsPanel.add(durationLabel, constraints);

        constraints.gridx++;

        durationField = new Rounded.RoundedTextField(20, 20);
        durationField.setPreferredSize(new Dimension(200, 30));
        durationField.setBackground(Color.WHITE);
        loanDetailsPanel.add(durationField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;

        calculateButton = new Rounded.RoundedButton("Calculate", 10);
        calculateButton.setPreferredSize(new Dimension(100, 30));
        calculateListener = new CustomCalculateListener();
        calculateButton.addActionListener(calculateListener);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        loanDetailsPanel.add(calculateButton, constraints);

        constraints.gridy++;
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;

        loanInfoField = new Rounded.RoundedTextArea(10, 17, 20, 5);
        loanInfoField.setBackground(Color.WHITE);
        loanInfoField.setFont(new Font("Arial", Font.BOLD, 16));
        loanInfoField.setLineWrap(true);
        loanInfoField.setWrapStyleWord(true);
        loanInfoField.setEditable(false);
        loanDetailsPanel.add(loanInfoField, constraints);
        constraints.gridy++;

        constraints.gridy++;
        constraints.gridwidth = 2;

        applyLoanButton = new Rounded.RoundedButton("Apply Loan", 10);
        applyLoanButton.setPreferredSize(new Dimension(150, 30));
        applyLoanListener = new ApplyLoanListener();
        applyLoanButton.addMouseListener(applyLoanListener);
        constraints.fill = GridBagConstraints.NONE;
        loanDetailsPanel.add(applyLoanButton, constraints);

        constraints.gridy++;

        add(loanDetailsPanel, BorderLayout.CENTER);

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getDurationField() {
        return durationField;
    }

    public JTextField getInterestRateField() {
        return interestRateField;
    }

    public JTextArea getLoanInfoField() {
        return loanInfoField;
    }

    private class CustomCalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double loanAmount = Double.parseDouble(getAmountField().getText());
                int loanDuration = Integer.parseInt(getDurationField().getText());

                java.util.Date currentDate = new java.util.Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDateStr = dateFormat.format(currentDate);

                // Calculation of the end date based on the loan duration
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.MONTH, loanDuration);
                java.util.Date endDate = calendar.getTime();
                String endDateStr = dateFormat.format(endDate);

                // Inserting loan information into the database
                String insertSql = "INSERT INTO loan (amount, startDATE, duration) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertSql,
                        Statement.RETURN_GENERATED_KEYS);
                insertStatement.setDouble(1, loanAmount);
                insertStatement.setString(2, startDateStr);
                insertStatement.setInt(3, loanDuration);
                insertStatement.executeUpdate();

                // Retrieving loan information from the database
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int loanID = generatedKeys.getInt(1);

                    // Querying the database to get the calculated values & interestRate
                    String loanInfoQuery = "SELECT duration, endDATE, interestRate, monthlyDue, loanBal FROM loan WHERE loanID = ?";
                    PreparedStatement loanInfoStatement = connection.prepareStatement(loanInfoQuery);
                    loanInfoStatement.setInt(1, loanID);
                    ResultSet loanInfoResult = loanInfoStatement.executeQuery();

                    if (loanInfoResult.next()) {
                        loanDuration = loanInfoResult.getInt("duration");
                        float interestRate = loanInfoResult.getFloat("interestRate");
                        double monthlyDue = loanInfoResult.getDouble("monthlyDue");
                        double loanBal = loanInfoResult.getDouble("loanBal");

                        // Updating the endDATE in the database
                        String updateSql = "UPDATE loan SET endDATE = ? WHERE loanID = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                        updateStatement.setString(1, endDateStr);
                        updateStatement.setInt(2, loanID);
                        updateStatement.executeUpdate();

                        // Updating the remainingBal in the database
                        String updateRemainingBalSql = "UPDATE loan SET remainingBal = ? WHERE loanID = ?";
                        PreparedStatement updateRemainingBalStatement = connection
                                .prepareStatement(updateRemainingBalSql);
                        updateRemainingBalStatement.setDouble(1, loanBal);
                        updateRemainingBalStatement.setInt(2, loanID);
                        updateRemainingBalStatement.executeUpdate();

                        String loanInfo = "\n   Loan Information:\n" +
                                "   Loan Amount: " + loanAmount + "\n" +
                                "   Interest Rate: " + interestRate + "%\n" +
                                "   Loan Duration: " + loanDuration + " months" + "\n" +
                                "   Start Date: " + startDateStr + "\n" +
                                "   End Date: " + endDateStr + "\n" +
                                "   Monthly Due: Php" + monthlyDue + "\n" +
                                "   Loan Balance: Php" + loanBal + "\n";
                        getLoanInfoField().setText(loanInfo);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ApplyLoanListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int option = JOptionPane.showOptionDialog(
                    null,
                    "Congratulations! You have successfully applied for a loan.",
                    "Loan Application",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[] { "OK" },
                    null);
            if (option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
                SwingUtilities.invokeLater(() -> {
                    LoanMenu loanMenu = new LoanMenu();
                    JFrame currentWindow = (JFrame) SwingUtilities.getWindowAncestor(BankLoan.this);
                    JPanel newContentPane = new JPanel();
                    newContentPane.setLayout(new BorderLayout());
                    newContentPane.add(loanMenu, BorderLayout.CENTER);
                    currentWindow.setContentPane(newContentPane);
                    currentWindow.revalidate();
                });
            }

        }
    }
}