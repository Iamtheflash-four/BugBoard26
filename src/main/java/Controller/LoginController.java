package Controller;

import javax.swing.JFrame;

public class LoginController 
{
	private JFrame frame;
	
	public LoginController()
	{
		frame = new boundary.LoginArea(this);
	}
}
