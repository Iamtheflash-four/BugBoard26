package boundary.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boundary.theme.ModernButton;
import boundary.theme.ModernPanel;
import boundary.theme.ModernTextField;
import boundary.theme.TitlePanel;
import controller.CreaTeamWorkController;

public class CreaTeamWorkDialog extends JDialog 
{
	private JPanel mainPanel;
	private TitlePanel headerPanel;
	private JButton indietroButton;
	private JPanel formPanel;
	private GridBagConstraints gridBag;
	private JTextField nomeField;
	private JTextField emailField;
	private JButton confermaButton;
	private JButton annullaButton;
	private JPanel buttonPanel;

	public CreaTeamWorkDialog(JFrame frame, CreaTeamWorkController controller) 
	{
		super(frame, "Crea TeamWork", true);
		this.setLocationRelativeTo(null);			//Center of screen
		this.setMinimumSize(new Dimension(500, 600));
		compiniGUI();
		addEvents(controller);
		setContentPane(mainPanel);
	}

	private void compiniGUI() {
		mainPanel = ModernPanel.createWhitePanel(500, 600);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		creaHeader();
		creaForm();
		addComponents();
	}

	private void creaHeader() {
		inizializzaHeader();
	}

	private void inizializzaHeader() {
		headerPanel = new boundary.theme.TitlePanel("Area Teamwork");
		indietroButton = ModernButton.createNavbarButton("chiudi");
		
		headerPanel.add(Box.createHorizontalStrut(5));
		headerPanel.add(indietroButton);
	}
	
	private void creaForm() 
	{
		creaFormPanel();
        
        nomeField = ModernTextField.createField("teamwork", 20);
        addFieldWidthLabel(formPanel, gridBag, "Nome TeamWork:", nomeField);
        
        emailField = ModernTextField.createField("utente@email.it", 20);
        addFieldWidthLabel(formPanel, gridBag, "Email utente responsabile", emailField);
        
        setupButtons();
        addButtonPanel(formPanel, gridBag);
	}

	private void addComponents() {
		mainPanel.add(headerPanel);
		mainPanel.add(formPanel);
	}
	
	protected void creaFormPanel() {
		formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);
        gridBag.anchor = GridBagConstraints.WEST;  
        gridBag.gridy = 0;
	}
	
	private void setupButtons()
    {
        // Conferma: bottone blu, testo bianco, bordo blu
        confermaButton = ModernButton.createNewButtonPainted("Conferma", Color.BLUE,   Color.WHITE,  Color.BLUE, 160, 40);
            
        // Annulla: sfondo bianco, testo blu, bordo bianco (quasi invisibile)
        annullaButton = ModernButton.createNewButtonPainted("Chiudi",Color.WHITE,  Color.BLUE, Color.BLUE, 160, 40);  
              
        annullaButton.addActionListener(e -> dispose());          
    }
	
	private void addFieldWidthLabel(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
	
	private void addButtonPanel(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(confermaButton);
        buttonPanel.add(annullaButton);

        panel.add(buttonPanel, gbc);
    }
	
	private void addEvents(CreaTeamWorkController controller)
	{
		confermaButton.addActionListener(e -> {
            creaTeamWork(controller);
        });  
		
		indietroButton.addActionListener(e -> {
            controller.close();
        });  
	}

	private void creaTeamWork(CreaTeamWorkController controller) 
	{
		String nomeTeamWork = this.nomeField.getText();
		String emailResponsabile = this.emailField.getText();
		
		try {
			controller.creaTeamWork(nomeTeamWork, emailResponsabile);
			JOptionPane.showMessageDialog(this, "Teamwork creato con successo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
