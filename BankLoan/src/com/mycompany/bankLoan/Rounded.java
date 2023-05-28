//Bataluna, For Rounded UI Components

package com.mycompany.bankLoan;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Rounded {
    public static class RoundedTextField extends JTextField {
        private int cornerRadius;

        public RoundedTextField(int columns, int cornerRadius) {
            super(columns);
            this.cornerRadius = cornerRadius;
            setOpaque(false);
            setBorder(null);
            setHorizontalAlignment(CENTER);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            DefaultCaret caret = (DefaultCaret) getCaret();
            caret.setBlinkRate(500);

            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        public void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

    public static class RoundedButton extends JButton {

        private int cornerRadius;

        public RoundedButton(String text, int cornerRadius) {
            super(text);
            this.cornerRadius = cornerRadius;
            setOpaque(false);
            setBorder(null);

            Color backgroundColor = Color.decode("#4f93d2");
            setBackground(backgroundColor);

            setForeground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    public static class RoundedTextArea extends JTextArea {
        private int cornerRadius;
        private int borderThickness;

        public RoundedTextArea(int rows, int columns, int cornerRadius, int borderThickness) {
            super(rows, columns);
            this.cornerRadius = cornerRadius;
            this.borderThickness = borderThickness;
            setOpaque(false);
            setBorder(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            // Draw the border
            Color borderColor = Color.decode("#4f93d2");
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            super.paintComponent(g2);
            g2.dispose();
        }

    }

    // Create a custom border with a specified thickness
    // Unused
    private class CustomBorder implements Border {
        private int thickness;

        public CustomBorder(int thickness) {
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < thickness; i++) {
                g.drawRect(x + i, y + i, width - 2 * i - 1, height - 2 * i - 1);
            }
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }

}
