package views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import data.DAO.FrutaDAO;
import data.DAO.PersonaDAO;
import data.DTO.FrutaDTO;
import data.DTO.PersonaDTO;

public class Menu {
	static Scanner s = new Scanner(System.in);
	static boolean bandera = false;
	static ArrayList<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
	static ArrayList<FrutaDTO> listaFrutas = new ArrayList<FrutaDTO>();
	static String rol = "";
	public static void main(String[] args) throws SQLException {

		PersonaDAO personaDAO = new PersonaDAO();
		int opcion = 100;
		Integer id = null;

		PersonaDTO persona = null;
		
		do { // MENU INICIO SESION
			opcion = primerMenu();
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego!");
				bandera = true;
				break;
			case 1:
				id = iniciarSesion();
				listaPersonas = personaDAO.obtenerTodos();
				if (existeUsuario(id)) {
					System.out.println("Login correcto");
					bandera = true;
				} else {
					System.out.println("Login incorrecto");
				}
				break;
			case 2:
				String nombre = registrarSesion();
				persona = new PersonaDTO(null, nombre, "Comprador");
				listaPersonas = personaDAO.obtenerTodos();

				if (personaDAO.insertar(persona)) {
					listaPersonas = personaDAO.obtenerTodos();
					persona = personaDAO.obtenerUno(listaPersonas.size());
					id = persona.getID();
					rol=persona.getRol();
					bandera = true;
					System.out.println("Usuario insertado");
				} else {
					System.out.println("Usuario no insertado");
				}
				break;
			}
		} while (!bandera);
		if(rol.equals("Comprador")) {
			System.out.println("comprador");
		}else {
			System.out.println("admin");
		}
	}

	public static boolean existeUsuario(Integer id) {
		for (int i = 0; i < listaPersonas.size(); i++) {
			if (listaPersonas.get(i).getID() == id) {
				rol=listaPersonas.get(i).getRol();
				bandera = true;
				return true;
			}
		}
		return false;
	}

	public static String registrarSesion() {
		System.out.println("Introduce tu nombre");
		return s.nextLine();
	}

	public static int iniciarSesion() {
		System.out.println("Introduce tu ID: ");
		int id = s.nextInt();
		s.nextLine();
		return id;
	}

	public static int primerMenu() {
		int opcion = 10;
		do {
			showInicioSesion();
			opcion = s.nextInt();
			s.nextLine();
		} while (opcion < 0 || opcion > 2);
		return opcion;
	}

	public static void showInicioSesion() {
		System.out.println("MENU INICIO SESION");
		System.out.println("1.Inicio sesion");
		System.out.println("2.Registro");
		System.out.println("0.Salir");
	}

	public static void menuComprador() {
		System.out.println("MENU COMPRADOR");
		System.out.println("1.Realizar compra");
		System.out.println("2.Ver ticket actual");
		System.out.println("3.Mostrar frutas");
		System.out.println("0.Cerrar sesión");
	}

	public static void menuAdmin() {
		System.out.println("MENU ADMIN");
		System.out.println("1.Realizar compra");
		System.out.println("2.Ver todos tickets");
		System.out.println("3.Ver todos los usuarios");
		System.out.println("4.Ver usuario por ID");
		System.out.println("5.Eliminar usuario");
		System.out.println("6.Registrar nuevo usuario(admin o comprador)");
		System.out.println("7.Insertar nueva fruta");
		System.out.println("8.Modificar cantidad fruta");
		System.out.println("9.Eliminar fruta");
		System.out.println("10.Mostrar frutas");
		System.out.println("0.Cerrar sesión");
	}
}
