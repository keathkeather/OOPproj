/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.*;

public class registration extends JFrame {
    
    private JTextField firstnameField, lastnameField, birthdateField, emailField, mobilenumberField;
    private JTextField middleinitialField, cityField, provinceField, zipcodeField;
    private JButton registerButton;
    private JLabel firstnameLabel, lastnameLabel, birthdateLabel, emailLabel, mobilenumberLabel;
    private JLabel middleinitialLabel, cityLabel, provinceLabel, zipcodeLabel;
    public registration() {
        super("Create an account: ");
        
        // create components
        firstnameField = new JTextField(20);
        firstnameField.setPreferredSize(new Dimension(200, 30));
        lastnameField = new JTextField(20);
        lastnameField.setPreferredSize(new Dimension(200, 30));
        birthdateField = new JTextField(20);
        birthdateField.setPreferredSize(new Dimension(200, 30));
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
        mobilenumberField = new JTextField(20);
        mobilenumberField.setPreferredSize(new Dimension(200, 30));
        middleinitialField = new JTextField(20);
        middleinitialField.setPreferredSize(new Dimension(200, 30));
        cityField = new JTextField(20);
        cityField.setPreferredSize(new Dimension(200, 30));
        provinceField = new JTextField(20);
        provinceField.setPreferredSize(new Dimension(200, 30));
        zipcodeField = new JTextField(20);
        zipcodeField.setPreferredSize(new Dimension(200, 30));
        registerButton = new JButton("Register");
        firstnameLabel = new JLabel("First name:");
        lastnameLabel = new JLabel("Last name:");
        birthdateLabel = new JLabel("Birth Date:");
        emailLabel = new JLabel("Email:");
        mobilenumberLabel = new JLabel("Mobile Number:");
        middleinitialLabel = new JLabel("Middle Initial:");
        cityLabel = new JLabel("City:");
        provinceLabel = new JLabel("Province/State:");
        zipcodeLabel = new JLabel("Zipcode:");
        
        // create panels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //insets (top, left, right, bottom)
        gbc.insets = new Insets(5, 5, 5, 5);
        
        formPanel.add(firstnameLabel,gbc);
        gbc.gridy = 8;
        formPanel.add(mobilenumberLabel, gbc);
        gbc.gridy = 7;
        formPanel.add(emailLabel, gbc);
        gbc.gridy = 6;
        formPanel.add(zipcodeLabel, gbc);
        gbc.gridy = 5;
        formPanel.add(provinceLabel,gbc);
        gbc.gridy = 4;
        formPanel.add(cityLabel,gbc);
        gbc.gridy = 3;
        formPanel.add(birthdateLabel,gbc);
        gbc.gridy = 2; //2
        formPanel.add(middleinitialLabel, gbc);
        gbc.gridy = 1; //1
        formPanel.add(lastnameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0; //0

        
        //textfields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(firstnameField, gbc);
        gbc.gridy = 1;
        formPanel.add(lastnameField, gbc);
        gbc.gridy = 2;
        formPanel.add(middleinitialField, gbc);
        gbc.gridy = 3;
        formPanel.add(birthdateField, gbc);
        gbc.gridy = 4;
        formPanel.add(cityField, gbc);
        gbc.gridy = 5;
        formPanel.add(provinceField, gbc);
        gbc.gridy = 6;
        formPanel.add(zipcodeField, gbc);
        gbc.gridy = 7;
        formPanel.add(emailField, gbc);
        gbc.gridy = 8;
        formPanel.add(mobilenumberField,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(registerButton, gbc);
        
        // add panels to frame
        add(formPanel);
        
        
        // set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new registration();
    }
}
