package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Conexion;
import data.DTO.FrutaDTO;
import data.DTO.FrutasTicketDTO;
import data.Entities.Fruta;

public class FrutasTicketDAO implements DAO<FrutasTicketDTO,Integer> {
	static final Conexion con= new Conexion();
	static final Connection c=con.conectar();

	public boolean insertar(FrutasTicketDTO f) throws SQLException {
		PreparedStatement statement = c.prepareStatement("INSERT INTO FrutasTicket values (?,?,?);");
		statement.setInt(1, f.getId());
		statement.setInt(2, f.getIdTicket());
		statement.setInt(3, f.getIdFruta());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean modificar(FrutasTicketDTO f) throws SQLException {
		PreparedStatement statement = c.prepareStatement("UPDATE FrutasTicket SET idTicket=?, idFruta=?where id=?;");
		statement.setInt(1, f.getIdTicket());
		statement.setInt(2, f.getIdFruta());
		statement.setInt(3, f.getId());
		
		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public boolean eliminar(FrutasTicketDTO f) throws SQLException {
		PreparedStatement statement = c.prepareStatement("DELETE FROM FrutasTicket WHERE id=?;");
		statement.setInt(1, f.getId());

		if(statement.executeUpdate()!=1) return false;
		return true;
	}

	public ArrayList<FrutasTicketDTO> obtenerTodos() throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM FrutasTicket;");
		ArrayList<FrutasTicketDTO> lista=new ArrayList<FrutasTicketDTO>();
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			lista.add(new FrutasTicketDTO(rs.getInt("id"),rs.getInt("idTicket"),rs.getInt("idFruta")));
		}
		return lista;
	}

	public FrutasTicketDTO obtenerUno(Integer id) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT * FROM FrutasTicket where id=?;");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		if(rs.next()) {
			return new FrutasTicketDTO(rs.getInt("id"),rs.getInt("idTicket"),rs.getInt("idFruta"));
		}
		return null;
	}
}