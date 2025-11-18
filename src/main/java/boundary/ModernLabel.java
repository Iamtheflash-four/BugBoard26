package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ModernLabel 
{
	public static JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.BLUE);
        return label;
    }
	
	public static JLabel createTitleLabel(String text)
	{
		JLabel titleLabel = new JLabel(text, SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.BLUE);
		
		titleLabel.setSize(titleLabel.getText().length()*20, 20);
		titleLabel.setPreferredSize(titleLabel.getSize());
		titleLabel.setMaximumSize(titleLabel.getSize());
		return titleLabel;
	}
}
