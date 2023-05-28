
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayLoan extends JPanel {
    private double remainingBal, monthlyDue;
    private Rounded.RoundedButton confirmButton;
    private Rounded.RoundedTextArea paymentSuccess;
    private String formattedBalance;
    private JPanel contentPanel;
    private NavBar navBar;
    private int customerID;

    GridBagConstraints gbc;

    public PayLoan(int customerID) {
        setLayout(new BorderLayout());
        this.customerID = customerID;

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.decode("#5cbfe9"));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        retrieveLoanDetails();

        paymentSuccess = new Rounded.RoundedTextArea(6, 20, 20, 5);
        paymentSuccess.setEditable(false);
        paymentSuccess.setOpaque(false);
        paymentSuccess.setFont(new Font("Arial", Font.BOLD, 16));

        paymentSuccess.append("\n                   Payment Success\n\n");
        paymentSuccess.append("               Amount Paid: " + monthlyDue + "\n");
        double remainingBalance = remainingBal - monthlyDue;
        formattedBalance = String.format("%.2f", remainingBalance);
        paymentSuccess.append("        Remaining Balance: " + formattedBalance + "\n");

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        contentPanel.add(paymentSuccess, gbc);

        confirmButton = new Rounded.RoundedButton("Confirm", 10);
        confirmButton.addActionListener(new ConfirmButtonListener());
        confirmButton.setPreferredSize(new Dimension(150, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        contentPanel.add(paymentSuccess, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        contentPanel.add(confirmButton, gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void retrieveLoanDetails() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");

            String sql = "SELECT remainingBal, monthlyDue FROM loan";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                remainingBal = resultSet.getDouble("remainingBal");
                monthlyDue = resultSet.getDouble("monthlyDue");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSavings(double amount) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");

            String sql = "UPDATE account SET currentBal = currentBal - ? WHERE accountTypeID = 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, amount);

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRemainingBal(double newLoanBal) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");

            String sql = "UPDATE loan SET remainingBal = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, newLoanBal);

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            updateSavings(monthlyDue);

            updateRemainingBal(remainingBal - monthlyDue);

            SwingUtilities.invokeLater(() -> {
                LoanMenu loanMenu = new LoanMenu(customerID);
                JFrame currentWindow = (JFrame) SwingUtilities.getWindowAncestor(PayLoan.this);
                JPanel newContentPane = new JPanel();
                newContentPane.setLayout(new BorderLayout());
                newContentPane.add(loanMenu, BorderLayout.CENTER);
                currentWindow.setContentPane(newContentPane);
                currentWindow.revalidate();
            });
        }
    }
}
