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
		formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		formPanel.setMaximumSize(new Dimension(500, 400));
		gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
		gridBag.insets = new Insets(5,5,5,5);
		gridBag.gridy = 0;
		
		passwordAttualeLabel = ModernLabel.createLabel("Password attuale");
		addFormPanel(passwordAttualeLabel);
		passwordAttualeField = ModernTextField.createPasswordField();
		addFormPanel(passwordAttualeField);
		
		addFormPanel(Box.createVerticalStrut(20));
		
		nuovaPasswordLabel1 = ModernLabel.createLabel("Nuova password");
		addFormPanel(nuovaPasswordLabel1);
		nuovaPasswordField1 = ModernTextField.createPasswordField();
		addFormPanel(nuovaPasswordField1);
		
		addFormPanel(Box.createVerticalStrut(20));
		
		nuovaPasswordLabel2 = ModernLabel.createLabel("Ripeti nuova password");
		addFormPanel(nuovaPasswordLabel2);
		nuovaPasswordField2 = ModernTextField.createPasswordField();
		addFormPanel(nuovaPasswordField2);
		addFormPanel(Box.createVerticalStrut(20));
	}
	
	private void creaBottons() 
	{
		sendButton = ModernButton.createNewButtonPainted("Conferma", Color.BLUE,  Color.WHITE, Color.BLUE);
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonPanel.setBackground(Color.WHITE);
		sendButton.addActionListener(e -> actionSend()); 
        buttonPanel.add(sendButton);
	}

	private void actionSend()
	{
	    String pwOld = passwordAttualeField.getText().trim();
	    String pwNew1 = nuovaPasswordField1.getText().trim();
	    String pwNew2 = nuovaPasswordField2.getText().trim();

	    boolean success = controller.executeChange(utente, pwOld, pwNew1, pwNew2);

	    if (success) {
	        javax.swing.JOptionPane.showMessageDialog(this, "Password cambiata con successo!");
	    } else {
	        javax.swing.JOptionPane.showMessageDialog(this, "Errore nel cambio password. Controlla i dati inseriti.");
	    }
	}

	private void addFormPanel(Component component) {
		gridBag.gridy++;
		formPanel.add(component, gridBag);
	}
}
