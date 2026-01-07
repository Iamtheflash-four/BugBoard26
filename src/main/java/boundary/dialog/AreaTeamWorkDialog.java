package boundary.dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boundary.personal.panels.TeamWorkCard;
import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import controller.AreaTeamWorkController;
import dto.TeamDTO;

public class AreaTeamWorkDialog extends JDialog 
{
	private JPanel mainPanel;
	private JPanel teamworkPanel;
	private JButton closeButton;
	private ArrayList<TeamDTO> elencoTeam;

	public AreaTeamWorkDialog(JFrame frame, AreaTeamWorkController controller) 
	{
		super(frame, "Area TeamWork", true);
		this.setMinimumSize(new Dimension(500, 600));
		compiniGUI();
		creaElencoTeam(controller);
		addEvents(controller);
		setContentPane(mainPanel);
	}

	private void compiniGUI() 
	{
		mainPanel = ModernPanel.createWhitePanel(500, 600);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		creaHeader();
		inizializzaElencoTeamPanel();
		
	}

	protected void creaHeader() {
		JPanel title = new boundary.theme.TitlePanel("Elenco team");
		closeButton = ModernButton.createNavbarButton("chiudi");
		
		title.add(Box.createHorizontalStrut(5));
		title.add(closeButton);
	}
	
	private void inizializzaElencoTeamPanel() {
		teamworkPanel = ModernPanel.createWhitePanel(500, 500);
		mainPanel.setLayout(new BoxLayout(teamworkPanel, BoxLayout.Y_AXIS));
	}

	private void creaElencoTeam(AreaTeamWorkController controller) {
		try {
			elencoTeam = controller.getElencoTeam();
			if(elencoTeam == null || elencoTeam.isEmpty())
			{
				JLabel errorLabel = ModernLabel.createBoldLabel("Nessun teamwork", Color.red);
				teamworkPanel.add(errorLabel);
				return;
			}
			for(TeamDTO team : elencoTeam)
				teamworkPanel.add(new TeamWorkCard(team, controller));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void addEvents(AreaTeamWorkController controller) {
		closeButton.addActionListener(e -> {
            this.dispose();
        }); 
	}
}
