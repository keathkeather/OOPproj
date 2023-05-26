/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

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
import bank.ModifiedBankLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ModifiedRegistration extends JFrame {
    
    private JTextField firstnameField, lastnameField, birthdateField, emailField, mobilenumberField;
    private JTextField middleinitialField, cityField, provinceField, zipcodeField;
    private JButton registerButton, loginButton;
    private JLabel firstnameLabel, lastnameLabel, birthdateLabel, emailLabel, mobilenumberLabel;
    private JLabel middleinitialLabel, cityLabel, provinceLabel, zipcodeLabel, registerLabel, orLabel;
    
    // Database credentials 
    private String dbUrl = "jdbc:mysql://localhost:3306/bank";
    private String username = "root";
    private String password = "";
    public ModifiedRegistration() {
        super("Create an account: ");
        
        // create components
        firstnameField = new RoundedTextField(20, 10);
        firstnameField.setPreferredSize(new Dimension(200, 30));
        lastnameField = new RoundedTextField(20, 10);
        lastnameField.setPreferredSize(new Dimension(200, 30));
        birthdateField = new RoundedTextField(20, 10);
        birthdateField.setPreferredSize(new Dimension(200, 30));
        emailField = new RoundedTextField(20, 10);
        emailField.setPreferredSize(new Dimension(200, 30));
        mobilenumberField = new RoundedTextField(20, 10);
        mobilenumberField.setPreferredSize(new Dimension(200, 30));
        middleinitialField = new RoundedTextField(20, 10);
        middleinitialField.setPreferredSize(new Dimension(200, 30));
        cityField = new RoundedTextField(20, 10);
        cityField.setPreferredSize(new Dimension(200, 30));
        provinceField = new RoundedTextField(20, 10);
        provinceField.setPreferredSize(new Dimension(200, 30));
        zipcodeField = new RoundedTextField(20, 10);
        zipcodeField.setPreferredSize(new Dimension(200, 30));
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        firstnameLabel = new JLabel("First name:");
        lastnameLabel = new JLabel("Last name:");
        birthdateLabel = new JLabel("Birth Date:");
        emailLabel = new JLabel("Email:");
        mobilenumberLabel = new JLabel("Mobile Number:");
        middleinitialLabel = new JLabel("Middle Initial:");
        cityLabel = new JLabel("City:");
        provinceLabel = new JLabel("Province/State:");
        zipcodeLabel = new JLabel("Zipcode:");
        registerLabel = new JLabel("Register an Account");
        orLabel = new JLabel("Or");
        
        
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Users/LENOVO E560/OneDrive/Documents/NetBeansProjects/database/src/bank/register2.png"); // Set the path to your image file
        imageLabel.setIcon(imageIcon);
        
        
        // create panels
        JPanel formPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //insets (top, left, right, bottom)
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(imageLabel,gbc);
        gbc.gridy = 9;
        formPanel.add(mobilenumberLabel,gbc);
        gbc.gridy = 8;
        formPanel.add(emailLabel, gbc);
        gbc.gridy = 7;
        formPanel.add(zipcodeLabel, gbc);
        gbc.gridy = 6;
        formPanel.add(provinceLabel, gbc);
        gbc.gridy = 5;
        formPanel.add(cityLabel,gbc);
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
        formPanel.add(cityField, gbc);
        gbc.gridy = 6;
        formPanel.add(provinceField, gbc);
        gbc.gridy = 7;
        formPanel.add(zipcodeField, gbc);
        gbc.gridy = 8;
        formPanel.add(emailField, gbc);
        gbc.gridy = 9;
        formPanel.add(mobilenumberField,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        formPanel.add(registerButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.CENTER;
        formPanel.add(orLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(loginButton,gbc);
        
        
        // add panels to frame
        add(formPanel);
        
        //font and background colors
        Font font = new Font("Roboto", Font.BOLD, 16);
        Font font2 = new Font("Roboto", Font.BOLD, 32);
        registerButton.setFont(font);
        registerButton.setBackground(Color.decode("#4f93d2"));
        registerButton.setForeground(Color.WHITE);
        loginButton.setFont(font);
        loginButton.setBackground(Color.decode("#4f93d2"));
        loginButton.setForeground(Color.WHITE);
        firstnameLabel.setFont(font);
        firstnameLabel.setForeground(Color.WHITE);
        lastnameLabel.setFont(font);
        lastnameLabel.setForeground(Color.WHITE);
        middleinitialLabel.setFont(font);
        middleinitialLabel.setForeground(Color.WHITE);
        birthdateLabel.setFont(font);
        birthdateLabel.setForeground(Color.WHITE);
        cityLabel.setFont(font);
        cityLabel.setForeground(Color.WHITE);
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
        orLabel.setFont(font);
        orLabel.setForeground(Color.WHITE);
        formPanel.setBackground(Color.decode("#5cbfe9"));
        // set frame properties
        setSize(1080,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // add action listener to register button
        registerButton.addActionListener(e -> {
            // retrieve input values
            String firstName = firstnameField.getText();
            String lastName = lastnameField.getText();
            String middleInitial = middleinitialField.getText();
            String birthdate = birthdateField.getText();
            String city = cityField.getText();
            String province = provinceField.getText();
            String zipcode = zipcodeField.getText();
            String email = emailField.getText();
            String mobileNumber = mobilenumberField.getText();
            
            // perform database ModifiedRegistration
            boolean success = registerUser(firstName, lastName, middleInitial, birthdate, city, province, zipcode, email, mobileNumber);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                // additional actions after successful ModifiedRegistration
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create an instance of the other class
                ModifiedBankLogin userLogin = new ModifiedBankLogin();
            }
        });
    }
    
    private boolean registerUser(String firstName, String lastName, String middleInitial, String birthdate, String city, String province, String zipcode, String email, String mobileNumber){
        String className= "com.mysql.cj.jdbc.Driver";
        
        try{
            Class.forName(className);
            System.out.println("Driver is loaded successfully.");
        }catch(ClassNotFoundException ex){
            System.out.println("Driver failed to load.");
        }
        try {
            // establish database connection
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            
            // create SQL statement
            String sql = "INSERT INTO registration (firstname, lastname, middleinitial, birthday, city, province, zipcode, emailaddress, contactnumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // set parameter values
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, middleInitial);
            statement.setString(4, birthdate);
            statement.setString(5, city);
            statement.setString(6, province);
            statement.setString(7, zipcode);
            statement.setString(8, email);
            statement.setString(9, mobileNumber);
            
            // execute the statement
            int rowsInserted = statement.executeUpdate();
            
            // close the resources
            statement.close();
            connection.close();
            
            return rowsInserted > 0; // true if at least one row was inserted
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        new ModifiedRegistration();
    }
}
