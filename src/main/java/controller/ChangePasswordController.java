package controller;

import entity.Utente;
import dto.ChangePasswordResponse;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.swing.JDialog;

import boundary.dialog.ChangePasswordDialog;

public class ChangePasswordController extends Controller
{
	private Utente utente;

    public ChangePasswordController(Controller controller, Utente utente)
    {
        super(controller);
        this.utente = utente;
        JDialog dialog = new boundary.dialog.ChangePasswordDialog(frame, this, utente);
    }
	
	public boolean executeChange(Utente utente, String oldPassword, String newPassword1, String newPassword2)
    {
    	if(utente.getToken().trim().isEmpty())
    	{
    		System.out.println("Invalid token");
    		return false; 
    	}
    	
    	String token = utente.getToken();
    		
        if(		oldPassword.trim().isEmpty() || newPassword1.trim().isEmpty() || 
        		newPassword2.trim().isEmpty() || newPassword1.length() <8 )
        	return false;

        if(newPassword1.equals(newPassword1) )
        	return applyChange(token, oldPassword, newPassword1);
        else
        	return false;
        
    }

    private boolean applyChange(String token, String passwordOld, String newPassword) 
    {
        try {
            
            String json = String.format("{\"token\":\"%s\", \"oldPassword\":\"%s\", \"newPassword\":\"%s\"}", token, passwordOld, newPassword);

            Response response = client.target(this.USER_SERVER_URL).path("auth/change-password")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);

            ChangePasswordResponse changeResponse = response.readEntity(ChangePasswordResponse.class);

            if (response.getStatus() == 200 && changeResponse.isSuccess()) {
                System.out.println("Password cambiata con successo.");
                return true;
            } else {
                System.out.println("Errore nel cambio password: " + changeResponse.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Errore durante la richiesta di cambio password: " + e.getMessage());
            return false;
        }
    }

	public void showProfiloDialog(ChangePasswordDialog dialog) {
		dialog.dispose();
		new ProfiloController(this, utente);
	}
}
