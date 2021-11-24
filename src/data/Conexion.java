package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private final String URL="jdbc:mysql://localhost:3306/Fruteria Hidalgo";
	private final String USER="root";
	private final String PASSWD="";

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
