import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;

public class getAccountID {
    private int customerID;
    private String url = "jdbc:mysql://localhost:3306/oopproject";
    private String username = "root";
    private String password = "";
   
    public getAccountID(int customerID){
        this.customerID = customerID;
    }

    public int getID(){
       
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement("SELECT accountID FROM account WHERE accountTypeID = 1 AND customerID = ?")) {
            statement.setInt(1, this.customerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int accID = resultSet.getInt("accountID");
                    return accID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle other exceptions here
        }
    
        return 0;
    }

}
