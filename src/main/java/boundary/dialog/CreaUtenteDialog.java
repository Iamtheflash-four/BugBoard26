package boundary.dialog;

import javax.swing.*; 
import boundary.theme.ModernButton;
import boundary.theme.ModernTextField;
import controller.CreaUtenteController;
import dto.CreaUtenteDTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CreaUtenteDialog extends JDialog
{
    private JTextField nomeField;
    private JTextField cognomeField; 
    private JTextField mailField; 
    private JPasswordField passwordField; 
    private JButton confermaButton; 
    private JButton annullaButton; 
    private JPanel mainPanel;
    private JPanel buttonPanel;
	private GridBagConstraints gridBag;
	private CreaUtenteController controller;
	private JCheckBox adminCheck; 

    public CreaUtenteDialog(CreaUtenteController controller, JFrame frame) 
    {
    	super(frame, "Crea nuovo utente", true);
    	this.controller = controller;
        setupButtons();
        creaComponenti();
        this.setSize(400, 500);
        this.setMinimumSize(new Dimension(350, 500));
        // background generale bianco
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        add(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void creaComponenti()
    {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);
        gridBag.anchor = GridBagConstraints.WEST;  
        gridBag.gridy = 0;
        initializeFields();
    }
    
    private void initializeFields()
    {
    	// Campi input con ModernTextField
        nomeField = ModernTextField.createField("Nome", 20);
        addFieldWidthLabel(mainPanel, gridBag, "Nome:", nomeField);

        cognomeField = ModernTextField.createField("Cognome", 20);
        addFieldWidthLabel(mainPanel, gridBag, "Cognome:",  cognomeField);

        mailField = ModernTextField.createField("Email", 20);
        addFieldWidthLabel(mainPanel, gridBag, "Email:", mailField);
        
        adminCheck = new JCheckBox("Amministratore");
        addFieldWidthLabel(mainPanel, gridBag, "Amministratore:", adminCheck);
        
        passwordField = ModernTextField.createPasswordField();
        addFieldWidthLabel(mainPanel, gridBag, "Password:", passwordField);

        // Pannello bottoni
        addButtonPanel(mainPanel, gridBag);   
    }

    private void setupButtons()
    {
        // Conferma: bottone blu, testo bianco, bordo blu
        confermaButton = ModernButton.createNewButtonPainted("Conferma", Color.BLUE,   Color.WHITE,  Color.BLUE, 160, 40);
            
        // Annulla: sfondo bianco, testo blu, bordo bianco (quasi invisibile)
        annullaButton = ModernButton.createNewButtonPainted("Chiudi",Color.WHITE,  Color.BLUE, Color.BLUE, 160, 40);
            
        confermaButton.addActionListener(e -> {
            creaUtente();
        });    
              
        annullaButton.addActionListener(e -> dispose());          
    }

    // Se ti servirà un DTO, potrai aggiungere questo metodo e il relativo import
    // public CreaUtenteRequestDTO getDatiUtente() { ... }

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
    
    public void creaUtente()
    {
    	String nome = nomeField.getText();
    	String cognome = cognomeField.getText();
    	String email = mailField.getText();
    	boolean admin = adminCheck.isSelected();
    	String password = passwordField.getPassword().toString();
    	CreaUtenteDTO utente = new CreaUtenteDTO(nome, cognome, email, admin, password);
    	
    	try {
			controller.creaUtente(utente);
			JOptionPane.showMessageDialog(this, "Utente creato con successo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
    }
}