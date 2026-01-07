package boundary.personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import dto.UserInfoDTO;

public class UserItem extends JPanel 
{
	private JLabel emailLabel;
	private JPanel emailPanel;
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	
	public UserItem(UserInfoDTO user)
	{
		super();
		ModernPanel.stylePanel(200, 50, this);
		setBorder(new MatteBorder(2, 0, 2, 0, Color.GRAY));
		creaLabels(user);
//		emailPanel = ModernPanel.createWhitePanel(200, 30);
		this.add(emailLabel, BorderLayout.NORTH);
		this.add(nomeLabel, BorderLayout.WEST);
		this.add(cognomeLabel, BorderLayout.EAST);
	}

	protected void creaLabels(UserInfoDTO user) {
		emailLabel = ModernLabel.createLabel(user.getEmail(), new Font("Arial", Font.BOLD, 15), Color.BLACK);
		nomeLabel = ModernLabel.createLabel(user.getNome(), new Font("Arial", Font.BOLD, 11), Color.BLACK);
		cognomeLabel = ModernLabel.createLabel(user.getNome(), new Font("Arial", Font.BOLD, 11), Color.BLACK);
	}
}
