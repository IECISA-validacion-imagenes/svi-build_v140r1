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
@Table(name = "TCI027D_PT_MOTIVO_DET_REVAL")
public class MotivoDetRevalDTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9057654187073643600L;


	@Id
    @SequenceGenerator(name = "SEQPTMOTIVODETREVAL", sequenceName="PT_MOTIVO_DET_REVAL_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTMOTIVODETREVAL")
    @Basic(optional = false)
    @Column(name = "ID_MOTIVO_DET_REVAL")
    private Long idMotivoDetReval;
	
	
	@JoinColumn(name = "ID_DETALLE_REVALIDACION", referencedColumnName = "ID_DETALLE_REVALIDACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleRevalidaDTO idDetalleReval;
	
	@JoinColumn(name = "ID_MOTIVO_REVA", referencedColumnName = "ID_MOTIVO_REVA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MotivoRevalidaDTO idMotivoReva;
	

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

	public Long getIdMotivoDetReval() {
		return idMotivoDetReval;
	}

	public void setIdMotivoDetReval(Long idMotivoDetReval) {
		this.idMotivoDetReval = idMotivoDetReval;
	}

	public DetalleRevalidaDTO getIdDetalleReval() {
		return idDetalleReval;
	}

	public void setIdDetalleReval(DetalleRevalidaDTO idDetalleReval) {
		this.idDetalleReval = idDetalleReval;
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
