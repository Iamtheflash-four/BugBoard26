package boundary.personal;

import java.awt.event.MouseAdapter;

import boundary.theme.DettagliButton;
import boundary.theme.IssueTable;
import controller.AreaAmministratoreController;
import dto.IssueDTO;

public class IssueTableAdmin extends IssueTable
{
	public IssueTableAdmin(AreaAmministratoreController controller)
	{
		super();
		setDettagliButton(new DettagliButton());
		addListener(controller);
	}
	
	public void addListener(AreaAmministratoreController controller) 
	{
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		    	
		        int row = rowAtPoint(e.getPoint());
		        int column = columnAtPoint(e.getPoint());
		        System.out.println("rowVisivo = " + row + 
		                   ", rowModello = " + convertRowIndexToModel(row));
		        System.out.println("colVisivo = " + column + ", colModello = " + convertColumnIndexToModel(column));
		        for (int i = 0; i < getRowCount(); i++) {
				    System.out.println("RIGA " + i + " → col6 = " + getValueAt(i, 6));
				}
		        int rowModel = convertRowIndexToModel(row);
		        int colModel = convertColumnIndexToModel(column);

		        if (colModel == 5 && rowModel >= 0) {
		            IssueDTO issue = (IssueDTO) getValueAt(rowModel, 6);
		            controller.showDettagliIssue(issue);
		        }
		    }
		});
	}
}
