package boundary;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import boundary.theme.ModernButton;
import controller.AreaUtenteController;
import dto.IssueDTO;

public class DettagliButton extends JButton 
{
	private AreaUtenteController controller;

	public DettagliButton(IssueDTO issue, AreaUtenteController controller) {
		super("Dettagli");
		this.controller = controller;
		ModernButton.styleButton(Color.RED, Color.WHITE, Color.RED, 80, 25, this);
		this.addActionListener(e->{
			controller.showDettagliIssue(issue);
		});
	}
	
	public static void main(String[] args)
	{
		JButton b = new DettagliButton(null, null);
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.add(b);
		frame.setVisible(true);
	}
}
