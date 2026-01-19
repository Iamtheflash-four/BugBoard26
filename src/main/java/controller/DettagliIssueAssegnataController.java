package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JDialog;

import boundary.dialog.DettagliIssueAssegnataDialog;
import boundary.dialog.DettagliIssueDialog;
import dto.IssueDTO;
import entity.Utente;
import dto.RispostaIssueDTO;
import exceptions.BadTokenException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class DettagliIssueAssegnataController extends DettagliIssueController
{
	public DettagliIssueAssegnataController(Controller controller, IssueDTO issue, Utente utente) {
		super(controller, issue, utente);
		this.utente = utente;
		this.issue = issue;
		creaDialog(issue);
	}
	
	@Override
	protected void creaDialog(IssueDTO issue) {
		dialog = new DettagliIssueAssegnataDialog(frame, this, issue);
	}

	public String getToken() {
		return utente.getToken();
	}

	public void salvaRisposta(String text) throws Exception {
		checkRisposta(text);
		RispostaIssueDTO risposta = new RispostaIssueDTO(issue.getIdIssue(), text);
		Response response = client.target(this.ISSUE_SERVER_URL).path("/issue/risposte")
				.request()
				.header("Token", utente.getToken())
				.post(Entity.entity(risposta, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200)
		{
			if (response.getStatus() == 401)
				throw new BadTokenException(response.getStatus() + ": " + response.readEntity(String.class));
			else
				throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
		}
	}

	private void checkRisposta(String text) throws Exception {
		if(text==null || text.equals(""))
			throw new Exception("La descrizione non può essere vuota");
	}

	public void showLoginArea() {
		new LoginController(this);
	}
}
