package data.Entities;

import java.util.List;

public class FrutasTicket {
	private int id;
	private int idTicket;
	private int idFruta;
	private static List<FrutasTicket> listaFrutasTicket;
	
	public FrutasTicket(int id,int idTicket,int idFruta) {
		setId(id);
		setIdFruta(idFruta);
		setIdTicket(idTicket);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	public int getIdFruta() {
		return idFruta;
	}
	public void setIdFruta(int idFruta) {
		this.idFruta = idFruta;
	}
	
}
