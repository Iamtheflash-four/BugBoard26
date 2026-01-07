package controller;

import java.util.ArrayList;

import javax.swing.JDialog;

import boundary.dialog.AddProgettoDialog;
import boundary.dialog.AddUtenteDialog;
import boundary.dialog.AreaTeamWorkDialog;
import dto.IssueDTO;
import dto.TeamDTO;
import entity.Utente;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

public class AreaTeamWorkController extends Controller
{
	protected JDialog dialog;
	private Utente utente;

	public AreaTeamWorkController(AreaUtenteController controller, Utente utente) {
		super(controller);
		this.utente = utente;
		creaDialog();
		dialog.setVisible(true);
	}

	protected void creaDialog() {
		dialog = new AreaTeamWorkDialog(frame, this); 
	}

	public ArrayList<TeamDTO> getElencoTeam() throws Exception 
	{
		Response response = client.target(USER_SERVER_URL).path("/teamwork/elenchi/team")
			.request()
			.header("token", utente.getToken())
			.get();
		
		if(response == null ||  response.getStatus() != 200)
			elaboraErrore(response);
		return response.readEntity(new GenericType<ArrayList<TeamDTO>>() {});		
	}

	public void showAddProject(long idTeam) {
		JDialog dialog = new AddProgettoDialog(this.dialog, this, idTeam);
	}

	public void addProject(String nomeProgetto, long idTeam) throws Exception {
		Response response = client.target(USER_SERVER_URL).path("/progetto/create")
				.request()
				.header("token", utente.getToken())
				.header("nome", nomeProgetto)
				.header("idteam", idTeam)
				.put(Entity.text(""));
		if(response == null ||  response.getStatus() != 200)
			elaboraErrore(response);
	}

	public void showAddUser(long idTeam) {
		JDialog dialog = new AddUtenteDialog(this.dialog, this, idTeam);
	}

	public void addUser(String email, long idTeam) throws Exception {
		Response response = client.target(USER_SERVER_URL).path("/teamwork/progetto/add")
				.request()
				.header("token", utente.getToken())
				.header("email", email)
				.header("idteam", idTeam)
				.put(Entity.text(""));
		if(response == null ||  response.getStatus() != 200)
			elaboraErrore(response);
	}
}