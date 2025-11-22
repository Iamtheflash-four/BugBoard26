package boundary.theme;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel
{
	private JLabel titleLabel;

	public TitlePanel(String text)
	{
		super();
		this.setPreferredSize(new Dimension(200, 60));
		this.setMaximumSize(new Dimension(200, 60));
		this.setBackground(Color.WHITE);
		
		titleLabel = ModernLabel.createTitleLabel(text);
		this.add(titleLabel);
	}
}
