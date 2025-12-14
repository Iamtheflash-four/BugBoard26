package boundary;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import controller.AreaUtenteController;
import entity.Utente;

public class BarraAdmin extends BarraUtente
{ 
	private JButton gestioneUtenzeButton; 
	
	public BarraAdmin(Utente utente, AreaUtenteController controller)
	{
		super(utente, controller); 	
		addAdminOptions();
		addActionButton(); 
		this.setVisible(true);
	}
	
	private void addAdminOptions()
	{
		gestioneUtenzeButton = ModernButton.createNavbarButton("<html>Gestione<br>utenze</html>\"");
        buttonPanel.add(gestioneUtenzeButton);
	}
	
	private void addActionButton()
	{
		//gestioneUtenze(); 
	}
	
/*	private void gestioneUtenze()
	{
		gestioneUtenzeButton.addActionListener();  
	} */
	
	

}
