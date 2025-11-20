package controller;

import javax.swing.JDialog;

import boundary.dialog.SegnalaIssueDialog;
import entity.Utente;

public class SegnalaIssueController extends Controller
{
	private Utente utente;
	JDialog dialog;
	
	public SegnalaIssueController(Controller controller, Utente utente)
	{
		super(controller);
		this.utente = utente;
		dialog = new SegnalaIssueDialog(frame, this);
	}
}
