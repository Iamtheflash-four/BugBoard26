package boundary.theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import controller.AreaUtenteController;
import dto.IssueDTO;

public class IssueTable extends JTable 
{
	protected DefaultTableModel tableModel;
	
	public IssueTable()
	{
		createModel();
		this.setModel(tableModel);
		//Nascondere issue
		getColumnModel().getColumn(6).setMinWidth(0);
		getColumnModel().getColumn(6).setMaxWidth(0);
		getColumnModel().getColumn(6).setPreferredWidth(0);
		//Personalizza tabella
		styleTable();
        // Header personalizzato
        createTableHeader();
	}
	
	public void createModel() 
	{
		String[] colonne = { "Progetto", "Priorità", "Tipo", "Titolo", "Data", "Dettagli", "Issue" };
		tableModel = new DefaultTableModel(colonne, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		};
	}
	
	public void styleTable() {
		// Font e altezza righe
        this.setFont(new Font("Arial", Font.PLAIN, 14));
        this.setRowHeight(30);

        // Colori di sfondo e testo
        this.setBackground(new Color(245, 250, 255)); // azzurro chiarissimo
        this.setForeground(new Color(30, 30, 30));    // testo scuro

        // Colore selezione
        this.setSelectionBackground(new Color(0, 120, 215)); // blu Unina
        this.setSelectionForeground(Color.WHITE);
        
        // Bordo tabella
        this.setShowGrid(true);
        this.setGridColor(new Color(200, 220, 240));
	}

	public void createTableHeader() {
		JTableHeader header = this.getTableHeader();
        header.setBackground(new Color(0, 120, 215));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setOpaque(true);
	}

	public String getNomeProgetto(int selectedRow) {
		return (String) tableModel.getValueAt(selectedRow, 0);
	}

	public void setRowCount(int i) {
		tableModel.setRowCount(i);
	}
	
	public void setDettagliButton(DettagliButton dettagliButton) {
        getColumnModel().getColumn(5).setCellRenderer(dettagliButton);
	}

	public void addRow(IssueDTO issue) 
	{
		Object[] row = new Object[7];
		row[0] = issue.getProgetto();
		row[1] = issue.getTipo();
		row[2] = issue.getPriorita();
		row[3] = issue.getTipo();
		row[4] = issue.getData();
		row[5] = "Dettagli";
		row[6] = issue;
		this.tableModel.addRow(row);
	}
	
	public TableModel getTableModel()
	{
		return tableModel;
	}
}
