package controller;

import entity.Utente;
import dto.ChangePasswordResponse;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.swing.JDialog;

public class ChangePasswordController extends Controller
{
    private Utente utente;

    public ChangePasswordController(Controller controller, Utente utente)
    {
        super(controller);
        this.utente = utente;
        JDialog dialog = new boundary.dialog.ChangePasswordDialog(frame, this, utente);
    }

    public boolean executeChange(Utente utente, String pwOld, String pwNew1, String pwNew2)
    {
    	if(utente.getToken().trim().isEmpty())
    	{
    		System.out.println("Invalid token");
    		return false; 
    	}
    	
    	String tkn = utente.getToken();
    		
        if(pwOld.trim().isEmpty() || pwNew1.trim().isEmpty() || pwNew2.trim().isEmpty())
        {
            System.out.println("Uno o più campi non sono compilati correttamente");
            return false;
        }

        if(pwNew1.trim().equalsIgnoreCase(pwNew2.trim()))
        {
            return applyChange(tkn, pwOld, pwNew1);
        }
        else
        {
            System.out.println("Le password non sono uguali!");
            return false;
        }
    }

    private boolean applyChange(String token, String pwOld, String newPassword)
    {
        try {
            
            String json = String.format("{\"token\":\"%s\", \"oldPassword\":\"%s\", \"newPassword\":\"%s\"}", token, pwOld, newPassword);

            Response response = server.path("auth/change-password")
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
}
