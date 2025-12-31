package boundary.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import boundary.theme.TitlePanel;
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

	public DettagliIssueDialog(IssueDTO issue)
	{
		super(new JFrame(), "InfoIssue", true);
		mainPanel = ModernPanel.createWhitePanel(450, 600);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		
		componiGUI(issue);
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
		creaDescrizione(issue.getDescrizione());
		showImmaginiAllegate(issue.getImageNames());
	}

	protected void createInfoHeader(IssueDTO issue) {
		headerPanel = ModernPanel.createWhitePanel(450, 60);
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		infoProgettoPanel = crateInfoBlck("Progetto", issue.getProgetto());
		infoTipoPanel = crateInfoBlck("Tipo", issue.getTipo());
		infoPrioritaPanel = crateInfoBlck("Priorita", issue.getPriorita());
		infoDataPanel = crateInfoBlck("Data",
			    new SimpleDateFormat("dd/MM/yyyy").format(issue.getData()));
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
	
	protected void creaDescrizione(String descrizione)
	{
		JTextArea descrizioneArea = new JTextArea(descrizione);
		descrizioneArea.setFont(new Font("Arial", Font.ITALIC, 15));
		descrizioneArea.setLineWrap(true);		//va a capo se è troppo lungo
		descrizioneArea.setWrapStyleWord(true);	//non spezzare la parola se va a capo
		descrizioneArea.setEditable(false);
		descrizioneArea.setBorder(null);
		descrizioneArea.setBackground(mainPanel.getBackground());
		descrizioneArea.setRows(10);
		descrizioneArea.setColumns(50);
		descrizioneArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		descrizioneArea.setMaximumSize(new Dimension(300, 500));
		JScrollPane descrizionePanel = ModernPanel.avvolgiScrollPane(descrizioneArea, 400, 200);
		mainPanel.add(descrizionePanel);
	}

	protected void showImmaginiAllegate(ArrayList<String> imageNames) {
		imageNamePanel = ModernPanel.createWhitePanel(300, 50);
		imageNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel imagesNameLabel = ModernLabel.createLabel("Immagini allegate", Color.BLUE);
		imageNamePanel.add(imagesNameLabel);
		
		if(imageNames != null)
			for(String image : imageNames)
				if(image != null)
				{
					imageNamePanel.add(ModernLabel.createLabel(image, Color.BLACK));
					imageNamePanel.add(Box.createHorizontalStrut(10));
				}
		mainPanel.add(imageNamePanel);
	}
	
	protected void avvolgiScrollPane(JPanel panel)
	{
		scrollMainPanel = ModernPanel.avvolgiScrollPane(panel, 500, 600);
	}
	
	public static void main(String[] args)
	{
		ArrayList<String> images = new ArrayList<String>();
		images.add("Ciao.png");  images.add("on so.jpg"); 
		String descrizione = "dhdahdahd hsahsahdk adh"
				+ "askjhdaks s"
				+ "ahdkjahdkaadsajhcsckjs \n"
				+ "dadlksajdaj"
				+ "adsa"
				+ "dasdsadsadsadashdkjsdhkjsdhkjsdhkv";
		IssueDTO issue = new IssueDTO(1, 1, "evergren", "bug", "Alta", "Issue Titolo", 
				descrizione, new java.util.Date());
		issue.setImageNames(images);
		JDialog dialog = new DettagliIssueDialog(issue);
	}
}
