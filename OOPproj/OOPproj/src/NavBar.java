

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar extends JPanel {
    private JPanel mainPanel;
    public NavBar(JPanel mainPanel,int customerID) {
        setBackground(Color.decode("#005176"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Navigation");
        titleLabel.setForeground(Color.decode("#FDFFFF"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        add(titleLabel);

        String[] navLabels = {"Client", "Savings", "Checking Accounts", "Transactions",
                "Loans", "Credit Cards", "Bill Payments", "Investments"};

        JPanel referenceBox = new JPanel();
        referenceBox.setLayout(new BorderLayout());
        referenceBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel referenceLabel = new JLabel("Checking Accounts");
        referenceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        referenceBox.add(referenceLabel, BorderLayout.CENTER);
        Dimension referenceSize = referenceBox.getPreferredSize();
        referenceSize.width += 35;

        for (String label : navLabels) {
            JPanel navBox = new JPanel();
            navBox.setBackground(Color.decode("#4F93D2"));
            navBox.setLayout(new BorderLayout());
            navBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            navBox.setPreferredSize(referenceSize);

            JButton navButton = new JButton(label);
            navButton.setForeground(Color.decode("#FDFFFF"));
            navButton.setFont(new Font("Arial", Font.PLAIN, 14));
            navButton.setBackground(Color.decode("#4F93D2"));
            navButton.setBorderPainted(false);
            navButton.setFocusPainted(false);
            if (label.equals("Savings")) {
                navButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.removeAll(); // Remove any existing components from the main panel
                        mainPanel.add(new SavingsMenu(customerID)); // Add the creditCard panel to the main panel
                        mainPanel.revalidate(); // Revalidate the main panel to update the layout
                        mainPanel.repaint(); // Repaint the main panel
                    }
                });
            }
            else if (label.equals("Credit Cards")) {
                navButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.removeAll(); // Remove any existing components from the main panel
                        mainPanel.add(new creditCard(customerID)); // Add the creditCard panel to the main panel
                        mainPanel.revalidate(); // Revalidate the main panel to update the layout
                        mainPanel.repaint(); // Repaint the main panel
                    }
                });
            }
            else if (label.equals("Transactions")) {
                navButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.removeAll(); // Remove any existing components from the main panel
                        mainPanel.add(new creditCard(customerID)); // Add the creditCard panel to the main panel
                        mainPanel.revalidate(); // Revalidate the main panel to update the layout
                        mainPanel.repaint(); // Repaint the main panel
                    }
                });
            }

            
            else if (label.equals("Bill Payments")) {
                navButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Window) getRootPane().getParent()).dispose();
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                // BillPayments billPayments = new BillPayments();
                                // billPayments.setVisible(true);
                            }
                        });
                    }
                });
            } else if (label.equals("Investments")) {
                navButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Window) getRootPane().getParent()).dispose();
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                // Investment investment = new Investment();
                                // investment.setVisible(true);
                            }
                        });
                    }
                });
            }

            navBox.add(navButton, BorderLayout.CENTER);

            JPanel boxContainer = new JPanel();
            boxContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
            boxContainer.setBackground(Color.decode("#005176"));
            boxContainer.add(navBox);

            add(boxContainer);
        }

        setPreferredSize(new Dimension(200, 600));
    }
}
