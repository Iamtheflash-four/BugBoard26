package boundary.dialog;

import javax.swing.*; 
import boundary.AreaUtenze_AdminOptions;
import boundary.theme.ModernButton;
import boundary.theme.ModernTextField;  
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CreaUtenteDialog extends JDialog
{
    private JTextField nomeField;
    private JTextField cognField; 
    private JTextField mailField; 
    private JPasswordField psswField; 
    private JButton confermaBtn; 
    private JButton annullaBtn; 
    private JPanel mainPanel;
    private JPanel btnPanel;
	private GridBagConstraints gridBag; 
    
    public CreaUtenteDialog(AreaUtenze_AdminOptions parent)
    {
        super(parent, "Crea nuovo utente", true);

        setupButtons();
        creaComponenti();

        // background generale bianco
        getContentPane().setBackground(Color.WHITE);

        pack();
        setLocationRelativeTo(parent);
        add(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void creaComponenti()
    {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);
        gridBag.anchor = GridBagConstraints.WEST;  
        gridBag.gridy = 0;
    }
    
    private void initializeFields()
    {
    	// Campi input con ModernTextField
        nomeField = ModernTextField.createField("Nome", 20);
        addLabelField(mainPanel, gridBag, "Nome:", nomeField);

        cognField = ModernTextField.createField("Cognome", 20);
        addLabelField(mainPanel, gridBag, "Cognome:",  cognField);

        mailField = ModernTextField.createField("Email", 20);
        addLabelField(mainPanel, gridBag, "Email:", mailField);

        psswField = ModernTextField.createPasswordField();
        addLabelField(mainPanel, gridBag, "Password:", psswField);

        // Pannello bottoni
        addButtonPanel(mainPanel, gridBag);   
    }

    private void setupButtons()
    {
        // Conferma: bottone blu, testo bianco, bordo blu
        confermaBtn = ModernButton.createNewButtonPainted(
            "Conferma",
            Color.BLUE,   // background
            Color.WHITE,  // testo
            Color.BLUE,   // bordo
            160,
            40
        );

        // Annulla: sfondo bianco, testo blu, bordo bianco (quasi invisibile)
        annullaBtn = ModernButton.createNewButtonPainted(
            "Annulla",
            Color.WHITE,  // background
            Color.BLUE,   // testo
            Color.WHITE,  // bordo
            160,
            40
        );

        confermaBtn.addActionListener(e -> {
            // qui aggiungerai la chiamata al controller
        });

        annullaBtn.addActionListener(e -> dispose());
    }

    // Se ti servirà un DTO, potrai aggiungere questo metodo e il relativo import
    // public CreaUtenteRequestDTO getDatiUtente() { ... }

    private void addLabelField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field) {
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

        btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(confermaBtn);
        btnPanel.add(annullaBtn);

        panel.add(btnPanel, gbc);
    }
}