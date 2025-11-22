package boundary.theme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class ModernTextField 
{
	public static JTextField createLoginField(String text) {
    	JTextField field = new JTextField(20);
    	styleTextField(field, text);
    	
    	field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getForeground().equals(Color.GRAY)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    	
    	return field;
    }
	
	public static JPasswordField createPasswordField()
    {
    	JPasswordField field = new JPasswordField(20);
    	styleTextField(field, "");
    	field.setForeground(Color.BLACK);
    	return field;
    }

	private static void styleTextField(JTextField field, String text) 
	{
		field.setText(text);
		field.setForeground(Color.GRAY);
        field.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));	//Solo bordo inferiore
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        // Size
        Dimension dim = new Dimension(420, 80);
	}
}
