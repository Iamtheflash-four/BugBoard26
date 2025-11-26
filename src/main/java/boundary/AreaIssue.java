package boundary;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class AreaIssue extends JPanel
{
    private JTable issueTable;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private boolean showingRicevute = true;

    public AreaIssue() {
        setSize(700, 400);
        componiGUI();
    }

    public void componiGUI() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        String[] colonne = { "Nome Progetto", "Priorità", "Tipo" };
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        createTable();

        JScrollPane scrollPane = new JScrollPane(issueTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 220), 2, true));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        caricaIssueRicevute();
    }

    private void createTable() {
        issueTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(235, 245, 255) : Color.WHITE);
                } else {
                    c.setBackground(new Color(0, 192, 255, 160)); // selezione azzurra soft
                }
                c.setForeground(new Color(30, 30, 30));
                return c;
            }
        };
        issueTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        issueTable.setRowHeight(32);

        JTableHeader header = issueTable.getTableHeader();
        header.setBackground(new Color(30, 140, 210));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(120, 170, 220)));

        issueTable.setShowGrid(true);
        issueTable.setGridColor(new Color(220, 230, 250));
    }

    // Dati di esempio
    private void caricaIssueRicevute() {
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[] { "Progetto Alpha", "Alta", "Bug" });
        tableModel.addRow(new Object[] { "Progetto Beta", "Media", "Richiesta" });
    }

    private void caricaIssueInviate() {
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[] { "Progetto Gamma", "Bassa", "Suggerimento" });
        tableModel.addRow(new Object[] { "Progetto Delta", "Alta", "Bug" });
    }
}
