package controller;

import javax.swing.JFrame;

import exceptions.BadTokenException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class Controller 
{
	//private HttpServer server;
    //protected WebTarget server;
    protected static final String USER_SERVER_URL = "https://bugboardlogin.onrender.com";
    protected static final String ISSUE_SERVER_URL = "https://bugboard26-issue.onrender.com";
    
//    protected static final String ISSUE_SERVER_URL = "http://localhost:8082";
//    protected static final String USER_SERVER_URL = "http://localhost:8080";
    
    protected Client client;
    protected JFrame frame;
    
    protected Controller()
    {
    	client = ClientBuilder.newClient();	
    }
    
    public Controller(Controller controller) {
        this.client = controller.client;
        this.frame = controller.frame;
    }
    
    public void elaboraErrore(Response response) throws Exception 
	{
		if(response == null)
			throw new Exception("Errore connesione");
		else 
		{
			if(response.getStatus() == 401)
				throw new BadTokenException("401:  token non valido o scaduto");
			throw new Exception(response.getStatus() + ": " + response.readEntity(String.class));
		}
	}
}
