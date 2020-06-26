package mx.com.teclo.svi.persistencia.hibernate.dto;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI010D_PT_ASIGN_VALIDADOR")
public class AsignValidadorDTO implements Serializable {

    

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 984075196688387523L;

	@Id
    @Basic(optional = false)
    @Column(name = "ID_ASIGN_VALIDADOR")
    @SequenceGenerator(name = "ptAsignSec", sequenceName="PT_ASIGN_VALIDADOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ptAsignSec")
    private Long idAsignValidador;
    
    @Column(name = "ID_PT_LOTE")
    private Long idPtLote;
    
    @Column(name = "ID_ARCHIVO_CSV")
    private Long idArchivoCsv;
    
    @Column(name = "ST_VALIDACION")
    private Short stValidacion;
    

    @Column(name = "ST_ACTIVO")
    private short stActivo;
    
   
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
   
    @Column(name = "ID_USR_CREACION")
    private long idUsrCreacion;
    
    
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private long idUsrModifica;
    
    @JoinColumn(name = "ID_VALIDADOR_CONFIG", referencedColumnName = "ID_VALIDADOR_CONFIG")
    @ManyToOne(optional = false)
    private ValidadoresConfigDTO idValidadorConfig;

    public AsignValidadorDTO() {
    }

    public AsignValidadorDTO(Long idAsignValidador) {
        this.idAsignValidador = idAsignValidador;
    }

    public AsignValidadorDTO(Long idAsignValidador, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idAsignValidador = idAsignValidador;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdAsignValidador() {
        return idAsignValidador;
    }

    public void setIdAsignValidador(Long idAsignValidador) {
        this.idAsignValidador = idAsignValidador;
    }

    public Long getIdPtLote() {
        return idPtLote;
    }

    public void setIdPtLote(Long idPtLote) {
        this.idPtLote = idPtLote;
    }

    public Long getIdArchivoCsv() {
        return idArchivoCsv;
    }

    public void setIdArchivoCsv(Long idArchivoCsv) {
        this.idArchivoCsv = idArchivoCsv;
    }

    public Short getStValidacion() {
        return stValidacion;
    }

    public void setStValidacion(Short stValidacion) {
        this.stValidacion = stValidacion;
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

    public ValidadoresConfigDTO getIdValidadorConfig() {
        return idValidadorConfig;
    }

    public void setIdValidadorConfig(ValidadoresConfigDTO idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }
   
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
