package boundary.personal;

import controller.AreaUtenzeController;
import entity.Utente;
import boundary.personal.BarraUtente;
import boundary.theme.ModernButton;  // ← Utility statica

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;

public class AreaUtenze_AdminOptions extends JFrame 
{
    private AreaUtenzeController controller;
    private JPanel mainPanel;
    private JPanel tablePanel;
 
    private JPanel titlePanel;
    private JTable tabellaUtenze;
    private JScrollPane scrollPane;
    private Utente utente;

    public AreaUtenze_AdminOptions(AreaUtenzeController controller, Utente utente) {
        this.controller = controller;
        this.utente = utente; 
        
        this.setTitle("Gestione Utenze - Unina BugBoard26");
        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        componiGUI();
        //caricaUtenze();
        
        this.setVisible(true);
    }
    
    private void componiGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // barra in alto
        titlePanel = new BarraUtenteAreaUtenze(utente, controller);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titlePanel);

        // pannello tabella “appoggiato” sotto la barra
        creaTabella();
        tablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(tablePanel);

        this.setContentPane(mainPanel);
    }

    private void creaTabella() 
    {
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] colonne = {"Nome", "Cognome", "Email", "Ruolo"};
        Object[][] dati = {};

        tabellaUtenze = new JTable(dati, colonne);
        tabellaUtenze.setRowHeight(30);
        tabellaUtenze.setFont(new Font("Arial", Font.PLAIN, 13));
        tabellaUtenze.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaUtenze.setGridColor(new Color(220, 220, 220));

        // header stile “barra blu”
        JTableHeader header = tabellaUtenze.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        ((DefaultTableCellRenderer) header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);  // titoli centrati [web:23][web:39]

        scrollPane = new JScrollPane(tabellaUtenze);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        tablePanel.add(scrollPane, BorderLayout.NORTH);
    }

  /* public void caricaUtenze() 
    {
        List<Utente> utenze = controller.getTutteUtenze();
        String[] colonne = {"ID", "Username", "Email", "Ruolo", "Data Registrazione"};
        Object[][] dati = new Object[utenze.size()][4];
        
        for (int i = 0; i < utenze.size(); i++) 
        {
            Utente u = utenze.get(i);
            dati[i][0] = u.getNome();
            dati[i][1] = u.getCognome();
            dati[i][2] = u.getEmail();
            	if(u.getAdminStatus())
            		dati[i][3] = "AMMINISTRATORE"; 
            	else
            		dati[i][3] = "STANDARD";
        }
        
        tabellaUtenze.setModel(new javax.swing.table.DefaultTableModel(dati, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    } */ 
}
