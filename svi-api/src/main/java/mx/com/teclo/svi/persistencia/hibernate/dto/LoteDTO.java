package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI002D_PT_LOTE")
public class LoteDTO implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8865733457156869359L;
	private static final String QUERY_ESTADO="CASE WHEN ST_ACTIVO = 0 THEN 'INACTIVO' WHEN ST_REVALIDACION = 2 THEN 'REVALIDADO' WHEN ST_REVALIDACION = 1 THEN 'REASIGNADO' WHEN ST_VALIDACION = 1 THEN 'VALIDADO' WHEN ST_VALIDACION = 0 THEN 'PENDIENTE' END";
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PT_LOTE")
    private Long idPtLote;
    
    @Basic(optional = false)
    @Column(name = "NB_PT_LOTE")
    private String nbPtLote;
    
    @Basic(optional = false)
    @Column(name = "ST_ENTREGA")
    private short stEntrega;
    
    @Basic(optional = false)
    @Column(name = "ST_VALIDACION")
    private short stValidacion;
    
    @Basic(optional = false)
    @Column(name = "ST_REVALIDACION")
    private short stRevalidacion;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private short stActivo;
    
    @Basic(optional = false)
    @Column(name = "NU_CARPETAS_IMG")
    private long nuCarpetasImg;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_IMG")
    private long nuTotalRegImg;
    
    @Basic(optional = false)
    @Column(name = "NU_ARCHIVOS_CSV")
    private long nuArchivosCsv;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_CSV")
    private long nuTotalRegCsv;
    
    @Basic(optional = false)
    @Column(name = "NU_CARPETAS_SIL")
    private long nuCarpetasSil;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_SIL")
    private long nuTotalRegSil;
    
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
    
    @Formula(value=QUERY_ESTADO)
    private String nbEstado;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtLote")
//    private Collection<ArchivoCsvDTO> archivoCsvCollection;
    
    @JoinColumn(name = "ID_ENTREGA", referencedColumnName = "ID_ENTREGA")
    @ManyToOne(optional = false)
    private EntregaDTO idEntrega;

    public LoteDTO() {
    }

    public LoteDTO(Long idPtLote) {
        this.idPtLote = idPtLote;
    }

    public LoteDTO(Long idPtLote, String nbPtLote, short stEntrega, short stValidacion, short stActivo, long nuCarpetasImg, long nuTotalRegImg, long nuArchivosCsv, long nuTotalRegCsv, long nuCarpetasSil, long nuTotalRegSil, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idPtLote = idPtLote;
        this.nbPtLote = nbPtLote;
        this.stEntrega = stEntrega;
        this.stValidacion = stValidacion;
        this.stActivo = stActivo;
        this.nuCarpetasImg = nuCarpetasImg;
        this.nuTotalRegImg = nuTotalRegImg;
        this.nuArchivosCsv = nuArchivosCsv;
        this.nuTotalRegCsv = nuTotalRegCsv;
        this.nuCarpetasSil = nuCarpetasSil;
        this.nuTotalRegSil = nuTotalRegSil;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdPtLote() {
        return idPtLote;
    }

    public void setIdPtLote(Long idPtLote) {
        this.idPtLote = idPtLote;
    }

    public String getNbPtLote() {
        return nbPtLote;
    }

    public void setNbPtLote(String nbPtLote) {
        this.nbPtLote = nbPtLote;
    }

    public short getStEntrega() {
        return stEntrega;
    }

    public void setStEntrega(short stEntrega) {
        this.stEntrega = stEntrega;
    }

    public short getStValidacion() {
        return stValidacion;
    }

    public void setStValidacion(short stValidacion) {
        this.stValidacion = stValidacion;
    }
    public short getStRevalidacion() {
        return stRevalidacion;
    }

    public void setStRevalidacion(short stRevalidacion) {
        this.stRevalidacion = stRevalidacion;
    }

    public short getStActivo() {
        return stActivo;
    }

    public void setStActivo(short stActivo) {
        this.stActivo = stActivo;
    }

    public long getNuCarpetasImg() {
        return nuCarpetasImg;
    }

    public void setNuCarpetasImg(long nuCarpetasImg) {
        this.nuCarpetasImg = nuCarpetasImg;
    }

    public long getNuTotalRegImg() {
        return nuTotalRegImg;
    }

    public void setNuTotalRegImg(long nuTotalRegImg) {
        this.nuTotalRegImg = nuTotalRegImg;
    }

    public long getNuArchivosCsv() {
        return nuArchivosCsv;
    }

    public void setNuArchivosCsv(long nuArchivosCsv) {
        this.nuArchivosCsv = nuArchivosCsv;
    }

    public long getNuTotalRegCsv() {
        return nuTotalRegCsv;
    }

    public void setNuTotalRegCsv(long nuTotalRegCsv) {
        this.nuTotalRegCsv = nuTotalRegCsv;
    }

    public long getNuCarpetasSil() {
        return nuCarpetasSil;
    }

    public void setNuCarpetasSil(long nuCarpetasSil) {
        this.nuCarpetasSil = nuCarpetasSil;
    }

    public long getNuTotalRegSil() {
        return nuTotalRegSil;
    }

    public void setNuTotalRegSil(long nuTotalRegSil) {
        this.nuTotalRegSil = nuTotalRegSil;
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

//    @XmlTransient
//    public Collection<ArchivoCsvDTO> getArchivoCsvCollection() {
//        return archivoCsvCollection;
//    }
//
//    public void setArchivoCsvCollection(Collection<ArchivoCsvDTO> archivoCsvCollection) {
//        this.archivoCsvCollection = archivoCsvCollection;
//    }

    public EntregaDTO getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(EntregaDTO idEntrega) {
        this.idEntrega = idEntrega;
    }

	public String getNbEstado() {
		return nbEstado;
	}

	public void setNbEstado(String nbEstado) {
		this.nbEstado = nbEstado;
	}

	public static String getQueryEstado() {
		return QUERY_ESTADO;
	}
    
    

}
