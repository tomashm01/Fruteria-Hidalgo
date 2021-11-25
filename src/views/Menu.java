package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import data.Conexion;
import data.DAO.FrutaDAO;
import data.DAO.FrutasTicketDAO;
import data.DAO.PersonaDAO;
import data.DAO.TicketDAO;
import data.DTO.FrutaDTO;
import data.DTO.FrutasTicketDTO;
import data.DTO.PersonaDTO;
import data.DTO.TicketDTO;

public class Menu {
	static Scanner s = new Scanner(System.in);
	static boolean bandera = false;
	static final Conexion con= new Conexion();
	static final Connection c=con.conectar();
	static ArrayList<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
	static ArrayList<TicketDTO> listaTicket = new ArrayList<TicketDTO>();
	static ArrayList<FrutaDTO> listaFrutas = new ArrayList<FrutaDTO>();
	static ArrayList<FrutasTicketDTO> listaFrutasTicket = new ArrayList<FrutasTicketDTO>();
	static String rol = "";

	public static void main(String[] args) throws SQLException {

		FrutaDAO frutaDAO = new FrutaDAO();
		PersonaDAO personaDAO = new PersonaDAO();
		TicketDAO ticketDAO = new TicketDAO();
		FrutasTicketDAO ftDAO = new FrutasTicketDAO();
		int opcion = 100;
		int numFrutasComprar=0;
		Date fecha = new Date(System.currentTimeMillis());
		Integer id = null;
		Integer cantidadFruta = null;

		PersonaDTO persona = null;
		TicketDTO ticket = null;
		FrutaDTO fruta = null;
		FrutasTicketDTO ft = null;
		do { // MENU INICIO SESION
			opcion = primerMenu();
			switch (opcion) {
			case 0: // SALIR
				System.out.println("Hasta luego!");
				bandera = true;
				break;
			case 1: // INICIO SESION
				id = iniciarSesion();
				listaPersonas = personaDAO.obtenerTodos();
				if (existeUsuario(id)) {
					System.out.println("Login correcto");
					persona=personaDAO.obtenerUno(id);
					bandera = true;
				} else {
					System.out.println("Login incorrecto");
				}
				break;
			case 2: // Registro usuario
				String nombre = registrarSesion();
				persona = new PersonaDTO(null, nombre, "Comprador");
				listaPersonas = personaDAO.obtenerTodos();

				if (personaDAO.insertar(persona)) {
					listaPersonas = personaDAO.obtenerTodos();
					persona = personaDAO.obtenerUno(listaPersonas.size());
					id = persona.getID();
					rol = persona.getRol();
					bandera = true;
					System.out.println("Usuario con ID" + id);
				} else {
					System.out.println("Usuario no insertado");
				}
				break;
			}
		} while (!bandera);
		bandera = false;

		if (rol.equals("Comprador")) { // MENU LOGIN COMPRADOR
			do {
				opcion = opcionMenuComprador();
				switch (opcion) {
				case 0:
					System.out.println("Hasta luego!");
					bandera = true;
					break;
				case 1:// Añadir fruta al ticket
					System.out.println("Selecciona ID de la fruta: ");
					id=s.nextInt();
					s.nextLine();
					System.out.println("Selecciona numero de unidades: ");
					cantidadFruta=s.nextInt();
					s.nextLine();
					
					if((fruta=frutaDAO.obtenerUno(id))!=null) {//Existe fruta
						if((fruta.getCantidad()-cantidadFruta)>0) { //Hay cantidad suficiente
							
						}else {
							System.out.println("No queda dicha cantidad ");
						}
					}else {
						System.out.println("No existe dicho ID de fruta");
					}
					break;
				case 2: // Mostrar todas las frutas
					listaFrutas = frutaDAO.obtenerTodos();
					for (int i = 0; i < listaFrutas.size(); i++) {
						System.out.println(listaFrutas.get(i).toString());
					}
					break;
				}
			} while (!bandera);
		} else if (rol.equals("Admin")) { // MENU LOGIN ADMIN
			System.out.println("admin");
		}
	}

	public static boolean existeUsuario(Integer id) {
		for (int i = 0; i < listaPersonas.size(); i++) {
			if (listaPersonas.get(i).getID() == id) {
				rol = listaPersonas.get(i).getRol();
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

	public static int opcionMenuComprador() {
		int opcion = 10;
		do {
			menuComprador();
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
		System.out.println("2.Mostrar frutas");
		System.out.println("0.Cerrar sesión");
	}

	public static int getTicketLista() {
		return listaTicket.size();
	}
	public static int getFrutaTicket() {
		return listaFrutas.size();
	}
	public static int getFrutasTicketLista() {
		return listaFrutasTicket.size();
	}
	public static int getPersonaLista() {
		return listaPersonas.size();
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
