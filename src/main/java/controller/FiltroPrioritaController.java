package controller;

import dto.IssueDTO;
import entity.Utente;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

import boundary.personal.panels.AreaIssue;

public class FiltroPrioritaController extends Controller{
    
    private AreaIssue areaIssue;
    private String token;
    
    public FiltroPrioritaController(AreaIssue areaIssue) {
    	super(areaIssue.getController());
        this.areaIssue = areaIssue;
        this.token = recuperaToken();
    }
    
    public void applicaFiltroPriorita(String priorita) throws Exception {
    	String path = creaPath();
        Response response = client.target(ISSUE_SERVER_URL).path(path)
        		.request()
                .header("Token", token)
                .header("priorita", priorita)
                .get();
        
        if (response.getStatus() == 200) {
            // Leggi la risposta come lista di IssueDTO
            ArrayList<IssueDTO> issueFiltrate = response.readEntity(
                    new GenericType<ArrayList<IssueDTO>>() {}
            );
            
            // Aggiorna la tabella con le issue filtrate
            aggiornaTabella(issueFiltrate);
        } else 
        	this.elaboraErrore(response);
        response.close();       
    }
    
   
    private String creaPath() {
    	boolean admin = getUtente().isAmministratore();
    	String path = "/issue/filtro/priorita";
		if(!admin)
		{
			path += "/utente";
			if(areaIssue.isShowingRicevute())
				path += "/assegnate";
			else
				path += "/segnalate";
		}
		else
			if(!areaIssue.isShowingRicevute())
				path += "/admin/segnalate";
			else
				path += "/utente/assegnate";
		return path;
	}

	private Utente getUtente() {
		return areaIssue.getController().getUtente();
	}

	private void aggiornaTabella(ArrayList<IssueDTO> issueFiltrate) {
        // Pulisci la tabella
        areaIssue.getIssueTable().setRowCount(0);
        
        // Aggiungi le nuove righe
        for (IssueDTO issue : issueFiltrate) {
            areaIssue.getIssueTable().addRow(issue);
        }
    }
        
    private String recuperaToken() {
        return areaIssue.getController().getToken();
    }
    
    public void resetFiltro() throws Exception {
        applicaFiltroPriorita("Tutte");
    }
}
