package data.DTO;

import data.Entities.Fruta;

public class FrutaDTO {

	private Integer id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;

	public FrutaDTO(Fruta f) {
		setId(f.getId());
		setCantidad(f.getCantidad());
		setNombre(f.getNombre());
		setPrecioUnidad(f.getPrecioUnidad());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return this.getId() + "." + this.getNombre() + ". CANTIDAD: " + this.getCantidad() + ". PRECIO/UNIDAD: "
				+ this.getPrecioUnidad() + "\n";
	}
}
