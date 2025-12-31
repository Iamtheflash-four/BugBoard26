package boundary.personal.panels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import boundary.personal.ManageUserMenu;
import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import controller.AreaAmministratoreController;
import controller.AreaUtenteController;
import entity.Utente;

public class BarraAdmin extends BarraUtente
{ 
	private JButton gestioneUtenzeButton; 
	
	public BarraAdmin(Utente utente, AreaAmministratoreController controller)
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
		 gestioneUtenze(); 
	}
	
	private void gestioneUtenze()
	{
		gestioneUtenzeButton.addActionListener(e -> {
	        ManageUserMenu menu = new ManageUserMenu(getController());
	        menu.show(gestioneUtenzeButton, 0, gestioneUtenzeButton.getHeight());
	    });

	}
	
	public AreaAmministratoreController getController() {
	    return (AreaAmministratoreController) super.controller;
	}
}
