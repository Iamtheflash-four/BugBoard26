package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.ImageDTO;
import entity.Issue;
import entity.Utente;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JDialog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SegnalaIssueControllerTest {

    private SegnalaIssueController controller;
    private Client client;
    private WebTarget target;
    private WebTarget path;
    private Invocation.Builder builder;
    private Response response;
    private Utente utente;

    private Issue issueValidaConImmagini;
    private Issue issueValidaSenzaImmagini;
    private ArrayList<File> immaginiValide;
    private ArrayList<File> nessunaImmagine;

    @BeforeEach
    void setup() {
        creaMoks();       
        controller.client = client;
        issueValidaConImmagini = creaIssue();

        issueValidaSenzaImmagini = creaIssue();

        immaginiValide = new ArrayList<>();
        immaginiValide.add(new File("immagine.png"));

        nessunaImmagine = new ArrayList<>();      
    }

	protected void creaMoks() {
		client = mock(Client.class);
        target = mock(WebTarget.class);
        path = mock(WebTarget.class);
        builder = mock(Invocation.Builder.class);
        response = mock(Response.class);
        utente = mock(Utente.class);
        controller = spy(new SegnalaIssueController(utente));
        setMockEvents();
        File f = mock(File.class);
        setMockFile(f);
	}

	protected void setMockFile(File f) {
		try {
			doReturn(new ArrayList<ImageDTO>()).when(controller).carciaImmagini(any());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setMockEvents() {
        when(client.target(anyString())).thenReturn(target);
        when(target.path(anyString())).thenReturn(path);
        when(path.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.header(eq("Token"), anyString())).thenReturn(builder);
        when(builder.post(any(Entity.class), eq(Response.class))).thenReturn(response);
        when(controller.getTokenUtente()).thenReturn("tokenValido");
	}

	protected Issue creaIssue() {
		return new Issue(
                1,
                "EVERGREEN",
                "Bug",
                "Alta",
                "Titolo valido",
                "Descrizione valida",
                LocalDate.now()
        );
	}

    @Test
    void R1_tokenValido_issueValidaConImmagini_server200() throws Exception {
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn("");

        String result = controller.segnalaIssue(issueValidaConImmagini, immaginiValide);

        assertEquals("Issue caricata con successo", result);
    }

    @Test
    void R2_tokenValido_issueValidaSenzaImmagini_server200() throws Exception {
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn("");

        String result = controller.segnalaIssue(issueValidaSenzaImmagini, nessunaImmagine);

        assertEquals("Issue caricata con successo", result);
    }

    @Test
    void R3_tokenValido_issueValidaConImmagini_server207() throws Exception {
        when(response.getStatus()).thenReturn(207);
        when(response.readEntity(String.class)).thenReturn("Immagini non caricate");

        String result = controller.segnalaIssue(issueValidaConImmagini, immaginiValide);

        assertEquals("Immagini non caricate", result);
    }

    @Test
    void R4_tokenNonValido_issueValidaConImmagini_server401() {
        when(utente.getToken()).thenReturn("tokenNonValido");
        when(response.getStatus()).thenReturn(401);
        when(response.readEntity(String.class)).thenReturn("401:  token non valido o scaduto");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R5_tokenNullo_issueValidaConImmagini() {
        when(utente.getToken()).thenReturn(null);
        when(response.getStatus()).thenReturn(401);
        when(response.readEntity(String.class)).thenReturn("401:  token non valido o scaduto");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R6_issueRequestNulla() {
        assertThrows(Exception.class, () ->
                controller.segnalaIssue(null, immaginiValide)
        );
    }

    @Test 
    void R7_issueNulla() 
    { 
    	Issue issueNulla = null; 
    	assertThrows(Exception.class, () -> 
    		controller.segnalaIssue(issueNulla, immaginiValide) 
    	); 
    }
    
    @Test
    void R8_titoloNull() {
        String old = issueValidaConImmagini.getTitolo();
        issueValidaConImmagini.setTitolo(null);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setTitolo(old);
    }

    @Test
    void R9_descrizioneNull() {
        String old = issueValidaConImmagini.getDescrizione();
        issueValidaConImmagini.setDescrizione(null);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setDescrizione(old);
    }

    @Test
    void R10_tipoNull() {
        String old = issueValidaConImmagini.getTipo();
        issueValidaConImmagini.setTipo(null);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setTipo(old);
    }

    @Test
    void R11_prioritaNull() {
        String old = issueValidaConImmagini.getPriorita();
        issueValidaConImmagini.setPriorita(null);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setPriorita(old);
    }

    @Test
    void R12_dataNull() {
        LocalDate old = issueValidaConImmagini.getData();
        issueValidaConImmagini.setData(null);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setData(old);
    }

    @Test
    void R13_titoloVuoto() {
        String old = issueValidaConImmagini.getTitolo();
        issueValidaConImmagini.setTitolo("");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setTitolo(old);
    }

    @Test
    void R14_titoloTroppoLungo() {
        String old = issueValidaConImmagini.getTitolo();
        issueValidaConImmagini.setTitolo("A".repeat(40));

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setTitolo(old);
    }
    
    @Test
    void R15_descrizioneTroppoLunga() {
        String old = issueValidaConImmagini.getDescrizione();
        issueValidaConImmagini.setDescrizione("A".repeat(600));

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setDescrizione(old);
    }

    @Test
    void R16_tipoNonAmmesso() {
        String old = issueValidaConImmagini.getTipo();
        issueValidaConImmagini.setTipo("TaskNonValido");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setTipo(old);
    }

    @Test
    void R17_prioritaNonAmmessa() {
        String old = issueValidaConImmagini.getPriorita();
        issueValidaConImmagini.setPriorita("Urgentissima");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setPriorita(old);
    }

    @Test
    void R18_dataFutura() {
        LocalDate old = issueValidaConImmagini.getData();
        issueValidaConImmagini.setData(LocalDate.now().plusDays(5));

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );

        issueValidaConImmagini.setData(old);
    }

    @Test
    void R19_troppiAllegati() {
        ArrayList<File> immagini = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            immagini.add(new File("img" + i + ".png"));

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immagini)
        );
    }

    @Test
    void R20_formatoFileNonValido() {
        ArrayList<File> immagini = new ArrayList<>();
        immagini.add(new File("file.exe"));

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immagini)
        );
    }

    @Test
    void R21_dimensioneFileEccessiva() {
        ArrayList<File> immagini = new ArrayList<>();
        File f = mock(File.class);
        when(f.length()).thenReturn(10_000_000L);
        immagini.add(f);

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immagini)
        );
    }

    @Test
    void R22_issueValidaSenzaImmagini_server400() {
        when(response.getStatus()).thenReturn(400);
        when(response.readEntity(String.class)).thenReturn("Errore");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaSenzaImmagini, nessunaImmagine)
        );
    }

    @Test
    void R23_issueValidaConImmagini_server500() {
        when(response.getStatus()).thenReturn(500);
        when(response.readEntity(String.class)).thenReturn("Errore interno");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R24_issueValidaConImmagini_timeout() {
        when(response.getStatus()).thenReturn(0);
        when(response.readEntity(String.class)).thenReturn("Timeout");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R25_issueValidaConImmagini_server404() {
        when(response.getStatus()).thenReturn(404);
        when(response.readEntity(String.class)).thenReturn("Non trovato");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R26_issueValidaConImmagini_server405() {
        when(response.getStatus()).thenReturn(405);
        when(response.readEntity(String.class)).thenReturn("Metodo errato");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R27_issueValidaConImmagini_server401_tokenValido() {
        when(response.getStatus()).thenReturn(401);
        when(response.readEntity(String.class)).thenReturn("401:  token non valido o scaduto");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }

    @Test
    void R28_issueValidaConImmagini_server401_tokenScaduto() {
        when(utente.getToken()).thenReturn("tokenScaduto");
        when(response.getStatus()).thenReturn(401);
        when(response.readEntity(String.class)).thenReturn("401:  token non valido o scaduto");

        assertThrows(Exception.class, () ->
                controller.segnalaIssue(issueValidaConImmagini, immaginiValide)
        );
    }
}