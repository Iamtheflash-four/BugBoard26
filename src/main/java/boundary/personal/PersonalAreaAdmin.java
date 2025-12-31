package boundary.personal;

import boundary.personal.panels.AreaIssueAdmin;
import boundary.personal.panels.BarraAdmin;
import controller.AreaAmministratoreController;
import controller.AreaUtenteController;
import entity.Utente;

public class PersonalAreaAdmin extends PersonalAreaUtente
{
	public PersonalAreaAdmin(AreaAmministratoreController controller, Utente utente) 
	{ 
		super(controller, utente); 
	} 
	
	@Override
	protected void creaNavBar() {
		  titlePanel = new BarraAdmin(utente, getController());
	}
	
	@Override
	public AreaAmministratoreController getController() {
	    return (AreaAmministratoreController) super.controller;
	}
	
	@Override 
	public void createAreaIssue()
	{
		areaIssue = new AreaIssueAdmin(getController());
	}
}
