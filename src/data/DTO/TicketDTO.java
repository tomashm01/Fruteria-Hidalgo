package data.DTO;

import java.sql.Date;

import data.Entities.Ticket;

public class TicketDTO {

	private Integer id;
	private float PrecioTotal;
	private Date fecha;
	private int idPersona;
	private int idFrutas;

	public TicketDTO(Ticket t) {
		setId(t.getId());
		this.idPersona=t.getIdPersona();
		this.idFrutas=t.getIdFrutas();
		setPrecioTotal(t.getPrecioTotal());
		setFecha(t.getFecha());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getIdFrutas() {
		return this.idFrutas;
	}
	
	public int getIdPersona() {
		return this.idPersona;
	}

	public float getPrecioTotal() {
		return PrecioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.PrecioTotal = precioTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String toString() {
		return this.id+". FECHA: "+this.fecha+". PRECIO TOTAL: "+this.PrecioTotal+". ID USUARIO: "+this.idPersona+"\n";
	}

}
