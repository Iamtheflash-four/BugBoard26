package boundary.personal;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class BarraGenerica extends JPanel
{
	public BarraGenerica()
	{
		 	setSize(Integer.MAX_VALUE, 70);
	        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
	        setPreferredSize(new Dimension(0, 70));
	        setAlignmentX(CENTER_ALIGNMENT);
	        setBackground(Color.WHITE);
	        setBorder(new MatteBorder(0, 0, 2, 0, new Color(200,200,200)));
	        setVisible(true);
	}

}
