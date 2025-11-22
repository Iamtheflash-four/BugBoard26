package boundary.theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ModernLabel 
{
	public static JLabel createLabel(String text)
    {
        return createLabel(text, new Font("Arial", Font.BOLD, 15), Color.BLUE);
    }
	
	public static JLabel createTitleLabel(String text)
	{
		return createLabel(text, new Font("Arial", Font.BOLD, 20) , Color.BLUE, SwingConstants.CENTER);
	}
	
	public static JLabel createBoldLabel(String text, int size, Color foreground)
	{
		return createLabel(text, new Font("Arial", Font.BOLD, size), foreground);
	}
	
	public static JLabel createLabel(String text, Color foreground)
	{
		return createLabel(text, new Font("Arial", Font.BOLD, 15), foreground);
	}
	
	public static JLabel createBoldLabel(String text, Color foreground)
	{
		return createLabel(text, new Font("Arial", Font.BOLD, 15), foreground);
	}
	
	public static JLabel createLabel(String text, Font font, Color foreground)
	{
		return createLabel(text, font, foreground, SwingConstants.LEFT);
	}
	
	public static JLabel createLabel(String text, Font font, Color foreground, int alignmentX)
	{
		JLabel label = new JLabel(text, alignmentX);
		label.setFont(font);
		label.setForeground(foreground);
		
		int textSize = label.getFont().getSize();
		label.setSize(text.length()*textSize, textSize + textSize/2);
		label.setPreferredSize(label.getSize());
		label.setMaximumSize(label.getSize());
		return label;
	}
}
