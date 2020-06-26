package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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

@Entity
@Table(name = "TCI026D_PT_DETALLE_REVALIDA")
public class DetalleRevalidaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "SEQPTDETALLEREVALIDACION", sequenceName="PT_DETALLE_REVALIDACION_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTDETALLEREVALIDACION")
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_REVALIDACION")
    private Long idDetalleReval;
	

    @JoinColumn(name = "ID_REGISTRO_CSV", referencedColumnName = "ID_REGISTRO_CSV")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ArchivoDetalleEvaDTO idRegistroCSV;
    
    @JoinColumn(name = "ID_MOTIVO_CSV", referencedColumnName = "ID_MOTIVO_CSV")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MotivoCsvDTO idMotivoCSV;
    
    @JoinColumn(name = "ID_CICLO_VALIDACION", referencedColumnName = "ID_CICLO_VALIDACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CicloValidacionDTO idCicloValidacion;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Boolean stActivo;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    private Date fechaCreacion ;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private Long idUserCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    private Date fechaModificacion ;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private Long idUserModifica ;

	public Long getIdDetalleReval() {
		return idDetalleReval;
	}

	public void setIdDetalleReval(Long idDetalleReval) {
		this.idDetalleReval = idDetalleReval;
	}

	public ArchivoDetalleEvaDTO getIdRegistroCSV() {
		return idRegistroCSV;
	}

	public void setIdRegistroCSV(ArchivoDetalleEvaDTO idRegistroCSV) {
		this.idRegistroCSV = idRegistroCSV;
	}

	public MotivoCsvDTO getIdMotivoCSV() {
		return idMotivoCSV;
	}

	public void setIdMotivoCSV(MotivoCsvDTO idMotivoCSV) {
		this.idMotivoCSV = idMotivoCSV;
	}

	public CicloValidacionDTO getIdCicloValidacion() {
		return idCicloValidacion;
	}

	public void setIdCicloValidacion(CicloValidacionDTO idCicloValidacion) {
		this.idCicloValidacion = idCicloValidacion;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getIdUserModifica() {
		return idUserModifica;
	}

	public void setIdUserModifica(Long idUserModifica) {
		this.idUserModifica = idUserModifica;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    

    
    
	
    
}
