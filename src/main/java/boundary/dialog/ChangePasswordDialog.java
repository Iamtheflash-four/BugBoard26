package boundary.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import boundary.theme.ModernTextField;
import boundary.theme.TitlePanel;
import controller.ChangePasswordController;
import controller.ProfiloController;
import entity.Utente;

public class ChangePasswordDialog extends JDialog 
{
	private ChangePasswordController controller;
	private JPanel formPanel;
	private Container mainPanel;
	private GridBagConstraints gridBag;
	private TitlePanel titlePanel;
	
	private JLabel passwordAttualeLabel;
	private JPasswordField passwordAttualeField;

	private JLabel nuovaPasswordLabel1;
	private JPasswordField nuovaPasswordField1;
	
	private JLabel nuovaPasswordLabel2;
	private JPasswordField nuovaPasswordField2;
	
	private JButton sendButton;
	private JPanel buttonPanel;
	private Utente utente;
	private JButton closeButton;
	
	public ChangePasswordDialog(JFrame frame, ChangePasswordController controller, Utente utente)
	{
		super(frame, "Cambia password", true);
		this.utente = utente;

		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.controller = controller;
		
		creaGUI(utente);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	private void creaGUI(Utente utente) {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		titlePanel = new TitlePanel("Cambia Password");
		
		creaForm();
		
		creaBottons();
		
		mainPanel.add(titlePanel);
		mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
	}

	private void creaForm() {
		createFormPanel();
		
		initializeForm();
		
		addComponentsToForm();
	}

	public void createFormPanel() {
		formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		formPanel.setMaximumSize(new Dimension(500, 400));
		gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
		gridBag.insets = new Insets(5,5,5,5);
		gridBag.gridy = 0;
	}

	public void initializeForm() {
		passwordAttualeLabel = ModernLabel.createLabel("Password attuale");
		passwordAttualeField = ModernTextField.createPasswordField();
		nuovaPasswordLabel1 = ModernLabel.createLabel("Nuova password");
		nuovaPasswordField1 = ModernTextField.createPasswordField();
		nuovaPasswordLabel2 = ModernLabel.createLabel("Ripeti nuova password");
		nuovaPasswordField2 = ModernTextField.createPasswordField();
	}

	public void addComponentsToForm() {
		addFormPanel(passwordAttualeLabel);
		addFormPanel(passwordAttualeField);		
		addFormPanel(Box.createVerticalStrut(20));				
		addFormPanel(nuovaPasswordLabel1);		
		addFormPanel(nuovaPasswordField1);		
		addFormPanel(Box.createVerticalStrut(20));				
		addFormPanel(nuovaPasswordLabel2);		
		addFormPanel(nuovaPasswordField2);
		addFormPanel(Box.createVerticalStrut(20));
	}
	
	private void creaBottons() 
	{
		sendButton = ModernButton.createNewButtonPainted("Conferma", Color.BLUE,  Color.WHITE, Color.BLUE);
		closeButton = ModernButton.createNewButtonPainted("Chiudi", Color.WHITE,  Color.BLUE, Color.BLUE);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonPanel.setBackground(Color.WHITE);
		
		addListeners(); 
		
        buttonPanel.add(sendButton);
        buttonPanel.add(closeButton);
	}

	private void addListeners() {
		sendButton.addActionListener(e -> actionSend()); 
		closeButton.addActionListener(e -> {
			this.dispose();
		});
	}

	private void actionSend()
	{
	    String oldPassword  = new String(passwordAttualeField.getPassword());
	    String newPassword1 = new String(nuovaPasswordField1.getPassword());
	    String newPassword2 = new String(nuovaPasswordField2.getPassword());
	    
	    try {
			controller.checkPassword(oldPassword, newPassword1, newPassword2);
			changePassword(oldPassword, newPassword1, newPassword2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}	   
	}

	public void changePassword(String oldPassword, String newPassword1, String newPassword2) 
	{
		boolean success = controller.executeChange(utente, oldPassword, newPassword1, newPassword2);
	    if (success) {
	        javax.swing.JOptionPane.showMessageDialog(this, "Password cambiata con successo!");
	        controller.showProfiloDialog(this);
	    } else {
	        javax.swing.JOptionPane.showMessageDialog(this, "Errore nel cambio password. Controlla i dati inseriti.");
	    }
	}

	private void addFormPanel(Component component) {
		gridBag.gridy++;
		formPanel.add(component, gridBag);
	}
}
