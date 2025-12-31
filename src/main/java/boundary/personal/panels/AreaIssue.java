package boundary.personal.panels;

import javax.swing.*;
import javax.swing.table.*;

import boundary.personal.IssueTableUtente;
import boundary.theme.IssueTable;
import boundary.theme.ModernButton;
import controller.AreaUtenteController;
import dto.IssueDTO;
import entity.Utente;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AreaIssue extends JPanel {
    protected IssueTable issueTable;
    private DefaultTableModel tableModel;
    private ArrayList<IssueDTO> elencoIssue;
    private JButton buttonNuovaIssue;
    private JButton buttonGestisciIssue;
    private JButton buttonSwitch;
    private JButton buttonDettagliIssue; 
    private JButton buttonAssegna; 
    private JButton buttonAreaUtenze; 
    private JButton buttonLogout;    
    private JLabel titolo; 
    private JPanel buttonPanel;      
    private boolean showingRicevute = true;
	protected AreaUtenteController controller;

    public AreaIssue(controller.AreaUtenteController controller) 
    {
    	super(new BorderLayout());
    	this.controller = controller;
    	setSize(700, 400);
    	componiGUI();
    	caricaIssueRicevute();
    }

   private void componiGUI() {
	//       this = new JPanel(new BorderLayout());
	   this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	   this.setBackground(Color.WHITE);

	   initializeButtons();
	   createTable();
	
	    JScrollPane scrollPane = new JScrollPane(issueTable);
//	    this.add(scrollPane, BorderLayout.CENTER);
	
	    addListeners();
	    this.add(scrollPane);
	    this.add(buttonPanel, BorderLayout.SOUTH);
	}

   public void createTable() {
	   issueTable = new IssueTableUtente(controller);
   }

   private void initializeButtons() {
//		buttonNuovaIssue = ModernButton.createNewButtonPainted("Nuova Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
//        buttonGestisciIssue = ModernButton.createNewButtonPainted("Gestisci Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
    buttonSwitch = ModernButton.createNewButtonPainted("Issue Inviate", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
//        buttonDettagliIssue = ModernButton.createNewButtonPainted("Dettagli Issue", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215)); 
//        buttonAssegna = CreateSpecialButton("Assegna Issue");
//        buttonAreaUtenze = CreateSpecialButton("Gestione Utenze");
//        buttonLogout = ModernButton.createNewButtonPainted("Logout", new Color(255, 0, 0), new Color(255,255,255), new Color(255, 0, 0));
	}

	private void addListeners() {
//		if (buttonNuovaIssue != null) { buttonNuovaIssue.addActionListener(e -> creaIssue()); }

        if (buttonGestisciIssue != null) {  buttonGestisciIssue.addActionListener(e -> gestisciIssue()); }

        if (buttonSwitch != null) { buttonSwitch.addActionListener(e -> switchView()); }
       
         buttonPanel = createPanel();
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


    private void gestisciIssue() {
        int selectedRow = issueTable.getSelectedRow();
        if (selectedRow != -1) {
            String nomeProgetto = issueTable.getNomeProgetto(selectedRow);
            JOptionPane.showMessageDialog(this, "Gestione issue per: " + nomeProgetto);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una issue dalla tabella");
        }
    }

    private void switchView() {
        showingRicevute = !showingRicevute;
        if (buttonSwitch != null) 
            buttonSwitch.setText(showingRicevute ? "Visualizza Inviate" : "Visualizza Ricevute");
        
        issueTable.setRowCount(0);

        if (showingRicevute) 
            caricaIssueRicevute();
         else 
            caricaIssueInviate();
    }

    //DATI DI TEST --ESEMPIO--
    private void caricaIssueRicevute() {
    	try {
			elencoIssue = controller.getElencoIssue();
			for(IssueDTO issue : elencoIssue)
				createRow(issue);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private Object[] createRow(IssueDTO issue) {
		Object[] row = new Object[6];
		row[0] = issue.getProgetto();
		row[1] = issue.getTipo();
		row[2] = issue.getPriorita();
		row[3] = issue.getTipo();
		row[4] = issue.getData();
		row[5] = issue;
		issueTable.addRow(row);
		return row;
	}

    public IssueTable getIssueTable() {
        return issueTable;
    }

    public AreaUtenteController getController() {
        return this.controller;
    }

	//DATI DI TEST --ESEMPIO--
    private void caricaIssueInviate() 
    {
//    	elencoIssueAssegnate
    }
}
