package boundary.personal;
import controller.*;
import entity.Utente;

import javax.swing.*;

import boundary.personal.panels.AreaIssue;
import boundary.personal.panels.BarraUtente;
import boundary.personal.panels.FiltroPrioritaPanel;

import java.awt.*;

public class PersonalAreaUtente extends JFrame
{
	protected AreaUtenteController controller; 
	protected JPanel mainPanel;
	protected JPanel buttonPanel; 
	protected JButton btnIssue, btnChangePassword, btnLogout; 
	protected JLabel titolo; 
	protected JPanel titlePanel;
	protected Utente utente;
	protected AreaIssue areaIssue;
	
//	protected FiltroPrioritaPanel filtroPrioritaPanel;
//	protected FiltroPrioritaController filtroPrioritaController;

	
	public PersonalAreaUtente(AreaUtenteController controller, Utente utente)
	{
		this.controller = controller; 
		this.utente = utente;
		
		this.setTitle("Area Personale - Unina BugBoard26");
        this.setSize(900, 600);
        this.setMinimumSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la finestra
       // this.setResizable(false);
        
        componiGUI();  
        this.setVisible(true);
	}
	
	private void componiGUI() {
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setBackground(Color.WHITE);
		
	    creaNavBar();
	    mainPanel.add(titlePanel);

	    createAreaIssue();    
	    
//	    filtroPrioritaController = new FiltroPrioritaController(areaIssue);
//	    filtroPrioritaPanel = new FiltroPrioritaPanel(filtroPrioritaController);
//	    mainPanel.add(filtroPrioritaPanel);
	    
	    areaIssue.setVisible(true);
	    mainPanel.add(areaIssue); 
	    
	    this.setContentPane(mainPanel);
	}

	public void createAreaIssue() {
		areaIssue = new AreaIssue(controller);
	}

	protected void creaNavBar() {
		  titlePanel = new BarraUtente(utente, controller);
	}
	
	public AreaUtenteController getController() {
	    return controller;
	}
}
