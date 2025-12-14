package controller;

import javax.swing.JDialog;

import boundary.dialog.SegnalaIssueDialog;
import entity.Utente;

public class AreaUtenteController extends Controller
{	
	private Utente utente;
	
	protected AreaUtenteController(Controller controller)
	{
		super(controller);
	}
	
	public AreaUtenteController(Controller controller, Utente user)
	{
		this.utente = user;
		frame = new boundary.PersonalAreaUtente(this, utente);
	}
	
	public void segnalaIssue(Utente utente) {
		new SegnalaIssueController(this, utente);
	}
	
	public static void main(String[] args)
	{
		Utente utente = new Utente(1,"Sasy", "Correra", "s.correra@studenti.unina.it", "nooo", true, "shdfh");
		new AreaUtenteController(new Controller(), utente);
	}

	public void switchLogin() {
		frame.dispose();
		new LoginController();
	}

	public void showProfilo(Utente utente) {
		new ProfiloController(this, utente);
	}
}
