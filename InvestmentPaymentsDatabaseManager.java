/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AssignedFeature;

import java.sql.*;

public class InvestmentPaymentsDatabaseManager {
    private Connection conn;

    public InvestmentPaymentsDatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tompar", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createInvestment(String investmentProduct, double productValue, String purchaseDate) {
        String sql = "INSERT INTO Investment (investmentProduct, productValue, purchaseDate) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, investmentProduct);
            pstmt.setDouble(2, productValue);
            pstmt.setString(3, purchaseDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInvestment(int customerID, String investmentProduct, double productValue, String purchaseDate) {
        String sql = "UPDATE Investment SET investmentProduct = ?, productValue = ?, purchaseDate = ? WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, investmentProduct);
            pstmt.setDouble(2, productValue);
            pstmt.setString(3, purchaseDate);
            pstmt.setInt(4, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInvestment(int customerID) {
        String sql = "DELETE FROM Investment WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCustomerIDExists(int customerID) {
        String sql = "SELECT * FROM Investment WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
