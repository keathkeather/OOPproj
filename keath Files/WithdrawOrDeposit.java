import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WithdrawOrDeposit extends JPanel {
    private Rounded.RoundedButton depositButton, withdrawButton, okButton;
    private Rounded.RoundedTextField depositTextField, withdrawTextField;
    private NavBar navBar;
    private JPanel inputPanel;
    private GridBagConstraints gbc;
    private Connection connection;

    private String dbUrl = "jdbc:mysql://localhost:3306/oop";
    private String username = "root";
    private String password = "";

    public WithdrawOrDeposit() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));

        navBar = new NavBar();
        add(navBar, BorderLayout.WEST);

        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.decode("#5cbfe9"));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        inputPanel.add(new JLabel("Deposit: "), gbc);
        gbc.gridx++;

        depositTextField = new Rounded.RoundedTextField(20, 20);
        depositTextField.setPreferredSize(new Dimension(200, 25));
        inputPanel.add(depositTextField, gbc);

        gbc.gridx++;

        depositButton = new Rounded.RoundedButton("Deposit", 10);
        depositButton.setPreferredSize(new Dimension(100, 30));
        depositButton.addActionListener(new DepositButtonListener());
        inputPanel.add(depositButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Withdraw: "), gbc);
        gbc.gridx++;

        withdrawTextField = new Rounded.RoundedTextField(20, 20);
        withdrawTextField.setPreferredSize(new Dimension(200, 25));
        inputPanel.add(withdrawTextField, gbc);

        gbc.gridx++;

        withdrawButton = new Rounded.RoundedButton("Withdraw", 10);
        withdrawButton.setPreferredSize(new Dimension(100, 30));
        withdrawButton.addActionListener(new WithdrawButtonListener());
        inputPanel.add(withdrawButton, gbc);

        okButton = new Rounded.RoundedButton("Ok", 10);
        okButton.setPreferredSize(new Dimension(100, 30));
        okButton.addActionListener(new OkButtonListener());
        gbc.gridy++;
        gbc.gridx = 2;
        inputPanel.add(okButton, gbc);

        add(inputPanel, BorderLayout.CENTER);

        // establish database connection
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private double retrieveSavingsFromDatabase() {
        double savings = 0.0; // Default value in case the retrieval fails

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT savings FROM account WHERE accountType = 1");

            if (resultSet.next()) {
                savings = resultSet.getDouble("savings");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savings;
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String depositAmount = depositTextField.getText();
            try {
                double amount = Double.parseDouble(depositAmount);
                updateSavingsInDatabase(amount);
                JOptionPane.showMessageDialog(null, "Deposit Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cannot deposit amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            depositTextField.setText("");
        }

    }

    private void updateSavingsInDatabase(double depositAmount) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE account SET savings = savings + ? WHERE accountType = 1");
            statement.setDouble(1, depositAmount);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawAmount = withdrawTextField.getText();

            try {
                double amount = Double.parseDouble(withdrawAmount);
                double savingsBalance = retrieveSavingsFromDatabase();

                if (amount > savingsBalance) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds. Cannot withdraw amount.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                updateSavingsInDatabase(-amount);
                JOptionPane.showMessageDialog(null, "Withdraw Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid withdraw amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            withdrawTextField.setText("");
        }
    }

    private class OkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SavingsMenu savingMenuPanel = new SavingsMenu();
            removeAll();
            setLayout(new BorderLayout());
            add(savingMenuPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
}
