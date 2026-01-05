package controller;

import java.util.ArrayList;

import boundary.dialog.DettagliIssueSegnalataDialog;
import dto.IssueDTO;
import dto.UserInfoDTO;
import entity.Utente;
import exceptions.BadTokenException;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

public class DettagliIssueSegnalataController extends DettagliIssueAssegnataController
{	
	public DettagliIssueSegnalataController(Controller controller, IssueDTO issue, Utente utente) 
	{
		super(controller, issue, utente);
	}

	@Override
	protected void creaDialog() {
		dialog = new DettagliIssueSegnalataDialog(this, issue);
		dialog.setVisible(true);
	}
	
	public ArrayList<UserInfoDTO> getElencoUtenti() throws Exception
	{
		ArrayList<UserInfoDTO> elenco;
		Response response = client.target(USER_SERVER_URL).path("elenchi/utenti")
			.request()
			.header("progetto", issue.getIdProgetto())
			.header("token", utente.getToken())
			.get();
		if(response.getStatus() == 200)
			return elenco = response.readEntity(new GenericType<ArrayList<UserInfoDTO>>() {});
		else if (response.getStatus() == 401)
			throw new BadTokenException(response.readEntity(String.class));
		else
			throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
	}
	
	public void assegnaIssue(long idUtente) 
	{
		Response response = client.target(ISSUE_SERVER_URL).path("/issue/assign")
			.request()
			.header("token", this.getToken())
			.header("destinatario", idUtente)
			.post(null);
		
	}
}
