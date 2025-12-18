package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;

import boundary.dialog.SegnalaIssueDialog;
import dto.ElencoProgettiResponse;
import dto.ImageDTO;
import dto.IssueWithImagesDTO;
import entity.Issue;
import entity.Progetto;
import entity.Utente;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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
		Response response = client.target(ISSUE_PROGETTI_URL).path("elenchi/progetti")
			.request(MediaType.APPLICATION_JSON)
		    .header("Token", utente.getToken()) // allega il token
		    .get();
		
		ElencoProgettiResponse elencoResponse = response.readEntity(ElencoProgettiResponse.class);
		
		if(response !=null && response.getStatus()==200)
			return elencoResponse.getElencoProgetti();
		else
			throw new Exception(elencoResponse.getMessage());
	}

	public String segnalaIssue(Issue issue, ArrayList<File> images) throws Exception 
	{
		if(!checkInput(issue))
			throw new Exception("Campi non compilati");
		
		ArrayList<ImageDTO> imagesDTO = carciaImmagini(images);
		Response response = caricaIssue(issue, imagesDTO);
		
		if(response.getStatus() != 200)
			throw new Exception(response.getStatus() + " " + response.readEntity(String.class));
		else if (response.getStatus() == 207)
			return response.readEntity(String.class);
		else
			return "Issue caricata con succeso";
	}

	private ArrayList<ImageDTO> carciaImmagini(ArrayList<File> images) throws IOException {
		ArrayList<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
		for(File f : images)
			imagesDTO.add(new ImageDTO(f));
		return imagesDTO;
	}

	private Response caricaIssue(Issue issue, ArrayList<ImageDTO> imagesDTO) {
		IssueWithImagesDTO issueDTO = new IssueWithImagesDTO(issue, imagesDTO);
		Jsonb jsonb = JsonbBuilder.create();

		Response response = client.target(ISSUE_SERVER_URL).path("issue/segnalazioni")
                .request(MediaType.APPLICATION_JSON) 
				.header("Token", utente.getToken())
                .post(Entity.entity(jsonb.toJson(issueDTO), MediaType.APPLICATION_JSON), Response.class);
		return response;
	}

	private boolean checkInput(Issue issue) {
		if(issue == null)
			return false;
		if( issue.getProgetto() == null || issue.getProgetto().equals("") ||
			issue.getTipo() == null || !issue.getTipo().matches("Question|Documentation|Bug|Feature") ||
			issue.getPriorita() == null || !issue.getPriorita().matches("Alta|Media|Bassa") ||
			issue.getTitolo() == null || issue.getTitolo().equals("") ||
			issue.getDescrizione() == null || issue.getDescrizione().equals("")
			)
			return false;
		return true;
	}

	public String getTokenUtente() {
		return utente.getToken();
	}
}
