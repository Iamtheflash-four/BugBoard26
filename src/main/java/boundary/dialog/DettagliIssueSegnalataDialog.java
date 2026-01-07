package boundary.dialog;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private ArrayList<UserInfoDTO> elencoUtenti;
	private UtentiMenu menuUtenti;
	
	public DettagliIssueSegnalataDialog(JFrame frame, DettagliIssueSegnalataController controller, IssueDTO issue) {
		super(frame, controller, issue);
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
			if( !menuUtenti.isVisible() )
			{
				annullaAssegnamentoButton.setVisible(true);
				menuUtenti.show(assegnaButton, 0, assegnaButton.getHeight());
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
			menuUtenti.loadItems(elencoUtenti, controller);
		}
		catch (BadTokenException e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			controller.showLoginArea();
		}
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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
