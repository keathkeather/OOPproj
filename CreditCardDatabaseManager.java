package AssignedFeature;

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tompar", "root", "");  
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public double getRemainingBalance(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT remaining_balance FROM creditcards WHERE card_id=?");
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble("remaining_balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // mo return -1 if naay error
    }

    public double getCurrentLimit(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT current_limit FROM creditcards WHERE card_id=?");
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble("current_limit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // mo return -1 if naay error
    }

    public String getPaymentDueDate(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT payment_due_date FROM creditcards WHERE card_id=?");
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
            PreparedStatement ps = conn.prepareStatement("SELECT card_id, card_type FROM creditcards");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> cards = new ArrayList<>();
            while(rs.next()) {
                cards.add(rs.getInt("card_id") + ": " + rs.getString("card_type"));
            }
            return cards.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0]; // mo return of empty ang array if naay error
    }

    public boolean createNewCreditCard(double balance, double limit, String dueDate, String cardType) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO creditcards (remaining_balance, current_limit, payment_due_date, card_type) VALUES (?,?,?,?)");
            ps.setDouble(1, balance);
            ps.setDouble(2, limit);
            ps.setString(3, dueDate);
            ps.setString(4, cardType);
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCreditCard(int cardId) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM creditcards WHERE card_id=?");
            ps.setInt(1, cardId);
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
