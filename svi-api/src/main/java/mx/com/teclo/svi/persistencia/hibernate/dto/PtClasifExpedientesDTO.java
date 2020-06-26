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
@Table(name = "TCI012C_PT_CLASIF_EXPEDIENTE")
public class PtClasifExpedientesDTO implements Serializable {

    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -815043078247611547L;
	@Id
    @Basic(optional = false)
    @Column(name = "ID_PT_CLASIF_EXPE")
    private Long idPtClasifExpe;
    @Basic(optional = false)
    @Column(name = "CD_CLASIF_EXPE")
    private String cdClasifExpe;
    @Basic(optional = false)
    @Column(name = "TX_CLASIF_EXPE")
    private String txClasifExpe;
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


    public Long getIdPtClasifExpe() {
        return idPtClasifExpe;
    }

    public void setIdPtClasifExpe(Long idPtClasifExpe) {
        this.idPtClasifExpe = idPtClasifExpe;
    }

    public String getCdClasifExpe() {
        return cdClasifExpe;
    }

    public void setCdClasifExpe(String cdClasifExpe) {
        this.cdClasifExpe = cdClasifExpe;
    }

    public String getTxClasifExpe() {
        return txClasifExpe;
    }

    public void setTxClasifExpe(String txClasifExpe) {
        this.txClasifExpe = txClasifExpe;
    }

    public Long getStActivo() {
        return stActivo;
    }

    public void setStActivo(Long stActivo) {
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