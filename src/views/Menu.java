package views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.DAO.PersonaDAO;
import data.DTO.PersonaDTO;

public class Menu {
    public static void main(String[] args) throws SQLException {
		Scanner s=new Scanner(System.in);
		ArrayList<PersonaDTO> listaPersonas=new ArrayList<PersonaDTO>();
		PersonaDAO personaDAO=new PersonaDAO();
		int opcion=0;
		Integer id=null;
		boolean registrado=false;
		PersonaDTO persona=null;
		do {
			//MENU INICIO SESION
			do {
				showInicioSesion();
				opcion=s.nextInt();
				s.nextLine();
				
				switch(opcion) {
					case 1: //Inicio sesión persona
						System.out.println("Introduce tu ID");
						id=s.nextInt();
						s.nextLine();
						
						listaPersonas=personaDAO.obtenerTodos();
						
						for(int i=0;i<listaPersonas.size();i++) {
							if(listaPersonas.get(i).getID()==id) {
								System.out.println("Login correcto");
								registrado=true;
							}
						}
						break;
					case 2: //Registro persona
						
						System.out.println("Introduce tu nombre");
						String nombre=s.nextLine();
						String rol="";
						do {
							System.out.println("Introduce tu rol");
							rol=s.nextLine();
						}while(!rol.equals("Admin") && !rol.equals("Comprador"));
						
						persona=new PersonaDTO(null,nombre,rol);
						
						listaPersonas=personaDAO.obtenerTodos();
						
						//Compruebo que la inserción se ha hecho bien
						if(personaDAO.insertar(persona)) {
							System.out.println("Usuario correctamente insertado");
							listaPersonas=personaDAO.obtenerTodos();
							persona=personaDAO.obtenerUno(listaPersonas.size());
							id=persona.getID();
							registrado=true;
						}else {
							System.out.println("Usuario no insertado");
						}
						break;
					default:
						System.out.println("Introduce un valor entre 1 y 2");
						break;
				}
			}while(!registrado && (opcion<1 || opcion>2));			
			System.out.println(persona.getID());
		}while(opcion!=6);
		
	}
    public static void showInicioSesion() {
    	System.out.println("MENU INICIO SESION");
    	System.out.println("1.Inicio sesion");
    	System.out.println("2.Registro");
    }
    
    public static void showMenuPrincipal() {
    	System.out.println("MENU PRINCIPAL");
    	System.out.println("1.Inicio sesion");
    	System.out.println("2.Registro");
    }
}
