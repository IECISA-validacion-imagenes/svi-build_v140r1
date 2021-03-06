package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ040D_AR_PARAM_PROP_SCRIPT")
public class ParamPropScriptDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5859077737908705919L;

	@Id
	@SequenceGenerator(name = "SQAQ040D", sequenceName="SQAQ040D_AR_PA_PRO_SCRIP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ040D")
	@Column(name = "ID_PARAM_PROP_SCRIPT", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idParamPropScript;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARAMETROS_PROP", nullable = false)
	private ParametrosPropDTO paramtrosPropDTO;

	@Column(name = "SCRIPT", nullable = false, length = 2000)
	private String script;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdParamPropScript() {
		return idParamPropScript;
	}

	public void setIdParamPropScript(Long idParamPropScript) {
		this.idParamPropScript = idParamPropScript;
	}

	public ParametrosPropDTO getParamtrosPropDTO() {
		return paramtrosPropDTO;
	}

	public void setParamtrosPropDTO(ParametrosPropDTO paramtrosPropDTO) {
		this.paramtrosPropDTO = paramtrosPropDTO;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

}
