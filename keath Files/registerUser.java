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


class registerUser extends JPanel {
    private String dbUrl = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";

    public boolean RegisterUser(String username, String password, String firstName, String lastName, String middleInitial, String province, String zipcode, String emailAddress, String contactNumber, String birthday) {
        String className = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(className);
            System.out.println("Driver is loaded successfully.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver failed to load.");
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, this.username, this.password)) {
            // Check if the username already exists
            String checkUsernameQuery = "SELECT * FROM customer WHERE username = ?";
            try (PreparedStatement checkUsernameStatement = connection.prepareStatement(checkUsernameQuery)) {
                checkUsernameStatement.setString(1, username);
                try (ResultSet resultSet = checkUsernameStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Username already exists
                        JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.", "Username Taken", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }

            // Create SQL statement
            String sql = "INSERT INTO customer (username, password, firstName, lastName, middleInitial, province, zipcode, emailAddress, contactNumber, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameter values
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, firstName);
                statement.setString(4, lastName);
                statement.setString(5, middleInitial);
                statement.setString(6, province);
                statement.setString(7, zipcode);
                statement.setString(8, emailAddress);
                statement.setString(9, contactNumber);
                statement.setString(10, birthday);

                // Execute the statement
                int rowsInserted = statement.executeUpdate();
                System.out.print(rowsInserted);

                return rowsInserted > 0; // True if at least one row was inserted
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

