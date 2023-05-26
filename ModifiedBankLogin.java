package bank;

import bank.ModifiedRegistration;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

class RoundedTextField extends JTextField {

    private int cornerRadius;

    public RoundedTextField(int columns, int cornerRadius) {
        super(columns);
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setBorder(new EmptyBorder(0, 5, 0, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }
}

class RoundedButton extends JButton {

    private int cornerRadius;

    public RoundedButton(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }
}

class RoundedPasswordField extends JPasswordField {

    private int cornerRadius;

    public RoundedPasswordField(int columns, int cornerRadius) {
        super(columns);
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setBorder(new EmptyBorder(0, 5, 0, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }
}

public class ModifiedBankLogin extends JFrame {
    private RoundedTextField accountNumberField;
    RoundedPasswordField passwordField;
    private RoundedButton loginButton, registerButton;
    private JLabel accountNumberLabel;
    private JLabel passwordLabel, banknameLabel, label;
    
    
    public ModifiedBankLogin() {
        super("Please Sign in first: ");
        
        // create components
        accountNumberField = new RoundedTextField(20, 10);
        accountNumberField.setPreferredSize(new Dimension(200, 30));
        passwordField = new RoundedPasswordField(20, 10);
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton = new RoundedButton("Login", 10);
        registerButton = new RoundedButton("Register", 10);
        accountNumberLabel = new JLabel("Account Number:");
        passwordLabel = new JLabel("Password:");
        banknameLabel = new JLabel("Login now and Save Easier with UniBank");
        label = new JLabel("No Account yet? Register now");
        
        
        
        // set font for button and label
        Font font = new Font("Roboto", Font.BOLD, 16);
        Font font2 = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
        loginButton.setFont(font);
        loginButton.setBackground(Color.decode("#4f93d2"));
        loginButton.setForeground(Color.WHITE);
        registerButton.setFont(font);
        registerButton.setBackground(Color.decode("#4f93d2"));
        registerButton.setForeground(Color.WHITE);
        accountNumberLabel.setFont(font);
        accountNumberLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        banknameLabel.setFont(font2);
        banknameLabel.setForeground(Color.WHITE);
        
        // create panels
        JPanel formPanel = new JPanel();
        formPanel.setPreferredSize(new Dimension(500,500));
        JPanel panel = new JPanel(new BorderLayout());
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 50);
        
        formPanel.add(accountNumberLabel, gbc);
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(accountNumberField, gbc);
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(label,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(registerButton,gbc);
        
        
        // set the background color of the panels
        formPanel.setBackground(Color.decode("#5cbfe9"));
        
        // create image label
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Users/LENOVO E560/OneDrive/Documents/NetBeansProjects/database/src/bank/work.png"); // Set the path to your image file
        imageLabel.setIcon(imageIcon);
        
        // create main panel to hold formPanel and imageLabel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.add(banknameLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.decode("#5cbfe9"));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        
        // add main panel to frame
        pack();
        add(mainPanel);
        
        // set frame properties
        setSize(1080,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create an instance of the other class
                ModifiedRegistration userLogin = new ModifiedRegistration();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModifiedBankLogin();
        });
    }
}
