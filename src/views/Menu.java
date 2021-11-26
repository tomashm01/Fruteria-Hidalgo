package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

	private static final Conexion con = Conexion.getInstance();
	private static final Connection c = con.conectar();
	private static Scanner s = new Scanner(System.in);

	private static boolean bandera = false;
	private static String rol = "";

	private static ArrayList<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
	private static ArrayList<TicketDTO> listaTicket = new ArrayList<TicketDTO>();
	private static ArrayList<FrutaDTO> listaFrutas = new ArrayList<FrutaDTO>();
	private static ArrayList<FrutasTicketDTO> listaFrutasTicket = new ArrayList<FrutasTicketDTO>();

	public static void main(String[] args) throws SQLException {

		FrutaDAO frutaDAO = new FrutaDAO();
		PersonaDAO personaDAO = new PersonaDAO();
		TicketDAO ticketDAO = new TicketDAO();
		FrutasTicketDAO ftDAO = new FrutasTicketDAO();
		
		int opcion = 100;
		Date fecha = new Date(System.currentTimeMillis());
		float precioFinal = 0;
		
		Integer id = null;
		Integer idTicket = null;
		Integer idFrutasTicket = null;
		Integer cantidadFruta = null;
		
		PersonaDTO persona = null;
		FrutaDTO fruta = null;
		try (PreparedStatement statement = c.prepareStatement("SET GLOBAL FOREIGN_KEY_CHECKS=0;");){
			statement.executeQuery();
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
						precioFinal = 0;
						persona = personaDAO.obtenerUno(id);
						
						bandera = true;
					} else {
						System.out.println("Login incorrecto");
					}
					
					break;
				case 2: // REGISTRAR USUARIO
					
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
					actualizarListas(frutaDAO, ftDAO, ticketDAO, personaDAO);
					opcion = opcionMenuComprador();

					switch (opcion) {

					case 0:// SALIR
						
						System.out.println("Hasta luego!");
						bandera = true;
						
						break;

					case 1:// AÑADIR FRUTA AL TICKET
						
						System.out.println("Selecciona ID de la fruta: ");
						id = s.nextInt();
						s.nextLine();
						
						System.out.println("Selecciona numero de unidades: ");
						cantidadFruta = s.nextInt();
						s.nextLine();

						// ACTUALIZAR LISTAS
						if ((listaFrutasTicket.size() != 0 && listaTicket.size() != 0)) {
							idFrutasTicket = listaFrutasTicket.get(listaFrutasTicket.size() - 1).getId() + 1;
							idTicket = listaTicket.get(listaTicket.size() - 1).getId() + 1;
						} else {
							idFrutasTicket = 1;
							idTicket = 1;
						}

						if ((fruta = frutaDAO.obtenerUno(id)) != null) {// Existe fruta
							if ((fruta.getCantidad() - cantidadFruta) > 0) { // Hay cantidad suficiente
								ftDAO.insertar(new FrutasTicketDTO(idFrutasTicket, idTicket, fruta.getId()));
								frutaDAO.modificar(new FrutaDTO(fruta.getId(), fruta.getNombre(),
										fruta.getCantidad() - cantidadFruta, fruta.getPrecioUnidad()));
								precioFinal += (cantidadFruta * fruta.getPrecioUnidad());
							} else {
								System.out.println("No queda dicha cantidad ");
							}
						} else {
							System.out.println("No existe dicho ID de fruta");
						}

						break;

					case 2: // MOSTRAR TODAS LAS FRUTAS
						
						listaFrutas = frutaDAO.obtenerTodos();
						for (int i = 0; i < listaFrutas.size(); i++) {
							System.out.println(listaFrutas.get(i).toString());
						}
						
						break;
					}
				} while (!bandera);

				if(persona!=null) {
					ticketDAO.insertar(new TicketDTO(idTicket, persona.getID(), idFrutasTicket, fecha, precioFinal));
				}
				
			} else if (rol.equals("Admin")) { // MENU LOGIN ADMIN
				do {
					actualizarListas(frutaDAO, ftDAO, ticketDAO, personaDAO);
					opcion = opcionMenuAdmin();

					switch (opcion) {

					case 0: // SALIR
						
						System.out.println("Hasta luego!");
						bandera = true;
						
						break;

					case 1:// MOSTRAR TICKETS
						
						for (int i = 0; i < listaTicket.size(); i++) {
							System.out.println(listaTicket.get(i).toString());
						}
						
						break;
						
					case 2://MOSTRAR USUARIOS
						
						for(int i=0;i<listaPersonas.size();i++) {
							System.out.println(listaPersonas.get(i).toString());
						}
						
						break;
						
					case 3: //MOSTRAR TODAS FRUTAS
						
						for(int i=0;i<listaFrutas.size();i++) {
							System.out.println(listaFrutas.get(i).toString());
						}
						
						break;
						
					case 4: //MOSTRAR USUARIO POR ID
						
						System.out.println("Inserta ID del usuario: ");
						id=s.nextInt();
						s.nextLine();
						
						persona=personaDAO.obtenerUno(id);
						
						if(persona!=null) {
							System.out.println(persona.toString());
						}else {
							System.out.println("No se ha encontrado esta persona");
						}
						
						break;
						
					case 5://ELIMINAR USUARIO
						
						System.out.println("Inserta ID del usuario: ");
						id=s.nextInt();
						s.nextLine();
				
						if(personaDAO.eliminar(id)){
							System.out.println("Persona eliminada correctamente");
						}else {
							System.out.println("No se ha eliminado");
						}
						break;
						
					case 6: //AÑADIR NUEVO USUARIO
						
						persona=newUsuario();
						
						if(personaDAO.insertar(persona)) {
							System.out.println("Persona correctamente insertada");
						}else {
							System.out.println("Persona no insertada");
						}
						break;
					
					case 7:	//AÑADIR NUEVA FRUTA
						
						fruta=newFruta();
						
						if(frutaDAO.insertar(fruta)) {
							System.out.println("Fruta correctamente insertada");
						}else {
							System.out.println("Fruta no insertada");
						}
						break;
					
					case 8://MODIFICAR FRUTA
						
						boolean modificar=false;
						fruta=modificarFruta();
						
						for(int i=0;i<listaFrutas.size();i++) {
							if(listaFrutas.get(i).getId()==fruta.getId()) {
								modificar=true;
							}
						}
						
						if(frutaDAO.modificar(fruta) && modificar) {
							System.out.println("Fruta modificada correctamente");
						}else {
							System.out.println("Fruta no modificada correctamente");
						}
						
						break;
					
					case 9://ELIMINAR FRUTA
						
						System.out.println("Inserta ID de la fruta: ");
						id=s.nextInt();
						s.nextLine();
						
						if(frutaDAO.eliminar(id)) {
							System.out.println("Fruta eliminada correctamente");
						}else {
							System.out.println("Fruta no eliminada");
						}
						
						break;
					
					}
				} while (!bandera);
			}
			c.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Pide datos al usuario y devuelve una fruta para modificar
	 * 
	 * @return FrutaDTO
	 */

	private static FrutaDTO modificarFruta() {

		System.out.println("Introduce ID de la fruta: ");
		Integer id = s.nextInt();
		s.nextLine();

		System.out.println("Introduce su nombre: ");
		String nombre = s.nextLine();

		System.out.println("Introduce su cantidad: ");
		Integer cantidad = s.nextInt();
		s.nextLine();

		System.out.println("Introduce el precio por unidad: ");
		float precioUnidad = s.nextFloat();

		return new FrutaDTO(id, nombre, cantidad, precioUnidad);
	}

	/**
	 * Devuelve una nuevaFruta para insertar
	 * 
	 * @return FrutaDTO
	 */

	private static FrutaDTO newFruta() {

		System.out.println("Introduce su nombre: ");
		String nombre = s.nextLine();

		System.out.println("Introduce su cantidad: ");
		Integer cantidad = s.nextInt();
		s.nextLine();

		System.out.println("Introduce el precio por unidad: ");
		float precioUnidad = s.nextFloat();

		return new FrutaDTO(null, nombre, cantidad, precioUnidad);
	}

	/**
	 * Crea un nuevo usuario para insertar
	 * 
	 * @return PersonaDTO
	 */

	private static PersonaDTO newUsuario() {

		System.out.println("Introduce su nombre: ");
		String nombre = s.nextLine();

		do {

			System.out.println("Introduce su rol");
			rol = s.nextLine();

		} while (!rol.equals("Admin") && !rol.equals("Comprador"));

		return new PersonaDTO(null, nombre, rol);
	}

	/**
	 * Menu para el usuario con rol administrador
	 */

	public static void menuAdmin() {
		System.out.println("MENU ADMIN");
		System.out.println("1.Ver todos los tickets");
		System.out.println("2.Ver todos los usuarios");
		System.out.println("3.Ver todas las frutas");
		System.out.println("4.Ver usuario por ID");
		System.out.println("5.Eliminar usuario");
		System.out.println("6.Registrar nuevo usuario(admin o comprador)");
		System.out.println("7.Insertar nueva fruta");
		System.out.println("8.Modificar fruta");
		System.out.println("9.Eliminar fruta");
		System.out.println("0.Cerrar sesión");
	}

	/**
	 * Uso una transacción para actualizar todas las listas de cada clase a la vez
	 * 
	 * @param frutaDAO
	 * @param ftDAO
	 * @param ticketDAO
	 * @param personaDAO
	 * @throws SQLException
	 */

	private static void actualizarListas(FrutaDAO frutaDAO, FrutasTicketDAO ftDAO, TicketDAO ticketDAO,
			PersonaDAO personaDAO) throws SQLException {

		c.setAutoCommit(false);

		listaFrutas = frutaDAO.obtenerTodos();
		listaFrutasTicket = ftDAO.obtenerTodos();
		listaPersonas = personaDAO.obtenerTodos();
		listaTicket = ticketDAO.obtenerTodos();

		c.commit();

	}

	/**
	 * Comprueba si el id se corresponde con un usuario de la BBDD
	 * 
	 * @param id
	 * @return boolean
	 */

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

	/**
	 * Pide el nombre del usuario para registrarse
	 * 
	 * @return String
	 */

	public static String registrarSesion() {
		System.out.println("Introduce tu nombre");
		return s.nextLine();
	}

	/**
	 * Pide el id de un usuario para iniciar sesión
	 * 
	 * @return int
	 */

	public static int iniciarSesion() {

		System.out.println("Introduce tu ID: ");
		int id = s.nextInt();
		s.nextLine();

		return id;
	}

	/**
	 * Devuelve una opción en el menú de inicio/registro de sesión
	 * 
	 * @return int
	 */

	public static int primerMenu() {

		int opcion = 10;

		do {
			showInicioSesion();
			opcion = s.nextInt();
			s.nextLine();
		} while (opcion < 0 || opcion > 2);

		return opcion;
	}

	/**
	 * Devuelve la opción seleccionada por el usuario con rol comprador
	 * 
	 * @return int
	 */

	public static int opcionMenuComprador() {

		int opcion = 10;

		do {

			menuComprador();
			opcion = s.nextInt();
			s.nextLine();

		} while (opcion < 0 || opcion > 2);

		return opcion;
	}

	/**
	 * Devuelve la opción seleccionada por el usuario con rol administrador
	 * 
	 * @return int
	 */

	public static int opcionMenuAdmin() {

		int opcion = 15;
		do {

			menuAdmin();
			opcion = s.nextInt();
			s.nextLine();

		} while (opcion < 0 || opcion > 9);

		return opcion;
	}

	/**
	 * Muestra el menu de inicio/registro de sesión
	 */

	public static void showInicioSesion() {
		System.out.println("MENU PRINCIPAL");
		System.out.println("1.Inicio sesion");
		System.out.println("2.Registro");
		System.out.println("0.Salir");
	}

	/**
	 * Muestra el menu del usuario con rol comprador
	 */

	public static void menuComprador() {
		System.out.println("MENU COMPRADOR");
		System.out.println("1.Realizar compra");
		System.out.println("2.Mostrar frutas");
		System.out.println("0.Cerrar sesión");
	}

	// Getters de listas

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
}
