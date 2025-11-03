package boundary;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.LoginController;

public class LoginArea extends JFrame 
{
	private LoginController controller;
	private JPanel mainPanel;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JLabel passwordLabel;
	private JTextField passwordField;
	
	public LoginArea(LoginController controller)
	{
		this.controller = controller;
		this.setSize(800, 600);
		
		//Username field 
		
	}
	
	private createLabel()
	{
		JLabel label 
	}
}
