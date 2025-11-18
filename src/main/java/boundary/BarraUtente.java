package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import entity.Utente;

public class BarraUtente extends JPanel
{
	private JPanel titlePanel;
	private JLabel title;
	private JButton segnalaIssueButton;
	private JPanel buttonPanel;
	
	public BarraUtente(Utente utente)
	{
		super();
		setSize(Integer.MAX_VALUE, 70);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		setPreferredSize(new Dimension(0, 70));
		this.setAlignmentX(CENTER_ALIGNMENT);
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(0, 0, 2, 0, new Color(200,200,200)));

		
		title = ModernLabel.createTitleLabel("Benvenuto " + utente.getNome());
		title.setPreferredSize(new Dimension(300, 30));
		
		segnalaIssueButton = ModernButton.createNavbarButton("Segnala issue");
		
		createTitlePanel();
	}

	private void createTitlePanel() 
	{
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setVisible(true);
		
		buttonPanel.add(segnalaIssueButton);
		buttonPanel.add(new JButton("Prova"));
			
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(title);
		add(buttonPanel);
	}
}
