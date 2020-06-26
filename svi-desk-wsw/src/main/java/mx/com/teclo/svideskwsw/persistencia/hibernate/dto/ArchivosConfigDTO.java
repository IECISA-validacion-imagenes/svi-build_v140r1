package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI008C_PT_ARCHIVOS_CONFIG")

public class ArchivosConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIGURACION")
    private Long idConfiguracion;
    
    @Basic(optional = false)
    @Column(name = "CD_CONFIGURACION")
    private String cdConfiguracion;
    
    @Basic(optional = false)
    @Column(name = "TX_CONFIGURACION")
    private String txConfiguracion;
    
    @Column(name = "NU_MIN")
    private Long nuMin;
    
    @Column(name = "NU_MAX")
    private Long nuMax;
    
    @Column(name = "NU_LIM_ASIGNACION")
    private Long nuLimAsignacion;
    
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
    
    @OneToMany(mappedBy = "idConfiguracion")
    private Collection<ValidadoresConfigDTO> validadoresConfigCollection;

    public ArchivosConfigDTO() {
    }

    public ArchivosConfigDTO(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public ArchivosConfigDTO(Long idConfiguracion, String cdConfiguracion, String txConfiguracion, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idConfiguracion = idConfiguracion;
        this.cdConfiguracion = cdConfiguracion;
        this.txConfiguracion = txConfiguracion;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getCdConfiguracion() {
        return cdConfiguracion;
    }

    public void setCdConfiguracion(String cdConfiguracion) {
        this.cdConfiguracion = cdConfiguracion;
    }

    public String getTxConfiguracion() {
        return txConfiguracion;
    }

    public void setTxConfiguracion(String txConfiguracion) {
        this.txConfiguracion = txConfiguracion;
    }

    public Long getNuMin() {
        return nuMin;
    }

    public void setNuMin(Long nuMin) {
        this.nuMin = nuMin;
    }

    public Long getNuMax() {
        return nuMax;
    }

    public void setNuMax(Long nuMax) {
        this.nuMax = nuMax;
    }

    public Long getNuLimAsignacion() {
        return nuLimAsignacion;
    }

    public void setNuLimAsignacion(Long nuLimAsignacion) {
        this.nuLimAsignacion = nuLimAsignacion;
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

    @XmlTransient
    public Collection<ValidadoresConfigDTO> getValidadoresConfigCollection() {
        return validadoresConfigCollection;
    }

    public void setValidadoresConfigCollection(Collection<ValidadoresConfigDTO> validadoresConfigCollection) {
        this.validadoresConfigCollection = validadoresConfigCollection;
    }

}
