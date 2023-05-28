// Bataluna, Popup for Loan Details

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MoreDetails {

    private Connection connection;
    private Statement statement;

    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";

    public void displayLoanDetails() {
        JTextArea loanDetailsArea = new JTextArea();
        loanDetailsArea.setEditable(false);
        loanDetailsArea.setFont(new Font("Arial", Font.BOLD, 14));

        // Initialize database connection
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM loan");

            if (resultSet.next()) {

                double amount = resultSet.getDouble("amount");
                double interestRate = resultSet.getDouble("interestRate");
                int duration = resultSet.getInt("duration");
                String startDATE = resultSet.getString("startDATE");
                String endDATE = resultSet.getString("endDATE");
                double monthlyDue = resultSet.getDouble("monthlyDue");
                double loanBal = resultSet.getDouble("loanBal");
                double remainingBal = resultSet.getDouble("remainingBal");

                String loanDetails = "Loan Amount: " + amount + "\n" +
                        "Interest Rate: " + interestRate + "%\n" +
                        "Loan Duration: " + duration + " months\n" +
                        "Start Date: " + startDATE + "\n" +
                        "End Date: " + endDATE + "\n" +
                        "Monthly Due: Php" + monthlyDue + "\n" +
                        "Loan Balance: Php" + loanBal +
                        "\nRemaining Balance: " + remainingBal + "\n";

                loanDetailsArea.setText(loanDetails);
            } else {
                loanDetailsArea.setText("You have no present loan.");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, new JScrollPane(loanDetailsArea), "Loan Details",
                JOptionPane.PLAIN_MESSAGE);
    }
}
