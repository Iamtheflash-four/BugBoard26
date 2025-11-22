package boundary;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import boundary.theme.ModernTextField;

import java.awt.*;
import controller.LoginController;

public class LoginArea extends JFrame 
{
    private LoginController controller;
    private JPanel mainPanel;
    private JPanel formPanel;
    private GridBagConstraints gbc;
    
    private JLabel titleLabel;
    private JPanel titlePanel;
    
    private JLabel emailLabel;
    private JTextField emailField;
    
    private JLabel passwordLabel;
    private JTextField passwordField;
    
    private JButton btnAccedi;
    private JLabel messageLabel;
    
    public LoginArea(LoginController controller)
    {
        this.controller = controller;
        
        // Configurazione finestra principale
        this.setTitle("Login - UninaSwap");
        this.setSize(500, 450);
        this.setMinimumSize(new Dimension(500, 450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la finestra
        this.setResizable(true);
        
        // Inizializza il pannello principale
        creaGUI();
        
        setVisible(true);
    }
    
    private void creaGUI()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Title
        creaTitolo();
        
        // Initialize Form panel
        creaForm();

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        btnAccedi = ModernButton.createNewButtonPainted("Accedi", new Color(0, 0, 255), new Color(255,255,255), new Color(0, 0, 255)); 
        btnAccedi.addActionListener(e -> {
            provaLogin();
        });
        buttonPanel.add(btnAccedi);
        
        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
        
        // Permette di premere Enter dopo aver digitato la password
        passwordField.addActionListener(e -> {
            provaLogin();
        });
        
        mainPanel.setBackground(Color.WHITE);
        formPanel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);
        
        this.add(mainPanel);
    }
    
    private void creaTitolo() {
    	titleLabel = ModernLabel.createTitleLabel("BugBoard26");
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(200, 60));
		titlePanel.setMaximumSize(new Dimension(200, 60));
		titlePanel.setBackground(Color.WHITE);
		
		titlePanel.add(titleLabel);
		mainPanel.add(titlePanel);
	}

	private void creaForm() {
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setMaximumSize(new Dimension(500, 400));
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Email
        emailLabel = createLoginLabel("Indirizzo e-mail");
        emailField =  createLoginField("nome@gmail.com"); //rimosso il campo di default nome@gmail.com
        
        addForm(Box.createVerticalStrut(20));
        
        //Password
        passwordLabel = createLoginLabel("Password");
        passwordField = createPasswordField(); //rimosso il campo di default xxxxxxx
        
        addForm(Box.createVerticalStrut(20));
    }

    private JLabel createLoginLabel(String text)
    {
        JLabel label = ModernLabel.createLabel(text);
        addForm(label);
        
        return label;
    }
    
 
	private JTextField createLoginField(String text) {
		JTextField field = ModernTextField.createLoginField(text);
    	addForm(field);
    	return field;
    }
	
	private JPasswordField createPasswordField()
	{
		JPasswordField field = ModernTextField.createPasswordField();
		addForm(field);
		return field;
	}
    
    private void addForm(Component component)
    {
    	gbc.gridy++;
    	formPanel.add(component, gbc);	
    }

	private void provaLogin()
	{
	    String email = emailField.getText().trim();
	    String password = new String(passwordField.getText());
	    
	    // Chiamata al metodo di validazione del controller
	    if (!controller.verificaDatiCampiLogin(email, password)) 
		    JOptionPane.showMessageDialog(this, "Campi non compilati");
	    
	    // Chiamata al controller per verificare le credenziali nel database
	    entity.Utente utente;
		try {
			utente = controller.verificaCredenziali(email, password);
			controller.switchAreaPersonale(utente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	} //commentato per evitare errori di compilazione
	
}