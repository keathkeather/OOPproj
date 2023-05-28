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

    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";
    private int  customerID;
    public WithdrawOrDeposit(int customerID) {
        this.customerID = customerID;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));

       

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


        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String depositAmount = depositTextField.getText();
            try {
                double amount = Double.parseDouble(depositAmount);
                System.out.println(amount);
                updateSavingsInDatabase(amount,customerID);
                JOptionPane.showMessageDialog(null, "Deposit Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cannot deposit amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            depositTextField.setText("");
            }
        });


        navBar = new NavBar(inputPanel,customerID);
        add(navBar, BorderLayout.WEST);
    }

    private double retrieveSavingsFromDatabase(int customerID) {
        getAccBalance balance = new getAccBalance(customerID);
        return balance.getBalance();
    }
    
    

   

    private void updateSavingsInDatabase(double depositAmount, int customerID) {
        PreparedStatement statement = null;
        ResultSet accIDResultSet = null;
    
        try {
            String retriveAccID = "SELECT accountID FROM account WHERE customerID = ? AND accountTypeID = 1";
            statement = connection.prepareStatement(retriveAccID);
            statement.setInt(1, customerID);
            accIDResultSet = statement.executeQuery();
    
            if (accIDResultSet.next()) {
                int accountID = accIDResultSet.getInt("accountID");
                Transaction transaction = new Transaction(accountID, customerID, depositAmount, "deposit");
                transaction.recordTransaction();
                // Perform any necessary operations with the transaction object
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (accIDResultSet != null) {
                    accIDResultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawAmount = withdrawTextField.getText();

            try {
                double amount = Double.parseDouble(withdrawAmount);
                if(amount<0){
                    throw new NumberFormatException();
                }
                double savingsBalance = retrieveSavingsFromDatabase(customerID);

                if (amount > savingsBalance) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds. Cannot withdraw amount.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                updateSavingsInDatabase(-amount,customerID);
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
            SavingsMenu savingMenuPanel = new SavingsMenu(customerID);
            removeAll();
            setLayout(new BorderLayout());
            add(savingMenuPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
}
