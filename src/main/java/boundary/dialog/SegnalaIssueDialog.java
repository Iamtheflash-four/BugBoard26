package boundary.dialog;

import javax.swing.*;
import boundary.theme.*;

import controller.SegnalaIssueController;
import entity.Issue;
import entity.Progetto;
import entity.Utente;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegnalaIssueDialog extends JDialog {
    private SegnalaIssueController controller;

    private JButton btnIndietro;
    private JButton btnSegnala;
    
    private ArrayList<Progetto> elencoProgetti;
    private JComboBox<String> comboBoxProgetto;
    
    private JComboBox<String> comboBoxPriorita;
    private JScrollPane scrollMainPanel;
    private JPanel mainPanel;
    private JPanel formPanel;

	private TitlePanel titlePanel;

	private JLabel progettoLabel;

	private GridBagConstraints gridBag;

	private JLabel prioritaLabel;

	private JLabel subjectLabel;
	private JTextField subjectField;

	private JLabel descrizioneLabel;
	private JTextArea descrizioneField;
	private JScrollPane descrizionePanel;
	
	private JButton sendButton;

	private ImageInput imageField;

    public SegnalaIssueDialog(JFrame frame, SegnalaIssueController controller)
    {
    	super(frame, "Segnala issue", true);
    	this.controller = controller;
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        componiGUI();
        
        setVisible(true);
    }

    private void componiGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        titlePanel = new TitlePanel("Segnala issue");
        creaForm();
        addEvents();
        
        mainPanel.add(titlePanel);
        mainPanel.add(formPanel);
//        mainPanel.add(imageField);
        avvolgiScrollPane(mainPanel);
        setContentPane(scrollMainPanel);
    }

	private void creaForm() {
		initializeFornPanel();

        initializeFormComponents();
        
        addComponentsToForm();
	}

	private void initializeFormComponents() {
		createComboBoxProgetti();
        
        prioritaLabel = ModernLabel.createLabel("Priorità", 12, Color.BLACK);
        comboBoxPriorita = ModernComboBox.createCombobox(new String[] { "Bassa", "Media", "Alta" });
        
        subjectLabel = ModernLabel.createLabel("Titolo", 12, Color.BLACK);
        subjectField = ModernTextField.createField("Nuova issue");
        subjectField.setColumns(30);
        
        descrizioneLabel = ModernLabel.createLabel("Descrizione", 12, Color.BLACK);
        descrizioneField = ModernTextField.createFieldBox("Nuova issue", 250, 130);
        descrizionePanel = ModernTextField.avvolgiScrollPane(descrizioneField, 250, 130);
        imageField = new ImageInput();
        sendButton = ModernButton.createNewButtonPainted("Segnala", 80, 40);
        sendButton.setMinimumSize(sendButton.getSize());
	}

	private void createComboBoxProgetti() {
		progettoLabel = ModernLabel.createLabel("Progetto", 12, Color.BLACK);
        try {
			elencoProgetti = controller.getElencoProgetti();
			if(elencoProgetti == null || elencoProgetti.isEmpty())
				throw new Exception("L'utente non lavora a nessun progetto");
		} 
        catch (SQLException e) {
        	e.printStackTrace();
        }    
        catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
        comboBoxProgetto = ModernComboBox.createCombobox(elencoProgetti);
	}

	private void initializeFornPanel() {
		formPanel =  new JPanel(new GridBagLayout());
		formPanel.setBackground(Color.WHITE);
		formPanel.setPreferredSize(new Dimension(500, 600));
		formPanel.setMaximumSize(new Dimension(500, 600));

        gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(12, 12, 12, 12);
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
	}
    
    public void addFormRow(JComponent component1, JComponent component2)
    {
    	formPanel.add(component1, gridBag);
    	gridBag.gridx++;
    	formPanel.add(component2, gridBag);
    	gridBag.gridx=0;
    	gridBag.gridy++;
    }
    
    private void addComponentsToForm()
    {
    	addFormRow(progettoLabel, comboBoxProgetto);
        addFormRow(prioritaLabel, comboBoxPriorita);
        addFormRow(subjectLabel, subjectField);
        gridBag.fill = GridBagConstraints.BOTH;
        addDescrizoneField();
        addImageField();
        gridBag.gridx=0;
        gridBag.fill = GridBagConstraints.NONE;
        gridBag.gridwidth=2;
        formPanel.add(sendButton, gridBag);
    }

	private void addDescrizoneField() {
		formPanel.add(descrizioneLabel, gridBag);
        gridBag.gridx++;
        formPanel.add(descrizioneField, gridBag); 
        gridBag.gridy++;
	}
    
    private void addImageField()
    {
    	gridBag.gridx=0;
    	gridBag.gridwidth = 2;
    	gridBag.anchor = gridBag.WEST;
    	gridBag.fill = gridBag.BOTH;
    	formPanel.add(imageField, gridBag);
    	gridBag.gridy++;
    }
	
    private void addEvents()
    {
    	sendButton.addActionListener(e->{
    		segnalaIssue();
    	});
    }

	private void segnalaIssue() {
		String progetto = (String)comboBoxProgetto.getSelectedItem();
		String priorita = (String)comboBoxPriorita.getSelectedItem();
		String titolo = subjectField.getText();
		String descrizione = descrizioneField.getText();
		controller.segnalaIssue(new Issue(progetto, priorita, titolo, descrizione));
	}
	
	private void avvolgiScrollPane(JPanel panel)
	{
		scrollMainPanel = new JScrollPane(panel);
		scrollMainPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollMainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		mainPanel.setSize(700, 500);
		mainPanel.setMinimumSize(scrollMainPanel.getSize());
		mainPanel.setPreferredSize(scrollMainPanel.getSize());
	}
}
