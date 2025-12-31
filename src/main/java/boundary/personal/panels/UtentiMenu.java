package boundary.personal.panels;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import boundary.personal.UserItem;
import dto.UserInfoDTO;

public class UtentiMenu extends JPopupMenu
{
	public UtentiMenu()
	{
		super();
		
		this.setSize(100, 80);
		this.setMinimumSize(getSize());
		this.setPreferredSize(getSize());
		this.setMaximumSize(getSize());
	}

	public void loadItems(ArrayList<UserInfoDTO> elencoUtenti) {
		for(UserInfoDTO user : elencoUtenti)
		{
			JPanel userItem = new UserItem(user);
			this.add(userItem);
		}
	}
	
}
