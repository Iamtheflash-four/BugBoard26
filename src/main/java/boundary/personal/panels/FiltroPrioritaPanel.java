package boundary.personal.panels;

import javax.swing.*;
import java.awt.*;
import controller.FiltroPrioritaController;
import boundary.theme.ModernComboBox;


public class FiltroPrioritaPanel extends JPanel {
    
    private JComboBox<String> comboPriorita;
    private JLabel labelFiltro;
    private FiltroPrioritaController controller;
    
    /**
     * Costruttore del panel filtro priorità
     * @param controller Il controller per gestire la logica del filtro
     */
    public FiltroPrioritaPanel(FiltroPrioritaController controller) {
        this.controller = controller;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(Color.WHITE);
        componiGUI();
    }
    
   
    private void componiGUI() {
        // Label per il filtro
        labelFiltro = new JLabel("Filtra per Priorità:");
        labelFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // ComboBox con i livelli di priorità
        String[] gradi = {"Tutte", "Alta", "Media", "Bassa"};
        comboPriorita = ModernComboBox.createCombobox(gradi);
        comboPriorita.setPreferredSize(new Dimension(120, 30));
        comboPriorita.setFont(new Font("Arial", Font.PLAIN, 13));
        
        comboPriorita.addActionListener(e -> filtraPerPriorita());

        add(labelFiltro);
        add(comboPriorita);
    }

    private void filtraPerPriorita() {
        String prioritaSelezionata = (String) comboPriorita.getSelectedItem();
        try {
			controller.applicaFiltroPriorita(prioritaSelezionata);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erroe", JOptionPane.ERROR_MESSAGE);
		}
    }   

    public String getPrioritaSelezionata() {
        return (String) comboPriorita.getSelectedItem();
    }
    
 
    public void setPrioritaSelezionata(String priorita) {
        comboPriorita.setSelectedItem(priorita);
    } 

    public void resetFiltro() {
        comboPriorita.setSelectedIndex(0); // Seleziona "Tutte"
    }
}
