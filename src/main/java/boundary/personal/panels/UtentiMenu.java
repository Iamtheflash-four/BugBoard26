package boundary.personal.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import boundary.personal.UserItem;
import controller.DettagliIssueSegnalataController;
import dto.UserInfoDTO;

public class UtentiMenu extends JPopupMenu
{
	public UtentiMenu()
	{
		super();
		this.setSize(200, 100);
		this.setMinimumSize(getSize());
		this.setPreferredSize(getSize());
		this.setMaximumSize(getSize());
	}

	public void loadItems(ArrayList<UserInfoDTO> elencoUtenti, DettagliIssueSegnalataController controller) {
		for(UserInfoDTO user : elencoUtenti)
		{
			JPanel userItem = new UserItem(user);
			this.add(userItem);
			userItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					assegnaIssue(controller, user.getIdUtente());
				}
			});
		}
	}

	protected void assegnaIssue(DettagliIssueSegnalataController controller, long idUtente) {
		try {
			controller.assegnaIssue(idUtente);
			JOptionPane.showMessageDialog(this, "Issue assegnata con successo!");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
		}
	}
	
}
