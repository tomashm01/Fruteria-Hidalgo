package data.DTO;

import java.sql.Date;
import java.util.List;
import data.Entities.Ticket;

public class TicketDTO {

	private int id;
	private float PrecioTotal;
	private Date fecha;
	private int idPersona;
	private int idFrutas;
	private static List<TicketDTO> listaTickets;

	public TicketDTO(Ticket t) {
		setId(t.getId());
		this.idPersona=t.getIdPersona();
		this.idFrutas=t.getIdFrutas();
		setPrecioTotal(t.getPrecioTotal());
		setFecha(t.getFecha());
	}
	
	public TicketDTO(int id,int idPersona,int idFrutas, Date fecha,float precioTotal) {
		setId(id);
		this.idPersona=idPersona;
		this.idFrutas=idFrutas;
		setPrecioTotal(precioTotal);
		setFecha(fecha);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
