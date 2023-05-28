import javax.swing.*;

import com.mysql.cj.protocol.Resultset;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SendMoney extends JPanel {
    private Rounded.RoundedButton sendButton, cancelButton;
    private Rounded.RoundedTextField amountTextField, receiverEmailTextField;
    private JPanel mainPanel;
    private JLabel sendMoneyDetailsLabel;
    private Font labelFont;
    private Connection connection;
    private NavBar navBar;

    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";
    private int customerID;
    public SendMoney(int customerID) {
        this.customerID = customerID;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#5cbfe9"));

        navBar = new NavBar();
        add(navBar, BorderLayout.WEST);

        labelFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 16);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#5cbfe9"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        sendMoneyDetailsLabel = new JLabel("Send Money Details");
        sendMoneyDetailsLabel.setFont(labelFont);
        mainPanel.add(sendMoneyDetailsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        amountTextField = new Rounded.RoundedTextField(20, 20);
        amountTextField.setPreferredSize(new Dimension(200, 25));
        mainPanel.add(amountTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Receiver Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        receiverEmailTextField = new Rounded.RoundedTextField(20, 20);
        receiverEmailTextField.setPreferredSize(new Dimension(200, 25));
        mainPanel.add(receiverEmailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        sendButton = new Rounded.RoundedButton("Send", 10);
        sendButton.setPreferredSize(new Dimension(100, 30));

        mainPanel.add(sendButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        cancelButton = new Rounded.RoundedButton("Cancel", 10);
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new CancelButtonListener());
        mainPanel.add(cancelButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // establish database connection
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.", "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strAmount = amountTextField.getText();
                Double amount = Double.parseDouble(strAmount);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String receiverEmail = receiverEmailTextField.getText();
                if (receiverEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Receiver email is required.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Get the customer ID based on the receiver's email
                    
                    String query = "SELECT customerID FROM customer WHERE emailAddress = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, receiverEmail);
                    ResultSet result = statement.executeQuery();

                    if (result.next()) {
                        int receiverID = result.getInt("customerID");
                        getAccountID accID = new getAccountID(receiverID);
                        int receiverAccountID = accID.getID();
                        getAccountID senderID =new getAccountID(customerID);
                        int accountID = senderID.getID();
                        getAccBalance accBal = new getAccBalance(customerID);
                        Double balance = accBal.getBalance();
                        if(amount<0){
                            JOptionPane.showMessageDialog(null, "Error invalid amount.", "ERROR",JOptionPane.INFORMATION_MESSAGE);
                            throw new NumberFormatException();
                        }
                        if(balance<amount){
                            JOptionPane.showMessageDialog(null, "Insufficient Funds", "ERROR",JOptionPane.INFORMATION_MESSAGE);
                            throw new NumberFormatException();
                        }

                        // Transfer the amount from the sender to the receiver
                        Transaction receiverTransaction = new Transaction(receiverAccountID,receiverID,amount,"Funds Transferred to "+ receiverEmail);
                        receiverTransaction.recordTransaction();
                        Double newAmount = amount*-1;
                        System.out.print(newAmount);
                        Transaction transaction = new Transaction(accountID,customerID,newAmount,"Funds Sent to "+receiverEmail);
                        transaction.recordTransaction();
                        JOptionPane.showMessageDialog(null, "Money sent successfully.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Receiver email not found.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error executing the query.", "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });




    }

   

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create and display the SavingsMenu panel
            SavingsMenu savingMenuPanel = new SavingsMenu(customerID);
            removeAll(); // Remove all components from the current panel
            setLayout(new BorderLayout()); // Set the desired layout for the panel
            add(savingMenuPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    // Method to retrieve the ID of the logged-in customer (replace with your
    // implementation)
    private String getLoggedInCustomerId() {
        // Replace this with your authentication or session management logic to retrieve
        // the logged-in customer's ID
        // For demonstration purposes, return a sample ID
        return "1";
    }
}
