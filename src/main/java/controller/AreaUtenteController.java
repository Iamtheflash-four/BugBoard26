package controller;

import javax.swing.JDialog;

import boundary.dialog.SegnalaIssueDialog;
import entity.Utente;

public class AreaUtenteController extends Controller
{	
	private Utente utente;
	public AreaUtenteController(Controller controller, Utente user)
	{
		super(controller);
		this.utente = user;
		
		frame = new boundary.PersonalAreaUtente(this, utente);
		
	}
	
	public void segnalaIssue(Utente utente) {
		new SegnalaIssueController(this, utente);
	}
	
	public static void main(String[] args)
	{
		Utente utente = new Utente();
		utente.setNome("Sasy");
		new AreaUtenteController(new Controller(), utente);
	}

	public void switchLogin() {
		frame.dispose();
		new LoginController();
	}
}
