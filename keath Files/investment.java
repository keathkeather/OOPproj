
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class investment extends JFrame {
    private JLabel marketValueLabel;
    private JLabel buyOrdersLabel;
    private JLabel sellOrdersLabel;
    private JLabel pendingOrdersLabel;
    // private InvestmentPaymentsDatabaseManager dbManager;

    public investment() {
        // dbManager = new InvestmentPaymentsDatabaseManager();

        setTitle("Investments");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        Color skyBlue = new Color(51, 122, 183);
        Color darkBlue = new Color(0, 32, 63);
        Color white = Color.WHITE;

        getContentPane().setBackground(skyBlue);

        setLayout(new BorderLayout());
        // NavBar navBar = new NavBar();
        // add(navBar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(skyBlue);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel titleLabel = new JLabel("Invest Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel marketValueTitleLabel = new JLabel("Total market value of investments:");
        marketValueTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        marketValueTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(marketValueTitleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel marketValueContainer = new JPanel();
        marketValueContainer.setBackground(white);
        marketValueContainer.setMaximumSize(new Dimension(150, 100)); // mao ni ang container size change lang nya kung
                                                                      // unsa ang ganahan
        marketValueContainer.setLayout(new BorderLayout());
        marketValueLabel = new JLabel("PHP 0.00");
        marketValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        marketValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        marketValueContainer.add(marketValueLabel, BorderLayout.CENTER);

        contentPanel.add(marketValueContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        pendingOrdersLabel = new JLabel("Pending Orders");
        pendingOrdersLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pendingOrdersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(pendingOrdersLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel buyOrdersTitleLabel = new JLabel("Total Buy Orders:");
        buyOrdersTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        buyOrdersTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(buyOrdersTitleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel buyOrdersContainer = new JPanel();
        buyOrdersContainer.setBackground(white);
        buyOrdersContainer.setMaximumSize(new Dimension(150, 100));
        buyOrdersContainer.setLayout(new BorderLayout());

        buyOrdersLabel = new JLabel("PHP 0.00");
        buyOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buyOrdersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        buyOrdersContainer.add(buyOrdersLabel, BorderLayout.CENTER);

        contentPanel.add(buyOrdersContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel sellOrdersTitleLabel = new JLabel("Total Sell Orders:");
        sellOrdersTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        sellOrdersTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(sellOrdersTitleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel sellOrdersContainer = new JPanel();
        sellOrdersContainer.setBackground(white);
        sellOrdersContainer.setMaximumSize(new Dimension(150, 100));
        sellOrdersContainer.setLayout(new BorderLayout());

        sellOrdersLabel = new JLabel("PHP 0.00");
        sellOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sellOrdersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sellOrdersContainer.add(sellOrdersLabel, BorderLayout.CENTER);

        contentPanel.add(sellOrdersContainer);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        StyledButton viewProductsButton = new StyledButton("View Investment Products");
        viewProductsButton.setBackground(darkBlue);
        viewProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInvestmentProductsDialog();
            }
        });
        contentPanel.add(viewProductsButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        StyledButton viewTransactionHistoryButton = new StyledButton("View Investment Transaction History");
        viewTransactionHistoryButton.setBackground(darkBlue);
        viewTransactionHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(viewTransactionHistoryButton);

        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private double getProductValue(String productName) {
        // g default 1k lang sa nako ang value sa mga products
        return 1000.00;
    }

    private void openInvestmentProductsDialog() {
        JDialog dialog = new JDialog(this, "Investment Products", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        String[] investmentProducts = {
                "CodeChum", "CIT Lair", "Bank of the Philippines",
                "Globe Telecom", "Smart Telecom", "TalkNText Telecom"
        };

        JComboBox<String> productComboBox = new JComboBox<>(investmentProducts);
        JLabel productValueLabel = new JLabel();
        JButton buyButton = new JButton("Buy");

        productValueLabel.setText("Value: P" + getProductValue(investmentProducts[0]));

        productComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) productComboBox.getSelectedItem();
                double productValue = getProductValue(selectedProduct);
                productValueLabel.setText("Value: " + productValue);
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) productComboBox.getSelectedItem();
                double productValue = getProductValue(selectedProduct);
                // dbManager.createInvestment(selectedProduct, productValue, "2023-05-28"); //
                // default gapon ang date d ko
                // kbaw unsaon na hahahah
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(productComboBox);
        panel.add(productValueLabel);
        panel.add(buyButton);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new investment().setVisible(true);
    }
}
