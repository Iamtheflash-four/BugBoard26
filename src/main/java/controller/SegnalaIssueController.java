package controller;

import java.util.ArrayList;

import javax.swing.JDialog;

import boundary.dialog.SegnalaIssueDialog;
import dto.ElencoProgettiResponse;
import entity.Issue;
import entity.Progetto;
import entity.Utente;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

	public ArrayList<Progetto> getElencoProgetti() throws Exception 
	{
		Response response = client.target("http://localhost:8081").path("elenchi/progetti")
			.request(MediaType.APPLICATION_JSON)
		    .header("Token", utente.getToken()) // allega il token
		    .get();
		
		ElencoProgettiResponse elencoResponse = response.readEntity(ElencoProgettiResponse.class);
		
		if(response.getStatus()==200)
			return elencoResponse.getElencoProgetti();
		else
			throw new Exception(elencoResponse.getMessage());
	}

	public void segnalaIssue(Issue issue) 
	{
		issue.setUtente(utente);
		Response response = server.path("auth/login")
                .request(MediaType.APPLICATION_JSON) 
                .post(Entity.entity(issue, MediaType.APPLICATION_JSON), Response.class);
	}
}
