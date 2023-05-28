import java.sql.*;

public class checkLogin{
    public int CheckLogin(String username, String password) {
        int customerID = -1; // Default value for invalid login
        
        // Modify the following variables with your database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/oopproject";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection(jdbcURL, usernameDB, passwordDB);

            // Prepare and execute the SQL query
            String sql = "SELECT customerID FROM customer WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // Check if the login is successful
            if (resultSet.next()) {
                // Retrieve the customerID from the result set
                customerID = resultSet.getInt("customerID");
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerID;
    }

    public static void main(String[] args) {
        // Example usage
       new checkLogin();
    }
}
