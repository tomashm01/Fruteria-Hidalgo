package data.DTO;

public class FrutaDTO {
	private int id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;
	
	public FrutaDTO(int id,String nombre,int cantidad,float precioUnidad) {
		setCantidad(cantidad);
		setId(id);
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
}
