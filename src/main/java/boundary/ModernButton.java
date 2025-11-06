package boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
		nuovoBottone.setFocusPainted(false);

        return nuovoBottone;
    }
}

