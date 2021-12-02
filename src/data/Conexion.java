package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	/**
	 * Patrón diseño Singleton usado
	 */
	
	private static Conexion instance=null;
	
	private static String URL="jdbc:mysql://localhost:3306/FruteriaHidalgo";
	private static String USER="root";
	private static String PASSWD="";

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
	
	public Connection conectar(boolean autocomit){
		Connection con=null;
		try{			
			con=DriverManager.getConnection(URL,USER,PASSWD);
			
			if(autocomit) {
				con.setAutoCommit(true);
			}else {
				con.setAutoCommit(false);
			}
			
		} catch (SQLException e ){
			e.printStackTrace();
			return null;
		}
		return con;
	}
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
