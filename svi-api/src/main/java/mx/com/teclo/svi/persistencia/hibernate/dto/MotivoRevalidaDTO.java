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

@Entity
@Table(name = "TCI019C_PT_MOTIVO_REVALIDA")
public class MotivoRevalidaDTO implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8937451697911265031L;

	@Id
    @Basic(optional = false)
    @Column(name = "ID_MOTIVO_REVA")
    private Long idMotivoReva;
    
    @Basic(optional = false)
    @Column(name = "CD_MOTIVO")
    private String cdMotivo;
    
    @Basic(optional = false)
    @Column(name = "TX_MOTIVO")
    private String txMotivo;
    
    @Basic(optional = false)
    @Column(name = "ST_MOTIVO_CSV")
    private Boolean stMotivoCsv;
    
    @Basic(optional = false)
    @Column(name = "ST_MOTIVO_DETALLE")
    private Boolean stMotivoDetalle;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Boolean stActivo;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private Long idUsrCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private Long idUsrModifica;

	public Long getIdMotivoReva() {
		return idMotivoReva;
	}

	public void setIdMotivoReva(Long idMotivoReva) {
		this.idMotivoReva = idMotivoReva;
	}

	public String getCdMotivo() {
		return cdMotivo;
	}

	public void setCdMotivo(String cdMotivo) {
		this.cdMotivo = cdMotivo;
	}

	public String getTxMotivo() {
		return txMotivo;
	}

	public void setTxMotivo(String txMotivo) {
		this.txMotivo = txMotivo;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
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

	public Boolean getStMotivoCsv() {
		return stMotivoCsv;
	}

	public void setStMotivoCsv(Boolean stMotivoCsv) {
		this.stMotivoCsv = stMotivoCsv;
	}

	public Boolean getStMotivoDetalle() {
		return stMotivoDetalle;
	}

	public void setStMotivoDetalle(Boolean stMotivoDetalle) {
		this.stMotivoDetalle = stMotivoDetalle;
	}
	
	
}
