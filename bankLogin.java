/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class bankLogin extends JFrame {
    
    private JTextField accountNumberField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel accountNumberLabel;
    private JLabel passwordLabel, banknameLabel, signinLabel;
    
    public bankLogin() {
        super("Please Sign in first: ");
        
        // create components
        accountNumberField = new JTextField(20);
        accountNumberField.setPreferredSize(new Dimension(200, 30));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton = new JButton("Login");
        accountNumberLabel = new JLabel("Account Number:");
        passwordLabel = new JLabel("Password:");
        
        // create panels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //insets (top, left, right, bottom)
        gbc.insets = new Insets(5, 5, 5, 5);

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
        
        // add panels to frame
        add(formPanel);
        //add(buttonPanel, BorderLayout.SOUTH);
        
        // set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new bankLogin();
    }
}
