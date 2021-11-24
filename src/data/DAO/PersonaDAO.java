package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.Conexion;
import data.DTO.PersonaDTO;
import data.Entities.Persona;

public class PersonaDAO implements DAO<PersonaDTO, String> {

	static final Conexion con = new Conexion();
	static final Connection c = con.conectar();

	/**
	 * Funcion que inserta
	 */
	public boolean insertar(PersonaDTO persona) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Persona values (?,?,?,?)");
		statement.setString(1, persona.getDni());
		statement.setString(2, persona.getNombre());
		statement.setString(3, persona.getEmail());
		statement.setString(4, persona.getRol());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean modificar(PersonaDTO persona) throws SQLException {
		PreparedStatement statement = c
				.prepareStatement("UPDATE Persona SET nombreCompleto=? , email=? , rol=? where dni=?");
		statement.setString(1, persona.getNombre());
		statement.setString(2, persona.getEmail());
		statement.setString(3, persona.getRol());
		statement.setString(4, persona.getDni());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean eliminar(PersonaDTO persona) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Persona WHERE dni=?");
		statement.setString(1, persona.getDni());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public List<PersonaDTO> getTodos() throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona ");
		ResultSet rs = statement.executeQuery();
		List<PersonaDTO> lista = null;
		while (rs.next()) {
			lista.add(new PersonaDTO(new Persona(rs.getString("dni"), rs.getString("nombreCompleto"),
					rs.getString("email"), rs.getString("rol"))));
		}
		return lista;
	}

	public PersonaDTO obtener(String dni) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Persona where dni=?");
		statement.setString(1, dni);
		PersonaDTO persona = null;
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			if (rs.getString("dni").equals(dni)) {
				persona = new PersonaDTO(new Persona(rs.getString("dni"), rs.getString("nombreCompleto"),
						rs.getString("email"), rs.getString("rol")));
			}
		}
		return persona;
	}

}
