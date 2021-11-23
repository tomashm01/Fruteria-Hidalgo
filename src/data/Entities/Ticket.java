package data.Entities;

import java.sql.Date;
import java.util.List;

public class Ticket {

	private int id;
	private float PrecioTotal;
	private Date fecha;
	private Persona tipoPersona;
	private Fruta tipoFruta;
	private static List<Ticket> listaTickets;

	public Ticket(int id, float precioTotal, Date fecha, Persona tipoPersona, Fruta tipoFruta) {
		setId(id);
		setPrecioTotal(precioTotal);
		setTipoFruta(tipoFruta);
		setTipoPersona(tipoPersona);
		setFecha(fecha);
	}

	public boolean addTicketToList(Ticket ticketNuevo) {
		if (ticketNuevo.getFecha() != null && ticketNuevo.getPrecioTotal() > 0 && ticketNuevo.getTipoFruta() != null
				&& ticketNuevo.getTipoPersona() != null) {
			listaTickets.add(ticketNuevo);
			return true;
		}
		return false;
	}

	public boolean removePersonaByID(int id) {
		for (int i = 0; i < listaTickets.size(); i++) {
			if (listaTickets.get(i).getId() == id) {
				listaTickets.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean changeTicket(Ticket ticketCambiado) {
		for (int posicion = 0; posicion < listaTickets.size(); posicion++) {
			if (listaTickets.get(posicion).getId() == ticketCambiado.getId()) {
				listaTickets.add(posicion, ticketCambiado);
				return true;
			}
		}
		return false;
	}

	public List<Ticket> showTickets() {
		return listaTickets;
	}

	public Ticket showTicketByID(int id) {
		for (int i = 0; i < listaTickets.size(); i++) {
			if (listaTickets.get(i).getId() == id) {
				return listaTickets.get(i);
			}
		}
		return null;
	}

	public Fruta getTipoFruta() {
		return this.tipoFruta;
	}

	public Persona getTipoPersona() {
		return this.tipoPersona;
	}

	private boolean setTipoPersona(Persona tipoPersona) {
		List<Persona> lista = tipoPersona.showPersonas();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equals(tipoPersona)) {
				this.tipoPersona = tipoPersona;
				return true;
			}
		}
		this.tipoPersona = null;
		return false;
	}

	private boolean setTipoFruta(Fruta tipoFruta) {
		List<Fruta> lista = tipoFruta.showFrutas();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equals(tipoFruta)) {
				this.tipoFruta = tipoFruta;
				return true;
			}
		}
		this.tipoFruta = null;
		return false;
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
