package controller;

import java.net.ResponseCache;
import java.util.ArrayList;

import javax.swing.JDialog;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import boundary.dialog.SegnalaIssueDialog;
import dto.IssueDTO;
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

	public ArrayList<IssueDTO> getElencoIssue() throws Exception 
	{
		Response response = client.target(ISSUE_SERVER_URL).path("issue/elencoIssueAssegnate")
			.request(MediaType.APPLICATION_JSON)
			.header("Token", utente.getToken())
			.get();
		if(response != null && response.getStatus() == 200)
			return response.readEntity(new GenericType<ArrayList<IssueDTO>>() {});
		else
			throw new Exception("Erroe server: " + response.getStatus());
	}

	public void showDettagliIssue(IssueDTO issue) {
		new DettagliIssueAssegnataController(this, issue, utente );
	}
}
