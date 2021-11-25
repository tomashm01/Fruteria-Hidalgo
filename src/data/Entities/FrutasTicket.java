package data.Entities;

public class FrutasTicket {
	private Integer id;
	private Integer idTicket;
	private Integer idFruta;
	
	public FrutasTicket(Integer id,Integer idTicket,Integer idFruta) {
		setId(id);
		setIdFruta(idFruta);
		setIdTicket(idTicket);
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
