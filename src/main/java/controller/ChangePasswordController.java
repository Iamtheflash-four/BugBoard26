package controller;

import entity.Utente;
import javax.swing.JDialog;

public class ChangePasswordController extends Controller
{
	public ChangePasswordController(Controller controller, Utente utente)
	{
		super(controller);
		JDialog dialog = new boundary.dialog.ChangePasswordDialog(frame, this, utente);
	}
}
