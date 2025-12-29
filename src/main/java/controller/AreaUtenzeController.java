package controller;
import javax.swing.*;
import entity.Utente;
public class AreaUtenzeController extends Controller
{
	private JFrame frame;
	private Utente ute;
	public AreaUtenzeController(Controller controller, Utente ute)
	{
		super(controller);
		this.ute = ute;
		 frame = new boundary.personal.AreaUtenze_AdminOptions(this, ute);
	}
}
