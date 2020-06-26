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
@Table(name = "TAQ013D_AR_PARAMETROS_PROP")
public class ParametrosPropDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9046626804561865322L;

	@Id
	@SequenceGenerator(name = "SQAQ013D", sequenceName="SQAQ013D_AR_PARAMETROS_PROP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ013D")
	@Column(name = "ID_PARAMETROS_PROP", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idParamtrosProp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARAMETRO", nullable = false)
	private ParametrosDTO parametro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROPIEDAD", nullable = false)
	private PropiedadesDTO propiedad;

	@Column(name = "TX_VALOR", nullable = false, length = 50)
	private String txValor;

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

	public Long getIdParamtrosProp() {
		return idParamtrosProp;
	}

	public void setIdParamtrosProp(Long idParamtrosProp) {
		this.idParamtrosProp = idParamtrosProp;
	}

	public ParametrosDTO getParametro() {
		return parametro;
	}

	public void setParametro(ParametrosDTO parametro) {
		this.parametro = parametro;
	}

	public PropiedadesDTO getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadesDTO propiedad) {
		this.propiedad = propiedad;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
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
