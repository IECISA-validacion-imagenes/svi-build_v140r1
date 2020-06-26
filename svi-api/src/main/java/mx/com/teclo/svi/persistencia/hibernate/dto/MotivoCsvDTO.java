package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.FetchMode;

@Entity
@Table(name = "TCI021D_PT_MOTIVO_CSV")
public class MotivoCsvDTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1739098059259177544L;

	@SequenceGenerator(name = "SEQPTMOTIVOCSVSEQ", sequenceName="PT_MOTIVO_CSV_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTMOTIVOCSVSEQ")
	@Id
    @Column(name = "ID_MOTIVO_CSV")
    private Long idMotivoCsv;
    
    @JoinColumn(name = "ID_ARCHIVO_CSV", referencedColumnName = "ID_ARCHIVO_CSV")
    @ManyToOne(fetch= FetchType.LAZY)
    private ArchivoCsvDTO idArchivoCsv;
    
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(fetch= FetchType.LAZY)
    private ValidadoresDTO idValidador;
    
    @JoinColumn(name = "ID_MOTIVO_REVA", referencedColumnName = "ID_MOTIVO_REVA")
    @ManyToOne(fetch= FetchType.LAZY)
    private MotivoRevalidaDTO idMotivoReva;
    
    @Column(name = "ST_ACTIVO")
    private Boolean stActivo;
    
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Column(name = "ID_USR_CREACION")
    private Long idUsrCreacion;
        
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    @Column(name = "ID_USR_MODIFICA")
    private Long idUsrModifica;
    
    @JoinColumn(name = "ID_CICLO_VALIDACION", referencedColumnName = "ID_CICLO_VALIDACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CicloValidacionDTO idCicloValidacion;
    
	public Long getIdMotivoCsv() {
		return idMotivoCsv;
	}

	public CicloValidacionDTO getIdCicloValidacion() {
		return idCicloValidacion;
	}

	public void setIdCicloValidacion(CicloValidacionDTO idCicloValidacion) {
		this.idCicloValidacion = idCicloValidacion;
	}

	public void setIdMotivoCsv(Long idMotivoCsv) {
		this.idMotivoCsv = idMotivoCsv;
	}

	public ArchivoCsvDTO getIdArchivoCsv() {
		return idArchivoCsv;
	}

	public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}

	public ValidadoresDTO getIdValidador() {
		return idValidador;
	}

	public void setIdValidador(ValidadoresDTO idValidador) {
		this.idValidador = idValidador;
	}

	public MotivoRevalidaDTO getIdMotivoReva() {
		return idMotivoReva;
	}

	public void setIdMotivoReva(MotivoRevalidaDTO idMotivoReva) {
		this.idMotivoReva = idMotivoReva;
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
}
