package boundary.theme;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.AreaAmministratoreController;
import controller.AreaUtenteController;

public class ManageUserMenu extends JPopupMenu
{
	private AreaAmministratoreController controller;
	private JMenuItem addUtenteOption;
	private JMenuItem addTeamOption;

	public ManageUserMenu(AreaAmministratoreController controller)
	{
		this.controller = controller;
		
		this.setSize(100, 80);
		this.setMinimumSize(getSize());
		this.setPreferredSize(getSize());
		this.setMaximumSize(getSize());
		
		createMenuItems();
		addMenuItems();
		addMenuEvents();
		this.setVisible(true);
	}

	protected void createMenuItems() {
		addUtenteOption = new JMenuItem("Aggiungi utente");
		addTeamOption = new JMenuItem("Aggiungi team");
	}
	
	protected void addMenuItems() 
	{
		this.add(addUtenteOption);
		this.add(addTeamOption);
	}
	
	protected void addMenuEvents()
	{
		addUtenteOption.addActionListener(e->{
			controller.showCreazioneUtente(controller.getUtente());
		});
//		visualizzaIssueSegnalateOption.addActionListener(e->{
//			controller.
//		});;
	}
}
