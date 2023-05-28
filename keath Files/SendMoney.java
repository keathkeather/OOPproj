import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SendMoney extends JPanel {
    private Rounded.RoundedButton sendButton, cancelButton;
    private Rounded.RoundedTextField amountTextField, receiverIdTextField;
    private JPanel mainPanel;
    private JLabel sendMoneyDetailsLabel;
    private Font labelFont;
    private Connection connection;
    private NavBar navBar;

    private String dbUrl = "jdbc:mysql://localhost:3306/oop";
    private String username = "root";
    private String password = "";

    public SendMoney() {
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
        mainPanel.add(new JLabel("Receiver ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        receiverIdTextField = new Rounded.RoundedTextField(20, 20);
        receiverIdTextField.setPreferredSize(new Dimension(200, 25));
        mainPanel.add(receiverIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        sendButton = new Rounded.RoundedButton("Send", 10);
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.addActionListener(new SendButtonListener());
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
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountStr = amountTextField.getText();
            String receiverId = receiverIdTextField.getText();
            String senderId = getLoggedInCustomerId(); // Retrieve the ID of the logged-in customer

            try {
                // Check if sender's savings are sufficient
                PreparedStatement senderStatement = connection
                        .prepareStatement("SELECT savings FROM account WHERE customerID = ? AND accountType = 1");
                senderStatement.setString(1, senderId);
                ResultSet senderResultSet = senderStatement.executeQuery();

                if (senderResultSet.next()) {
                    double senderSavings = senderResultSet.getDouble("savings");
                    double amount = Double.parseDouble(amountStr);

                    if (senderSavings >= amount) {
                        // Deduct from sender's savings
                        double senderNewSavings = senderSavings - amount;
                        PreparedStatement senderUpdateStatement = connection
                                .prepareStatement(
                                        "UPDATE account SET savings = ? WHERE customerID = ? AND accountType = 1");
                        senderUpdateStatement.setDouble(1, senderNewSavings);
                        senderUpdateStatement.setString(2, senderId);
                        senderUpdateStatement.executeUpdate();

                        // Add to receiver's savings
                        PreparedStatement receiverStatement = connection
                                .prepareStatement(
                                        "SELECT savings FROM account WHERE customerID = ? AND accountType = 1");
                        receiverStatement.setString(1, receiverId);
                        ResultSet receiverResultSet = receiverStatement.executeQuery();

                        if (receiverResultSet.next()) {
                            double receiverSavings = receiverResultSet.getDouble("savings");
                            double receiverNewSavings = receiverSavings + amount;

                            PreparedStatement receiverUpdateStatement = connection
                                    .prepareStatement(
                                            "UPDATE account SET savings = ? WHERE customerID = ? AND accountType = 1");
                            receiverUpdateStatement.setDouble(1, receiverNewSavings);
                            receiverUpdateStatement.setString(2, receiverId);
                            receiverUpdateStatement.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Amount sent successfully!");

                            amountTextField.setText("");
                            receiverIdTextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Receiver ID not found!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sender ID not found!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create and display the SavingsMenu panel
            SavingsMenu savingMenuPanel = new SavingsMenu();
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
