package boundary;

import controller.AreaAmministratoreController;
import controller.AreaUtenteController;
import entity.Utente;

public class PersonalAreaAdmin extends PersonalAreaUtente
{
	public PersonalAreaAdmin(AreaAmministratoreController controller, Utente utente) 
	{ 
		super(controller, utente); 
		this.setVisible(true);
	} 
	
	@Override
	protected void creaNavBar() {
		  titlePanel = new BarraAdmin(utente, controller);
	}
}
