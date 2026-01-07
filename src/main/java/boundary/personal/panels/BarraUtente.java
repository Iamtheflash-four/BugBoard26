package boundary.personal.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import controller.AreaUtenteController;
import entity.Utente;

public class BarraUtente extends BarraGenerica
{
    protected AreaUtenteController controller;
    protected Utente utente;
    private JLabel title;
    private JButton segnalaIssueButton;
    protected JPanel buttonPanel;
    private JButton logoutButton;
    private JButton profiloButton;
	private JButton teamButton;

//    private JButton gestioneIssueButton;  // Sempre visibile

    public BarraUtente(Utente utente, AreaUtenteController controller)
    {
        super();
        this.controller = controller;
        this.utente = utente;
        
        title = ModernLabel.createTitleLabel("Benvenuto " + utente.getNome());
        title.setPreferredSize(new Dimension(300, 30));

        segnalaIssueButton = ModernButton.createNavbarButton("<html>Segnala<br>issue</html>");
        teamButton = ModernButton.createNavbarButton("<html>Gestisci<br>team</html>");
        logoutButton = ModernButton.createNavbarButton("Logout");
        profiloButton = ModernButton.createNavbarButton("Profilo 👤");

        addButtonEvents();
        createTitlePanel();
    }

    private void createTitlePanel() 
    {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(segnalaIssueButton);
        buttonPanel.add(teamButton);
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
        addTeamEvent();
        addProfiloEvent();
        addLogoutEvent();
    }

    private void addSegnalaIssueEvent() {
        segnalaIssueButton.addActionListener(e->{
            controller.showSegnalazioneIssue(utente);
        });
    }
    
    private void addTeamEvent() {
        segnalaIssueButton.addActionListener(e->{
            controller.showAreaTeamWork(utente);
        });
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
