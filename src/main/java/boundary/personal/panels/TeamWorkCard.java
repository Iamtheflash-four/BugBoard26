package boundary.personal.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import controller.AreaTeamWorkController;
import dto.TeamDTO;

public class TeamWorkCard extends JPanel 
{
	private JLabel nomeTeamLabel;
	private JButton aggiungiProgettoButton;
	private JButton aggiungiUtenteButton;

	public TeamWorkCard(TeamDTO team, AreaTeamWorkController controller) 
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		ModernPanel.stylePanel(400, 150, this);
		this.setBorder(new MatteBorder(2, 0, 2, 0, Color.GRAY));
		initializeComponents(team);
		addComponents();
		addListeners(controller, team);
	}

	private void initializeComponents(TeamDTO team) 
	{
		System.out.println("Nome: " + team.getNome());
		nomeTeamLabel = ModernLabel.createLabel(team.getNome(), new Font("Arial", Font.PLAIN, 12 ), Color.BLACK);
		aggiungiProgettoButton = ModernButton.createNewButtonPainted("<html>Aggiungi<br>progetto</html>", 100, 30);
		aggiungiUtenteButton = ModernButton.createNewButtonPainted("<html>Aggiungi<br>utente</html>", 100, 30);
	}
	
	protected void addComponents()
	{
		this.add(nomeTeamLabel);
		this.add(aggiungiProgettoButton);
		this.add(aggiungiUtenteButton);
	}
	
	protected void addListeners(AreaTeamWorkController controller, TeamDTO team)
	{
		aggiungiProgettoButton.addActionListener(e -> {
            addProjetc(controller, team);
        }); 
		
		aggiungiUtenteButton.addActionListener(e -> {
            addUser(controller, team);
        }); 
	}

	private void addProjetc(AreaTeamWorkController controller, TeamDTO team) {
		try {
			controller.showAddProject(team.getIdTeam());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void addUser(AreaTeamWorkController controller, TeamDTO team) {
		try {
			controller.showAddUser(team.getIdTeam());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
