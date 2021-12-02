package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Conexion;
import data.DTO.TicketDTO;
import data.Entities.Ticket;

public class TicketDAO implements DAO<TicketDTO,Integer>{
	
	private static Connection c = Conexion.getInstance().conectar();
	
	/**
	 * Constructor vacío
	 */
	
	public TicketDAO() {
		
	}
	
	/**
	 * Creo una transaccion
	 * @throws SQLException 
	 */
	
	public void transaccion(Boolean rollback) throws SQLException {
		c.setAutoCommit(true);
		if(rollback) {
			c.rollback();
		}
	}
	
	/**
	 * Inserta un nuevo Ticket en la BBDD
	 * 
	 *  @param TicketDTO
	 *  
	 *  @return boolean
	 */
	
	public boolean insertar(TicketDTO t) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("INSERT INTO Ticket values (null,?,?,?,?);");
		
		statement.setInt(1, t.getIdPersona());
		statement.setInt(2, t.getIdFrutas());
		statement.setDate(3, t.getFecha());
		statement.setFloat(4, t.getPrecioTotal());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}
	
	/**
	 * Modifica un ticket en la BBDD
	 * 
	 *  @param TicketDTO
	 *  
	 *  @return boolean
	 */

	public boolean modificar(TicketDTO t) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("UPDATE Ticket SET idPersona=? , idFrutas=?, fecha=?, precioTotal=? where id=?;");
		
		statement.setInt(1, t.getIdPersona());
		statement.setInt(2, t.getIdFrutas());
		statement.setDate(3, t.getFecha());
		statement.setFloat(4, t.getPrecioTotal());
		statement.setInt(5, t.getId());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}
	
	/**
	 * Elimina un ticket en la BBDD
	 * 
	 *  @param Integer
	 *  
	 *  @return boolean
	 */

	public boolean eliminar(Integer id) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("DELETE FROM Ticket WHERE id=?;");
		statement.setInt(1, id);

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	/**
	 * Devuelve una lista con todos los tickets de la BBDD
	 * 
	 *  @param void
	 *  
	 *  @return ArrayList<TicketDTO>
	 */
	
	public ArrayList<TicketDTO> obtenerTodos() throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Ticket;");
		ArrayList<TicketDTO> lista=new ArrayList<TicketDTO>();
		ResultSet rs=statement.executeQuery();
		
		while(rs.next()) {
			lista.add(new TicketDTO(new Ticket(rs.getInt("id"),rs.getInt("idPersona"),rs.getInt("idFrutas"),rs.getDate("fecha"),rs.getFloat("precioTotal"))));
		}
		
		return lista;
	}

	/**
	 * Busca un ticket en la BBDD y lo devuelve
	 * 
	 *  @param Integer
	 *  
	 *  @return TicketDTO
	 */

	public TicketDTO obtenerUno(Integer id) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Ticket where id=?;");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		
		if(rs.next()) {
			return new TicketDTO(new Ticket(rs.getInt("id"),rs.getInt("idPersona"),rs.getInt("idFrutas"),rs.getDate("fecha"),rs.getFloat("precioTotal")));
		}
		
		return null;
		
	}

}
