package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI003D_PT_ARCHIVO_CSV")
public class ArchivoCsvDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String QUERY_ESTADO="CASE WHEN ST_ACTIVO = 0 THEN 'INACTIVO' WHEN ST_REVALIDACION = 2 THEN 'REVALIDADO' WHEN ST_REVALIDACION = 1 THEN 'REASIGNADO' WHEN ST_VALIDACION = 1 THEN 'VALIDADO' WHEN ST_VALIDACION = 0 THEN 'PENDIENTE' END";
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ARCHIVO_CSV")
    private Long idArchivoCsv;
    @Column(name = "NB_ARCHIVO_CSV")
    private String nbArchivoCsv;
    @Column(name = "NU_REGISTROS_CSV")
    private Long nuRegistrosCsv;
    @Column(name = "NB_CARPETA_IMG")
    private String nbCarpetaImg;
    @Column(name = "TX_CARPETA_IMG")
    private String txCarpetaImg;
    @Column(name = "NU_IMAGENES")
    private Long nuImagenes;
    @Column(name = "NB_CARPETA_SIL")
    private String nbCarpetaSil;
    @Column(name = "TX_CARPETA_SIL")
    private String txCarpetaSil;
    @Column(name = "NU_SILUETAS")
    private Long nuSiluetas;
    @Basic(optional = false)
    @Column(name = "ST_VALIDACION")
    private short stValidacion;
    @Column(name = "ST_REVALIDACION")
    private short stRevalidacion;
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
    @Formula(value=QUERY_ESTADO)
    private String nbEstado;
      
    @JoinColumn(name = "ID_PT_LOTE", referencedColumnName = "ID_PT_LOTE")
    @ManyToOne(optional = false)
    private LoteDTO idPtLote;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idArchivoCsv")
    private Collection<MotivoCsvDTO> reasignsCollection;
    
    public Long getIdArchivoCsv() {
        return idArchivoCsv;
    }

    public void setIdArchivoCsv(Long idArchivoCsv) {
        this.idArchivoCsv = idArchivoCsv;
    }

    public String getNbArchivoCsv() {
        return nbArchivoCsv;
    }

    public void setNbArchivoCsv(String nbArchivoCsv) {
        this.nbArchivoCsv = nbArchivoCsv;
    }

    public Long getNuRegistrosCsv() {
        return nuRegistrosCsv;
    }

    public void setNuRegistrosCsv(Long nuRegistrosCsv) {
        this.nuRegistrosCsv = nuRegistrosCsv;
    }

    public String getNbCarpetaImg() {
        return nbCarpetaImg;
    }

    public void setNbCarpetaImg(String nbCarpetaImg) {
        this.nbCarpetaImg = nbCarpetaImg;
    }

    public String getTxCarpetaImg() {
        return txCarpetaImg;
    }

    public void setTxCarpetaImg(String txCarpetaImg) {
        this.txCarpetaImg = txCarpetaImg;
    }

    public Long getNuImagenes() {
        return nuImagenes;
    }

    public void setNuImagenes(Long nuImagenes) {
        this.nuImagenes = nuImagenes;
    }

    public String getNbCarpetaSil() {
        return nbCarpetaSil;
    }

    public void setNbCarpetaSil(String nbCarpetaSil) {
        this.nbCarpetaSil = nbCarpetaSil;
    }

    public String getTxCarpetaSil() {
        return txCarpetaSil;
    }

    public void setTxCarpetaSil(String txCarpetaSil) {
        this.txCarpetaSil = txCarpetaSil;
    }

    public Long getNuSiluetas() {
        return nuSiluetas;
    }

    public void setNuSiluetas(Long nuSiluetas) {
        this.nuSiluetas = nuSiluetas;
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

    public LoteDTO getIdPtLote() {
        return idPtLote;
    }

    public void setIdPtLote(LoteDTO idPtLote) {
        this.idPtLote = idPtLote;
    }

	public String getNbEstado() {
		return nbEstado;
	}

	public void setNbEstado(String nbEstado) {
		this.nbEstado = nbEstado;
	}

	public Collection<MotivoCsvDTO> getReasignsCollection() {
		return reasignsCollection;
	}

	public void setReasignsCollection(Collection<MotivoCsvDTO> reasignsCollection) {
		this.reasignsCollection = reasignsCollection;
	}
   

}

