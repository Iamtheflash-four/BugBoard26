package boundary;

import javax.swing.*;
import java.awt.*;
import Controller.LoginController;

public class LoginArea extends JFrame 
{
    private LoginController controller;
    private JPanel mainPanel;
    
    private JLabel emailLabel;
    private JTextField emailField;
    
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    
    private JButton btnAccedi;
    private JButton btnRecuperoPassword;
    private JLabel messageLabel;
    
    public LoginArea(LoginController controller)
    {
        this.controller = controller;
        
        // Configurazione finestra principale
        this.setTitle("Login - UninaSwap");
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
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label e campo Email
        emailLabel = createLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(emailLabel, gbc);
        
        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(emailField, gbc);
        
        // Label e campo Password
        passwordLabel = createLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*'); // Carattere per nascondere la password
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(passwordField, gbc);
        
        // Messaggio di errore/successo
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        mainPanel.add(messageLabel, gbc);
        
        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        btnAccedi = new JButton("Accedi");
        btnAccedi.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Funzione in sviluppo", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(btnAccedi);
        
        btnRecuperoPassword = new JButton("Recupera Password");
        btnRecuperoPassword.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Funzione in sviluppo", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(btnRecuperoPassword);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(buttonPanel, gbc);
        
        // Permette di premere Enter dopo aver digitato la password
        passwordField.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Funzione in sviluppo", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        this.add(mainPanel);
    }
    
    private JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
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
}
