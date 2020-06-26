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

@Entity
@Table(name = "TCI024D_PT_ASIGN_REG_VALIDADOR")
public class AsignRegValidadorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ASIGN_REG_VAL")
    @SequenceGenerator(name = "ptAsignRegSec", sequenceName="SQCI024D_PT_ASIGNREVAL", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ptAsignRegSec")
    private Long idAsignValidador;
    
    @Column(name = "ID_REGISTRO_CSV")
    private Long idRegistroCsv;
    
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
    
    

	public AsignRegValidadorDTO() {
	}



	public AsignRegValidadorDTO(Long idAsignValidador, Long idRegistroCsv, Short stValidacion, short stActivo,
			Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica,
			ValidadoresConfigDTO idValidadorConfig) {
		super();
		this.idAsignValidador = idAsignValidador;
		this.idRegistroCsv = idRegistroCsv;
		this.stValidacion = stValidacion;
		this.stActivo = stActivo;
		this.fhCreacion = fhCreacion;
		this.idUsrCreacion = idUsrCreacion;
		this.fhModificacion = fhModificacion;
		this.idUsrModifica = idUsrModifica;
		this.idValidadorConfig = idValidadorConfig;
	}



	public Long getIdAsignValidador() {
		return idAsignValidador;
	}



	public void setIdAsignValidador(Long idAsignValidador) {
		this.idAsignValidador = idAsignValidador;
	}



	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}



	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
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
