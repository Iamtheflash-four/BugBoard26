package controller;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.swing.JFrame;

public class LoginController 
{
	private HttpServer server;
    private WebTarget target;
    private static final String SERVER_URL = "http://localhost:8080";
	
    private JFrame frame;
	
	public LoginController()
	{
		frame = new boundary.LoginArea(this);
	}

    public boolean verificaDatiCampiLogin(String email, String password) {
        return email != null && !email.isBlank() &&
               password != null && !password.isBlank();
    }
    
    public boolean verificaCredenziali(String email, String password) {
        Client client = ClientBuilder.newClient();	
        target = client.target(this.SERVER_URL);	//URL server
        
        //JSON credenziali
        String json = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        //Invio HTTP request
        String response = target.path("auth/login")
                .request(MediaType.TEXT_PLAIN) 
                .post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);	//Method post
        
        return "Riuscito".equals(response);
    }

    public boolean verificaEmail(String email) {
        return email != null && email.contains("@");
    }

   	public static void main(String[] args)
   	{
	   new LoginController();
   	}
}
