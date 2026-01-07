package controller;

import javax.swing.JDialog;

import boundary.dialog.CreaTeamWorkDialog;
import entity.Utente;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class CreaTeamWorkController extends Controller{
	private Utente utente;
	protected JDialog dialog;
	
	public CreaTeamWorkController(AreaAmministratoreController controller, Utente utente) {
		super(controller);
		this.utente = utente;
		creaDialog();
		dialog.setVisible(true);
	}

	private void creaDialog() {
		dialog = new CreaTeamWorkDialog(frame, this);
	}

	public void creaTeamWork(String nomeTeamWork, String emailResponsabile) throws Exception {
		checkCreazioneValues(nomeTeamWork,emailResponsabile);
		
		Response response = client.target(ISSUE_SERVER_URL).path("/teamwork/creazione")
			.request()
			.header("token", utente.getToken())
			.header("nome", nomeTeamWork)
			.header("email", emailResponsabile)
			.put(Entity.text(""));
		
		elaboraErrore(response);
	}

	private void checkCreazioneValues(String nomeTeamWork, String emailResponsabile) throws Exception {
		if(	nomeTeamWork == null || nomeTeamWork.equals("") ||
			emailResponsabile == null || emailResponsabile.equals("") 	
			)
			throw new Exception("Campi non compilati");
	}

	public void close() {
		dialog.dispose();
	}
}
