package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

import java.util.List;

import mx.com.teclo.svideskwsw.enumerable.EnumErrorValidacion;

public class NodoPT {

	private boolean isHead;
	private NodoPT next;
	private NodoPT prev;
	private String name;
	private EnumErrorValidacion error;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EnumErrorValidacion getError() {
		return error;
	}

	public void setError(EnumErrorValidacion error) {
		this.error = error;
	}
	private List<NodoError> archivos;
	private List<NodoError> siluetas;
	private List<NodoError> imagenes;
	
	public NodoPT(NodoPT prevVal){
		if(prevVal != null){
			prevVal.setNext(this);
			this.setPrev(prevVal);
		}else{
			this.isHead = true;
		}
	}
	
	public boolean isHead() {
		return isHead;
	}
	public NodoPT getNext() {
		return next;
	}
	public void setNext(NodoPT next) {
		this.next = next;
	}
	public NodoPT getPrev() {
		return prev;
	}
	public void setPrev(NodoPT prev) {
		this.prev = prev;
	}
	public List<NodoError> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<NodoError> archivos) {
		this.archivos = archivos;
	}
	public List<NodoError> getSiluetas() {
		return siluetas;
	}
	public void setSiluetas(List<NodoError> siluetas) {
		this.siluetas = siluetas;
	}
	public List<NodoError> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<NodoError> imagenes) {
		this.imagenes = imagenes;
	}
	public boolean isHasNext() {
		return this.next != null;
	}
	public boolean isHasPrev() {
		return this.prev != null;
	}
}
