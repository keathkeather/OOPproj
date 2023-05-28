import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TransactionLog extends JFrame {
    JTable logTable;
    DefaultTableModel tableModel;

    public TransactionLog(int customerInt) {
        setTitle("Transaction Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

        // Create a table model with columns for transaction data
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Transaction Amount");
        tableModel.addColumn("Transaction Description");
        tableModel.addColumn("Transaction Type");

        // Create a table using the table model
        logTable = new JTable(tableModel);
        logTable.setEnabled(false);

        // Set the font size for the table
        Font font = new Font("Arial", Font.PLAIN, 16); // Adjust the font size as needed
        logTable.setFont(font);

        // Create a scroll pane to contain the table
        JScrollPane scrollPane = new JScrollPane(logTable);

        getContentPane().setLayout(new BorderLayout());
        // getContentPane().add(new NavBar(customerInt), BorderLayout.WEST);
        getContentPane().add(scrollPane);

        // Fetch and display the transaction log
        fetchTransactionLog();

        setVisible(true);
    }

    private void fetchTransactionLog() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");

            // Prepare the SQL statement to fetch transaction data with account and account
            // type details
            String sql = "SELECT tl.transactionDate, tl.transactionAmount, tl.TransactionDesc, at.accountTypeDesc " +
                    "FROM transactionlog tl " +
                    "JOIN account a ON tl.accountID = a.accountID " +
                    "JOIN accounttype at ON a.accountTypeID = at.accountTypeID " +
                    "WHERE tl.customerID = 2";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultSet = statement.executeQuery();

            // Iterate over the result set and populate the table with transaction details
            while (resultSet.next()) {
                String transactionDate = resultSet.getString("transactionDate");
                double transactionAmount = resultSet.getDouble("transactionAmount");
                String transactionDesc = resultSet.getString("TransactionDesc");
                String accountTypeDesc = resultSet.getString("accountTypeDesc");

                // Determine the transaction type based on the account type description
                String transactionType;
                if (accountTypeDesc != null) {
                    if (accountTypeDesc.equalsIgnoreCase("credit")) {
                        transactionType = "Credit";
                    } else if (accountTypeDesc.equalsIgnoreCase("debit")) {
                        transactionType = "Debit";
                    } else {
                        transactionType = "Checking";
                    }
                } else {
                    transactionType = "Unknown";
                }

                // Add a row to the table model with the transaction data
                System.out.println(transactionType);
                tableModel
                        .addRow(new Object[] { transactionDate, transactionAmount, transactionDesc, transactionType });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // new TransactionLog();
        });
    }
}
