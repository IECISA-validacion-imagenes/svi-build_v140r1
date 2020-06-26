package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TCI009D_PT_VALIDADORES_CONFIG")

public class ValidadoresConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALIDADOR_CONFIG")
    private Long idValidadorConfig;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValidadorConfig")
    private Collection<AsignValidadorDTO> asignValidadorCollection;
    
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne
    private ValidadoresDTO idValidador;
    
    @JoinColumn(name = "ID_CONFIGURACION", referencedColumnName = "ID_CONFIGURACION")
    @ManyToOne
    private ArchivosConfigDTO idConfiguracion;
    

    public ValidadoresConfigDTO() {
    }

    public ValidadoresConfigDTO(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public ValidadoresConfigDTO(Long idValidadorConfig, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idValidadorConfig = idValidadorConfig;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdValidadorConfig() {
        return idValidadorConfig;
    }

    public void setIdValidadorConfig(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
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
    public Collection<AsignValidadorDTO> getAsignValidadorCollection() {
        return asignValidadorCollection;
    }

    public void setTci010dPtAsignValidadorCollection(Collection<AsignValidadorDTO> asignValidadorCollection) {
        this.asignValidadorCollection = asignValidadorCollection;
    }

    public ValidadoresDTO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresDTO idValidador) {
        this.idValidador = idValidador;
    }

    public ArchivosConfigDTO getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(ArchivosConfigDTO idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
}
