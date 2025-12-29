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
import exceptions.BadTokenException;

public class DettagliIssueAssegnataDialog extends DettagliIssueDialog 
{
	private JButton rispondiButton;
	private JTextArea rispostaField;
	private JScrollPane rispostaPanel;
	private JPanel buttonPanel;
	private JButton annullaButton;

	public DettagliIssueAssegnataDialog(DettagliIssueAssegnataController controller, IssueDTO issue) 
	{
		super(issue);
		addListeners(controller);
	}
	
	@Override
	protected void componiGUI(IssueDTO issue)
	{
		super.componiGUI(issue);
		
		rispostaPanel = creaRiespostaField();
		buttonPanel = creaFlowLayoutPanel(300, 50);
		createButtons();
		addButtons(buttonPanel);
		
		mainPanel.add(rispostaPanel);
		mainPanel.add(buttonPanel);
	}
	
	private JScrollPane creaRiespostaField() {
		rispostaField = ModernTextField.createFieldBox("Scrivi la risposta", 400, 600);
		JScrollPane pane = ModernTextField.avvolgiScrollPane(rispostaField, 400, 200);
		pane.setVisible(false);
		return pane;
	}
	
	protected void createButtons()
	{
		rispondiButton = creaRispondiButton();
		annullaButton = creaAnnullaButton();
	}
	
	protected void addButtons(JPanel panel)
	{
		panel.add(rispondiButton);
		panel.add(annullaButton);
	}
	
	public JPanel creaFlowLayoutPanel(int width, int heigth)
	{
		JPanel panel = ModernPanel.createWhitePanel(width, heigth);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		panel.setVisible(true);
		return panel;
	}
	
	private JButton creaRispondiButton() 
	{
		JButton button = ModernButton.createNewButtonPainted("Rispondi", 100, 40);
		button.setVisible(true);
		return button;
	}
	
	private JButton creaAnnullaButton()
	{
		JButton button = ModernButton.createNewButtonPainted("Annulla", 100, 40);
		button.setVisible(false);
		return button;
	}
	
	private void salvaRisposta(DettagliIssueAssegnataController controller)
	{
		String text = this.rispostaField.getText();
		try {
			controller.salvaRisposta(text);
			JOptionPane.showMessageDialog(this, "Risposta salvata con successo", "200: successo", JOptionPane.INFORMATION_MESSAGE);
		}catch(BadTokenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			controller.showLoginArea();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void addListeners(DettagliIssueAssegnataController controller)
	{
		addRispondiEvent(controller);
		addAnnullaEvent(controller);
	}

	private void addAnnullaEvent(DettagliIssueAssegnataController controller) 
	{
		annullaButton.addActionListener(e->{
			if(rispostaPanel.isVisible())
			{
				rispostaField.setText("");
				rispostaPanel.setVisible(false);
				annullaButton.setVisible(false);
			}		
		});
	}

	private void addRispondiEvent(DettagliIssueAssegnataController controller) 
	{
		rispondiButton.addActionListener(e->{
			if(rispostaPanel.isVisible()) {
				salvaRisposta(controller);
			}
			else 
			{
				rispostaPanel.setVisible(true);
				annullaButton.setVisible(true);
				mainPanel.revalidate();  // ricalcola il layout
				mainPanel.repaint();     // ridisegna la finestra
			}		
		});
	}
}
