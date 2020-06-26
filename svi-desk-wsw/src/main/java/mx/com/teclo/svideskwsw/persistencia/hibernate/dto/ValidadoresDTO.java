package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "TCI007C_PT_VALIDADORES")
public class ValidadoresDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALIDADOR")
    private Long idValidador;
    
    @Basic(optional = false)
    @Column(name = "ST_VALIDADOR_ACTIVO")
    private short stValidadorActivo;
    
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
    
//    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
//    @ManyToOne(optional = false)
//    private Taq025cSeUsuarios idUsuario;
    
    @OneToMany(mappedBy = "idValidador")
    private Collection<ValidadoresConfigDTO> validadoresConfigCollection;

    public ValidadoresDTO() {
    }

    public ValidadoresDTO(Long idValidador) {
        this.idValidador = idValidador;
    }

    public ValidadoresDTO(Long idValidador, short stValidadorActivo, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idValidador = idValidador;
        this.stValidadorActivo = stValidadorActivo;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(Long idValidador) {
        this.idValidador = idValidador;
    }

    public short getStValidadorActivo() {
        return stValidadorActivo;
    }

    public void setStValidadorActivo(short stValidadorActivo) {
        this.stValidadorActivo = stValidadorActivo;
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

//    public Taq025cSeUsuarios getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(Taq025cSeUsuarios idUsuario) {
//        this.idUsuario = idUsuario;
//    }

    @XmlTransient
    public Collection<ValidadoresConfigDTO> getTvalidadoresConfigCollection() {
        return validadoresConfigCollection;
    }

    public void setValidadoresConfigCollection(Collection<ValidadoresConfigDTO> validadoresConfigCollection) {
        this.validadoresConfigCollection = validadoresConfigCollection;
    }

}

