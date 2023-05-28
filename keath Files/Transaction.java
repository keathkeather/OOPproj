import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transaction {
    private int accountID;
    private int customerID;
    private double transactionAmount;
    private String transactionDesc;

    public Transaction(int accountID, int customerID, double transactionAmount, String transactionDesc) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.transactionAmount = transactionAmount;
        this.transactionDesc = transactionDesc;
    }

    public void recordTransaction() {
        // Provide your database connection details
        String url = "jdbc:mysql://localhost:3306/oopproject";
        String username = "root";
        String password = "";

        // Create a connection and prepared statement
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO transactionLog " +
                     "(transactionDate, transactionAmount, TransactionDesc, accountID, customerID) " +
                     "VALUES (?, ?, ?, ?, ?)")) {

            // Set the parameter values for the prepared statement
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // current timestamp
            statement.setDouble(2, transactionAmount);
            statement.setString(3, transactionDesc);
            statement.setInt(4, accountID);
            statement.setInt(5, customerID);

            // Execute the query
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction recorded successfully.");
            } else {
                System.out.println("Failed to record the transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int accountID = 12345;
        int customerID = 54321;
        double transactionAmount = 1000.0;
        String transactionDesc = "Transaction Description";

        Transaction transaction = new Transaction(accountID, customerID, transactionAmount, transactionDesc);
        transaction.recordTransaction();
    }
}
