package controller;

import java.util.ArrayList;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.IssueDTO;
import entity.Utente;

public class AreaUtenteController extends Controller
{	
	protected Utente utente;
	
	protected AreaUtenteController(Controller controller)
	{
		super(controller);
	}
	
	public AreaUtenteController(Controller controller, Utente user)
	{
		this(controller);
		this.utente = user;
		frame = new boundary.personal.PersonalAreaUtente(this, utente);
	}
	
	public void showSegnalazioneIssue(Utente utente) {
		new SegnalaIssueController(this, utente);
	}

	public void switchLogin() {
		this.frame.dispose();
		new LoginController(this);
	}

	public void showProfilo(Utente utente) {
		new ProfiloController(this, utente);
	}

	public ArrayList<IssueDTO> getElencoIssuAssegnate() throws Exception 
	{
		Response response = client.target(ISSUE_SERVER_URL).path("issue/elencoIssueAssegnate")
			.request(MediaType.APPLICATION_JSON)
			.header("Token", utente.getToken())
			.get();
		
		if(response != null && response.getStatus() == 200)
			return response.readEntity(new GenericType<ArrayList<IssueDTO>>() {});
		else
			throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
	}

	public void showDettagliIssue(IssueDTO issue) {
		new DettagliIssueAssegnataController(this, issue, utente );
	}
	
	public Utente getUtente()
	{
		return utente;
	}
	
	public String getToken() {
	    return utente.getToken(); 
	}

	
	public static void main(String[] args)
	{
		Utente utente = new Utente(1,"Sasy", "Correra", "s.correra@studenti.unina.it", "nooo", true, "shdfh");
		new AreaUtenteController(new Controller(), utente);
	}

	public ArrayList<IssueDTO> getElencoIssueSegnalate() throws Exception {
		Response response = client.target(ISSUE_SERVER_URL).path("issue/elencoSegnalateUtente")
				.request(MediaType.APPLICATION_JSON)
				.header("Token", utente.getToken())
				.get();
			
		if(response != null && response.getStatus() == 200)
			return response.readEntity(new GenericType<ArrayList<IssueDTO>>() {});
		else
			throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
	}
}
