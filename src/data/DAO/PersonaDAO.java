package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Conexion;
import data.DTO.PersonaDTO;

public class PersonaDAO implements DAO<PersonaDTO,Integer> {
	static final Conexion con= new Conexion();
	static final Connection c=con.conectar();
	
	public boolean insertar(PersonaDTO p) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Persona values (null,?,?);");
		statement.setString(1, p.getNombre());
		statement.setString(2, p.getRol());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean modificar(PersonaDTO p) throws SQLException {
		PreparedStatement statement = c.prepareStatement("UPDATE Persona SET nombre=? , rol=? where id=?;");
		statement.setString(1, p.getNombre());
		statement.setString(2, p.getRol());
		statement.setInt(3, p.getID());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean eliminar(PersonaDTO p) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Persona WHERE id=?;");
		statement.setInt(1, p.getID());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public ArrayList<PersonaDTO> obtenerTodos() throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona;");
		ArrayList<PersonaDTO> lista = new ArrayList<PersonaDTO>();
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			lista.add(new PersonaDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("rol")));
		}
		return lista;
	}

	public PersonaDTO obtenerUno(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona where id=?;");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		if(rs.next()) {
			return new PersonaDTO(id,rs.getString("nombre"),rs.getString("rol"));
		}
		return null;
	}

}
