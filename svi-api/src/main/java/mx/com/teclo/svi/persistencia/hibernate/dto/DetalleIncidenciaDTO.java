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

@Entity
@Table(name = "TCI023D_PT_DETALLE_INCIDENCIA")
public class DetalleIncidenciaDTO implements Serializable{

	
private static final long serialVersionUID = 7078244648189729088L;
	
	@Id
	@SequenceGenerator(name = "seqPtIncidencia", sequenceName="PT_DETALLE_INCIDENCIA_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPtIncidencia")
	@Column(name="ID_DET_INCIDENCIA")
	private Long idDetIncidencia;
	
	@JoinColumn(name="ID_REGISTRO_CSV", referencedColumnName = "ID_REGISTRO_CSV")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
	private ArchivoDetalleEvaDTO idRegistroCsv;
	
	@JoinColumn(name="ID_INCIDENCIA", referencedColumnName = "ID_INCIDENCIA")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
	private CatIncidenciaDTO idIncidencia;
	
	@JoinColumn(name = "ID_REASIGNA_VAL", referencedColumnName = "ID_REASIGNA_VAL")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private DetalleValidadorDTO idReasignaVal;
	
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	
	@Column(name="ID_USR_CREACION")
	private Long idUsrCreacion;
	
	@Column(name="FH_MODIFICACION")
	private Date fhModificacion;
	
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;

	public Long getIdDetIncidencia() {
		return idDetIncidencia;
	}

	public void setIdDetIncidencia(Long idDetIncidencia) {
		this.idDetIncidencia = idDetIncidencia;
	}

	public ArchivoDetalleEvaDTO getIdRegistroCsv() {
		return idRegistroCsv;
	}

	public void setIdRegistroCsv(ArchivoDetalleEvaDTO idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}

	public CatIncidenciaDTO getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(CatIncidenciaDTO idIncidencia) {
		this.idIncidencia = idIncidencia;
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

	public DetalleValidadorDTO getIdReasignaVal() {
		return idReasignaVal;
	}

	public void setIdReasignaVal(DetalleValidadorDTO idReasignaVal) {
		this.idReasignaVal = idReasignaVal;
	}
	
}
