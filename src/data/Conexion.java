package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	/**
	 * Patrón diseño Singleton usado
	 */
	
	private static Conexion instance=null;
	private static Connection conex=null;
	private static String URL="jdbc:mysql://localhost:3306/FruteriaHidalgo";
	private static String USER="root";
	private static String PASSWD="";
	
	public Conexion() {
		
	}
	
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
	
	public Connection setConexion(boolean commit) {
		try{			
			if(commit) {
				conex.commit();
			}else {
				conex.setAutoCommit(false);
			}
			
		} catch (SQLException e ){
			e.printStackTrace();
			return null;
		}
		return conex;
	}
	
	/**
	 * Establece conexion con la clase Connection 
	 * 
	 * @return Connection
	 */
	public Connection conectar(){
		try{			
			conex=DriverManager.getConnection(URL,USER,PASSWD);	
		} catch (SQLException e ){
			e.printStackTrace();
			return null;
		}
		return conex;
	}
}
