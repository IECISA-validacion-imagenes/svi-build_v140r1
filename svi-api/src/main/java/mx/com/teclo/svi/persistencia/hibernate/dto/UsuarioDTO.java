package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "TAQ025C_SE_USUARIOS")

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Basic(optional = false)
    @Column(name = "CD_USUARIO")
    private String cdUsuario;
    @Basic(optional = false)
    @Column(name = "NB_CONTRASENIA")
    private String nbContrasenia;
    @Basic(optional = false)
    @Column(name = "NB_USUARIO")
    private String nbUsuario;
    @Basic(optional = false)
    @Column(name = "NB_APATERNO")
    private String nbApaterno;
    @Column(name = "NB_AMATERNO")
    private String nbAmaterno;
    @Column(name = "NB_EMAIL")
    private String nbEmail;
    @Column(name = "NU_TELEFONO")
    private Long nuTelefono;
    @Column(name = "ST_CONTRASENIA")
    private Short stContrasenia;
    @Column(name = "FH_MODIF_CONTRASENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModifContrasenia;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private short stActivo;
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private long idUsrCreacion;
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private long idUsrModifica;
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<ValidadoresDTO> validadoresCollection;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioDTO(Long idUsuario, String cdUsuario, String nbContrasenia, String nbUsuario, String nbApaterno, short stActivo, long idUsrCreacion, Date fhCreacion, long idUsrModifica, Date fhModificacion) {
        this.idUsuario = idUsuario;
        this.cdUsuario = cdUsuario;
        this.nbContrasenia = nbContrasenia;
        this.nbUsuario = nbUsuario;
        this.nbApaterno = nbApaterno;
        this.stActivo = stActivo;
        this.idUsrCreacion = idUsrCreacion;
        this.fhCreacion = fhCreacion;
        this.idUsrModifica = idUsrModifica;
        this.fhModificacion = fhModificacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(String cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNbContrasenia() {
        return nbContrasenia;
    }

    public void setNbContrasenia(String nbContrasenia) {
        this.nbContrasenia = nbContrasenia;
    }

    public String getNbUsuario() {
        return nbUsuario;
    }

    public void setNbUsuario(String nbUsuario) {
        this.nbUsuario = nbUsuario;
    }

    public String getNbApaterno() {
        return nbApaterno;
    }

    public void setNbApaterno(String nbApaterno) {
        this.nbApaterno = nbApaterno;
    }

    public String getNbAmaterno() {
        return nbAmaterno;
    }

    public void setNbAmaterno(String nbAmaterno) {
        this.nbAmaterno = nbAmaterno;
    }

    public String getNbEmail() {
        return nbEmail;
    }

    public void setNbEmail(String nbEmail) {
        this.nbEmail = nbEmail;
    }

    public Long getNuTelefono() {
        return nuTelefono;
    }

    public void setNuTelefono(Long nuTelefono) {
        this.nuTelefono = nuTelefono;
    }

    public Short getStContrasenia() {
        return stContrasenia;
    }

    public void setStContrasenia(Short stContrasenia) {
        this.stContrasenia = stContrasenia;
    }

    public Date getFhModifContrasenia() {
        return fhModifContrasenia;
    }

    public void setFhModifContrasenia(Date fhModifContrasenia) {
        this.fhModifContrasenia = fhModifContrasenia;
    }
    
	public short getStActivo() {
        return stActivo;
    }

    public void setStActivo(short stActivo) {
        this.stActivo = stActivo;
    }

    public long getIdUsrCreacion() {
        return idUsrCreacion;
    }

    public void setIdUsrCreacion(long idUsrCreacion) {
        this.idUsrCreacion = idUsrCreacion;
    }

    public Date getFhCreacion() {
        return fhCreacion;
    }

    public void setFhCreacion(Date fhCreacion) {
        this.fhCreacion = fhCreacion;
    }

    public long getIdUsrModifica() {
        return idUsrModifica;
    }

    public void setIdUsrModifica(long idUsrModifica) {
        this.idUsrModifica = idUsrModifica;
    }

    public Date getFhModificacion() {
        return fhModificacion;
    }

    public void setFhModificacion(Date fhModificacion) {
        this.fhModificacion = fhModificacion;
    }

    @XmlTransient
    public Collection<ValidadoresDTO> getValidadoresCollection() {
        return validadoresCollection;
    }

    public void setvalidadoresCollection(Collection<ValidadoresDTO> validadoresCollection) {
        this.validadoresCollection = validadoresCollection;
    }
    
}
