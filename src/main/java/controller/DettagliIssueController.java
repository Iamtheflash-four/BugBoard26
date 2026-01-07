package controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JDialog;

import dto.IssueDTO;
import entity.Utente;
import jakarta.ws.rs.core.Response;

public class DettagliIssueController extends Controller
{
	JDialog dialog;
	protected Utente utente;
	protected IssueDTO issue;
	
	public DettagliIssueController(Controller controller, IssueDTO issue, Utente utente) {
		super(controller);
		this.utente = utente;
		this.issue = issue;
		creaDialog(issue);
		dialog.setVisible(true);
	}

	private void creaDialog(IssueDTO issue) {
		dialog = new boundary.dialog.DettagliIssueDialog(frame, this, issue);
	}

	public Path scaricaImmagine(String imageName) throws Exception {
	    Response response = client.target(ISSUE_SERVER_URL).path("7issue/immagini/download")
	    		.request()
	    		.header("idIssue", issue.getIdIssue())
	            .header("nome", imageName)	            
	            .header("Token", utente.getToken())
	            .get();

	    if (response.getStatus() != 200) 
	    	elaboraErrore(response);
	    
	    byte[] data = response.readEntity(byte[].class);
        Path output = Paths.get("download/" + imageName);
        Files.createDirectories(output.getParent());
        Files.write(output, data);	       
	    return output;
	}
}
