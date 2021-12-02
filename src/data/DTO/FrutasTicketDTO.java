package data.DTO;

import data.Entities.FrutasTicket;

public class FrutasTicketDTO {
	private Integer id;
	private Integer idTicket;
	private Integer idFruta;
	
	public FrutasTicketDTO(FrutasTicket ft) {
		setId(ft.getId());
		setIdFruta(ft.getIdFruta());
		setIdTicket(ft.getIdTicket());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}
	public Integer getIdFruta() {
		return idFruta;
	}
	public void setIdFruta(Integer idFruta) {
		this.idFruta = idFruta;
	}
	
}
