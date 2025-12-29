package controller;

import boundary.dialog.DettagliIssueSegnalataDialog;
import dto.IssueDTO;
import entity.Utente;

public class DettagliIssueSegnalataController extends DettagliIssueAssegnataController
{
	public DettagliIssueSegnalataController(Controller controller, IssueDTO issue, Utente utente) 
	{
		super(controller, issue, utente);
		this.utente = utente;
		creaDialog(issue);
	}

	@Override
	protected void creaDialog(IssueDTO issue) {
		dialog = new DettagliIssueSegnalataDialog(this, issue);
	}
	
	public void assegnaIssue() {
		
	}
}
