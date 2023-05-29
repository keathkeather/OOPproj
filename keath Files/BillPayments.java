
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillPayments extends JFrame {
    private BillPaymentsDatabaseManager dbManager;
    private JLabel billerLabel;
    private JComboBox<String> billerComboBox;
    private JLabel amountLabel;
    private JTextField amountField;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel paymentMethodLabel;
    private JRadioButton creditButton;
    private JRadioButton debitButton;

    public BillPayments() {
        dbManager = new BillPaymentsDatabaseManager();

        setTitle("Bill Payments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

        Color skyBlue = new Color(51, 122, 183);
        Color darkBlue = new Color(0, 32, 63);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(skyBlue);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(skyBlue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        billerLabel = new JLabel("Select Biller:");
        billerLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        billerLabel.setForeground(Color.WHITE);

        billerComboBox = new JComboBox<>(new String[] {
                "PLDT", "Globe Telecom", "VECO", "Cebu Water",
                "Smart Communications", "Sun Cellular", "Sky Cable", "Converge ICT"
        });
        billerComboBox.setMaximumSize(billerComboBox.getPreferredSize());

        amountLabel = new JLabel("Payment Amount:");
        amountLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        amountLabel.setForeground(Color.WHITE);

        amountField = new JTextField(20);
        amountField.setMaximumSize(amountField.getPreferredSize());

        dateLabel = new JLabel("Payment Date:");
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        dateLabel.setForeground(Color.WHITE);

        dateField = new JTextField(20);
        dateField.setMaximumSize(dateField.getPreferredSize());

        paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        paymentMethodLabel.setForeground(Color.WHITE);

        creditButton = new JRadioButton("Credit");
        creditButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        creditButton.setBackground(skyBlue);
        creditButton.setForeground(Color.WHITE);

        debitButton = new JRadioButton("Debit");
        debitButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        debitButton.setBackground(skyBlue);
        debitButton.setForeground(Color.WHITE);

        JPanel paymentMethodPanel = new JPanel(new GridLayout(3, 1));
        paymentMethodPanel.setBackground(skyBlue);
        paymentMethodPanel.add(creditButton);
        paymentMethodPanel.add(debitButton);

        mainPanel.add(billerLabel, gbc);
        mainPanel.add(billerComboBox, gbc);
        mainPanel.add(amountLabel, gbc);
        mainPanel.add(amountField, gbc);
        mainPanel.add(dateLabel, gbc);
        mainPanel.add(dateField, gbc);
        mainPanel.add(paymentMethodLabel, gbc);
        mainPanel.add(paymentMethodPanel, gbc);

        contentPanel.add(mainPanel);

        StyledButton payButton = new StyledButton("Pay");
        payButton.setBackground(darkBlue);
        payButton.setForeground(Color.WHITE);
        paymentMethodPanel.add(payButton);

        payButton.addActionListener(e -> performPaymentOperation());

        StyledButton updateButton = new StyledButton("Update bill payment");
        updateButton.setBackground(darkBlue);
        updateButton.setForeground(Color.WHITE);

        updateButton.addActionListener(e -> {
            String customerID = JOptionPane.showInputDialog(this, "Enter Customer ID to Update:");
            performUpdateOperation(customerID,
                    (String) billerComboBox.getSelectedItem(),
                    Double.parseDouble(amountField.getText()),
                    dateField.getText(),
                    creditButton.isSelected() ? "Credit" : "Debit");
        });

        StyledButton deleteButton = new StyledButton("Delete bill payment");
        deleteButton.setBackground(darkBlue);
        deleteButton.setForeground(Color.WHITE);

        deleteButton.addActionListener(e -> {
            String customerID = JOptionPane.showInputDialog(this, "Enter Customer ID to Delete:");
            performDeleteOperation(customerID);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(skyBlue);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        contentPanel.add(buttonPanel);

        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // NavBar navBar = new NavBar();

        // JScrollPane navBarScrollPane = new JScrollPane(navBar);
        // navBarScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // getContentPane().add(navBarScrollPane, BorderLayout.WEST);
    }

    private void performPaymentOperation() {
        String biller = (String) billerComboBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        String date = dateField.getText();
        String paymentMethod = creditButton.isSelected() ? "Credit" : "Debit";

        dbManager.createBillPayment(biller, amount, date, paymentMethod);

        JOptionPane.showMessageDialog(this,
                "Payment of " + amount + " pesos" + " to " + biller + " on " + date + " through " + paymentMethod
                        + " was successful.",
                "Payment Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void performUpdateOperation(String customerID, String biller, double amount, String date,
            String paymentMethod) {
        boolean customerExists = dbManager.checkCustomerIDExists(customerID);
        if (customerExists) {
            dbManager.updateBillPayment(customerID, biller, amount, date, paymentMethod);
            JOptionPane.showMessageDialog(this,
                    "Bill payment with Customer ID " + customerID + " has been successfully updated.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Customer ID " + customerID + " does not exist.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performDeleteOperation(String customerID) {
        boolean customerExists = dbManager.checkCustomerIDExists(customerID);
        if (customerExists) {
            dbManager.deleteBillPayment(customerID);
            JOptionPane.showMessageDialog(this,
                    "Bill payment with Customer ID " + customerID + " has been successfully deleted.",
                    "Deletion Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Customer ID " + customerID + " does not exist.",
                    "Deletion Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BillPayments().setVisible(true);
    }
}
