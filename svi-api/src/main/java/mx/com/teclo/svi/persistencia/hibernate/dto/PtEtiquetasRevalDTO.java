package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI029D_PT_ETIQUETAS_REVAL")
public class PtEtiquetasRevalDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7114872744755332740L;

	@SequenceGenerator(name = "SEQPTETIQUETASREVAL", sequenceName = "PT_ETIQUETAS_REVAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTETIQUETASREVAL")
	@Id
	@Basic(optional = false)
	@Column(name = "ID_ETIQ_REVAL")
	private Long idEtiqReval;
	
	@Column(name = "NB_ETIQUETA")
	private String nbEtiqueta;
	
	@Basic(optional = false)
	@Column(name= "CD_UNICIDAD")
	private Long cdUnicidad;
	
	@Column(name = "ID_MOTIVO_REVA")
	private Long idMotivoReva;
	
	@Column(name = "ST_ACTIVO")
	private boolean stActivo;
	
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

	public Long getIdEtiqReval() {
		return idEtiqReval;
	}

	public void setIdEtiqReval(Long idEtiqReval) {
		this.idEtiqReval = idEtiqReval;
	}

	public String getNbEtiqueta() {
		return nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public Long getCdUnicidad() {
		return cdUnicidad;
	}

	public void setCdUnicidad(Long cdUnicidad) {
		this.cdUnicidad = cdUnicidad;
	}

	public Long getIdMotivoReva() {
		return idMotivoReva;
	}

	public void setIdMotivoReva(Long idMotivoReva) {
		this.idMotivoReva = idMotivoReva;
	}

	public boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(boolean stActivo) {
		this.stActivo = stActivo;
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
