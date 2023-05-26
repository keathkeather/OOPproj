/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AssignedFeature;

/**
 *
 * @author Simoun
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreditCardDatabaseManager {
    private Connection connection;

    public CreditCardDatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Simoun\\Documents\\NetBeansProjects\\OOPproject\\Tompar\\dboopproject.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public ResultSet fetchCreditCardData() {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM creditcard");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addCreditCard(String cardNumber, double creditLimit, double interestRate, String paymentDueDate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO creditcard (cardNumber, creditLimit, interestRate, paymentDueDate) VALUES (?, ?, ?, ?)"
            );

            statement.setString(1, cardNumber);
            statement.setDouble(2, creditLimit);
            statement.setDouble(3, interestRate);
            statement.setString(4, paymentDueDate);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCreditCard(int cardID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM creditcard WHERE cardID = ?");
            statement.setInt(1, cardID);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCreditCard(int cardID, String cardNumber, double creditLimit, double interestRate, String paymentDueDate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE creditcard SET cardNumber = ?, creditLimit = ?, interestRate = ?, paymentDueDate = ? WHERE cardID = ?"
            );

            statement.setString(1, cardNumber);
            statement.setDouble(2, creditLimit);
            statement.setDouble(3, interestRate);
            statement.setString(4, paymentDueDate);
            statement.setInt(5, cardID);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCreditCard(int cardID) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM creditcard WHERE cardID = ?"
            );

            statement.setInt(1, cardID);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
