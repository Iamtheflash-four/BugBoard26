package boundary;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class AreaIssue extends JFrame {

    private JTable issueTable;
    private DefaultTableModel tableModel;

    private JButton btnNuovaIssue;
    private JButton btnGestisciIssue;
    private JButton btnSwitch;
    private JButton btnDettagliIssue; 
    private JButton btnAssegna; 
    private JButton btnAreaUtenze; 
    private JButton btnLogout; 
    
    private JLabel titolo; 
    private JPanel mainPanel;  
    private JPanel buttonPanel; 
    
    private boolean showingRicevute = true;

    public AreaIssue() {
        setTitle("Gestione Issue - BugBoard26");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        componiGUI();
        titolo = new JLabel("Benvenuto nell'area Issue");
        titolo.setFont(new Font("Arial", Font.BOLD, 16));
        titolo.setForeground(new Color(30, 30, 30));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        setVisible(true);
    }

    private void componiGUI() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);

        String[] colonne = { "Nome Progetto", "Priorità", "Tipo" };
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        createTable();

        JScrollPane scrollPane = new JScrollPane(issueTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        btnNuovaIssue = ModernButton.createNewButtonPainted("Nuova Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        btnGestisciIssue = ModernButton.createNewButtonPainted("Gestisci Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        btnSwitch = ModernButton.createNewButtonPainted("Issue Inviate", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        btnDettagliIssue = ModernButton.createNewButtonPainted("Dettagli Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215)); 
        btnAssegna = CreateSpecialButton("Assegna Issue");
        btnAreaUtenze = CreateSpecialButton("Gestione Utenze");
        btnLogout = ModernButton.createNewButtonPainted("Logout", new Color(255, 0, 0), new Color(255,255,255), new Color(255, 0, 0));

        if (btnNuovaIssue != null) { btnNuovaIssue.addActionListener(e -> creaIssue()); }

        if (btnGestisciIssue != null) {  btnGestisciIssue.addActionListener(e -> gestisciIssue()); }

        if (btnSwitch != null) { btnSwitch.addActionListener(e -> switchView()); }
        
        if(btnDettagliIssue != null) { btnDettagliIssue.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }
        
        if(btnAssegna != null) { btnAssegna.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }

        if(btnAreaUtenze != null) { btnAreaUtenze.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }
        
        if(btnLogout != null) { btnLogout.addActionListener(e -> dispose()); } 
         buttonPanel = createPanel();

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);

        caricaIssueRicevute();
    }

	private void createTable() {
		issueTable = new JTable(tableModel);
     // Font e altezza righe
        issueTable.setFont(new Font("Arial", Font.PLAIN, 14));
        issueTable.setRowHeight(28);

        // Colori di sfondo e testo
        issueTable.setBackground(new Color(245, 250, 255)); // azzurro chiarissimo
        issueTable.setForeground(new Color(30, 30, 30));    // testo scuro

        // Colore selezione
        issueTable.setSelectionBackground(new Color(0, 120, 215)); // blu Unina
        issueTable.setSelectionForeground(Color.WHITE);

        // Header personalizzato
        JTableHeader header = issueTable.getTableHeader();
        header.setBackground(new Color(0, 120, 215));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setOpaque(true);

        // Bordo tabella
        issueTable.setShowGrid(true);
        issueTable.setGridColor(new Color(200, 220, 240));
	}

	private JPanel createPanel() 
	{
		JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 10));
		if (btnNuovaIssue != null) buttonPanel.add(btnNuovaIssue);
        if (btnGestisciIssue != null) buttonPanel.add(btnGestisciIssue);
        if (btnSwitch != null) buttonPanel.add(btnSwitch);
        if (btnDettagliIssue != null) buttonPanel.add(btnDettagliIssue);
        if (btnAssegna != null) buttonPanel.add(btnAssegna);
        if (btnAreaUtenze != null) buttonPanel.add(btnAreaUtenze);
        if (btnLogout != null) buttonPanel.add(btnLogout);
        buttonPanel.setBackground(Color.WHITE);
		return buttonPanel;
	}

    private void creaIssue() {
        JOptionPane.showMessageDialog(this, "Funzione 'Crea Nuova Issue' in sviluppo");
    }

    private void gestisciIssue() {
        int selectedRow = issueTable.getSelectedRow();
        if (selectedRow != -1) {
            String nomeProgetto = (String) tableModel.getValueAt(selectedRow, 0);
            JOptionPane.showMessageDialog(this, "Gestione issue per: " + nomeProgetto);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una issue dalla tabella");
        }
    }

    private void switchView() {
        showingRicevute = !showingRicevute;
        if (btnSwitch != null) {
            btnSwitch.setText(showingRicevute ? "Visualizza Inviate" : "Visualizza Ricevute");
        }
        tableModel.setRowCount(0);

        if (showingRicevute) {
            caricaIssueRicevute();
        } else {
            caricaIssueInviate();
        }
    }
 
    private JButton CreateSpecialButton(String text)
	{
		if(true) //ho messo true/false per testarlo. Da sostituire con il metodo del controller
		{
			JButton btn; 
			btn = ModernButton.createNewButtonPainted(text, new Color(0, 120, 215) , new Color(255, 255, 255), new Color(0, 120, 215));
			btn.addActionListener(e -> {
	            JOptionPane.showMessageDialog(this, 
	                    "Funzione in sviluppo", 
	                    "Info", 
	                    JOptionPane.INFORMATION_MESSAGE);
	            });
			return btn; 
		}
		else return null;
	}

    //DATI DI TEST --ESEMPIO--
    private void caricaIssueRicevute() {
        tableModel.addRow(new Object[] { "Progetto Alpha", "Alta", "Bug" });
        tableModel.addRow(new Object[] { "Progetto Beta", "Media", "Richiesta" });
    }
    //DATI DI TEST --ESEMPIO--
    private void caricaIssueInviate() {
        tableModel.addRow(new Object[] { "Progetto Gamma", "Bassa", "Suggerimento" });
        tableModel.addRow(new Object[] { "Progetto Delta", "Alta", "Bug" });
    }
}
