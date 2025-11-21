package controller;

import javax.swing.JDialog;

import entity.Utente;

public class ProfiloController extends Controller  
{
	public ProfiloController(Controller controller, Utente utente)
	{
		super(controller);
		JDialog dialog = new boundary.dialog.ProfiloDialog(frame, this, utente);
	}
}
