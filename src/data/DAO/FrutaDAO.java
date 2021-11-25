package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Conexion;
import data.DTO.FrutaDTO;

public class FrutaDAO implements DAO<FrutaDTO,Integer>{
	static final Conexion con= new Conexion();
	static final Connection c=con.conectar();
	
	public FrutaDAO() {
		
	}
	
	public boolean insertar(FrutaDTO f) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO Fruta values (null,?,?,?);");
		statement.setString(1, f.getNombre());
		statement.setInt(2, f.getCantidad());
		statement.setFloat(3, f.getPrecioUnidad());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean modificar(FrutaDTO f) throws SQLException {
		PreparedStatement statement = c.prepareStatement("UPDATE Fruta SET nombre=? , cantidad=?, precioUnidad=? where id=?;");
		statement.setString(1, f.getNombre());
		statement.setInt(2, f.getCantidad());
		statement.setFloat(3, f.getPrecioUnidad());
		statement.setInt(4, f.getId());
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean eliminar(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM Fruta WHERE id=?;");
		statement.setInt(1, id);

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public ArrayList<FrutaDTO> obtenerTodos() throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Fruta;");
		ArrayList<FrutaDTO> lista=new ArrayList<FrutaDTO>();
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			lista.add(new FrutaDTO(rs.getInt("id"),rs.getString("nombre"),rs.getInt("cantidad"),rs.getFloat("precioUnidad")));
		}
		return lista;
	}

	public FrutaDTO obtenerUno(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM Fruta where id=?;");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		if(rs.next()) {
			return new FrutaDTO(rs.getInt("id"),rs.getString("nombre"),rs.getInt("cantidad"),rs.getFloat("precioUnidad"));
		}
		return null;
	}

}
