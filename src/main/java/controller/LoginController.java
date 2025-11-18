package controller;

import entity.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

import boundary.LoginArea;


public class LoginController extends Controller
{	
	public LoginController()
	{
		this.frame = new boundary.LoginArea(this);
	}

    public boolean verificaDatiCampiLogin(String email, String password) {
        return email != null && !email.isBlank() &&
               password != null && !password.isBlank();
    }
    
    public Utente verificaCredenziali(String email, String password) {
        //JSON credenziali
        String json = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        //Invio HTTP request
        Utente response = server.path("auth/login")
                .request(MediaType.APPLICATION_JSON) 
                .post(Entity.entity(json, MediaType.APPLICATION_JSON), Utente.class);	//Method post
        if (response != null && response.equals(""))
        	response = null;
        return response;
    }

    public boolean verificaEmail(String email) {
        return email != null && email.contains("@");
    }

   	public static void main(String[] args)
   	{
	   new LoginController();
   	}

	public void switchAreaPersonale(Utente utente) {
		this.frame.dispose();
		new AreaUtenteController(this, utente);
	}
}
