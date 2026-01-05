package boundary.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import controller.ProfiloController;
import entity.Utente;

public class ProfiloDialog extends JDialog 
{
	private ProfiloController controller;
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JLabel title;
	private Object nomePanel;
	private JPanel cognomePanel;
	private JPanel emailPanel;
	private JPanel buttonsPanel;
	private JButton changePasswordButton;
	private JButton chiudiButton;
	
	public ProfiloDialog(JFrame frame, ProfiloController controller, Utente utente)
	{
		super(frame, "Profilo " + utente.getNome(), true);
		this.setSize(500, 300);
		this.setMinimumSize(new Dimension(500, 300));
		this.setLocationRelativeTo(null);
		this.controller = controller;
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		creaGui(utente);
		
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	private void creaGui(Utente utente) {
		creaTitle();
		
		nomePanel = creaInfoPanel("Nome: ", utente.getNome());
		cognomePanel = creaInfoPanel("Cognome: ", utente.getCognome());
		emailPanel = creaInfoPanel("Email: ", utente.getEmail());
		
		buttonsPanel = creaButtonsPanel();
		addListeners(utente);
	}

	private JPanel creaButtonsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.WHITE);
		
		changePasswordButton = ModernButton.createNewButtonPainted("Cambia password", Color.WHITE, Color.BLUE, Color.BLUE, 140, 40);
		chiudiButton = ModernButton.createNewButtonPainted("Chiudi", Color.BLUE, Color.WHITE, Color.BLUE, 100, 40);
		
		panel.add(changePasswordButton);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(chiudiButton);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		mainPanel.add(panel);
		return panel;
	}

	private void creaTitle()
	{
		title = ModernLabel.createTitleLabel("BugBoard26");
		titlePanel = new JPanel();
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		titlePanel.setBackground(Color.WHITE);
		
		titlePanel.add(title);
		mainPanel.add(titlePanel);
	}
	
	private JPanel creaInfoPanel(String fieldName, String value)
	{
		JLabel fieldNameLabel = ModernLabel.createLabel(fieldName, 15, new Color(135, 206, 235));
		JLabel valueLabel = ModernLabel.createLabel(value, 15 , Color.BLACK);
		fieldNameLabel.setAlignmentX(LEFT_ALIGNMENT);
		valueLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setAlignmentX(LEFT_ALIGNMENT);
		panel.setBackground(Color.WHITE);

		panel.setSize(new Dimension(Integer.MAX_VALUE, 30));
		panel.setMinimumSize(panel.getSize());
		panel.setPreferredSize(panel.getSize());
		panel.setMaximumSize(panel.getSize());
		
		panel.add(fieldNameLabel);
		panel.add(valueLabel);
		mainPanel.add(panel);
		return panel;
	}
	
	private void addListeners(Utente utente)
	{
		changePasswordButton.addActionListener(e->{
			controller.showChangePasswordDialog(this, utente);
		});	
		chiudiButton.addActionListener(e->{
			this.dispose();
		});	
	}
}
