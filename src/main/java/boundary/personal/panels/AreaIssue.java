package boundary.personal.panels;

import javax.swing.*;
import javax.swing.table.*;

import boundary.personal.IssueTableUtente;
import boundary.theme.IssueTable;
import boundary.theme.ModernButton;
import boundary.theme.ModernPanel;
import controller.AreaUtenteController;
import dto.IssueDTO;
import entity.Utente;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AreaIssue extends JPanel {
	protected IssueTable issueTable;
    private AreaIssueHeader headerPanel;      
	protected AreaUtenteController controller;
	protected JButton updateButton;
	private ArrayList<IssueDTO> elencoIssueSegnalate;
	protected boolean showingRicevute;
	private ArrayList<IssueDTO> elencoIssueAssegnate;

    public AreaIssue(controller.AreaUtenteController controller) 
    {
    	super();
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.controller = controller;
    	setSize(700, 400);
    	componiGUI();
    }

   private void componiGUI() {
	   this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	   this.setBackground(Color.WHITE);

	   initializeHeader(controller);
	   createTable();
	   	
	    JScrollPane scrollPane = new JScrollPane(issueTable);
	    
	    this.add(scrollPane);
	}

   public void createTable() {
	   issueTable = new IssueTableUtente(controller);
	   this.showingRicevute = false;
	   this.switchView();
   }

   private void initializeHeader(AreaUtenteController controller) {
	   headerPanel = new AreaIssueHeader(this);
	   this.add(headerPanel);
	}

    public void switchView() {
        showingRicevute = !showingRicevute;
        
        headerPanel.getButtonSwitch().setText(showingRicevute ? "Visualizza Segnalate" : "Visualizza Assegnate");
        
        updateTable();
    }

    public void caricaIssueRicevute() {
    	try {
			elencoIssueAssegnate = controller.getElencoIssuAssegnate();
			for(IssueDTO issue : elencoIssueAssegnate)
				issueTable.addRow(issue);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public IssueTable getIssueTable() {
        return issueTable;
    }

    private void caricaIssueInviate() 
    {
    	try {
			elencoIssueSegnalate = controller.getElencoIssueSegnalate();
			for(IssueDTO issue : elencoIssueSegnalate)
				issueTable.addRow(issue);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void updateTable() 
    {
    	issueTable.setRowCount(0);

        if (showingRicevute) 
            caricaIssueRicevute();
         else 
            caricaIssueInviate();
	}

	public AreaUtenteController getController() {
		return controller;
	}
	
	public boolean isShowingRicevute() {
		return showingRicevute;
	}
}
