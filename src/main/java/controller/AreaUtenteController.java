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
		
		if(utente.getAdminStatus())
		{
			frame = new boundary.PersonalAreaUtenteAdmin(this, utente);

		}
		else frame = new boundary.PersonalAreaUtente(this, utente); 
		
	}
	
	public void segnalaIssue(Utente utente) {
		new SegnalaIssueController(this, utente);
	}
	
	public static void main(String[] args)
	{
		Utente utente = new Utente(1,"Sasy", "Correra", "s.correra@studenti.unina.it", "RomaMerda", "shdfh", true);
		Utente utente2 = new Utente(2, "Francy", "Cervera", "f.cervera@studenti.unina.it", "Forzainter", "lll", true);
		Utente provaNotAdmin = new Utente (3, "Adolf", "Hitler", "ad.hilter@vivailduce.de", "DuceAlè", "vljfjdbf", false);
		
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
