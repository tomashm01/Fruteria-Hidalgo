package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Conexion;
import data.DTO.PersonaDTO;
import data.Entities.Persona;

public class PersonaDAO implements DAO<PersonaDTO,Integer> {
	
	static final Conexion con= Conexion.getInstance();
	static final Connection c=con.conectar();
	
	/**
	 * Constructor vacío
	 */
	
	public PersonaDAO() {
		
	}
	
	/**
	 * Inserta una nueva Persona en la BBDD
	 * 
	 *  @param PersonaDTO
	 *  
	 *  @return boolean
	 */
	
	public boolean insertar(PersonaDTO p) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("INSERT INTO Persona values (null,?,?);");
		
		statement.setString(1, p.getNombre());
		statement.setString(2, p.getRol());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	/**
	 * Modifica una Persona en la BBDD
	 * 
	 *  @param PersonaDTO
	 *  
	 *  @return boolean
	 */
	
	public boolean modificar(PersonaDTO p) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("UPDATE Persona SET nombre=? , rol=? where id=?;");
		
		statement.setString(1, p.getNombre());
		statement.setString(2, p.getRol());
		statement.setInt(3, p.getID());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}
	
	/**
	 * Elimina una Persona en la BBDD
	 * 
	 *  @param Integer
	 *  
	 *  @return boolean
	 */

	public boolean eliminar(Integer id) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("DELETE FROM Persona WHERE id=?;");
		statement.setInt(1, id);

		if(statement.executeUpdate()!=1) return false;
		return true;
	}
	
	/**
	 * Devuelve una lista con todos las personas de la BBDD
	 * 
	 *  @param void
	 *  
	 *  @return ArrayList<PersonaDTO>
	 */

	public ArrayList<PersonaDTO> obtenerTodos() throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona;");
		ArrayList<PersonaDTO> lista = new ArrayList<PersonaDTO>();
		ResultSet rs=statement.executeQuery();
		
		while(rs.next()) {
			lista.add(new PersonaDTO(new Persona(rs.getInt("id"),rs.getString("nombre"),rs.getString("rol"))));
		}
		
		return lista;
	}

	/**
	 * Busca una persona en la BBDD y la devuelve
	 * 
	 *  @param Integer
	 *  
	 *  @return FrutaDTO
	 */
	
	public PersonaDTO obtenerUno(Integer id) throws SQLException {
		
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona where id=?;");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		
		if(rs.next()) {
			return new PersonaDTO(new Persona(id,rs.getString("nombre"),rs.getString("rol")));
		}
		
		return null;
	}

}
