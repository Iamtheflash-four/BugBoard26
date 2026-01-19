package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class SegnalaIssueController extends Controller
{
	private Utente utente;
	JDialog dialog;
	
	public SegnalaIssueController(Utente utente){this.utente = utente;}
	
	public SegnalaIssueController(Controller controller, Utente utente)
	{
		super(controller);
		this.utente = utente;
		creaDialog();
		dialog.setVisible(true);
	}

	protected void creaDialog() {
		dialog = new SegnalaIssueDialog(frame, this);
	}

	public ArrayList<Progetto> getElencoProgetti() throws Exception 
	{
		Response response = client.target(USER_SERVER_URL).path("elenchi/progetti")
			.request(MediaType.APPLICATION_JSON)
		    .header("Token", utente.getToken()) // allega il token
		    .get();
		
		if(response !=null && response.getStatus()==200)
			return response.readEntity(new GenericType<ArrayList<Progetto>>(){});
		else if (response != null)
			throw new Exception(response.readEntity(String.class));
		else
			throw new Exception("500: Nessuna connessione");
	}

	public String segnalaIssue(Issue issue, ArrayList<File> images) throws Exception 
	{
		if(!checkInput(issue))
			throw new Exception("Campi non compilati");
		
		ArrayList<ImageDTO> imagesDTO = carciaImmagini(images);
		if(imagesDTO !=null)
			for(ImageDTO i : imagesDTO)
				System.out.print(i.getFileName() + ", ");
		Response response = caricaIssue(issue, imagesDTO);
		
		if (response.getStatus() == 207)
			return response.readEntity(String.class);
		else if (response.getStatus() != 200)
			throw new Exception(response.getStatus() + " " + response.readEntity(String.class));
		else
			return "Issue caricata con successo";
	}

	public ArrayList<ImageDTO> carciaImmagini(ArrayList<File> images) throws IOException {
		ArrayList<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
		if(images == null)
			return imagesDTO;
		for(File f : images)
			if( f!= null)
				imagesDTO.add(new ImageDTO(f));
		return imagesDTO;
	}

	private Response caricaIssue(Issue issue, ArrayList<ImageDTO> imagesDTO) {
		IssueWithImagesDTO issueDTO = new IssueWithImagesDTO(issue, imagesDTO);
		Jsonb jsonb = JsonbBuilder.create();

		Response response = client.target(ISSUE_SERVER_URL).path("issue/segnalazioni")
                .request(MediaType.APPLICATION_JSON) 
				.header("Token", getTokenUtente())
                .post(Entity.entity(jsonb.toJson(issueDTO), MediaType.APPLICATION_JSON), Response.class);
		return response;
	}

	private boolean checkInput(Issue issue) {
		if(	issue == null || 
				issue.getProgetto() == null || issue.getProgetto().equals("") ||  
				issue.getTipo() == null || !issue.getTipo().matches("Question|Documentation|Bug|Feature") ||
				issue.getPriorita() == null || !issue.getPriorita().matches("Alta|Media|Bassa") ||
				issue.getTitolo() == null || issue.getTitolo().equals("") || issue.getTitolo().length() > 30 ||
				issue.getData() == null || issue.getData().isAfter(LocalDate.now()) ||
				issue.getDescrizione() == null || issue.getDescrizione().equals("") ||  issue.getDescrizione().length() > 500
			)
				return false;
		return true;
	}

	public String getTokenUtente() {
		return utente.getToken();
	}
}