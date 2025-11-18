package boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class RoundedButton extends JButton 
{

	private Color colorPressed;
	private Color backGround;
	
    public RoundedButton(String text) {
        this(text, Color.WHITE, Color.LIGHT_GRAY);
    }
    
    public RoundedButton(String text, Color color1, Color color2)
    {
    	super(text);
    	setBackground(color1);
    	this.setOpaque(false);
    	colorPressed = color2;
    	backGround = color1;
    	setBorderPainted(false);
    	setFocusPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Colore di sfondo
        if (getModel().isPressed()) {
            g2.setColor(backGround);
        } else if (getModel().isRollover()) {
            g2.setColor(colorPressed);
        } else {
            g2.setColor(getBackground());
        }

        // Rettangolo arrotondato
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		super.paintComponent(g);
    }
    
    private void setColor(Color color)
    {
    	backGround = color; 
    }
}
