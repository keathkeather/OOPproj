
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
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.*;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifiedRegistration extends JPanel {
    
    private JTextField firstnameField, lastnameField, birthdateField, emailField, mobilenumberField;
    private JTextField middleinitialField, provinceField, zipcodeField, usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel firstnameLabel, lastnameLabel, birthdateLabel, emailLabel, mobilenumberLabel, passwordLabel;
    private JLabel middleinitialLabel, provinceLabel, zipcodeLabel, registerLabel,usernameLabel;

    
    // Database credentials 
    
    public ModifiedRegistration() {
        super(new BorderLayout());
        registerUser RegisterUser = new registerUser();
        // create components
        firstnameField = new modRoundedTextField(20, 10);
        firstnameField.setPreferredSize(new Dimension(200, 30));
        lastnameField = new modRoundedTextField(20, 10);
        lastnameField.setPreferredSize(new Dimension(200, 30));
        birthdateField = new modRoundedTextField(20, 10);
        birthdateField.setPreferredSize(new Dimension(200, 30));
        emailField = new modRoundedTextField(20, 10);
        emailField.setPreferredSize(new Dimension(200, 30));
        mobilenumberField = new modRoundedTextField(20, 10);
        mobilenumberField.setPreferredSize(new Dimension(200, 30));
        middleinitialField = new modRoundedTextField(20, 10);
        middleinitialField.setPreferredSize(new Dimension(200, 30));
        provinceField = new modRoundedTextField(20, 10);
        provinceField.setPreferredSize(new Dimension(200, 30));
        zipcodeField = new modRoundedTextField(20, 10);
        zipcodeField.setPreferredSize(new Dimension(200, 30));
        usernameField = new modRoundedTextField(20, 10);
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField = new RoundedPasswordField(20, 10);
        passwordField.setPreferredSize(new Dimension(200, 30));
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("Create username:");
        firstnameLabel = new JLabel("First name:");
        lastnameLabel = new JLabel("Last name:");
        birthdateLabel = new JLabel("Birth Date: yy/mm/dd");
        emailLabel = new JLabel("Email:");
        mobilenumberLabel = new JLabel("Mobile Number:");
        middleinitialLabel = new JLabel("Middle Initial:");
        provinceLabel = new JLabel("Province/State:");
        zipcodeLabel = new JLabel("Zipcode:");
        registerLabel = new JLabel("Register an Account");
        passwordLabel = new JLabel("Create password: ");
       
        
        
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Users/keath/Desktop/OOPproj/OOPproj/src/pics/register2.png"); // Set the path to your image file
        imageLabel.setIcon(imageIcon);
        
        
        // create panels
        JPanel formPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //insets (top, left, right, bottom)
        gbc.insets = new Insets(3, 3, 3, 3);
        formPanel.add(imageLabel,gbc);
        gbc.gridy = 10;
        formPanel.add(passwordLabel,gbc);
        gbc.gridy = 9;
        formPanel.add(usernameLabel,gbc);
        gbc.gridy = 8;
        formPanel.add(mobilenumberLabel,gbc);
        gbc.gridy = 7;
        formPanel.add(emailLabel, gbc);
        gbc.gridy = 6;
        formPanel.add(zipcodeLabel, gbc);
        gbc.gridy = 5;
        formPanel.add(provinceLabel, gbc);
        gbc.gridy = 4;
        formPanel.add(birthdateLabel,gbc);
        gbc.gridy = 3;
        formPanel.add(middleinitialLabel,gbc);
        gbc.gridy = 2; //2
        formPanel.add(lastnameLabel, gbc);
        gbc.gridy = 1; //1
        formPanel.add(firstnameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0; //0

        
        //textfields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(registerLabel,gbc);
        gbc.gridy = 1;
        formPanel.add(firstnameField, gbc);
        gbc.gridy = 2;
        formPanel.add(lastnameField, gbc);
        gbc.gridy = 3;
        formPanel.add(middleinitialField, gbc);
        gbc.gridy = 4;
        formPanel.add(birthdateField, gbc);
        gbc.gridy = 5;
        formPanel.add(provinceField, gbc);
        gbc.gridy = 6;
        formPanel.add(zipcodeField, gbc);
        gbc.gridy = 7;
        formPanel.add(emailField, gbc);
        gbc.gridy = 8;
        formPanel.add(mobilenumberField,gbc);
        gbc.gridy = 9;
        formPanel.add(usernameField, gbc);
        gbc.gridy = 10;
        formPanel.add(passwordField,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        formPanel.add(registerButton, gbc);
       
     
        
        
        // add panels to frame
        add(formPanel);
        
        //font and background colors
        Font font = new Font("Roboto", Font.BOLD, 16);
        Font font2 = new Font("Roboto", Font.BOLD, 32);
        registerButton.setFont(font);
        registerButton.setBackground(Color.decode("#4f93d2"));
        registerButton.setForeground(Color.WHITE);
        firstnameLabel.setFont(font);
        firstnameLabel.setForeground(Color.WHITE);
        lastnameLabel.setFont(font);
        lastnameLabel.setForeground(Color.WHITE);
        middleinitialLabel.setFont(font);
        middleinitialLabel.setForeground(Color.WHITE);
        birthdateLabel.setFont(font);
        birthdateLabel.setForeground(Color.WHITE);
        provinceLabel.setFont(font);
        provinceLabel.setForeground(Color.WHITE);
        zipcodeLabel.setFont(font);
        zipcodeLabel.setForeground(Color.WHITE);
        emailLabel.setFont(font);
        emailLabel.setForeground(Color.WHITE);
        mobilenumberLabel.setFont(font);
        mobilenumberLabel.setForeground(Color.WHITE);
        registerLabel.setFont(font2);
        registerLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.WHITE);
        formPanel.setBackground(Color.decode("#5cbfe9"));
        // set frame properties
        setSize(1080,720);

        setVisible(true);
        
        // add action listener to register button
        registerButton.addActionListener(e -> {
            // retrieve input values
            String firstName = firstnameField.getText();
            String lastName = lastnameField.getText();
            String middleInitial = middleinitialField.getText();
            String birthdate = birthdateField.getText();
            String province = provinceField.getText();
            String zipcode = zipcodeField.getText();
            String email = emailField.getText();
            String contactNumber = mobilenumberField.getText();
            String username = usernameField.getText();
            char[] passwordchar = passwordField.getPassword();
            String password = new String(passwordchar);
            // perform database ModifiedRegistration
           
            boolean success = RegisterUser.RegisterUser(username, password, firstName, lastName, middleInitial, province, zipcode, email, contactNumber,birthdate);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                // additional actions after successful ModifiedRegistration
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
            }
        });
        
        
    }

    public static void main(String[] args) {
        new ModifiedRegistration();
    }
}
