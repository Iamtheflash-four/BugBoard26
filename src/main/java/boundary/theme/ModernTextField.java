package boundary.theme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class ModernTextField 
{
	public static JTextField createField(String text) {
    	return createField(text, 20);
    }
	
	public static JTextArea createFieldBox(String text, int width, int heigth) {
		JTextArea field = new JTextArea(5, 40);
		field.setLineWrap(true);
		field.setWrapStyleWord(true);
		styleTextField(field, text, new MatteBorder(2,2,2,2, Color.BLUE));
		addListeners(field, text);
		field.setDocument(new PlainDocument() {
		    @Override
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		        if (str == null) return;
		        if (getLength() + str.length() <= 500) {
		            super.insertString(offs, str, a);
		        } else {
		            Toolkit.getDefaultToolkit().beep(); // opzionale
		        }
		    }
		});
		// Size
		field.setSize(width, heigth);
		field.setPreferredSize(field.getSize());
		field.setMaximumSize(field.getSize());
   
    	return field;
    }

	public static JScrollPane avvolgiScrollPane(JTextArea field, int width, int heigth) {
		JScrollPane scrollPane = new JScrollPane(field);
        scrollPane.setPreferredSize(new Dimension(width, heigth));
        scrollPane.setMinimumSize(new Dimension(width, heigth));
        scrollPane.setMaximumSize(new Dimension(width, heigth));
        
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
	}
	
	public static JTextField createField(String text, int length)
	{
		JTextField field = new JTextField(20);
    	styleTextField(field, text);
    	
    	addListeners(field, text);
    	
    	return field;
	}

	private static void addListeners(JTextComponent field, String text) {
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
		styleTextField(field, text, new MatteBorder(0, 0, 2, 0, Color.BLUE));
	}

	private static void styleTextField(JTextComponent field, String text, Border border) 
	{
		field.setText(text);
		field.setForeground(Color.GRAY);
        field.setBorder(border);	//Solo bordo inferiore
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setFont(new Font("Arial", Font.PLAIN, 15));
	}

}
