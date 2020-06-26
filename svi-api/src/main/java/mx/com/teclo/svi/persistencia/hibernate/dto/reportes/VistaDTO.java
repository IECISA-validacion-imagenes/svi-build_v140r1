package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_REP_INFRAC_AIO_TOTAL")
public class VistaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199467062618987L;

	@Id
	@Column(name = "DEP_ID")
	private Long depId;

	@Column(name = "DEP_NOMBRE")
	private String depNombre;

	@Column(name = "CANTIDAD")
	private Long cantidad;

	@Column(name = "AAAA")
	private Long anio;

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getDepNombre() {
		return depNombre;
	}

	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}
	
	@Override
	public String toString(){
		return "VistaDTO = [depId:"+depId+",depNombre:"+depNombre+",cantidad:"+cantidad+",anio:"+anio+" ]";
	}

}
