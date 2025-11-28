package boundary;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import controller.AreaUtenteController;
import entity.Utente;

public class BarraUtente extends JPanel
{
    private AreaUtenteController controller;
    private Utente utente;
    private JLabel title;
    private JButton segnalaIssueButton;
    protected JPanel buttonPanel;
    private JButton logoutButton;
    private JButton profiloButton;

    private JButton gestioneIssueButton;  // Sempre visibile

    public BarraUtente(Utente utente, AreaUtenteController controller)
    {
        super();
        this.controller = controller;
        this.utente = utente;
        setSize(Integer.MAX_VALUE, 70);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        setPreferredSize(new Dimension(0, 70));
        setAlignmentX(CENTER_ALIGNMENT);
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(0, 0, 2, 0, new Color(200,200,200)));

        title = ModernLabel.createTitleLabel("Benvenuto " + utente.getNome());
        title.setPreferredSize(new Dimension(300, 30));

        segnalaIssueButton = ModernButton.createNavbarButton("<html>Segnala<br>issue</html>");
        logoutButton = ModernButton.createNavbarButton("Logout");
        profiloButton = ModernButton.createNavbarButton("Profilo 👤");

        gestioneIssueButton = ModernButton.createNavbarButton("Gestione Issue");
        gestioneIssueButton.setPreferredSize(new Dimension(160, 30));

    

        addButtonEvents();
        createTitlePanel();
    }

    private void createTitlePanel() 
    {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(segnalaIssueButton);

        // Bottone sempre visibile
        buttonPanel.add(gestioneIssueButton);

      

        buttonPanel.add(logoutButton);
        buttonPanel.add(profiloButton);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(title);
        add(Box.createHorizontalGlue());
        add(buttonPanel);
    }

    private void addButtonEvents()
    {
        addSegnalaIssueEvent();
        addGestioneIssueEvent();
        addProfiloEvent();
        addLogoutEvent();
      
    }

    private void addSegnalaIssueEvent() {
        segnalaIssueButton.addActionListener(e->{
            controller.segnalaIssue(utente);
        });
    }

    private void addGestioneIssueEvent() {
        //da impl

    }

    private void addProfiloEvent() {
        profiloButton.addActionListener(e->{
            controller.showProfilo(utente);
        });
    }

    private void addLogoutEvent() {
        logoutButton.addActionListener(e->{
            controller.switchLogin();
        });
    }

  
}
