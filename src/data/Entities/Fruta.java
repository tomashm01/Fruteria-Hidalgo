package data.Entities;

import java.util.List;

public class Fruta {

	private int id;
	private String nombre;
	private int cantidad;
	private float precioUnidad;
	private List<Fruta> listaFrutas;
	
	
	public Fruta(int id,String nombre,int cantidad,float precioUnidad) {
		setId(id);
		setCantidad(cantidad);
		setNombre(nombre);
		setPrecioUnidad(precioUnidad);
	}
	
	public boolean addFrutaToList(Fruta frutaNueva) {
		if (frutaNueva.getCantidad()>0 && frutaNueva.getNombre()!=null && frutaNueva.getPrecioUnidad()>0) {
			listaFrutas.add(frutaNueva);
			return true;
		}
		return false;
	}

	public boolean removeFrutaByID(int id) {
		for (int i = 0; i < listaFrutas.size(); i++) {
			if (listaFrutas.get(i).getId()==id) {
				listaFrutas.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean changeFruta(Fruta frutaCambiada) {
		for (int posicion = 0; posicion < listaFrutas.size(); posicion++) {
			if (listaFrutas.get(posicion).getId()==frutaCambiada.getId()){
				listaFrutas.add(posicion, frutaCambiada);
				return true;
			}
		}
		return false;
	}
	
	public List<Fruta> showFrutas(){
		return listaFrutas;
	}

	public Fruta showFrutaByID(int id) {
		for(int i=0;i<listaFrutas.size();i++) {
			if(listaFrutas.get(i).getId()==id){
				return listaFrutas.get(i);
			}
		}
		return null;
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
		this.cantidad=cantidad;
	}
	public float getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	
	
}
