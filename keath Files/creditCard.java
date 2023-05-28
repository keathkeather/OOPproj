

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;


public class creditCard extends JFrame {

    // private CreditCardDatabaseManager dbManager = new CreditCardDatabaseManager();

    public creditCard() {
        setTitle("Credit Card Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        getContentPane().setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Card ID");
        tableModel.addColumn("Card Number");
        tableModel.addColumn("Credit Limit");
        tableModel.addColumn("Interest Rate");
        tableModel.addColumn("Payment Due Date");

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        // StyledButton addCardButton = new StyledButton("Add New Credit Card");
        // addCardButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         showAddCreditCardDialog(tableModel);
        //     }
        // });
        // buttonPanel.add(addCardButton);

        // StyledButton updateCardButton = new StyledButton("Update Credit Card");
        // updateCardButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         int selectedRow = table.getSelectedRow();
        //         if (selectedRow != -1) {
        //             int cardID = (int) table.getValueAt(selectedRow, 0);
        //             showUpdateCreditCardDialog(cardID, tableModel);
        //         } else {
        //             JOptionPane.showMessageDialog(CreditCard.this, "Please select a credit card to update.", "No Credit Card Selected", JOptionPane.WARNING_MESSAGE);
        //         }
        //     }
        // });
        // buttonPanel.add(updateCardButton);

        // StyledButton deleteCardButton = new StyledButton("Delete Credit Card");
        // deleteCardButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         int selectedRow = table.getSelectedRow();
        //         if (selectedRow != -1) {
        //             int cardID = (int) table.getValueAt(selectedRow, 0);
        //             deleteCreditCard(cardID, tableModel);
        //         } else {
        //             JOptionPane.showMessageDialog(CreditCard.this, "Please select a credit card to delete.", "No Credit Card Selected", JOptionPane.WARNING_MESSAGE);
        //         }
        //     }
        // });
        // buttonPanel.add(deleteCardButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // fetchCreditCardData(tableModel);

        NavBar NavBar = new NavBar();
        JScrollPane navBarScrollPane = new JScrollPane(NavBar);
        navBarScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(navBarScrollPane, BorderLayout.WEST);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new creditCard().setVisible(true);
            }
        });
    }
}