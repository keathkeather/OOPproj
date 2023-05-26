// package com.mycompany.bankLoan;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
// import javax.swing.JOptionPane;

// public class CustomCalculateListener implements ActionListener {
// private LoanMenu bankLoan;
// private Connection connection;

// public CustomCalculateListener(LoanMenu bankLoan, Connection connection) {
// this.bankLoan = bankLoan;
// this.connection = connection;
// }

// @Override
// public void actionPerformed(ActionEvent e) {
// try {
// // Calculate loan details
// double loanAmount = Double.parseDouble(bankLoan.getAmountField().getText());
// int loanDuration = Integer.parseInt(bankLoan.getDurationField().getText());
// double interestRate =
// Double.parseDouble(bankLoan.getInterestRateField().getText());

// // Get the current date as the start date
// java.util.Date currentDate = new java.util.Date();
// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
// String startDateStr = dateFormat.format(currentDate);

// // Calculate the end date based on the loan duration
// Calendar calendar = Calendar.getInstance();
// calendar.setTime(currentDate);
// calendar.add(Calendar.MONTH, loanDuration);
// java.util.Date endDate = calendar.getTime();
// String endDateStr = dateFormat.format(endDate);

// // Insert loan information into the database
// String insertSql = "INSERT INTO loan (amount, interestRate, startDATE,
// duration) VALUES (?, ?, ?, ?)";
// PreparedStatement insertStatement = connection.prepareStatement(insertSql,
// Statement.RETURN_GENERATED_KEYS);
// insertStatement.setDouble(1, loanAmount);
// insertStatement.setDouble(2, interestRate);
// insertStatement.setString(3, startDateStr);
// insertStatement.setInt(4, loanDuration);
// insertStatement.executeUpdate();

// // Retrieve loan information from the database
// ResultSet generatedKeys = insertStatement.getGeneratedKeys();
// if (generatedKeys.next()) {
// int loanId = generatedKeys.getInt(1);

// // Query the database to get the calculated values
// String loanInfoQuery = "SELECT duration, endDATE, monthlyDue, loanBal FROM
// loan WHERE loanId = ?";
// PreparedStatement loanInfoStatement =
// connection.prepareStatement(loanInfoQuery);
// loanInfoStatement.setInt(1, loanId);
// ResultSet loanInfoResult = loanInfoStatement.executeQuery();

// if (loanInfoResult.next()) {
// loanDuration = loanInfoResult.getInt("duration");
// double monthlyDue = loanInfoResult.getDouble("monthlyDue");
// double loanBal = loanInfoResult.getDouble("loanBal");

// // Display loan information
// String loanInfo = "Loan Amount: " + loanAmount + "\n" +
// "Interest Rate: " + interestRate + "%\n" +
// "Loan Duration: " + loanDuration + " months" + "\n" +
// "Start Date: " + startDateStr + "\n" +
// "End Date: " + endDateStr + "\n" +
// "Monthly Due: Php" + monthlyDue + "\n" +
// "Loan Balance: Php" + loanBal;
// bankLoan.getLoanInfoField().setText(loanInfo);
// }
// }
// } catch (SQLException ex) {
// ex.printStackTrace();
// JOptionPane.showMessageDialog(null, "Error connecting to the database.",
// "Database Error",
// JOptionPane.ERROR_MESSAGE);
// } catch (NumberFormatException ex) {
// JOptionPane.showMessageDialog(null, "Invalid input.", "Input Error",
// JOptionPane.ERROR_MESSAGE);
// }
// }
// }
