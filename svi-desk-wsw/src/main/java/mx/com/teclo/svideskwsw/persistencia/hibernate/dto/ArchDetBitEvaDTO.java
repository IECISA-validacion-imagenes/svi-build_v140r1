package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TCI006D_PT_ARCHIVO_DET_BIT_EVA")
public class ArchDetBitEvaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REG_BIT_EVA")
    private Long idRegBitEva;
    
    @Column(name = "CD_PLACA_DELANTERA")
    private String cdPlacaDelantera;
    
    @Column(name = "CD_ENTIDAD_DELANTERA")
    private String cdEntidadDelantera;
    
    @Column(name = "CD_PLACA_TRASERA")
    private String cdPlacaTrasera;
    
    @Column(name = "CD_ENTIDAD_TRASERA")
    private String cdEntidadTrasera;
    
    @Column(name = "CD_PERFIL")
    private String cdPerfil;
    
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
    
    @JoinColumn(name = "ID_ARCHIVO_CSV", referencedColumnName = "ID_ARCHIVO_CSV")
    @ManyToOne(optional = false)
    private ArchivoCsvDTO idArchivoCsv;
    
    @JoinColumn(name = "ID_REGISTRO_CSV", referencedColumnName = "ID_REGISTRO_CSV")
    @ManyToOne(optional = false)
    private ArchivoDetalleDTO idRegistroCsv;

    public ArchDetBitEvaDTO() {
    }

    public ArchDetBitEvaDTO(Long idRegBitEva) {
        this.idRegBitEva = idRegBitEva;
    }

    public ArchDetBitEvaDTO(Long idRegBitEva, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idRegBitEva = idRegBitEva;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdRegBitEva() {
        return idRegBitEva;
    }

    public void setIdRegBitEva(Long idRegBitEva) {
        this.idRegBitEva = idRegBitEva;
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

    public String getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(String cdPerfil) {
        this.cdPerfil = cdPerfil;
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

    public ArchivoCsvDTO getIdArchivoCsv() {
        return idArchivoCsv;
    }

    public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
        this.idArchivoCsv = idArchivoCsv;
    }

    public ArchivoDetalleDTO getIdRegistroCsv() {
        return idRegistroCsv;
    }

    public void setIdRegistroCsv(ArchivoDetalleDTO idRegistroCsv) {
        this.idRegistroCsv = idRegistroCsv;
    }
   
}
