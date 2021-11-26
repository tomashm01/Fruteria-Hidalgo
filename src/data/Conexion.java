package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	/**
	 * Patrón diseño Singleton usado
	 */
	
	private static Conexion instance=null;
	
	private final String URL="jdbc:mysql://localhost:3306/Fruteria Hidalgo";
	private final String USER="root";
	private final String PASSWD="";

	/**
	 * Devuelve la instancia actual
	 * 
	 * @return Conexion
	 */
	
	public static Conexion getInstance() {
		if(Conexion.instance==null) {
			Conexion.instance=new Conexion();
		}
		return Conexion.instance;
	}
	
	/**
	 * Establece conexion con la clase Connection 
	 * 
	 * @return Connection
	 */
	
	public Connection conectar(){
		Connection con=null;
		try{			
			con=DriverManager.getConnection(URL,USER,PASSWD);
		} catch (SQLException e ){
			e.printStackTrace();
			return null;
		}
		return con;
	}
}
