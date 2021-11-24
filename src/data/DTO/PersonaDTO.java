package data.DTO;

import java.util.List;

import data.Entities.Persona;

public class PersonaDTO {
	private String dni;
	private String nombre;
	private String email;
	private String rol;
	
	public PersonaDTO(Persona persona) {
		setDni(persona.getDni());
		setEmail(persona.getEmail());
		setNombre(persona.getNombre());
		setRol(persona.getRol());
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
