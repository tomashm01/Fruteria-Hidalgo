package data.DTO;

public class FrutaDTO {

	private Integer id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;

	public FrutaDTO(Integer id, String nombre, int cantidad, float precioUnidad) {
		setId(id);
		setCantidad(cantidad);
		setNombre(nombre);
		setPrecioUnidad(precioUnidad);
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
