package boundary.personal;

import java.awt.event.MouseAdapter;

import boundary.theme.DettagliButton;
import boundary.theme.IssueTable;
import controller.AreaUtenteController;
import dto.IssueDTO;

public class IssueTableUtente extends IssueTable
{
	public IssueTableUtente(AreaUtenteController controller)
	{
		super();
		
		setDettagliButton(new DettagliButton());
		addListener(controller);
	}

	
	public void addListener(AreaUtenteController controller) 
	{
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        int row = rowAtPoint(e.getPoint());
		        int column = columnAtPoint(e.getPoint());
		        if (column == 5 && row >= 0) {
		            IssueDTO issue = (IssueDTO) tableModel.getValueAt(row, 5);
		            controller.showDettagliIssue(issue);
		        }
		    }
		});
	}
}
