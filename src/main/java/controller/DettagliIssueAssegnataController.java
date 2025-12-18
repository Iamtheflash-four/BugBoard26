package controller;

import java.util.ArrayList;

import javax.swing.JDialog;

import boundary.dialog.DettagliIssueAssegnataDialog;
import boundary.dialog.DettagliIssueDialog;
import dto.IssueDTO;
import entity.Utente;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class DettagliIssueAssegnataController extends Controller
{
	private Utente utente;
	JDialog dialog;
	
	public DettagliIssueAssegnataController(Controller areaUtenteController, IssueDTO issue, Utente utente) {
		dialog = new DettagliIssueAssegnataDialog(this, issue);
	}

	public String getToken() {
		return utente.getToken();
	}
	
	public static void main(String[] args)
	{
		ArrayList<String> images = new ArrayList<String>();
		images.add("Ciao.png");  images.add("on so.jpg"); 
		String descrizione = "dhdahdahd hsahsahdk adh"
				+ "askjhdaks s"
				+ "ahdkjahdkaadsajhcsckjs \n"
				+ "dadlksajdaj"
				+ "adsa"
				+ "dasdsadsadsadashdkjsdhkjsdhkjsdhkv";
		IssueDTO issue = new IssueDTO(1, "evergren", "bug", "Alta", "Issue Titolo", 
				descrizione, new java.util.Date());
		issue.setImageNames(images);
		new DettagliIssueAssegnataController(null, issue, new Utente(1, "s","dsa","dd","dd", true, "TOKEeeen"));
	}

	public void salvaRisposta(String text) throws Exception {
		checkRisposta(text);
		Response response = client.target(this.ISSUE_SERVER_URL).path("")
				.request()
				.header("Token", utente.getToken())
				.post(Entity.entity(text, MediaType.TEXT_PLAIN));
	}

	private void checkRisposta(String text) throws Exception {
		if(text==null || text.equals(""))
			throw new Exception("La descrizione non può essere vuota");
	}
}
