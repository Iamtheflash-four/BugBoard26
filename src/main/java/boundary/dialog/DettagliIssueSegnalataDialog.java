package boundary.dialog;

import javax.swing.JButton;
import javax.swing.JPanel;

import boundary.theme.ModernButton;
import boundary.theme.ModernPanel;
import controller.DettagliIssueSegnalataController;
import dto.IssueDTO;

public class DettagliIssueSegnalataDialog extends DettagliIssueAssegnataDialog
{
	private JButton assegnaButton;
	private DettagliIssueSegnalataController controller;
	private JPanel buttonPanel;
	private JPanel assegnaIssuePanel;

	public DettagliIssueSegnalataDialog(DettagliIssueSegnalataController controller, IssueDTO issue) {
		super(controller, issue);
		createButtons();
		mainPanel.add(buttonPanel);
		setVisible(true);
	}

	@Override
	protected void componiGUI(IssueDTO issue)
	{
		super.componiGUI(issue);
		assegnaIssuePanel = creaFlowLayoutPanel(200, 40);
	}
	
	@Override
	protected void createButtons() {
		super.createButtons();
		assegnaButton = ModernButton.createNewButtonPainted("Assegna", 100, 40);
		assegnaButton.addActionListener(e->{
			controller.assegnaIssue();
		});
		assegnaButton.setVisible(true);
		buttonPanel = new JPanel();
		buttonPanel.add(assegnaButton);
	}

}
