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
@Table(name = "TAQ011D_AR_TIPO_PARAM_COMP")
public class TipoParamCompDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3382314285252744295L;

	@Id
	@SequenceGenerator(name = "SQAQ011D", sequenceName="SQAQ011D_AR_TIPO_PARAM_COMP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ011D")
	@Column(name = "ID_TIPO_PARAM_COMP", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoParamComp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_PARAMETRO", nullable = false)
	private TipoParametroDTO tipoParametro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COMPONENTE", nullable = false)
	private ComponentesDTO componente;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICA", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdTipoParamComp() {
		return idTipoParamComp;
	}

	public void setIdTipoParamComp(Long idTipoParamComp) {
		this.idTipoParamComp = idTipoParamComp;
	}

	public TipoParametroDTO getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroDTO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public ComponentesDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesDTO componente) {
		this.componente = componente;
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
