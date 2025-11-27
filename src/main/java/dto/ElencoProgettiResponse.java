package dto;

import java.util.ArrayList;

public class ElencoProgettiResponse 
{
	private int status;
	private String message;
	private ArrayList<String> elencoProgetti;
	
	public ElencoProgettiResponse() {}

	public ElencoProgettiResponse(int status, String message, ArrayList<String> elencoProgetti) {
		super();
		this.status = status;
		this.message = message;
		this.elencoProgetti = elencoProgetti;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public ArrayList<String> getElencoProgetti() {
		return elencoProgetti;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setElencoProgetti(ArrayList<String> elencoProgetti) {
		this.elencoProgetti = elencoProgetti;
	}
}
