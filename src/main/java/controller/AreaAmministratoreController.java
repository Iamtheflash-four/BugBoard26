package controller;

import boundary.personal.PersonalAreaAdmin;
import dto.IssueDTO;
import entity.Utente;

public class AreaAmministratoreController extends AreaUtenteController
{
	public AreaAmministratoreController (Controller controller, Utente utente)
	{
		super(controller);
		this.utente = utente;
		new PersonalAreaAdmin(this, utente);
	}
	
	public void showCreazioneUtente(Utente utente) {
		new CreaUtenteController(this, utente);
	}
	
	@Override
	public void showDettagliIssue(IssueDTO issue)  {
		new DettagliIssueSegnalataController(this, issue, utente);
	}
	
	public static void main(String[] args)
	{
		Utente utente = new Utente(1,"Sasy", "Correra", "s.correra@studenti.unina.it", "nooo", true, "shdfh");
		new AreaAmministratoreController(new Controller(), utente);
	}
}
