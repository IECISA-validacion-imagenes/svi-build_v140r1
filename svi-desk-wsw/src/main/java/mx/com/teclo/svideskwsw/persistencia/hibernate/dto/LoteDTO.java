package mx.com.teclo.svideskwsw.persistencia.hibernate.dto;

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

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI002D_PT_LOTE")
public class LoteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PT_LOTE")
    private Long idPtLote;
    
    @Basic(optional = false)
    @Column(name = "NB_PT_LOTE")
    private String nbPtLote;
    
    @Basic(optional = false)
    @Column(name = "ST_ENTREGA")
    private Boolean stEntrega;
    
    @Basic(optional = false)
    @Column(name = "ST_VALIDACION")
    private Boolean stValidacion;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Boolean stActivo;
    
    @Basic(optional = false)
    @Column(name = "NU_CARPETAS_IMG")
    private Long nuCarpetasImg;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_IMG")
    private Long nuTotalRegImg;
    
    @Basic(optional = false)
    @Column(name = "NU_ARCHIVOS_CSV")
    private Long nuArchivosCsv;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_CSV")
    private Long nuTotalRegCsv;
    
    @Basic(optional = false)
    @Column(name = "NU_CARPETAS_SIL")
    private Long nuCarpetasSil;
    
    @Basic(optional = false)
    @Column(name = "NU_TOTAL_REG_SIL")
    private Long nuTotalRegSil;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private Long idUsrCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private Long idUsrModifica;
    
    @Column(name = "ST_REVALIDACION")
    private long stRevalidacion;
    
    
    @JoinColumn(name = "ID_ENTREGA", referencedColumnName = "ID_ENTREGA")
    @ManyToOne(optional = false)
    private EntregaDTO idEntrega;
    
    /* Columna para medir progreso de carga CSV */
    @Basic(optional = false)
    @Column(name = "ST_CARGADO")
    private Long statusCarga;
    
    @Basic(optional = false)
    @Column(name = "NU_TOT_REG_LOTE")
    private Long nuTotalRegistrosxLote;
    
    
    
	/**
	 * @return the nuTotalRegistrosxLote
	 */
	public Long getNuTotalRegistrosxLote() {
		return nuTotalRegistrosxLote;
	}

	/**
	 * @param nuTotalRegistrosxLote the nuTotalRegistrosxLote to set
	 */
	public void setNuTotalRegistrosxLote(Long nuTotalRegistrosxLote) {
		this.nuTotalRegistrosxLote = nuTotalRegistrosxLote;
	}

	/**
	 * @return the statusCarga
	 */
	public Long getStatusCarga() {
		return statusCarga;
	}

	/**
	 * @param statusCarga the statusCarga to set
	 */
	public void setStatusCarga(Long statusCarga) {
		this.statusCarga = statusCarga;
	}

	/**
	 * @return the stRevalidacion
	 */
	public long getStRevalidacion() {
		return stRevalidacion;
	}

	/**
	 * @param stRevalidacion the stRevalidacion to set
	 */
	public void setStRevalidacion(long stRevalidacion) {
		this.stRevalidacion = stRevalidacion;
	}

	

//    public LoteDTO() {
//    }
//
//    public LoteDTO(Long idPtLote) {
//        this.idPtLote = idPtLote;
//    }
//
//    public LoteDTO(Long idPtLote, String nbPtLote, Boolean stEntrega, Boolean stValidacion, Boolean stActivo, Long nuCarpetasImg, Long nuTotalRegImg, Long nuArchivosCsv, Long nuTotalRegCsv, Long nuCarpetasSil, Long nuTotalRegSil, Date fhCreacion, Long idUsrCreacion, Date fhModificacion, Long idUsrModifica) {
//        this.idPtLote = idPtLote;
//        this.nbPtLote = nbPtLote;
//        this.stEntrega = stEntrega;
//        this.stValidacion = stValidacion;
//        this.stActivo = stActivo;
//        this.nuCarpetasImg = nuCarpetasImg;
//        this.nuTotalRegImg = nuTotalRegImg;
//        this.nuArchivosCsv = nuArchivosCsv;
//        this.nuTotalRegCsv = nuTotalRegCsv;
//        this.nuCarpetasSil = nuCarpetasSil;
//        this.nuTotalRegSil = nuTotalRegSil;
//        this.fhCreacion = fhCreacion;
//        this.idUsrCreacion = idUsrCreacion;
//        this.fhModificacion = fhModificacion;
//        this.idUsrModifica = idUsrModifica;
//    }

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

    public Boolean getStEntrega() {
        return stEntrega;
    }

    public void setStEntrega(Boolean stEntrega) {
        this.stEntrega = stEntrega;
    }

    public Boolean getStValidacion() {
        return stValidacion;
    }

    public void setStValidacion(Boolean stValidacion) {
        this.stValidacion = stValidacion;
    }

    public Boolean getStActivo() {
        return stActivo;
    }

    public void setStActivo(Boolean stActivo) {
        this.stActivo = stActivo;
    }

    public Long getNuCarpetasImg() {
        return nuCarpetasImg;
    }

    public void setNuCarpetasImg(Long nuCarpetasImg) {
        this.nuCarpetasImg = nuCarpetasImg;
    }

    public Long getNuTotalRegImg() {
        return nuTotalRegImg;
    }

    public void setNuTotalRegImg(Long nuTotalRegImg) {
        this.nuTotalRegImg = nuTotalRegImg;
    }

    public Long getNuArchivosCsv() {
        return nuArchivosCsv;
    }

    public void setNuArchivosCsv(Long nuArchivosCsv) {
        this.nuArchivosCsv = nuArchivosCsv;
    }

    public Long getNuTotalRegCsv() {
        return nuTotalRegCsv;
    }

    public void setNuTotalRegCsv(Long nuTotalRegCsv) {
        this.nuTotalRegCsv = nuTotalRegCsv;
    }

    public Long getNuCarpetasSil() {
        return nuCarpetasSil;
    }

    public void setNuCarpetasSil(Long nuCarpetasSil) {
        this.nuCarpetasSil = nuCarpetasSil;
    }

    public Long getNuTotalRegSil() {
        return nuTotalRegSil;
    }

    public void setNuTotalRegSil(Long nuTotalRegSil) {
        this.nuTotalRegSil = nuTotalRegSil;
    }

    public Date getFhCreacion() {
        return fhCreacion;
    }

    public void setFhCreacion(Date fhCreacion) {
        this.fhCreacion = fhCreacion;
    }

    public Long getIdUsrCreacion() {
        return idUsrCreacion;
    }

    public void setIdUsrCreacion(Long idUsrCreacion) {
        this.idUsrCreacion = idUsrCreacion;
    }

    public Date getFhModificacion() {
        return fhModificacion;
    }

    public void setFhModificacion(Date fhModificacion) {
        this.fhModificacion = fhModificacion;
    }

    public Long getIdUsrModifica() {
        return idUsrModifica;
    }

    public void setIdUsrModifica(Long idUsrModifica) {
        this.idUsrModifica = idUsrModifica;
    }

    public EntregaDTO getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(EntregaDTO idEntrega) {
        this.idEntrega = idEntrega;
    }

}
