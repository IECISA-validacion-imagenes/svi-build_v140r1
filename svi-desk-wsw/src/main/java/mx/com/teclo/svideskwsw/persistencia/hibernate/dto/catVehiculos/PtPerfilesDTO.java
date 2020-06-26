package mx.com.teclo.svideskwsw.persistencia.hibernate.dto.catVehiculos;

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
@Table(name = "TCI013C_PT_PERFILES")
public class PtPerfilesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PT_PERFILES")
    private long idPtPerfiles;
   
    @Basic(optional = false)
    @Column(name = "CD_IDENTIFICACION")
    private String cdIdentificacion;
    
    @Basic(optional = false)
    @Column(name = "LB_PERFIL")
    private Serializable lbPerfil;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION")
    private String txDescripcion;
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

   

    public long getIdPtPerfiles() {
        return idPtPerfiles;
    }

    public void setIdPtPerfiles(long idPtPerfiles) {
        this.idPtPerfiles = idPtPerfiles;
    }

    public String getCdIdentificacion() {
        return cdIdentificacion;
    }

    public void setCdIdentificacion(String cdIdentificacion) {
        this.cdIdentificacion = cdIdentificacion;
    }

    public Serializable getLbPerfil() {
        return lbPerfil;
    }

    public void setLbPerfil(Serializable lbPerfil) {
        this.lbPerfil = lbPerfil;
    }

    public String getTxDescripcion() {
        return txDescripcion;
    }

    public void setTxDescripcion(String txDescripcion) {
        this.txDescripcion = txDescripcion;
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

