package data.DTO;

import java.util.List;

import data.Entities.Fruta;

public class FrutaDTO {
	private int id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;
	private List<FrutaDTO> listaFrutas;
	
	public FrutaDTO(Fruta fruta) {
		setCantidad(fruta.getCantidad());
		setId(fruta.getId());
		setNombre(fruta.getNombre());
		setPrecioUnidad(fruta.getPrecioUnidad());
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
}
