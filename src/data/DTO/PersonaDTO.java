package data.DTO;
import data.Entities.Persona;

public class PersonaDTO {
	private Integer id;
	private String nombre;
	private String rol;

	public PersonaDTO(Persona p) {
		this.id=p.getID();
		setNombre(p.getNombre());
		setRol(p.getRol());
	}
	
	public PersonaDTO(Integer id, String nombre, String rol) {
		this.id=id;
		setNombre(nombre);
		setRol(rol);
	}

	public Integer getID() {
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
