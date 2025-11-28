package controller;

import entity.Utente;
import boundary.PersonalAreaAdmin;

public class AreaAmministratoreController extends AreaUtenteController
{
	public AreaAmministratoreController (Controller controller, Utente utente)
	{
		super(controller);
		new PersonalAreaAdmin(this, utente);
	}
}
