package boundary.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import boundary.theme.TitlePanel;
import controller.DettagliIssueController;
import dto.IssueDTO;

public class DettagliIssueDialog extends JDialog
{
	protected JPanel mainPanel;
	protected JPanel headerPanel;
	protected JPanel infoProgettoPanel;
	protected JPanel infoTipoPanel;
	protected JPanel infoDataPanel;
	protected JPanel infoPrioritaPanel;
	protected JLabel descrizioneLabel;
	protected TitlePanel titlePanel;
	protected JPanel imgeNAmePanel;
	protected JPanel imageNamePanel;
	protected JScrollPane scrollMainPanel;
	private JTextArea rispostaArea;
	private JTextArea descrizioneArea;

	public DettagliIssueDialog(JFrame frame, DettagliIssueController controller, IssueDTO issue)
	{
		super(frame, "InfoIssue", true);
		mainPanel = ModernPanel.createWhitePanel(450, 600);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		componiGUI(issue);
		showImmaginiAllegate(issue.getImageNames(), controller);
		avvolgiScrollPane(mainPanel);
		setContentPane(scrollMainPanel);
	}

	protected void componiGUI(IssueDTO issue) {
		creatTitlePanel(issue.getTitolo());
		creaInfoSection(issue);
	}

	protected void creatTitlePanel(String titolo) {
		titlePanel = new TitlePanel(titolo);
		mainPanel.add(titlePanel);
	}

	protected void creaInfoSection(IssueDTO issue) {
		createInfoHeader(issue);
		creaTextArea(issue.getDescrizione(), descrizioneArea);
		if(issue.getRisposta() != null && !issue.getRisposta().equals(""))
			showRisposta(issue);
	}

	protected void createInfoHeader(IssueDTO issue) {
		headerPanel = ModernPanel.createWhitePanel(450, 60);
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		infoProgettoPanel = crateInfoBlck("Progetto", issue.getProgetto());
		infoTipoPanel = crateInfoBlck("Tipo", issue.getTipo());
		infoPrioritaPanel = crateInfoBlck("Priorita", issue.getPriorita());
		infoDataPanel = crateInfoBlck("Data",
			    issue.getData().toString());
		mainPanel.add(headerPanel);
	}

	protected JPanel crateInfoBlck(String nome, String value) {
		JPanel panel = ModernPanel.createWhitePanel(100, 50);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel nomeInfoLabel = ModernLabel.createBoldLabel(nome, Color.BLUE);
		JLabel valueInfoLabel = ModernLabel.createLabel(value, Color.BLACK);
		panel.add(nomeInfoLabel);
		panel.add(valueInfoLabel);
		headerPanel.add(panel);
		return panel;
	}
	
	protected void creaTextArea(String text, JTextArea area)
	{
		area = new JTextArea(text);
		area.setFont(new Font("Arial", Font.ITALIC, 15));
		area.setLineWrap(true);		//va a capo se è troppo lungo
		area.setWrapStyleWord(true);	//non spezzare la parola se va a capo
		area.setEditable(false);
		area.setBorder(null);
		area.setBackground(mainPanel.getBackground());
		area.setRows(10);
		area.setColumns(50);
		area.setAlignmentX(Component.CENTER_ALIGNMENT);
		area.setMaximumSize(new Dimension(300, 500));
		JScrollPane areaPanel = ModernPanel.avvolgiScrollPane(area, 400, 200);
		mainPanel.add(areaPanel);
	}

	protected void showImmaginiAllegate(ArrayList<String> imageNames, DettagliIssueController controller) {
		imageNamePanel = ModernPanel.createWhitePanel(300, 50);
		imageNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel imagesNameLabel = ModernLabel.createLabel("Immagini allegate", Color.BLUE);
		imageNamePanel.add(imagesNameLabel);
		
		if(imageNames != null) {
			for(String image : imageNames)
				addImage(image, controller);
		}
		else {
			JLabel emptyLabel = ModernLabel.createBoldLabel("Nessuna immagine", Color.red);
			imageNamePanel.add(emptyLabel);
		}
		mainPanel.add(imageNamePanel);
	}

	protected void addImage(String image, DettagliIssueController controller) {
		if(image != null)
		{
			JLabel imageLabel = ModernLabel.createLabel(image, Color.BLACK); 
			imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
			imageLabel.addMouseListener(new MouseAdapter() { 
				@Override public void mouseClicked(MouseEvent e){ 
					scaricaImmagine(imageLabel, controller); 	
				}
			});
			imageNamePanel.add(imageLabel); 
			imageNamePanel.add(Box.createHorizontalStrut(10));
		}		
	}
	
	protected void scaricaImmagine(JLabel imageLabel, DettagliIssueController controller) {
		String nome = imageLabel.getText();
		try {
			Path path = controller.scaricaImmagine(nome).toAbsolutePath();
			JOptionPane.showMessageDialog(this, "Scaricato in:\n " + path.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "erroe", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void showRisposta(IssueDTO issue)
	{
		creaTextArea(issue.getRisposta(), rispostaArea);
	}
	
	protected void avvolgiScrollPane(JPanel panel)
	{
		scrollMainPanel = ModernPanel.avvolgiScrollPane(panel, 500, 600);
	}
}
