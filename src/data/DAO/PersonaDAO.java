package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.Conexion;
import data.Entities.Persona;

public class PersonaDAO implements DAO<Persona,String>{

	final Conexion c=new Conexion();
	final Connection myConexion=c.conectar();
	
	
	
	@Override
	public boolean insertar(Persona persona) throws SQLException {
		PreparedStatement statement = myConexion.prepareStatement("INSERT INTO Persona values (?,?,?,?)");
		statement.setString(1, persona.getDni());
		
		return false;
	}

	@Override
	public boolean modificar(Persona a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Persona a) {
		// TODO Auto-generated method stub
		return false;
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
