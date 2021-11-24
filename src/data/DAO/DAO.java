package data.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> {
	boolean insertar(T a) throws SQLException;
	boolean modificar(T a)throws SQLException;
	boolean eliminar(T a)throws SQLException;
	List<T>getTodos()throws SQLException;
	T obtener(K id)throws SQLException;
}
