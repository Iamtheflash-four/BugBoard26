package boundary;

import javax.swing.*;
import javax.swing.table.*;

import boundary.theme.ModernButton;

import java.awt.*;

public class AreaIssue extends JPanel {
    private JTable issueTable;
    private DefaultTableModel tableModel;

    private JButton buttonNuovaIssue;
    private JButton buttonGestisciIssue;
    private JButton buttonSwitch;
    private JButton buttonDettagliIssue; 
    private JButton buttonAssegna; 
    private JButton buttonAreaUtenze; 
    private JButton buttonLogout; 
    
    private JLabel titolo; 
    private JPanel mainPanel;  
    private JPanel buttonPanel; 
    
    private boolean showingRicevute = true;

    public AreaIssue() 
    {
       setSize(700, 400);
       componiGUI();
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

        initializeButtons();

        addListeners();

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        caricaIssueRicevute();
    }

	private void initializeButtons() {
		buttonNuovaIssue = ModernButton.createNewButtonPainted("Nuova Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        buttonGestisciIssue = ModernButton.createNewButtonPainted("Gestisci Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        buttonSwitch = ModernButton.createNewButtonPainted("Issue Inviate", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
        buttonDettagliIssue = ModernButton.createNewButtonPainted("Dettagli Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215)); 
        buttonAssegna = CreateSpecialButton("Assegna Issue");
        buttonAreaUtenze = CreateSpecialButton("Gestione Utenze");
        buttonLogout = ModernButton.createNewButtonPainted("Logout", new Color(255, 0, 0), new Color(255,255,255), new Color(255, 0, 0));
	}

	private void addListeners() {
		if (buttonNuovaIssue != null) { buttonNuovaIssue.addActionListener(e -> creaIssue()); }

        if (buttonGestisciIssue != null) {  buttonGestisciIssue.addActionListener(e -> gestisciIssue()); }

        if (buttonSwitch != null) { buttonSwitch.addActionListener(e -> switchView()); }
        
        if(buttonDettagliIssue != null) { buttonDettagliIssue.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }
        
        if(buttonAssegna != null) { buttonAssegna.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }

        if(buttonAreaUtenze != null) { buttonAreaUtenze.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }); }
        
         buttonPanel = createPanel();
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
		if (buttonNuovaIssue != null) buttonPanel.add(buttonNuovaIssue);
        if (buttonGestisciIssue != null) buttonPanel.add(buttonGestisciIssue);
        if (buttonSwitch != null) buttonPanel.add(buttonSwitch);
        if (buttonDettagliIssue != null) buttonPanel.add(buttonDettagliIssue);
        if (buttonAssegna != null) buttonPanel.add(buttonAssegna);
        if (buttonAreaUtenze != null) buttonPanel.add(buttonAreaUtenze);
        if (buttonLogout != null) buttonPanel.add(buttonLogout);
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
        if (buttonSwitch != null) {
            buttonSwitch.setText(showingRicevute ? "Visualizza Inviate" : "Visualizza Ricevute");
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
			JButton button; 
			button = ModernButton.createNewButtonPainted(text, new Color(0, 120, 215) , new Color(255, 255, 255), new Color(0, 120, 215));
			button.addActionListener(e -> {
	            JOptionPane.showMessageDialog(this, 
	                    "Funzione in sviluppo", 
	                    "Info", 
	                    JOptionPane.INFORMATION_MESSAGE);
	            });
			return button; 
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
