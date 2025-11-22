package boundary.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		changePasswordButton.addActionListener(e->{
			controller.showChangePasswordDialog(this, utente);
		});	}

	private JPanel creaButtonsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.WHITE);
		
		changePasswordButton = ModernButton.createNewButtonPainted("Cambia password", Color.WHITE, Color.BLUE, Color.BLUE);
		chiudiButton = ModernButton.createNewButtonPainted("Chiudi", Color.BLUE, Color.WHITE, Color.BLUE);
		
		panel.add(changePasswordButton);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(chiudiButton);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		mainPanel.add(panel);
		return panel;
	}

	private void creaTitle()
	{
		title = ModernLabel.createTitleLabel("Info utente");
		titlePanel = new JPanel();
		titlePanel.add(title);
		title.setPreferredSize( new Dimension(Integer.MAX_VALUE, title.getHeight()) );
		title.setMaximumSize( new Dimension(Integer.MAX_VALUE, title.getHeight()) );
		mainPanel.add(title);
	}
	
	private JPanel creaInfoPanel(String fieldName, String value)
	{
		JLabel fieldNameLabel = ModernLabel.createBoldLabel(fieldName, 15, new Color(135, 206, 235));
		JLabel valueLabel = ModernLabel.createLabel(value, Color.BLACK);
		fieldNameLabel.setAlignmentX(LEFT_ALIGNMENT);
		valueLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setAlignmentX(LEFT_ALIGNMENT);
		panel.setBackground(Color.WHITE);

		panel.setSize(new Dimension( fieldNameLabel.getWidth() + valueLabel.getWidth(), 
											fieldNameLabel.getHeight()));
		panel.setPreferredSize(panel.getSize());
		panel.setMaximumSize(panel.getSize());
		
		panel.add(fieldNameLabel);
		panel.add(valueLabel);
		mainPanel.add(panel);
		return panel;
	}
}
