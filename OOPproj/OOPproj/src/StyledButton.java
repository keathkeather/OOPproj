
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(51, 122, 183));
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(40, 96, 144));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(51, 122, 183));
            }
        });
    }
}

