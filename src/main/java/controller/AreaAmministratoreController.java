package controller;

import java.util.ArrayList;

import boundary.personal.PersonalAreaAdmin;
import dto.IssueDTO;
import entity.Utente;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class AreaAmministratoreController extends AreaUtenteController
{
	public AreaAmministratoreController (Controller controller, Utente utente)
	{
		super(controller);
		this.utente = utente;
		frame = new PersonalAreaAdmin(this, utente);
	}
	
	public void showCreazioneUtente(Utente utente) {
		new CreaUtenteController(this, utente);
	}
	
	@Override
	public void showDettagliIssue(IssueDTO issue)  {
		if(!getShowingRicevute())
			new DettagliIssueSegnalataController(this, issue, utente);
		else
			new DettagliIssueController(this, issue, utente);
	}
	
	@Override
	public ArrayList<IssueDTO> getElencoIssueSegnalate() throws Exception {
		Response response = client.target(ISSUE_SERVER_URL).path("issue/admin/elenchi/segnalazioni")
				.request(MediaType.APPLICATION_JSON)
				.header("Token", utente.getToken())
				.get();
			
		if(response != null && response.getStatus() == 200)
			return response.readEntity(new GenericType<ArrayList<IssueDTO>>() {});
		else
			throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
	}
	
	public static void main(String[] args)
	{
		Utente utente = new Utente(1,"Sasy", "Correra", "s.correra@studenti.unina.it", "nooo", true, "shdfh");
		new AreaAmministratoreController(new Controller(), utente);
	}

	public void showCreaTeamWork(Utente utente) {
		new CreaTeamWorkController(this, utente);
	}
}