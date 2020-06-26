package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

import mx.com.teclo.svideskwsw.enumerable.EnumErrorValidacion;

public class NodoError {

	private String nombre;
	private long codigo;
	private String mensaje;
	
	public NodoError(String nombre, EnumErrorValidacion eeV){
		this.nombre = nombre;
		this.codigo = eeV.getIdError();
		this.mensaje = eeV.getMensajeError();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
