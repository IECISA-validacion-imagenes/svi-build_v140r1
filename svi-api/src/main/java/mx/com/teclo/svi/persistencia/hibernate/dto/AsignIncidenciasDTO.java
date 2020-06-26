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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI015D_PT_ASIGN_INCIDENCIAS")
public class AsignIncidenciasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ptAsignIncon", sequenceName="PT_ASIGN_INCIDENCIAS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ptAsignIncon")
    @Basic(optional = false)
    @Column(name = "ID_ASIGN_INCIDENCIA")
    private Long idAsignIncidencia;
    @Column(name = "ID_PT_LOTE")
    private Long idPtLote;
    @Column(name = "ID_ARCHIVO_CSV")
    private Long idArchivoCsv;
    @Basic(optional = false)
    @Column(name = "ST_VALIDACION")
    private Long stValidacion;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Long stActivo;
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
    
    @JoinColumn(name = "ID_VALIDADOR_CONFIG", referencedColumnName = "ID_VALIDADOR_CONFIG")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValidadoresConfigDTO idValidadorConfig;

	/**
	 * @return the idAsignIncidencia
	 */
	public Long getIdAsignIncidencia() {
		return idAsignIncidencia;
	}

	/**
	 * @param idAsignIncidencia the idAsignIncidencia to set
	 */
	public void setIdAsignIncidencia(Long idAsignIncidencia) {
		this.idAsignIncidencia = idAsignIncidencia;
	}

	/**
	 * @return the idPtLote
	 */
	public Long getIdPtLote() {
		return idPtLote;
	}

	/**
	 * @param idPtLote the idPtLote to set
	 */
	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}

	/**
	 * @return the idArchivoCsv
	 */
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}

	/**
	 * @param idArchivoCsv the idArchivoCsv to set
	 */
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}

	/**
	 * @return the stValidacion
	 */
	public Long getStValidacion() {
		return stValidacion;
	}

	/**
	 * @param stValidacion the stValidacion to set
	 */
	public void setStValidacion(Long stValidacion) {
		this.stValidacion = stValidacion;
	}

	/**
	 * @return the stActivo
	 */
	public Long getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}

	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	/**
	 * @return the idUsrCreacion
	 */
	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}

	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	/**
	 * @return the idUsrModifica
	 */
	public long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the idValidadorConfig
	 */
	public ValidadoresConfigDTO getIdValidadorConfig() {
		return idValidadorConfig;
	}

	/**
	 * @param idValidadorConfig the idValidadorConfig to set
	 */
	public void setIdValidadorConfig(ValidadoresConfigDTO idValidadorConfig) {
		this.idValidadorConfig = idValidadorConfig;
	}

	
    
}
