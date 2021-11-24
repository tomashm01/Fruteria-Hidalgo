package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.Conexion;
import data.DTO.FrutaDTO;
import data.DTO.PersonaDTO;
import data.DTO.TicketDTO;
import data.Entities.Fruta;
import data.Entities.Persona;
import data.Entities.Ticket;

public class TicketDAO implements DAO<TicketDTO,Integer>{

	static final Conexion con = new Conexion();
	static final Connection c = con.conectar();

	public boolean insertar(TicketDTO ticket) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Ticket values (null,?,?,?,?)");
		statement.setString(1, ticket.getTipoPersona().getDni());
		statement.setInt(2, ticket.getTipoFruta().getId());
		statement.setFloat(3, ticket.getPrecioTotal());
		statement.setDate(4, ticket.getFecha());
		
		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean modificar(TicketDTO ticket) throws SQLException {
		PreparedStatement statement = c
				.prepareStatement("UPDATE Ticket SET precioTotal=? , fecha=? where id=?");
		statement.setFloat(1, ticket.getPrecioTotal());
		statement.setDate(2, ticket.getFecha());
		statement.setInt(3, ticket.getId());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean eliminar(TicketDTO ticket) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Ticket WHERE id=?");
		statement.setInt(1, ticket.getId());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public List<TicketDTO> getTodos() throws SQLException {
		PreparedStatement stTicket = c.prepareStatement("SELECT * FROM Ticket ");
		ResultSet rsTicket = stTicket.executeQuery();
		
		List<TicketDTO> lista=null;
		PersonaDAO persona=null;
		FrutaDAO fruta=null;
		List<PersonaDTO>listaPersonas=persona.getTodos();
		List<FrutaDTO> listaFrutas = fruta.getTodos();
		
		while (rsTicket.next()) {
			String dniTicket=rsTicket.getString("dniPersona");
			int idFruta=rsTicket.getInt("idFruta");
			Persona newPersona=null;
			Fruta newFruta=null;
			
			for(int i=0;i<listaPersonas.size();i++) {
				if(listaPersonas.get(i).equals(dniTicket)) {
					newPersona=new Persona(listaPersonas.get(i).getDni(),listaPersonas.get(i).getNombre(),listaPersonas.get(i).getEmail(),listaPersonas.get(i).getRol());
				}
			}
			for(int i=0;i<listaFrutas.size();i++) {
				if(listaFrutas.get(i).equals(idFruta)) {
					newFruta=new Fruta(listaFrutas.get(i).getId(),listaFrutas.get(i).getNombre(),listaFrutas.get(i).getCantidad(),listaFrutas.get(i).getPrecioUnidad());
				}
			}
			lista.add(new TicketDTO(new Ticket(rsTicket.getInt("id"),newPersona,newFruta,rsTicket.getFloat("precioTotal"),rsTicket.getDate("fecha"))));
			
		}
		return lista;
	}

	@Override
	public TicketDTO obtener(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Ticket where id=?");
		statement.setInt(1, id);
		
		TicketDTO ticket = null;
		List<TicketDTO> lista=null;
		PersonaDAO persona=null;
		FrutaDAO fruta=null;
		
		List<PersonaDTO>listaPersonas=persona.getTodos();
		List<FrutaDTO> listaFrutas = fruta.getTodos();
		
		ResultSet rs = statement.executeQuery();
		boolean encontrado=false;
		while (rs.next() || !encontrado){			
			if(rs.getInt("id")==id){
				String dniTicket=rs.getString("dniPersona");
				int idFruta=rs.getInt("idFruta");
				Persona newPersona=null;
				Fruta newFruta=null;
				
				for(int i=0;i<listaPersonas.size();i++) {
					if(listaPersonas.get(i).equals(dniTicket)) {
						newPersona=new Persona(listaPersonas.get(i).getDni(),listaPersonas.get(i).getNombre(),listaPersonas.get(i).getEmail(),listaPersonas.get(i).getRol());
					}
				}
				for(int i=0;i<listaFrutas.size();i++) {
					if(listaFrutas.get(i).equals(idFruta)) {
						newFruta=new Fruta(listaFrutas.get(i).getId(),listaFrutas.get(i).getNombre(),listaFrutas.get(i).getCantidad(),listaFrutas.get(i).getPrecioUnidad());
					}
				}
				ticket=new TicketDTO(new Ticket(id,newPersona,newFruta,rs.getFloat("precioTotal"),rs.getDate("fecha")));
				encontrado=true;
			}
		}
		return ticket;
	}

}
