
import java.sql.*;

public class BillPaymentsDatabaseManager {
    private Connection conn;

    public BillPaymentsDatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tompar", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBillPayment(String biller, double amount, String date, String paymentMethod) {
        String sql = "INSERT INTO billpayments (biller, amount, date, payment_method) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, biller);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, date);
            pstmt.setString(4, paymentMethod);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBillPayment(String customerID, String biller, double amount, String date, String paymentMethod) {
        String sql = "UPDATE billpayments SET biller = ?, amount = ?, date = ?, payment_method = ? WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, biller);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, date);
            pstmt.setString(4, paymentMethod);
            pstmt.setString(5, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillPayment(String customerID) {
        String sql = "DELETE FROM billpayments WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCustomerIDExists(String customerID) {
        String sql = "SELECT * FROM billpayments WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
