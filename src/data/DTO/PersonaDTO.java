package data.DTO;

import java.util.List;
import data.Entities.Persona;

public class PersonaDTO {
	private int id;
	private String nombre;
	private String rol;
	private static List<PersonaDTO> listaPersonas;

	public PersonaDTO(Persona p) {
		this.id=p.getID();
		setNombre(p.getNombre());
		setRol(p.getRol());
	}
	
	public PersonaDTO(int id, String nombre, String rol) {
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
