package boundary.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;
import boundary.theme.ModernPanel;
import boundary.theme.ModernTextField;
import controller.AreaTeamWorkController;

public class AddProgettoDialog extends JDialog 
{
	private long idTeam;
	private JLabel progettoLabel;
	private JTextField progettoField;
	private JButton closeButton;
	private JPanel mainPanel;
	private JPanel formPanel;
	private JButton confermaButton;
	private JPanel buttonPanel;

	public AddProgettoDialog(JDialog dialog, AreaTeamWorkController controller, long idTeam) {
		super(dialog, "Crea progetto", true);
		this.idTeam = idTeam;
		setMinimumSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		componiGUI();
		addListeners(controller);
	}

	protected void componiGUI() 
	{
		createPanels();
		createComponents();	
		addComponents();
	}

	protected void createPanels() {
		mainPanel = ModernPanel.createWhitePanel(300, 200);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		formPanel = ModernPanel.createWhitePanel(300, 100);
		formPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel = ModernPanel.createWhitePanel(300, 100);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	protected void createComponents() {
		progettoLabel = ModernLabel.createLabel("Nome progetto", new Font("Arial", Font.PLAIN, 12 ), Color.BLACK);
		progettoField = ModernTextField.createField("nuovo progetto");
		confermaButton = ModernButton.createNewButtonPainted("Conferma", 100, 30);
		closeButton = ModernButton.createNewButtonPainted("Chiudi", Color.BLUE, Color.WHITE, Color.BLUE, 100, 30);
	}

	protected void addComponents() {
		formPanel.add(progettoLabel);
		formPanel.add(progettoField);
		buttonPanel.add(confermaButton);
		buttonPanel.add(closeButton);
		mainPanel.add(formPanel);
		mainPanel.add(closeButton);
	}
	
	protected void addListeners(AreaTeamWorkController controller)
	{
		confermaButton.addActionListener(e -> {
            addProject(controller);
        }); 
		
		closeButton.addActionListener(e -> {
            this.dispose();
        }); 
	}

	protected void addProject(AreaTeamWorkController controller) {
		String nomeProgetto = progettoField.getText();
		try {
			controller.addProject(nomeProgetto, idTeam);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
}
