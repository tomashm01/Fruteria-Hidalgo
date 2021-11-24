package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.Conexion;
import data.Entities.Persona;

public class PersonaDAO implements DAO<Persona,String>{

	static final Conexion con= new Conexion();
	static final Connection c=con.conectar();	
	
	public boolean insertar(Persona persona) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Persona values (?,?,?,?)");
		statement.setString(1, persona.getDni());
		statement.setString(2, persona.getNombre());
		statement.setString(3, persona.getEmail());
		statement.setString(4, persona.getRol());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean modificar(Persona persona) throws SQLException {
		PreparedStatement statement = c.prepareStatement("UPDATE Persona SET nombreCompleto=? , email=? , rol=? where dni=?");
		statement.setString(1, persona.getNombre());
		statement.setString(2, persona.getEmail());
		statement.setString(3, persona.getRol());
		statement.setString(4, persona.getDni());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	@Override
	public boolean eliminar(Persona persona) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Persona WHERE dni=?");
		statement.setString(1, persona.getDni());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	@Override
	public List<Persona> getTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona obtener(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
