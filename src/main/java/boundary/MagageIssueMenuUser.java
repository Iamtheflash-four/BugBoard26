package boundary;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.AreaUtenteController;

public class MagageIssueMenuUser extends JPopupMenu
{
	private AreaUtenteController controller;
	private JMenuItem segnalaIssueOption;
	private JMenuItem visualizzaIssueSegnalateOption;

	public MagageIssueMenuUser(AreaUtenteController controller)
	{
		this.controller = controller;
		
		this.setSize(100, 80);
		this.setMinimumSize(getSize());
		this.setPreferredSize(getSize());
		this.setMaximumSize(getSize());
		
		addMenuItems();
	}

	private void addMenuItems() {
		segnalaIssueOption = new JMenuItem("Segnala issue");
		visualizzaIssueSegnalateOption = new JMenuItem("Visualizza issue segnalate");
		segnalaIssueOption = new JMenuItem("Segnala issue");
	}
}
