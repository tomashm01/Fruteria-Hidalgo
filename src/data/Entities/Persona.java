package data.Entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {
	private String dni;
	private String nombre;
	private String email;
	private String rol;
	private static List<Persona> listaPersonas;

	public Persona(String dni, String nombre, String email, String rol) {
		setDni(dni);
		setNombre(nombre);
		setEmail(email);
		setRol(rol);
	}

	public boolean addPersonaToList(Persona personaNueva) {
		if (personaNueva.getDni() != null && personaNueva.getEmail() != null && personaNueva.getNombre() != null
				&& personaNueva.getRol() != null) {
			listaPersonas.add(personaNueva);
			return true;
		}
		return false;
	}

	public boolean removePersonaByDNI(String dni) {
		for (int i = 0; i < listaPersonas.size(); i++) {
			if (listaPersonas.get(i).getDni().equals(dni)) {
				listaPersonas.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean changePersona(Persona personaCambiada) {
		for (int posicion = 0; posicion < listaPersonas.size(); posicion++) {
			if (listaPersonas.get(posicion).getDni().equals(personaCambiada.getDni())) {
				listaPersonas.add(posicion, personaCambiada);
				return true;
			}
		}
		return false;
	}
	
	public List<Persona> showPersonas(){
		return listaPersonas;
	}

	public Persona showPersonaByDNI(String dni) {
		for(int i=0;i<listaPersonas.size();i++) {
			if(listaPersonas.get(i).getDni().equals(dni)) {
				return listaPersonas.get(i);
			}
		}
		return null;
	}
	
	private boolean validar(String paraValidar, String expresion) {
		Pattern patron = Pattern.compile(expresion);
		Matcher match = patron.matcher(paraValidar);
		if (match.find()) {
			return true;
		}
		return false;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (validar(dni, "\\d{8}[A-HJ-NP-TV-Z]")) {
			this.dni = dni;
		} else {
			this.dni = null;
		}
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
		if (validar(email, "^(.+)@(.+)$")) {
			this.email = email;
		} else {
			this.email = null;
		}
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		if (rol.equals("Vendedor") || rol.equals("Comprador") || rol.equals("Propietario")) {
			this.rol = rol;
		} else {
			this.rol = null;
		}
	}

}
