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

	private int opcion;
	private String opciones[] = null;
	private String titulo;

	private static final Conexion con = Conexion.getInstance();
	private static final Connection c = con.conectar();
	private static Scanner s = new Scanner(System.in);

	private static boolean salir = false;
	private static String rol = "";

	private static ArrayList<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
	private static ArrayList<TicketDTO> listaTicket = new ArrayList<TicketDTO>();
	private static ArrayList<FrutaDTO> listaFrutas = new ArrayList<FrutaDTO>();
	private static ArrayList<FrutasTicketDTO> listaFrutasTicket = new ArrayList<FrutasTicketDTO>();

	/**
	 * Constructor Menu
	 * 
	 * @param opcion
	 * @param opciones
	 */

	public Menu(String titulo, String opciones[]) {
		this.titulo = titulo;
		this.opciones = opciones;
	}

	public int getOpcion() {
		return this.opcion;
	}

	public void insertarOpcion() {
		do {
			System.out.println(this.titulo);
			this.mostrar();
			this.opcion = s.nextInt();
			s.nextLine();
		} while (opcion < 0 || opcion > this.opciones.length);
	}

	public void mostrar() {
		for (int i = 0; i < this.opciones.length; i++) {
			System.out.println(i + 1 + "." + this.opciones[i]);
		}
	}

	public static void main(String[] args) throws SQLException {

		FrutaDAO frutaDAO = new FrutaDAO();
		PersonaDAO personaDAO = new PersonaDAO();
		TicketDAO ticketDAO = new TicketDAO();
		FrutasTicketDAO ftDAO = new FrutasTicketDAO();

		String arrayInicio[] = { "Inicio sesión", "Registro de sesión", "Salir" };
		String arrayAdmin[]= {"Mostrar tickets","Mostrar usuarios","Mostrar frutas","Buscar usuario","Eliminar usuario"
				,"Registrar usuario","Registrar fruta","Modificar fruta","Eliminar fruta","Salir"};
		String arrayComprador[]= {"Añadir fruta al carrito","Mostrar frutas","Salir"};
		
		Date fecha = new Date(System.currentTimeMillis());
		float precioFinal = 0;

		Integer id = null;
		Integer idTicket = null;
		Integer idFrutasTicket = null;
		Integer cantidadFruta = null;

		PersonaDTO persona = null;
		FrutaDTO fruta = null;
		try (PreparedStatement statement = c.prepareStatement("SET GLOBAL FOREIGN_KEY_CHECKS=0;");) {
			statement.executeQuery();
			do { // MENU INICIO SESION

				Menu inicio = new Menu("MENU DE SESIÓN", arrayInicio);
				inicio.insertarOpcion();
				
				switch (inicio.getOpcion()) {
				case 1: // INICIO SESION

					id = iniciarSesion();
					listaPersonas = personaDAO.obtenerTodos();

					if (existeUsuario(id)) {

						System.out.println("Login correcto");
						precioFinal = 0;
						persona = personaDAO.obtenerUno(id);

						salir = true;
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
						persona = personaDAO.obtenerUno(listaPersonas.get(listaPersonas.size()-1).getID());

						salir = true;
						listaPersonas = personaDAO.obtenerTodos();
						
						id = persona.getID();
						rol = persona.getRol();
						
						System.out.println("Usuario con ID " + id);
					} else {
						System.out.println("Usuario no insertado");
					}
					break;
				case 3: // SALIR

					System.out.println("Hasta luego!");
					salir = true;

					break;
				}
			} while (!salir);
			salir = false;
			
			if (rol.equals("Comprador")) { // MENU LOGIN COMPRADOR
				do {
					
					actualizarListas(frutaDAO, ftDAO, ticketDAO, personaDAO);
					Menu comprador = new Menu("MENU DE COMPRADOR", arrayComprador);
					comprador.insertarOpcion();

					switch (comprador.getOpcion()) {

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

					case 3:// SALIR

						System.out.println("Hasta luego!");
						salir = true;

						break;
					}
				} while (!salir);

				if (persona != null && idTicket != null && idFrutasTicket != null) {
					ticketDAO.insertar(new TicketDTO(idTicket, persona.getID(), idFrutasTicket, fecha, precioFinal));
				}

			} else if (rol.equals("Admin")) { // MENU LOGIN ADMIN
				do {
					
					actualizarListas(frutaDAO, ftDAO, ticketDAO, personaDAO);
					Menu admin = new Menu("MENU DE ADMINISTRADOR", arrayAdmin);
					admin.insertarOpcion();

					switch (admin.getOpcion()) {

					case 1:// MOSTRAR TICKETS

						for (int i = 0; i < listaTicket.size(); i++) {
							System.out.println(listaTicket.get(i).toString());
						}

						break;

					case 2:// MOSTRAR USUARIOS

						for (int i = 0; i < listaPersonas.size(); i++) {
							System.out.println(listaPersonas.get(i).toString());
						}

						break;

					case 3: // MOSTRAR TODAS FRUTAS

						for (int i = 0; i < listaFrutas.size(); i++) {
							System.out.println(listaFrutas.get(i).toString());
						}

						break;

					case 4: // MOSTRAR USUARIO POR ID

						System.out.println("Inserta ID del usuario: ");
						id = s.nextInt();
						s.nextLine();

						persona = personaDAO.obtenerUno(id);

						if (persona != null) {
							System.out.println(persona.toString());
						} else {
							System.out.println("No se ha encontrado esta persona");
						}

						break;

					case 5:// ELIMINAR USUARIO

						System.out.println("Inserta ID del usuario: ");
						id = s.nextInt();
						s.nextLine();

						if (personaDAO.eliminar(id)) {
							System.out.println("Persona eliminada correctamente");
						} else {
							System.out.println("No se ha eliminado");
						}
						break;

					case 6: // AÑADIR NUEVO USUARIO

						persona = newUsuario();

						if (personaDAO.insertar(persona)) {
							System.out.println("Persona correctamente insertada");
						} else {
							System.out.println("Persona no insertada");
						}
						break;

					case 7: // AÑADIR NUEVA FRUTA

						fruta = newFruta();

						if (frutaDAO.insertar(fruta)) {
							System.out.println("Fruta correctamente insertada");
						} else {
							System.out.println("Fruta no insertada");
						}
						break;

					case 8:// MODIFICAR FRUTA

						boolean modificar = false;
						fruta = modificarFruta();

						for (int i = 0; i < listaFrutas.size(); i++) {
							if (listaFrutas.get(i).getId() == fruta.getId()) {
								modificar = true;
							}
						}

						if (frutaDAO.modificar(fruta) && modificar) {
							System.out.println("Fruta modificada correctamente");
						} else {
							System.out.println("Fruta no modificada correctamente");
						}

						break;

					case 9:// ELIMINAR FRUTA

						System.out.println("Inserta ID de la fruta: ");
						id = s.nextInt();
						s.nextLine();

						if (frutaDAO.eliminar(id)) {
							System.out.println("Fruta eliminada correctamente");
						} else {
							System.out.println("Fruta no eliminada");
						}

						break;

					case 10: // SALIR

						System.out.println("Hasta luego!");
						salir = true;

						break;

					}
				} while (!salir);
			}
			c.close();
		} catch (SQLException e) {
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
				salir = true;
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
