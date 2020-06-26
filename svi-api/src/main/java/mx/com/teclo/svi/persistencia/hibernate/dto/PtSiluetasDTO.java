package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI016C_PT_SILUETAS")
public class PtSiluetasDTO implements Serializable {


   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1619060180870436034L;
	@Id
    @Basic(optional = false)
    @Column(name = "ID_PT_SILUETA")
    private Long idPtSilueta;
    @Basic(optional = false)
    @Column(name = "CD_PT_SILUETA")
    private String cdPtSilueta;
    @Basic(optional = false)
    @Column(name = "TX_SILUETA")
    private String txSilueta;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Long stActivo;
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
    
    
	/**
	 * @return the idPtSilueta
	 */
	public Long getIdPtSilueta() {
		return idPtSilueta;
	}
	/**
	 * @param idPtSilueta the idPtSilueta to set
	 */
	public void setIdPtSilueta(Long idPtSilueta) {
		this.idPtSilueta = idPtSilueta;
	}
	/**
	 * @return the cdPtSilueta
	 */
	public String getCdPtSilueta() {
		return cdPtSilueta;
	}
	/**
	 * @param cdPtSilueta the cdPtSilueta to set
	 */
	public void setCdPtSilueta(String cdPtSilueta) {
		this.cdPtSilueta = cdPtSilueta;
	}
	/**
	 * @return the txSilueta
	 */
	public String getTxSilueta() {
		return txSilueta;
	}
	/**
	 * @param txSilueta the txSilueta to set
	 */
	public void setTxSilueta(String txSilueta) {
		this.txSilueta = txSilueta;
	}
	/**
	 * @return the stActivo
	 */
	public Long getStActivo() {
		return stActivo;
	}
	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}
	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}
	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	/**
	 * @return the idUsrCreacion
	 */
	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}
	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	/**
	 * @return the idUsrModifica
	 */
	public long getIdUsrModifica() {
		return idUsrModifica;
	}
	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}

