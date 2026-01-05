package boundary.personal.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import boundary.theme.IssueTable;
import boundary.theme.ModernButton;
import boundary.theme.ModernPanel;
import controller.AreaUtenteController;
import controller.FiltroPrioritaController;
import dto.IssueDTO;

public class AreaIssueHeader extends JPanel 
{
	private JButton buttonSwitch;
	private JButton updateButton;
	protected FiltroPrioritaPanel filtroPrioritaPanel;
	protected FiltroPrioritaController filtroPrioritaController;
	
	public AreaIssueHeader(AreaIssue areaIssue) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		ModernPanel.stylePanel(600, 50, this);
		createHeaderItem(areaIssue);
		addHeader();
		addListeners(areaIssue);
	}
	
	protected void createHeaderItem(AreaIssue areaIssue) {
	   buttonSwitch = ModernButton.createNewButtonPainted("Issue Inviate", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
	   updateButton = ModernButton.createNewButtonPainted("Aggiorna", new Color(0, 120, 215), new Color(255, 255, 255), new Color(0, 120, 215));
	   filtroPrioritaController = new FiltroPrioritaController(areaIssue);
	   filtroPrioritaPanel = new FiltroPrioritaPanel(filtroPrioritaController);
	}
	   
   protected void addHeader()
   {
	   this.add(filtroPrioritaPanel);
	   this.add(buttonSwitch);
	   this.add(updateButton);
   }

	private void addListeners(AreaIssue areaIssue) {
		buttonSwitch.addActionListener(e -> { 
			areaIssue.switchView();
		});
		updateButton.addActionListener(e -> { 
			areaIssue.updateTable();
		});
	}

	public JButton getButtonSwitch() {
		return buttonSwitch;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setButtonSwitch(JButton buttonSwitch) {
		this.buttonSwitch = buttonSwitch;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}  
}
