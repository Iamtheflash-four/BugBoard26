package boundary;
import controller.*;
import entity.Utente;

import javax.swing.*;
import java.awt.*;

public class PersonalAreaUtente extends JFrame
{
	private AreaUtenteController controller; 
	private JPanel mainPanel;
	private JPanel buttonPanel; 
	private JButton btnIssue, btnChangePassword, btnLogout; 
	private JLabel titolo; 
	private JPanel titlePanel;
	private Utente utente;
	private AreaIssue areaIssueSegnalte;
	
	public PersonalAreaUtente(AreaUtenteController controller, Utente utente)
	{
		this.controller = controller; 
		this.utente = utente;
		
		this.setTitle("Area Personale - Unina BugBoard26");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la finestra
       // this.setResizable(false);
        
        componiGUI(); 
        

        this.setVisible(true);
	}
	
	private void componiGUI() {
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    //mainPanel = new JPanel(); 
		//mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    mainPanel.setBackground(Color.WHITE);
	    
	    titlePanel = new BarraUtente(utente);
	    mainPanel.add(titlePanel);
	    
	    areaIssueSegnalte = new AreaIssue();
	    
	    this.setContentPane(mainPanel);
	}


}
