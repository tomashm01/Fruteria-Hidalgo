package data.DTO;

import java.util.List;
import data.Entities.FrutasTicket;

public class FrutasTicketDTO {
	private int id;
	private int idTicket;
	private int idFruta;
	private static List<FrutasTicketDTO> listaFrutasTicket;
	
	public FrutasTicketDTO(FrutasTicket ft) {
		setId(ft.getId());
		setIdFruta(ft.getIdFruta());
		setIdTicket(ft.getIdTicket());
	}
	
	public FrutasTicketDTO(int id,int idTicket,int idFruta) {
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
