package controller;

import javax.swing.JDialog;

import boundary.dialog.CreaUtenteDialog;
import dto.CreaUtenteDTO;
import entity.Utente;
import exceptions.BadTokenException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CreaUtenteController extends Controller
{
	private JDialog dialog;
	private Utente utente;
	
	public CreaUtenteController(AreaAmministratoreController controller, Utente utente) {
		super(controller);
		this.utente = utente;
		creadDialog();
		dialog.setVisible(true);
	}

	public void creadDialog() {
		dialog = new CreaUtenteDialog(this, frame);	
	}

	public Utente getUtente()
	{
		return utente;
	}

	public void creaUtente(CreaUtenteDTO utenteDTO) throws Exception 
	{
		if( !checkUtente(utenteDTO) )
			throw new Exception("Campi non compilati");
			
		Response response = client.target(USER_SERVER_URL).path("/users/createUser")
				.request()
				.header( "Token", utente.getToken() )
				.put(Entity.json(utenteDTO));
		
		if(response.getStatus() != 200)
			if(response.getStatus() == 403)
				throw new BadTokenException("Token non valido o o scaduto");
			else
				throw new Exception(response.getStatus() + " " + response.readEntity(String.class));
	}

	private boolean checkUtente(CreaUtenteDTO utente) 
	{
		if(	utente == null || 
			utente.getNome() == null || utente.getNome().equals("") || 
			utente.getCognome() == null || utente.getCognome().equals("") ||
			utente.getEmail() == null || utente.getEmail().equals("") ||
			utente.getPassword() == null || utente.getPassword().equals("") 
			)
			return false;
		return true;
	}
}
