package boundary;
import controller.*;
import entity.Utente;

import javax.swing.*;
import java.awt.*;

public class PersonalAreaUtente extends JFrame
{
	protected AreaUtenteController controller; 
	protected JPanel mainPanel;
	private JPanel buttonPanel; 
	private JButton btnIssue, btnChangePassword, btnLogout; 
	private JLabel titolo; 
	protected JPanel titlePanel;
	protected Utente utente;
	private AreaIssue areaIssueSegnalte;
	
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

	    areaIssueSegnalte = new AreaIssue(controller);
	    areaIssueSegnalte.setVisible(true);    
	    
	    mainPanel.add(areaIssueSegnalte); // Aggiungiamo il pannello AreaIssue al contenuto principale

	    this.setContentPane(mainPanel);
	}

	protected void creaNavBar() {
		  titlePanel = new BarraUtente(utente, controller);
	}
}
