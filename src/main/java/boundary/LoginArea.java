package Boundary;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import Controller.LoginController;
public class LoginArea extends JFrame 
{
    private LoginController controller;
    private JPanel mainPanel;
    private JPanel formPanel;
    
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
        this.setTitle("Login - Unina BugBoard26");
        this.setSize(400, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la finestra
        this.setResizable(false);
        
        // Inizializza il pannello principale
        creaGUI();
        
        setVisible(true);
    }
    
    private void creaGUI()
    {
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
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
    
    private void creaForm() {
        formPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Email
        emailLabel = createLabel("Indirizzo e-mail", gbc, 0);
        emailField = new JTextField();
        createLoginField(emailField, "", gbc, 1); //rimosso il campo di default nome@gmail.com
        
        //Password
        passwordLabel = createLabel("Password", gbc, 2);
        passwordField = new JPasswordField();
        createLoginField(passwordField, "", gbc, 3); //rimosso il campo di default xxxxxxx
    }

    private JLabel createLabel(String text, GridBagConstraints gbc, int row)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLUE);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(label, gbc);
        
        return label;
    }
    
    private void createLoginField(JTextField field, String text, GridBagConstraints gbc, int row) {
        field.setText(text);
        field.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Size
        Dimension dim = new Dimension(350, 30);
        field.setPreferredSize(dim);
        field.setMaximumSize(dim); 
        field.setMinimumSize(dim);
        
        gbc.gridx = 0;
        gbc.gridy = row;
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
