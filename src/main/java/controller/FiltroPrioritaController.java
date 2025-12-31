package controller;

import dto.IssueDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

import boundary.personal.panels.AreaIssue;


public class FiltroPrioritaController {
    
    private AreaIssue areaIssue;
    private String backendUrl;
    private String token;
    private Client client;
    

    public FiltroPrioritaController(AreaIssue areaIssue) {
        this.areaIssue = areaIssue;
        this.backendUrl = "https://bugboard26-issue.onrender.com"; // TODO: Configura con l'URL reale
        this.client = ClientBuilder.newClient();
        this.token = recuperaToken();
    }
    
    public void applicaFiltroPriorita(String priorita) {
        try {
            // Crea il target dell'endpoint
            WebTarget target = client.target(backendUrl)
                    .path("issue/filtraPriorita")
                    .queryParam("priorita", priorita);
            
            // Effettua la richiesta GET con il token nell'header
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .header("Token", token)
                    .get();
            
            if (response.getStatus() == 200) {
                // Leggi la risposta come lista di IssueDTO
                ArrayList<IssueDTO> issueFiltrate = response.readEntity(
                        new GenericType<ArrayList<IssueDTO>>() {}
                );
                
                // Aggiorna la tabella con le issue filtrate
                aggiornaTabella(issueFiltrate);
            } else {
                System.err.println("Errore nel recupero issue: " + response.getStatus());
                // TODO: Mostra messaggio di errore all'utente
            }
            
            response.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Mostra messaggio di errore all'utente
        }
    }
    
   
    private void aggiornaTabella(ArrayList<IssueDTO> issueFiltrate) {
        // Pulisci la tabella
        areaIssue.getIssueTable().setRowCount(0);
        
        // Aggiungi le nuove righe
        for (IssueDTO issue : issueFiltrate) {
            Object[] row = new Object[6];
            row[0] = issue.getProgetto();
            row[1] = issue.getTipo();
            row[2] = issue.getPriorita();
            row[3] = issue.getTitolo();
            row[4] = issue.getData();
            row[5] = issue;
            areaIssue.getIssueTable().addRow(row);
        }
    }
    
    
    private String recuperaToken() {
        return areaIssue.getController().getToken();
    }
    
 
    public void resetFiltro() {
        applicaFiltroPriorita("Tutte");
    }
}
