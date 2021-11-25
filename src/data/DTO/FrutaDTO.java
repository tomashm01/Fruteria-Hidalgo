package data.DTO;

import data.Entities.Fruta;

public class FrutaDTO {

	private int id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;

	public FrutaDTO(Fruta fruta) {
		setId(fruta.getId());
		setCantidad(fruta.getCantidad());
		setNombre(fruta.getNombre());
		setPrecioUnidad(fruta.getPrecioUnidad());
	}

	public FrutaDTO(Integer id, String nombre, int cantidad, float precioUnidad) {
		setId(id);
		setCantidad(cantidad);
		setNombre(nombre);
		setPrecioUnidad(precioUnidad);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public String toString() {
		return this.getId() + "." + this.getNombre() + ". Cantidad: " + this.getCantidad() + ". Precio/Unidad: "
				+ this.getPrecioUnidad() + "\n";
	}
}
