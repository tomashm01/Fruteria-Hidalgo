package data.Entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {
	private int id;
	private String nombre;
	private String rol;
	private static List<Persona> listaPersonas;

	public Persona(int id, String nombre, String rol) {
		this.id=id;
		setNombre(nombre);
		setRol(rol);
	}

	public int getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		if (rol.equals("Admin") || rol.equals("Comprador")) {
			this.rol = rol;
		} else {
			this.rol = null;
		}
	}

}
