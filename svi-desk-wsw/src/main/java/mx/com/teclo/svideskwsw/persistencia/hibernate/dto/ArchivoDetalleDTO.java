package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI004D_PT_ARCHIVO_DETALLE")
public class ArchivoDetalleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQPTDETALLE", sequenceName="PT_ARCHIVO_DETALLE_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTDETALLE")
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_CSV")
    private Long idRegistroCsv;
    @Column(name = "NODO_VPN")
    private Long nodoVpn;
    @Column(name = "NU_EXPEDIENTE")
    private String nuExpediente;
    @Column(name = "FH_GENERACION")
    private String fhGeneracion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NU_CARRIL")
    private long nuCarril;
    @Column(name = "CD_PLACA_DELANTERA")
    private String cdPlacaDelantera;
    @Column(name = "CD_ENTIDAD_DELANTERA")
    private String cdEntidadDelantera;
    @Column(name = "CD_PLACA_TRASERA")
    private String cdPlacaTrasera;
    @Column(name = "CD_ENTIDAD_TRASERA")
    private String cdEntidadTrasera;
    @Column(name = "NB_IMAGEN_PLACA_DELANTERA")
    private String nbImagenPlacaDelantera;
    @Column(name = "NB_IMAGEN_PLACA_TRASERA")
    private String nbImagenPlacaTrasera;
    @Column(name = "NB_IMAGEN_CONDUCTOR")
    private String nbImagenConductor;
    @Column(name = "NB_IMAGEN_AMBIENTAL")
    private String nbImagenAmbiental;
    @Column(name = "CD_PERFIL")
    private String cdPerfil;
    @Column(name = "NB_IMAGEN_PERFIL")
    private String nbImagenPerfil;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Boolean stActivo;
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private long idUsrCreacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistroCsv")
    private Collection<ArchDetBitEvaDTO> archivoDetBitEvaCollection;
    
    @JoinColumn(name = "ID_ARCHIVO_CSV", referencedColumnName = "ID_ARCHIVO_CSV")
    @ManyToOne(optional = false)
    private ArchivoCsvDTO idArchivoCsv;

    public ArchivoDetalleDTO() {
    }

    public ArchivoDetalleDTO(Long idRegistroCsv) {
        this.idRegistroCsv = idRegistroCsv;
    }

    public ArchivoDetalleDTO(Long idRegistroCsv, Boolean stActivo, Date fhCreacion, long idUsrCreacion) {
        this.idRegistroCsv = idRegistroCsv;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
    }

    public Long getIdRegistroCsv() {
        return idRegistroCsv;
    }

    public void setIdRegistroCsv(Long idRegistroCsv) {
        this.idRegistroCsv = idRegistroCsv;
    }

    public Long getNodoVpn() {
        return nodoVpn;
    }

    public void setNodoVpn(Long nodoVpn) {
        this.nodoVpn = nodoVpn;
    }

    public String getNuExpediente() {
        return nuExpediente;
    }

    public void setNuExpediente(String nuExpediente) {
        this.nuExpediente = nuExpediente;
    }

    public String getFhGeneracion() {
        return fhGeneracion;
    }

    public void setFhGeneracion(String fhGeneracion) {
        this.fhGeneracion = fhGeneracion;
    }

    public long getNuCarril() {
        return nuCarril;
    }

    public void setNuCarril(long nuCarril) {
        this.nuCarril = nuCarril;
    }

    public String getCdPlacaDelantera() {
        return cdPlacaDelantera;
    }

    public void setCdPlacaDelantera(String cdPlacaDelantera) {
        this.cdPlacaDelantera = cdPlacaDelantera;
    }

    public String getCdEntidadDelantera() {
        return cdEntidadDelantera;
    }

    public void setCdEntidadDelantera(String cdEntidadDelantera) {
        this.cdEntidadDelantera = cdEntidadDelantera;
    }

    public String getCdPlacaTrasera() {
        return cdPlacaTrasera;
    }

    public void setCdPlacaTrasera(String cdPlacaTrasera) {
        this.cdPlacaTrasera = cdPlacaTrasera;
    }

    public String getCdEntidadTrasera() {
        return cdEntidadTrasera;
    }

    public void setCdEntidadTrasera(String cdEntidadTrasera) {
        this.cdEntidadTrasera = cdEntidadTrasera;
    }

    public String getNbImagenPlacaDelantera() {
        return nbImagenPlacaDelantera;
    }

    public void setNbImagenPlacaDelantera(String nbImagenPlacaDelantera) {
        this.nbImagenPlacaDelantera = nbImagenPlacaDelantera;
    }

    public String getNbImagenPlacaTrasera() {
        return nbImagenPlacaTrasera;
    }

    public void setNbImagenPlacaTrasera(String nbImagenPlacaTrasera) {
        this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
    }

    public String getNbImagenConductor() {
        return nbImagenConductor;
    }

    public void setNbImagenConductor(String nbImagenConductor) {
        this.nbImagenConductor = nbImagenConductor;
    }

    public String getNbImagenAmbiental() {
        return nbImagenAmbiental;
    }

    public void setNbImagenAmbiental(String nbImagenAmbiental) {
        this.nbImagenAmbiental = nbImagenAmbiental;
    }

    public String getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(String cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public String getNbImagenPerfil() {
        return nbImagenPerfil;
    }

    public void setNbImagenPerfil(String nbImagenPerfil) {
        this.nbImagenPerfil = nbImagenPerfil;
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

    @XmlTransient
    public Collection<ArchDetBitEvaDTO> getArchivoDetBitEvaCollection() {
        return archivoDetBitEvaCollection;
    }

    public void setArchDetBitEvaCollection(Collection<ArchDetBitEvaDTO> archivoDetBitEvaCollection) {
        this.archivoDetBitEvaCollection = archivoDetBitEvaCollection;
    }

    public ArchivoCsvDTO getIdArchivoCsv() {
        return idArchivoCsv;
    }

    public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
        this.idArchivoCsv = idArchivoCsv;
    }
}
