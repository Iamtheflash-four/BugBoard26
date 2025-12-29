package boundary.theme;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DettagliButton extends JButton implements TableCellRenderer 
{	
	public DettagliButton() {
		super("Dettagli");
		ModernButton.styleButton(Color.RED, Color.WHITE, Color.RED, 80, 25, this);
		setMargin(new Insets(5, 10, 5, 10));
	}
	
	@Override 
	public Component getTableCellRendererComponent( JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column) 
	{ 
		JButton button = new JButton("Dettagli");
	    ModernButton.styleButton(Color.RED, Color.WHITE, Color.RED, 80, 25, button);

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(245, 250, 255));
	    panel.add(button);
	    return panel;
	}
}
