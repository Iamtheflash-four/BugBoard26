package boundary.dialog;

import javax.swing.*;
import boundary.theme.*;

import controller.SegnalaIssueController;

import java.awt.*;

public class SegnalaIssueDialog extends JDialog {
    private SegnalaIssueController controller;

    private JButton btnIndietro;
    private JButton btnSegnala;

    private JComboBox<String> comboProgetto;
    private JComboBox<String> comboPriorita;
    private JTextField txtSubject;
    private JTextArea txtMessaggio;
    private JPanel mainPanel;
    private JPanel formPanel;

    public SegnalaIssueDialog(JFrame frame, SegnalaIssueController controller)
    {
    	super(frame, "Segnala issue", true);
    	this.controller = controller;
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        componiGUI();
        setVisible(true);
    }

    private void componiGUI() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        creaForm();
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void creaForm() {
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Color labelColor = new Color(50, 50, 50);

        formPanel.add(creaLabel("Progetto:", labelFont, labelColor), gbc);
        gbc.gridx = 1;
        comboProgetto = new JComboBox<>(new String[] { "Progetto A", "Progetto B", "Progetto C" });
        comboProgetto.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboProgetto.setBackground(Color.WHITE);
        formPanel.add(comboProgetto, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(creaLabel("Priorità:", labelFont, labelColor), gbc);
        gbc.gridx = 1;
        comboPriorita = new JComboBox<>(new String[] { "Bassa", "Media", "Alta" });
        comboPriorita.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboPriorita.setBackground(Color.WHITE);
        formPanel.add(comboPriorita, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(creaLabel("Subject:", labelFont, labelColor), gbc);
        gbc.gridx = 1;
        txtSubject = new JTextField(20);
        txtSubject.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtSubject.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2)); // bordo blu
        formPanel.add(txtSubject, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(creaLabel("Messaggio:", labelFont, labelColor), gbc);
        gbc.gridx = 1;
        txtMessaggio = new JTextArea(5, 20);
        txtMessaggio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtMessaggio.setLineWrap(true);
        txtMessaggio.setWrapStyleWord(true);
        txtMessaggio.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2)); // bordo blu
        JScrollPane scroll = new JScrollPane(txtMessaggio);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // rimuove bordo scroll
        formPanel.add(scroll, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(creaLabel("Allega Immagini:", labelFont, labelColor), gbc);
        gbc.gridx = 1;
        JButton btnUpload = new JButton("Carica Immagini");
        btnUpload.setFont(new Font("SansSerif", Font.PLAIN, 13));
        formPanel.add(btnUpload, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        btnIndietro = ModernButton.createNewButtonPainted("Indietro", new Color(255, 255, 255), new Color(0, 0, 255), new Color(255, 255, 255));
        btnSegnala = ModernButton.createNewButtonPainted("Segnala", new Color(0, 0, 255), new Color(255, 255, 255), new Color(0, 0, 255));
        buttonPanel.add(btnIndietro);
        buttonPanel.add(btnSegnala);
        formPanel.add(buttonPanel, gbc);
        

    }

    private JLabel creaLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

//    public static void main(String[] args)
//    {
//    	new S();
//    }

}
