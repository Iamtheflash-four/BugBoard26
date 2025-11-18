package controller;

import javax.swing.JFrame;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class Controller 
{
	//private HttpServer server;
    protected WebTarget server;
//    protected static final String SERVER_URL = "https://bugboardlogin.onrender.com";
    private static final String SERVER_URL = "http://localhost:8080";
    protected Client client;
    protected JFrame frame;
    
    protected Controller()
    {
    	client = ClientBuilder.newClient();	
    	server = client.target(this.SERVER_URL);	//URL server
    }
    
    public Controller(Controller controller) {
        this.client = controller.client;
        this.server = controller.server;
        this.frame = controller.frame;
    }
}
