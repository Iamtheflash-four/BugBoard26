package boundary.personal.panels;

import boundary.personal.IssueTableAdmin;
import boundary.personal.IssueTableUtente;
import controller.AreaAmministratoreController;

public class AreaIssueAdmin extends AreaIssue 
{
	public AreaIssueAdmin(AreaAmministratoreController controller)
	{
		super(controller);
	}
	
	@Override
	public void createTable()
	{
		issueTable = new IssueTableUtente(controller);
		this.showingRicevute = true;
		this.switchView();
	}
	
	public AreaAmministratoreController getController() {
	    return (AreaAmministratoreController) super.controller;
	}
}
