/*    file inutilizzato */ 


/*ackage Boundary;
import Controller.PersonalAreaController;
import javax.swing.*;
import java.awt.*;

public class PersonalArea extends JFrame
{
	private PersonalAreaController controller; 
	private JPanel mainPanel;
	private JPanel buttonPanel; 
	private JButton btnIssue, btnChangePassword, btnLogout; 
	private JLabel titolo; 
	
	public PersonalArea(PersonalAreaController controller)
	{
		this.controller = controller; 
		
		this.setTitle("Area Personale - Unina BugBoard26");
        this.setSize(400, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la finestra
        this.setResizable(false);
        
        componiGUI(); 
        
        titolo = new JLabel("Benvenuto nella tua area personale");
        titolo.setFont(new Font("Arial", Font.BOLD, 16));
        titolo.setForeground(new Color(30, 30, 30));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titolo, BorderLayout.NORTH);

        this.setVisible(true);
	}
	
	private void componiGUI() {
	    mainPanel = new JPanel(new BorderLayout());
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    mainPanel.setBackground(Color.WHITE);

	    btnIssue = ModernButton.createNewButtonPainted("Area Issue", new Color(138, 43, 226), new Color(30, 30, 30), new Color(138, 43, 226));
	    btnChangePassword = ModernButton.createNewButtonPainted("Cambia Password", new Color(138, 43, 226), new Color(30, 30, 30), new Color(138, 43, 226));
	    btnLogout = ModernButton.createNewButtonPainted("Logout", new Color(255, 0, 0), new Color(30,30,30), new Color(255, 0, 0));

	    btnIssue.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            
            
	    btnChangePassword.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                    "Funzione in sviluppo", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            
	    btnLogout.addActionListener(e -> dispose());

	    JButton btnUtenze= AdminOptions(); 

	    if(btnUtenze == null)
	    {
		   buttonPanel = createPanel(btnIssue, btnChangePassword, btnLogout, null);
	    } else 	    buttonPanel = createPanel(btnIssue, btnChangePassword, btnUtenze, btnLogout);

	    mainPanel.add(buttonPanel, BorderLayout.CENTER);
	    
	    this.setContentPane(mainPanel);
	}

	
	private JPanel createPanel(JButton b1, JButton b2, JButton b3, JButton b4) 
	{
	    JPanel panel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(10, 0, 10, 0); // spazio tra bottoni

	    gbc.gridy = 0;
	    panel.add(b1, gbc);

	    gbc.gridy = 1;
	    panel.add(b2, gbc);

	    gbc.gridy = 2;
	    panel.add(b3, gbc);
	    
	    if(b4 != null)
	    {
	    	gbc.gridy = 3; 
	    	panel.add(b4, gbc);
	    }
	    
	    panel.setBackground(Color.WHITE);
	    return panel;
	}
	
	private JButton AdminOptions()
	{
		if(true) //ho messo true/false per testarlo. Da sostituire con il metodo del controller
		{
			JButton btnUtenze; 
			btnUtenze = ModernButton.createNewButtonPainted("Area Utenze", new Color(138, 43, 226), new Color(30, 30, 30), new Color(138, 43, 226));
			btnUtenze.addActionListener(e -> {
	            JOptionPane.showMessageDialog(this, 
	                    "Funzione in sviluppo", 
	                    "Info", 
	                    JOptionPane.INFORMATION_MESSAGE);
	            });
			return btnUtenze; 
		}
		else return null;
	}
}
*/
