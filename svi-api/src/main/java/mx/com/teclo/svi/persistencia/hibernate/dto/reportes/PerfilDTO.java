package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity(name="PerfilDTO")
@Table(name="PERFILES")
@Immutable
public class PerfilDTO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2073543093107067611L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PERFIL_ID", unique = true, nullable = false)
	private Long perfilId;
	
	@Column(name = "PERFIL_NOMBRE")
	private String perfilNombre;
	
	@Column(name = "ST_VISIBLE")
	private String stVisible;
	
	@Column(name = "CD_PERFIL", length = 15)
	private String perfilCodigo;
	
	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private boolean stActivo;
	
	@Column(name = "ID_APLICACION")
	private Long aplicacionDTO;
	

	/**
	 * @return the perfilId
	 */
	public Long getPerfilId() {
		return perfilId;
	}

	/**
	 * @param perfilId the perfilId to set
	 */
	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	/**
	 * @return the perfilNombre
	 */
	public String getPerfilNombre() {
		return perfilNombre;
	}

	/**
	 * @param perfilNombre the perfilNombre to set
	 */
	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}

	/**
	 * @return the stVisible
	 */
	public String getStVisible() {
		return stVisible;
	}

	/**
	 * @param stVisible the stVisible to set
	 */
	public void setStVisible(String stVisible) {
		this.stVisible = stVisible;
	}

	/**
	 * @return the perfilCodigo
	 */
	public String getPerfilCodigo() {
		return perfilCodigo;
	}

	/**
	 * @param perfilCodigo the perfilCodigo to set
	 */
	public void setPerfilCodigo(String perfilCodigo) {
		this.perfilCodigo = perfilCodigo;
	}

	/**
	 * @return the stActivo
	 */
	public boolean isStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(boolean stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the aplicacionDTO
	 */
	public Long getAplicacionDTO() {
		return aplicacionDTO;
	}

	/**
	 * @param aplicacionDTO the aplicacionDTO to set
	 */
	public void setAplicacionDTO(Long aplicacionDTO) {
		this.aplicacionDTO = aplicacionDTO;
	}	
}
