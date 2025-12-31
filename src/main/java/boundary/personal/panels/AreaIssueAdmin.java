package boundary.personal.panels;

import boundary.personal.IssueTableAdmin;
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
		issueTable = new IssueTableAdmin(getController());
	}
	
	public AreaAmministratoreController getController() {
	    return (AreaAmministratoreController) super.controller;
	}
}
