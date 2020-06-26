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
@Table(name = "TCI011C_PT_ENTIDAD")
public class PtEntidadDTO implements Serializable {

    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -643953249529452015L;

	@Id
    @Basic(optional = false)
    @Column(name = "ID_PT_CATALOGO_ENTIDAD")
    private Long idPtCatalogoEntidad;
    
    @Basic(optional = false)
    @Column(name = "CD_ENTIDAD")
    private String cdEntidad;
    
    @Basic(optional = false)
    @Column(name = "TX_DESC_ENTIDAD")
    private String txDescEntidad;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private short stActivo;
    
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

   
    public Long getIdPtCatalogoEntidad() {
        return idPtCatalogoEntidad;
    }

    public void setIdPtCatalogoEntidad(Long idPtCatalogoEntidad) {
        this.idPtCatalogoEntidad = idPtCatalogoEntidad;
    }

    public String getCdEntidad() {
        return cdEntidad;
    }

    public void setCdEntidad(String cdEntidad) {
        this.cdEntidad = cdEntidad;
    }

    public String getTxDescEntidad() {
        return txDescEntidad;
    }

    public void setTxDescEntidad(String txDescEntidad) {
        this.txDescEntidad = txDescEntidad;
    }

    public short getStActivo() {
        return stActivo;
    }

    public void setStActivo(short stActivo) {
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
