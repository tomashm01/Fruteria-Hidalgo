package data.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> {
	boolean insertar(T a) throws SQLException;
	boolean modificar(T a);
	boolean eliminar(T a);
	List<T>getTodos();
	T obtener(K id);
}
