import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;

public class getAccBalance {
    private int customerID;
    private String url = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";
   
    public getAccBalance(int customerID){
        this.customerID = customerID;
    }

    public double getBalance(){
       
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement("SELECT currentBalance FROM account_balance_view WHERE accountTypeID = 1 AND customerID = ?")) {
            statement.setInt(1, this.customerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double savings = resultSet.getDouble("currentBalance");
                    return savings;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle other exceptions here
        }
    
        return 0;
    }

}
