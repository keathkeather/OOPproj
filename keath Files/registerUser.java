
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
        
            // Create SQL statement to insert into customer table
            String insertCustomerSql = "INSERT INTO customer (username, password, firstName, lastName, middleInitial, province, zipcode, emailAddress, contactNumber, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertCustomerStatement = connection.prepareStatement(insertCustomerSql, Statement.RETURN_GENERATED_KEYS)) {
                // Set parameter values for customer table
                insertCustomerStatement.setString(1, username);
                insertCustomerStatement.setString(2, password);
                insertCustomerStatement.setString(3, firstName);
                insertCustomerStatement.setString(4, lastName);
                insertCustomerStatement.setString(5, middleInitial);
                insertCustomerStatement.setString(6, province);
                insertCustomerStatement.setString(7, zipcode);
                insertCustomerStatement.setString(8, emailAddress);
                insertCustomerStatement.setString(9, contactNumber);
                insertCustomerStatement.setString(10, birthday);
        
                // Execute the statement and retrieve the generated customer ID
                int rowsInserted = insertCustomerStatement.executeUpdate();
                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = insertCustomerStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int customerId = generatedKeys.getInt(1);
                            System.out.println("Customer ID: " + customerId);
        
                            // Create SQL statement to insert into account table
                            String insertAccountSql = "INSERT INTO account (customerID) VALUES (?)";
                            try (PreparedStatement insertAccountStatement = connection.prepareStatement(insertAccountSql)) {
                                // Set parameter value for account table
                                insertAccountStatement.setInt(1, customerId);
        
                                // Execute the statement
                                int rowsInsertedAccount = insertAccountStatement.executeUpdate();
                                System.out.println("Rows inserted into account table: " + rowsInsertedAccount);
                            }
        
                            return true;
                        }
                    }
                }
                return rowsInserted > 0; // True if at least one row was inserted
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}