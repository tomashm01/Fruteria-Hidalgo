package data.Entities;

import java.sql.Date;
import java.util.List;

public class Ticket {

	private int id;
	private float PrecioTotal;
	private Date fecha;
	private int idPersona;
	private int idFrutas;
	private static List<Ticket> listaTickets;

	public Ticket(int id,int idPersona,int idFrutas, Date fecha,float precioTotal) {
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
