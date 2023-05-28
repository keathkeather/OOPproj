package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Font;

public class HomePage extends JFrame {
    private JLabel greetingLabel, banknameLabel;
    private JLabel balanceLabel, imagePanel2Label;
    private JButton sendMoneyButton;
    private JButton withdrawButton;
    private JButton payBillsButton;
    private JButton loanButton;
    private JButton investButton;
    

    // MySQL database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oopproject";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public HomePage(String username) {
        setTitle("Banking System - Home Page");
        setSize(1080,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Create a panel with GridBagLayout
        JPanel contentPanel = new JPanel();
        JPanel imagePanel = new JPanel();
        JPanel imagePanel2 = new JPanel();
        JPanel banknamePanel = new JPanel();
        banknamePanel.setPreferredSize(new Dimension(0,100));
        imagePanel.setPreferredSize(new Dimension(100,720));
        imagePanel2.setLayout(new BoxLayout(imagePanel2, BoxLayout.Y_AXIS));
        contentPanel.setLayout(new GridBagLayout());
        //set background color for the panels

        imagePanel2.setBackground(Color.decode("#5cbfe9"));
        contentPanel.setBackground(Color.decode("#5cbfe9"));
        banknamePanel.setBackground(Color.decode("#005176"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        greetingLabel = new JLabel("Hello, " + username + "!");
        balanceLabel = new JLabel("Current Balance: $0.00");
        banknameLabel = new JLabel("Welcome to UniBank!");
        sendMoneyButton = new JButton("Send Money");
        withdrawButton = new JButton("Withdraw");
        payBillsButton = new JButton("Pay Bills");
        loanButton = new JButton("Apply for a Loan");
        investButton = new JButton("Invest");
        imagePanel2Label = new JLabel("Try investing to grow your money.");
        JLabel imageLabel = new JLabel();
        JLabel imageLabel2 = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Users/LENOVO E560/OneDrive/Documents/NetBeansProjects/database/src/bank/business.png"); // Set the path to your image file
        imageLabel.setIcon(imageIcon);
        ImageIcon imageIcon2 = new ImageIcon("C:/Users/LENOVO E560/OneDrive/Documents/NetBeansProjects/database/src/bank/invest.png"); // Set the path to your image file
        imageLabel2.setIcon(imageIcon2);

        //set the colors of the labels and buttons
        Font font = new Font("Roboto", Font.BOLD, 16);
        Font font2 = new Font("Roboto", Font.BOLD, 24);
        sendMoneyButton.setFont(font);
        sendMoneyButton.setBackground(Color.decode("#4f93d2"));
        sendMoneyButton.setForeground(Color.WHITE);
        withdrawButton.setFont(font);
        withdrawButton.setBackground(Color.decode("#4f93d2"));
        withdrawButton.setForeground(Color.WHITE);
        payBillsButton.setFont(font);
        payBillsButton.setBackground(Color.decode("#4f93d2"));
        payBillsButton.setForeground(Color.WHITE);
        loanButton.setFont(font);
        loanButton.setBackground(Color.decode("#4f93d2"));
        loanButton.setForeground(Color.WHITE);
        investButton.setFont(font);
        investButton.setBackground(Color.decode("#4f93d2"));
        investButton.setForeground(Color.WHITE);
        greetingLabel.setFont(font);
        greetingLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(font);
        balanceLabel.setForeground(Color.WHITE);
        banknameLabel.setFont(font2);
        banknameLabel.setForeground(Color.WHITE);
        imagePanel2Label.setFont(font2);
        imagePanel2Label.setForeground(Color.WHITE);

        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(banknameLabel,gbc);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        contentPanel.add(imageLabel,gbc);
        gbc.gridy = 2;
        
        contentPanel.add(greetingLabel,gbc);
        gbc.gridy = 3;
        contentPanel.add(balanceLabel, gbc);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(sendMoneyButton, gbc);
        gbc.gridy = 5;
        contentPanel.add(withdrawButton, gbc);
        gbc.gridy = 6;
        contentPanel.add(payBillsButton, gbc);
        gbc.gridy = 7;
        contentPanel.add(loanButton, gbc);
        gbc.gridy = 8;
        contentPanel.add(investButton, gbc);
        gbc.gridy = 9;
        
        //imagepanel properties
        imageLabel2.setBorder(BorderFactory.createEmptyBorder(150, 50, 0, 50));
        imagePanel2Label.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 20));
        
        imagePanel2.add(Box.createHorizontalGlue());
        imagePanel2.add(imageLabel2);
        imagePanel2.add(imagePanel2Label);
        
        imagePanel2.add(Box.createHorizontalGlue());
        
        imageLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel2.setAlignmentY(Component.CENTER_ALIGNMENT);
        imagePanel2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel2Label.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        //mainPanel.add(imagePanel, BorderLayout.EAST);
        mainPanel.add(imagePanel2, BorderLayout.WEST);

       
        //add the mainPanel to the frame
        add(mainPanel);
        
        sendMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // diri nga part kay ilisdan lng aron maka connect sa uban file
                JOptionPane.showMessageDialog(HomePage.this, "Send Money button clicked.");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // diri nga part kay ilisdan lng aron maka connect sa uban file
                JOptionPane.showMessageDialog(HomePage.this, "Withdraw button clicked.");
            }
        });

        payBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // diri nga part kay ilisdan lng aron maka connect sa uban file
                JOptionPane.showMessageDialog(HomePage.this, "Pay Bills button clicked.");
            }
        });

        // Retrieve and display the current balance from the database
        fetchAndDisplayBalance(username);
    }

    private void fetchAndDisplayBalance(String username) {
    try {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
        // Retrieve the username from the userlogin table
        String usernameQuery = "SELECT userName FROM userlogin WHERE userName = ?";
        PreparedStatement usernameStatement = conn.prepareStatement(usernameQuery);
        usernameStatement.setString(1, username);
        ResultSet usernameResultSet = usernameStatement.executeQuery();
        
        if (usernameResultSet.next()) {
            String retrievedUsername = usernameResultSet.getString("userName");
            greetingLabel.setText("Hello, " + retrievedUsername + "!");
        }
        
        usernameResultSet.close();
        usernameStatement.close();
        
        // Retrieve the current balance from the account table
        String balanceQuery = "SELECT currentBal FROM account WHERE userName = ?";
        PreparedStatement balanceStatement = conn.prepareStatement(balanceQuery);
        balanceStatement.setString(1, username);
        ResultSet balanceResultSet = balanceStatement.executeQuery();
        
        if (balanceResultSet.next()) {
            double balance = balanceResultSet.getDouble("currentBal");
            balanceLabel.setText("Current Balance: $" + balance);
        }
        
        balanceResultSet.close();
        balanceStatement.close();
        
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while fetching the balance.");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String username = "John"; // Replace with the actual username after logging in
                HomePage homePage = new HomePage(username);
                homePage.setVisible(true);
            }
        });
    }
}
