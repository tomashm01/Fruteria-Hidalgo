package data.DTO;

import java.sql.Date;
import data.Entities.Fruta;
import data.Entities.Persona;

public class TicketDTO {
	private int id;
	private float PrecioTotal;
	private Date fecha;
	private Persona tipoPersona;
	private Fruta tipoFruta;
	
	public TicketDTO(int id, float precioTotal, Date fecha, Persona tipoPersona, Fruta tipoFruta) {
		setId(id);
		setPrecioTotal(precioTotal);
		setTipoFruta(tipoFruta);
		setTipoPersona(tipoPersona);
		setFecha(fecha);
	}
	
	public Fruta getTipoFruta() {
		return this.tipoFruta;
	}

	public Persona getTipoPersona() {
		return this.tipoPersona;
	}

	private void setTipoPersona(Persona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	private void setTipoFruta(Fruta tipoFruta) {
		this.tipoFruta = tipoFruta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrecioTotal() {
		return PrecioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		PrecioTotal = precioTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
