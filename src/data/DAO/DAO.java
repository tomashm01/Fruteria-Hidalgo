package data.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T,K> {
	boolean insertar(T a) throws SQLException;
	boolean modificar(T a)throws SQLException;
	boolean eliminar(T a)throws SQLException;
	ArrayList<T>obtenerTodos()throws SQLException;
	T obtenerUno(K id)throws SQLException;
}
