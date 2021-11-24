package data.Entities;

public class Persona {
	private Integer id;
	private String nombre;
	private String rol;

	public Persona(int id, String nombre, String rol) {
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
