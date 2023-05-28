import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class investmentProducts extends JFrame {
    public investmentProducts() {
        setTitle("Investment Products");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a panel to hold the product boxes
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create product boxes
        ProductBox stockBox = new ProductBox("Stocks", "stock.png");
        ProductBox bondBox = new ProductBox("Bonds", "bond.png");
        ProductBox mutualFundBox = new ProductBox("Mutual Funds", "mutual_fund.png");
        ProductBox realEstateBox = new ProductBox("Real Estate", "real_estate.png");

        // Add product boxes to the panel
        panel.add(stockBox);
        panel.add(bondBox);
        panel.add(mutualFundBox);
        panel.add(realEstateBox);

        // Set the panel as the content pane
        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(investmentProducts::new);
    }

    // Inner class representing a product box
    class ProductBox extends JPanel {
        public ProductBox(String productName, String imageName) {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(200, 250));
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // Load and scale the product image
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imageName));
            Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            // Create the label for the product name
            JLabel nameLabel = new JLabel(productName);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            // Add the components to the product box
            add(imageLabel, BorderLayout.CENTER);
            add(nameLabel, BorderLayout.SOUTH);
        }
    }
   
}
