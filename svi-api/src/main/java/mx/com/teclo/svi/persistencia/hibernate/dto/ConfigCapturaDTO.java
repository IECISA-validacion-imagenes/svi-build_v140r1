package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI032P_PT_CONFIG_CAPTURA")
public class ConfigCapturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_CAPTURA")
	private Long idCaptura;
	@Basic
	@Column(name = "TX_CONFIGURACION")
	private String txConfiguracion;
	@Basic(optional = false)
	@Column(name = "FH_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;
	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION")
	private long idUsrCreacion;
	@Basic(optional = false)
	@Column(name = "FH_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhModificacion;
	@Basic(optional = false)
	@Column(name = "ID_USR_MODIFICA")
	private long idUsrModifica;

	public Long getIdCaptura() {
		return idCaptura;
	}

	public void setIdCaptura(Long idCaptura) {
		this.idCaptura = idCaptura;
	}

	public String getTxConfiguracion() {
		return txConfiguracion;
	}

	public void setTxConfiguracion(String txConfiguracion) {
		this.txConfiguracion = txConfiguracion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

}
