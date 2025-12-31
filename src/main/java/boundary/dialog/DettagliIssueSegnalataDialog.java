package boundary.dialog;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boundary.personal.panels.UtentiMenu;
import boundary.theme.ModernButton;
import controller.DettagliIssueSegnalataController;
import dto.IssueDTO;
import dto.UserInfoDTO;
import exceptions.BadTokenException;

public class DettagliIssueSegnalataDialog extends DettagliIssueAssegnataDialog
{
	private JButton assegnaButton;
	private JPanel assegnaIssuePanel;
	private JButton annullaAssegnamentoButton;
	private JComboBox<String> utentiComboBox;
	private ArrayList<UserInfoDTO> elencoUtenti;
	private UtentiMenu menuUtenti;
	
	public DettagliIssueSegnalataDialog(DettagliIssueSegnalataController controller, IssueDTO issue) {
		super(controller, issue);
		createButtons();
		addAssignmentPanel();
		mainPanel.add(assegnaIssuePanel);
		
		addAdminEvents(controller);
		loadElencoUtenti(controller);
	}

	@Override
	protected void componiGUI(IssueDTO issue)
	{
		super.componiGUI(issue);
		createAssignmentPanel();
	}

	public void createAssignmentPanel() {
		assegnaIssuePanel = creaFlowLayoutPanel(200, 40);
		createAssignmentButtons();
		menuUtenti = new UtentiMenu();
		menuUtenti.setVisible(false);
		
	}
	
	protected void createAssignmentButtons() {
		assegnaButton = ModernButton.createNewButtonPainted("Assegna", 100, 40);
		annullaAssegnamentoButton = ModernButton.createNewButtonPainted("Annulla", Color.BLUE, Color.WHITE, Color.BLUE, 100, 40);
		annullaAssegnamentoButton.setVisible(false);
	}

	public void addAdminEvents(DettagliIssueSegnalataController controller)
	{
		addAssegnaEvent(controller);
		addAnnullaAssegnamentoEvent(controller);
	}
	
	public void addAnnullaAssegnamentoEvent(DettagliIssueSegnalataController controller) {
		annullaAssegnamentoButton.addActionListener(e->{
			annullaAssegnamentoButton.setVisible(false);
			menuUtenti.setVisible(false);
		});
	}

	public void addAssegnaEvent(DettagliIssueSegnalataController controller) {
		assegnaButton.addActionListener(e->{
			if( !utentiComboBox.isVisible() )
			{
				annullaAssegnamentoButton.setVisible(true);
				menuUtenti.setVisible(true);
			}
			else
				assegnaIssue();
		});
	}
	
	
	private void loadElencoUtenti(DettagliIssueSegnalataController controller) 
	{
		try {
			elencoUtenti = controller.getElencoUtenti();
			if(elencoUtenti == null)
				JOptionPane.showMessageDialog(this, "Nessun utente nel teamwork del progetto", "Errore", JOptionPane.ERROR_MESSAGE);
			menuUtenti.loadItems(elencoUtenti);
		}
		catch (BadTokenException e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			controller.showLoginArea();
		}
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void addAssignmentPanel()
	{
		assegnaIssuePanel.add(assegnaButton);
		assegnaIssuePanel.add(menuUtenti);
		assegnaIssuePanel.add(annullaAssegnamentoButton);
		mainPanel.add(assegnaIssuePanel);
	}
	
	protected void assegnaIssue()
	{
//		String
	}
}
