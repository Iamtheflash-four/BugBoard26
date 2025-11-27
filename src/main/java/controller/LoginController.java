package controller;

import entity.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import boundary.LoginArea;
import dto.LoginResponse;


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
    
    public Utente verificaCredenziali(String email, String password) throws Exception
    {
        //JSON credenziali
        String json = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        //Invio HTTP request
        Response response = server.path("auth/login")
                .request(MediaType.APPLICATION_JSON) 
                .post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);	//Method post
        
        LoginResponse loginResponse = response.readEntity(LoginResponse.class);
        
        if (!loginResponse.isSuccess()) 
        	throw new Exception(response.getStatus() + " " + loginResponse.getMessage());
        return loginResponse.getUtente();
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
