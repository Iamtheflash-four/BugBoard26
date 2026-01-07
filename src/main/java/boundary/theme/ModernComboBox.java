package boundary.theme;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

import entity.Progetto;

public class ModernComboBox 
{

	public static JComboBox<String> createCombobox(String text, ArrayList<Progetto> elenco)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
		styleComboBox(comboBox);
		comboBox.addItem(text);
		if(elenco != null)
			for(Progetto progetto : elenco)
				comboBox.addItem(progetto.getNome());
		return comboBox;
	}
	
	public static JComboBox<String> createCombobox(String[] elenco) {
		JComboBox<String> comboBox = new JComboBox<String>(elenco);
		styleComboBox(comboBox);
		return comboBox;
	}

	private static void styleComboBox(JComboBox<String> comboBox) {
		comboBox.setSize(120, 40);
		comboBox.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		
		comboBox.setPreferredSize(comboBox.getSize());
		comboBox.setMaximumSize(comboBox.getSize());
	}
}
