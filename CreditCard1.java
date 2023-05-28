package AssignedFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditCard extends JFrame {
    private CreditCardDatabaseManager dbManager;
    private JLabel remainingBalanceLabel;
    private JLabel currentLimitLabel;
    private JLabel paymentDueLabel;

    public CreditCard() {
        dbManager = new CreditCardDatabaseManager();

        setTitle("Credit Card Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        Color skyBlue = new Color(51, 122, 183);
        Color darkBlue = new Color(0, 32, 63);
        Color white = Color.WHITE;

        getContentPane().setBackground(skyBlue);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(skyBlue);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel remainingBalanceTitle = new JLabel("Remaining Balance:");
        remainingBalanceTitle.setFont(new Font("Arial", Font.BOLD, 24));
        remainingBalanceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(remainingBalanceTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel remainingBalanceContainer = new JPanel();
        remainingBalanceContainer.setBackground(white);
        remainingBalanceContainer.setMaximumSize(new Dimension(150, 100));
        remainingBalanceContainer.setLayout(new BorderLayout());

        remainingBalanceLabel = new JLabel();
        updateRemainingBalanceLabel();
        remainingBalanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        remainingBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        remainingBalanceContainer.add(remainingBalanceLabel, BorderLayout.CENTER);

        contentPanel.add(remainingBalanceContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel currentLimitTitle = new JLabel("Current Limit:");
        currentLimitTitle.setFont(new Font("Arial", Font.BOLD, 24));
        currentLimitTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(currentLimitTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel currentLimitContainer = new JPanel();
        currentLimitContainer.setBackground(white);
        currentLimitContainer.setMaximumSize(new Dimension(150, 100));
        currentLimitContainer.setLayout(new BorderLayout());

        currentLimitLabel = new JLabel();
        updateCurrentLimitLabel();
        currentLimitLabel.setFont(new Font("Arial", Font.BOLD, 20));
        currentLimitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentLimitContainer.add(currentLimitLabel, BorderLayout.CENTER);

        contentPanel.add(currentLimitContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel paymentDueTitle = new JLabel("Payment Due Date:");
        paymentDueTitle.setFont(new Font("Arial", Font.BOLD, 24));
        paymentDueTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(paymentDueTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel paymentDueContainer = new JPanel();
        paymentDueContainer.setBackground(white);
        paymentDueContainer.setMaximumSize(new Dimension(150, 100));
        paymentDueContainer.setLayout(new BorderLayout());

        paymentDueLabel = new JLabel();
        updatePaymentDueLabel();
        paymentDueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        paymentDueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentDueContainer.add(paymentDueLabel, BorderLayout.CENTER);

        contentPanel.add(paymentDueContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        StyledButton transactionHistoryButton = new StyledButton("Transaction History");
        transactionHistoryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        transactionHistoryButton.setBackground(darkBlue);
        transactionHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // e add lang nya dri ang trasactionHistory na code 
            }
        });
        transactionHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(transactionHistoryButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(skyBlue);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        StyledButton createButton = new StyledButton("Create Credit Card");
        createButton.setFont(new Font("Arial", Font.PLAIN, 15));
        createButton.setBackground(darkBlue);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] cardTypes = dbManager.getDefaultCardTypes();
                JComboBox<String> cardTypeBox = new JComboBox<>(cardTypes);
                int result = JOptionPane.showConfirmDialog(null, cardTypeBox, "Please select a card type", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String cardType = (String) cardTypeBox.getSelectedItem();
                    if (dbManager.createNewCreditCard(500.0, 1000.0, "2023-12-31", cardType)) {
                        JOptionPane.showMessageDialog(null, "Credit Card creation is successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        updateRemainingBalanceLabel();
                        updateCurrentLimitLabel();
                        updatePaymentDueLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in creating the Credit Card!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));

        StyledButton deleteButton = new StyledButton("Delete Credit Card");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 15));
        deleteButton.setBackground(darkBlue);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] cards = dbManager.getCreditCards(); 
                JComboBox<String> cardBox = new JComboBox<>(cards);
                int result = JOptionPane.showConfirmDialog(null, cardBox, "Please select a card to delete", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String cardInfo = (String) cardBox.getSelectedItem();
                    int cardId = Integer.parseInt(cardInfo.split(":")[0]); // Assuming that the cardInfo string starts with the card_id.
                    if (dbManager.deleteCreditCard(cardId)) {
                        JOptionPane.showMessageDialog(null, "Credit Card deletion is successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        updateRemainingBalanceLabel();
                        updateCurrentLimitLabel();
                        updatePaymentDueLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in deleting the Credit Card!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(deleteButton);

        contentPanel.add(buttonPanel);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        NavBar navBar = new NavBar();
        JScrollPane navBarScrollPane = new JScrollPane(navBar);
        navBarScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(navBarScrollPane, BorderLayout.WEST);
    }

    private void updateRemainingBalanceLabel() {
        double balance = dbManager.getRemainingBalance(2);  // g assume rani nako sila dri na cardID 2 pero e connect lang nya nis changes sa database
        remainingBalanceLabel.setText("P" + balance);
    }

    private void updateCurrentLimitLabel() {
        double limit = dbManager.getCurrentLimit(2);  
        currentLimitLabel.setText("P" + limit);
    }

    private void updatePaymentDueLabel() {
        String dueDate = dbManager.getPaymentDueDate(3); 
        paymentDueLabel.setText(dueDate);
    }

    public static void main(String[] args) {
        new CreditCard().setVisible(true);
    }
}
