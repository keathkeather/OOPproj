import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SavingsMenu extends JPanel {
    private Rounded.RoundedButton withdrawOrDepositButton, sendButton, closeAccountButton;
    private Rounded.RoundedTextArea balanceTextArea;
    private JPanel mainPanel;
    private Connection connection;
    private NavBar navBar;

    GridBagConstraints gbc;

    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";
    private int customerID;

    public SavingsMenu(int customerID) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));

        this.customerID = customerID;

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#5cbfe9"));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        balanceTextArea = new Rounded.RoundedTextArea(4, 17, 20, 5);
        balanceTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        balanceTextArea.setEditable(false);
        mainPanel.add(balanceTextArea, gbc);

        gbc.gridy++;
        withdrawOrDepositButton = new Rounded.RoundedButton("Withdraw or Deposit", 10);
        withdrawOrDepositButton.setPreferredSize(new Dimension(200, 30));
        withdrawOrDepositButton.addActionListener(new WithdrawOrDepositButtonListener());
        gbc.anchor = GridBagConstraints.CENTER; // Set anchor to center
        mainPanel.add(withdrawOrDepositButton, gbc);

        gbc.gridy++;
        sendButton = new Rounded.RoundedButton("Send Money", 10);
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(sendButton, gbc);

        gbc.gridy++;
        closeAccountButton = new Rounded.RoundedButton("Close Account", 10);
        closeAccountButton.setPreferredSize(new Dimension(100, 30));
        closeAccountButton.addActionListener(new CloseAccountButtonListener());
        mainPanel.add(closeAccountButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
        if (navBar == null) {
            navBar = new NavBar(mainPanel, customerID);
            add(navBar, BorderLayout.WEST);
        }
        // establish database connection
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Retrieve savings from the database and update the balanceTextArea
        retrieveSavingsFromDatabase(customerID);
    }

    private class WithdrawOrDepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WithdrawOrDeposit withdrawOrDepositPanel = new WithdrawOrDeposit(customerID);
            removeAll();
            setLayout(new BorderLayout());
            add(withdrawOrDepositPanel, BorderLayout.CENTER);
            // add(navBar, BorderLayout.WEST); // Add the NavBar back
            revalidate();
            repaint();
        }
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SendMoney sendMoneyPanel = new SendMoney(customerID);
            removeAll();
            setLayout(new BorderLayout());
            add(sendMoneyPanel, BorderLayout.CENTER);
            // add(navBar, BorderLayout.WEST); // Add the NavBar back
            revalidate();
            repaint();
        }
    }

    private class CloseAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to close your savings account permanently? This is irreversible.",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new String[] { "OK", "Cancel" },
                    "OK");

            if (choice == JOptionPane.OK_OPTION) {
                boolean accountDeleted = deleteSavingsAccountEntry();
                if (accountDeleted) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your savings account has been closed successfully.",
                            "Account Closed",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to close your savings account.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private boolean deleteSavingsAccountEntry() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root",
                        "");

                String query = "DELETE FROM account WHERE accountType = 1";
                Statement statement = connection.createStatement();

                int rowsAffected = statement.executeUpdate(query);

                statement.close();
                connection.close();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private void retrieveSavingsFromDatabase(int customerID) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT currentBalance FROM account_balance_view WHERE accountTypeID = 1 AND customerID = ?");
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double savings = resultSet.getDouble("currentBalance");
                balanceTextArea.setText("\n              Savings Balance: \n"
                        + "                      " + savings);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
