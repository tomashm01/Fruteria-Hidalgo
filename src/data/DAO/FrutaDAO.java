package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.Conexion;
import data.DTO.FrutaDTO;
import data.DTO.PersonaDTO;
import data.Entities.Fruta;
import data.Entities.Persona;

public class FrutaDAO implements DAO<FrutaDTO,Integer> {

	static final Conexion con = new Conexion();
	static final Connection c = con.conectar();
	
	public boolean insertar(FrutaDTO fruta) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Fruta values (null,?,?,?)");
		statement.setString(1, fruta.getNombre());
		statement.setInt(2, fruta.getCantidad());
		statement.setFloat(3, fruta.getPrecioUnidad());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean modificar(FrutaDTO fruta) throws SQLException {
		PreparedStatement statement = c
				.prepareStatement("UPDATE Fruta SET nombre=? , cantidad=? , PrecioUnidad=? where id=?");
		statement.setString(1, fruta.getNombre());
		statement.setInt(2, fruta.getCantidad());
		statement.setFloat(3, fruta.getPrecioUnidad());
		statement.setInt(4, fruta.getId());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public boolean eliminar(FrutaDTO fruta) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Fruta WHERE id=?");
		statement.setInt(1, fruta.getId());

		if (statement.executeUpdate() != 1)
			return false;
		return true;
	}

	public List<FrutaDTO> getTodos() throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Fruta ");
		ResultSet rs = statement.executeQuery();
		List<FrutaDTO> lista = null;
		while (rs.next()) {
			lista.add(new FrutaDTO(new Fruta(rs.getInt("id"), rs.getString("nombre"),
					rs.getInt("cantidad"), rs.getFloat("PrecioUnidad"))));
		}
		return lista;
	}

	@Override
	public FrutaDTO obtener(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Fruta where id=?");
		statement.setInt(1, id);
		FrutaDTO fruta = null;
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			if (rs.getInt("id")==id) {
				fruta = new FrutaDTO(new Fruta(rs.getInt("id"), rs.getString("nombre"),
						rs.getInt("cantidad"), rs.getFloat("PrecioUnidad")));
			}
		}
		return fruta;
	}

}
