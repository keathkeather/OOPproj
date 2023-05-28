

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardDatabaseManager {

    private Connection conn;

    public CreditCardDatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "");  
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public double getRemainingBalance(int customerID) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT currentBalance FROM account_balance_view WHERE customerID  = ? AND accountTypeID = 2");
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble("currentBalance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // mo return -1 if naay error
    }

    public double getCurrentLimit(int customerID) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT creditLimit FROM account WHERE customerID = ? AND accountTypeID = 2");
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble("creditLimit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // mo return -1 if naay error
    }

    public String getPaymentDueDate(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT payment_due_date FROM account WHERE customerID=?");
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDate("payment_due_date").toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String[] getDefaultCardTypes() {
        return new String[] {"Visa", "MasterCard", "American Express", "Discover"};
    }

    public String[] getCreditCards() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT customerID FROM account");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> cards = new ArrayList<>();
            while(rs.next()) {
                cards.add(rs.getInt("customerID") + ": " + rs.getString("card_type"));
            }
            return cards.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0]; // mo return of empty ang array if naay error
    }

    public boolean createNewCreditCard(int customerID, double limit, String dueDate) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO account (customerID, creditLimit, payment_due_date) VALUES (?,?,?)");
            ps.setDouble(1, customerID);
            ps.setDouble(2, limit);
            ps.setString(3, dueDate);
          
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCreditCard(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM account WHERE creditID=? AND accountTypeID = 2");
            ps.setInt(1, cardId);
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
