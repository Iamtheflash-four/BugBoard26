package boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ModernButton 
{
	public static JButton createNewButtonPainted(String textArea, Color colorButton, Color colorText, Color colorBorder) 
    { 
        JButton nuovoBottone = new JButton(textArea);
        nuovoBottone.setBackground(colorButton);
        nuovoBottone.setFont(new Font("Arial", Font.BOLD, 14));
        nuovoBottone.setForeground(colorText);
        nuovoBottone.setBorder(BorderFactory.createLineBorder(colorBorder, 2));
        nuovoBottone.setPreferredSize(new Dimension(160, 40)); // puoi aumentare questi valori
        return nuovoBottone;
    }

	public static JButton createNavbarButton(String text) {
		JButton button = new RoundedButton(text, Color.WHITE, Color.LIGHT_GRAY);
		button.setFont(new Font("Arial", Font.BOLD, 11));
		button.setForeground(Color.BLACK);
		button.setVisible(true);
		//Size
		Dimension size = new Dimension(100, 30);
		button.setSize(size);
		button.setPreferredSize(size);
		button.setMaximumSize(size);
		
		return button;
	}
}
