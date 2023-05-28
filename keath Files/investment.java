import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class investment extends JFrame {
    public investment() {
        setTitle("Investment Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

        // Create labels to display investment information
        JLabel totalInvestmentsLabel = new JLabel("Total Investments: $10,000");
        JLabel pendingBuyOrders = new JLabel("Pending Buy Orders: 5");
        JLabel pendingOrdersLabel = new JLabel("Pending Sell Orders: 5");

        // Create buttons for navigation
        JButton investmentProductsButton = new JButton("Investment Products");
        JButton investmentHistoryButton = new JButton("View Investment History");

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(totalInvestmentsLabel);
        panel.add(pendingBuyOrders);
        panel.add(pendingOrdersLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(investmentProductsButton);
        panel.add(investmentHistoryButton);

        // Set alignment for the labels and buttons
        totalInvestmentsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pendingBuyOrders.setAlignmentX(Component.CENTER_ALIGNMENT);
        pendingOrdersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        investmentProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        investmentHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set action listeners for the buttons
        investmentProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle button click for investment products page
                JOptionPane.showMessageDialog(investment.this, "Redirecting to Investment Products Page");
            }
        });

        investmentHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle button click for investment history page
                JOptionPane.showMessageDialog(investment.this, "Redirecting to Investment History Page");
            }
        });

        // Add the navBar to the WEST position of the frame's BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new NavBar(), BorderLayout.WEST);
        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(investment::new);
    }
}
