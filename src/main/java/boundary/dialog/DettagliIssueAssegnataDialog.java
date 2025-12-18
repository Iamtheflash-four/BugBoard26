package boundary.dialog;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import boundary.theme.ModernButton;
import boundary.theme.ModernPanel;
import boundary.theme.ModernTextField;
import controller.DettagliIssueAssegnataController;
import dto.IssueDTO;

public class DettagliIssueAssegnataDialog extends DettagliIssueDialog 
{
	private JButton rispondiButton;
	private JTextArea rispostaField;
	private JScrollPane rispostaPanel;
	private JPanel buttonPanel;
	private JButton annullaButton;
	private DettagliIssueAssegnataController controller;

	public DettagliIssueAssegnataDialog(DettagliIssueAssegnataController controller, IssueDTO issue) 
	{
		super(issue);
		this.controller = controller;
	}
	
	@Override
	protected void componiGUI(IssueDTO issue)
	{
		super.componiGUI(issue);
		rispostaPanel = creaRiespostaField();
		buttonPanel = creaButtonPanel();
		
		mainPanel.add(rispostaPanel);
		mainPanel.add(buttonPanel);
	}
	
	private JScrollPane creaRiespostaField() {
		rispostaField = ModernTextField.createFieldBox("Scrivi la risposta", 400, 600);
		JScrollPane pane = ModernTextField.avvolgiScrollPane(rispostaField, 400, 200);
		pane.setVisible(false);
		return pane;
	}
	
	private JPanel creaButtonPanel()
	{
		JPanel panel = ModernPanel.createWhitePanel(300, 50);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		rispondiButton = creaRispondiButton();
		annullaButton = creaAnnullaButton();
		panel.add(rispondiButton);
		panel.add(annullaButton);
		panel.setVisible(true);
		return panel;
	}
	
	private JButton creaRispondiButton() 
	{
		JButton button = ModernButton.createNewButtonPainted("Rispondi", 100, 40);
		button.setVisible(true);
		button.addActionListener(e->{
			if(rispostaPanel.isVisible()) {
				salvaRisposta();
			}
			else 
			{
				rispostaPanel.setVisible(true);
				annullaButton.setVisible(true);
				mainPanel.revalidate();  // ricalcola il layout
				mainPanel.repaint();     // ridisegna la finestra
			}		
		});
		return button;
	}
	
	private JButton creaAnnullaButton()
	{
		JButton button = ModernButton.createNewButtonPainted("Annulla", 100, 40);
		button.setVisible(false);
		button.addActionListener(e->{
			if(rispostaPanel.isVisible())
			{
				rispostaField.setText("");
				rispostaPanel.setVisible(false);
				annullaButton.setVisible(false);
			}		
		});
		return button;
	}
	
	private void salvaRisposta()
	{
		String text = this.rispostaField.getText();
		try {
			controller.salvaRisposta(text);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
			
	}
}
