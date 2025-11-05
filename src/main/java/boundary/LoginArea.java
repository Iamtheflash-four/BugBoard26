package boundary;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import controller.LoginController;

public class LoginArea extends JFrame 
{
    private LoginController controller;
    private JPanel mainPanel;
    private JPanel formPanel;
    
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
        this.setSize(400, 350);
        this.setMinimumSize(new Dimension(400, 350));
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
            JOptionPane.showMessageDialog(this, 
                "Funzione in sviluppo", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(btnAccedi);
        
        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
        
        // Permette di premere Enter dopo aver digitato la password
        passwordField.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Funzione in sviluppo", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        mainPanel.setBackground(Color.WHITE);
        formPanel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);
        
        this.add(mainPanel);
    }
    
    private void creaTitolo() {
		titleLabel = new JLabel("BugBoard26", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.BLUE);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		
		titlePanel.add(titleLabel);
		mainPanel.add(titlePanel);
	}

	private void creaForm() {
        formPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Email
        emailLabel = createLabel("Indirizzo e-mail", gbc, 0);
        emailField =  createLoginField("nome@gmail.com", gbc); //rimosso il campo di default nome@gmail.com
        
        //Password
        passwordLabel = createLabel("Password", gbc, 2);
        passwordField = createLoginField("xxxxx", gbc); //rimosso il campo di default xxxxxxx
    }

    private JLabel createLabel(String text, GridBagConstraints gbc, int row)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLUE);
        
        gbc.gridy++;
        formPanel.add(label, gbc);
        
        return label;
    }
    
    private JTextField createLoginField(String text, GridBagConstraints gbc) {
    	JTextField field = new JTextField(20);
    	styleTextField(field, text, gbc);
    	return field;
    }
    
    private JPasswordField createPasswordField(String text, GridBagConstraints gbc)
    {
    	JPasswordField field = new JPasswordField(20);
    	styleTextField(field, text, gbc);
    	return field;
    }

	private void styleTextField(JTextField field, String text, GridBagConstraints gbc) 
	{
		field.setText(text);
		field.setForeground(Color.GRAY);
        field.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));	//Solo bordo inferiore
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Size
        Dimension dim = new Dimension(350, 40);
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getForeground().equals(Color.GRAY)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        
        gbc.gridy++;
        formPanel.add(field, gbc);
	}
}

/*  private void provaLogin()
{
    String email = emailField.getText().trim();
    String password = new String(passwordField.getPassword());
    
    // Chiamata al metodo di validazione del controller
    if (!controller.verificaDatiCampiLogin(email, password)) 
    {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText("Attenzione! Verifica i dati inseriti");
        return;
    }
    
    // Chiamata al controller per verificare le credenziali nel database
    boolean loginSuccess = controller.verificaCredenziali(email, password);
    
    if (loginSuccess) {
        messageLabel.setForeground(Color.GREEN);
        messageLabel.setText("Login effettuato con successo!");
        // dispose();
        // new MainWindow(controller).setVisible(true);
    } else {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText("Email o password errati!");
        passwordField.setText(""); // Pulisci il campo password
    }
} */ //commentato per evitare errori di compilazione

/* private void gestisciRecuperoPassword()
{
    String email = emailField.getText().trim();
    
    if (!controller.VerificaEmail(email)) {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText("Attenzione! Verificare la mail inserita");
        return;
    }
    
    // Chiamata al controller per il recupero password
    String recuperoMessage = controller.recuperaPassword(email);
    messageLabel.setForeground(Color.BLUE);
    messageLabel.setText(recuperoMessage);
} */   //commentato per evitare errori di compilazione
