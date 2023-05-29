/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CheckingAccountUI extends JFrame {
    private String accountNumber;
    private double balance;
    private Connection connection;
    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";

    private JLabel balanceLabel, transferLabel, withdrawLabel;
    private Rounded.RoundedButton transferButton, withdrawButton;
    private Rounded.RoundedTextField transferAmountField;
    private Rounded.RoundedTextField withdrawAmountField;
    
    
    
    public CheckingAccountUI() {
   
        setTitle("Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080,720);
        setLocationRelativeTo(null);

        
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));
        
        // Establish a database connection (assuming you have set up the connection details)
        String className = "com.mysql.cj.jdbc.Driver";

    try {
        Class.forName(className);
        System.out.println("Driver is loaded successfully.");
    } catch (ClassNotFoundException ex) {
        System.out.println("Driver failed to load.");
    }
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }


        //components
        balanceLabel = new JLabel();
        
        transferAmountField = new Rounded.RoundedTextField(50,5);
        transferButton = new Rounded.RoundedButton("Transfer to Savings",10);
        transferAmountField.setPreferredSize(new Dimension(200, 25));
        
        
        withdrawAmountField = new Rounded.RoundedTextField(50,5);
        withdrawButton = new Rounded.RoundedButton("Withdraw",10);
        withdrawButton.setPreferredSize(new Dimension(200,25));
        
        
        
        withdrawLabel = new JLabel("Withdraw Amount:");
        transferLabel = new JLabel("Transfer Amount:");

        
        //set font for labels and buttons
        Font font = new Font("Roboto", Font.BOLD, 16);
        Font font2 = new Font("Roboto", Font.BOLD, 24);
        transferButton.setFont(font);
        transferButton.setBackground(Color.decode("#4f93d2"));
        transferButton.setForeground(Color.WHITE);
        withdrawButton.setFont(font);
        withdrawButton.setBackground(Color.decode("#4f93d2"));
        withdrawButton.setForeground(Color.WHITE);
        withdrawLabel.setFont(font);
        withdrawLabel.setForeground(Color.WHITE);
        transferLabel.setFont(font);
        transferLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(font);
        balanceLabel.setForeground(Color.WHITE);

        
        // create panels
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        //panel properties
        panel.setBackground(Color.decode("#5cbfe9"));
        mainPanel.add(panel,BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //insets (top, left, right, bottom)
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(balanceLabel,gbc);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(transferLabel,gbc);
        gbc.gridy = 2;
        panel.add(transferAmountField,gbc);
        gbc.gridy = 3;
        panel.add(transferButton,gbc);
        gbc.gridy = 4;
        panel.add(withdrawLabel,gbc);
        gbc.gridy = 5;
        panel.add(withdrawAmountField,gbc);
        gbc.gridy = 6;
        panel.add(withdrawButton,gbc);
        gbc.gridy = 7;

        
        add(mainPanel);
        setVisible(true);
        updateBalanceLabel();
        
        withdrawButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawAmount = withdrawAmountField.getText();

            try {
                double amount = Double.parseDouble(withdrawAmount);
                double currentBalance = retrieveBalanceFromDatabase();

                if (amount < currentBalance) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds. Cannot withdraw amount.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                updateBalanceInDatabase(-amount);
                JOptionPane.showMessageDialog(null, "Withdraw Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid withdraw amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            withdrawAmountField.setText("");
        }
    });
        
        transferButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String transferAmount = transferAmountField.getText();
            try {
                double amount = Double.parseDouble(transferAmount);
                updateBalanceInDatabase(amount);
                JOptionPane.showMessageDialog(null, "Transfer Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cannot transfer amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            transferAmountField.setText("");
        }

    });
    }
    
        
    
    private double retrieveBalanceFromDatabase() {
        double currentBalance = 0.0; // Default value in case the retrieval fails

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT currentBal FROM account WHERE accountTypeID = 1");

            if (resultSet.next()) {
                currentBalance = resultSet.getDouble("currentBal");
                
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentBalance;
    }
    
    private void updateBalanceInDatabase(double sendToSavingsAmount) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE account SET currentBal = savings + ? WHERE accountTypeID = 1");
            statement.setDouble(1, sendToSavingsAmount);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + retrieveBalanceFromDatabase());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CheckingAccountUI();
            }
        });
    }
}

