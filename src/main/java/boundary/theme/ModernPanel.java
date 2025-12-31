package boundary.theme;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ModernPanel 
{
	public static JPanel createWhitePanel(int length, int heigth)
	{
		JPanel panel = new JPanel();
		stylePanel(length, heigth, panel);
		return panel;
	}

	public static void stylePanel(int length, int heigth, JPanel panel) {
		panel.setBackground(Color.WHITE);
		panel.setSize(length, heigth);
		panel.setMinimumSize(panel.getSize());
		panel.setPreferredSize(panel.getSize());
		panel.setMaximumSize(panel.getSize());
	}

	public static JScrollPane avvolgiScrollPane(JComponent descrizioneArea, int base, int heigth) {
		JScrollPane scrollPane = new JScrollPane(descrizioneArea);
		scrollPane.setBorder(null);
		scrollPane.setSize(base, heigth);
		scrollPane.setMinimumSize(scrollPane.getSize());
		scrollPane.setPreferredSize(scrollPane.getSize());
		scrollPane.setMaximumSize(scrollPane.getSize());
        
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(15);
        return scrollPane;
	}
}
