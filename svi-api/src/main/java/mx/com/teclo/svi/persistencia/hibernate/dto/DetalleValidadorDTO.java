/**
 * 
 */
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

/**
 * @author FernandoSanchez
 *
 */

@Entity
@Table(name = "TCI022D_PT_DETALLE_VALIDADOR")
public class DetalleValidadorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	

    @Id
    @SequenceGenerator(name = "SEQPTDETALLEVALIDADOR", sequenceName="PT_DETALLE_VALIDADOR_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTDETALLEVALIDADOR")
    @Basic(optional = false)
    @Column(name = "ID_REASIGNA_VAL")
    private Long idReasignaVal;
    
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Long stActivo;
    
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
    
    @Basic(optional = false)
    @Column(name = "NU_ORDEN")
    private Long nuOrden;
    
    @JoinColumn(name = "ID_CICLO_VALIDACION", referencedColumnName = "ID_CICLO_VALIDACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)    
    private CicloValidacionDTO idCicloValidacion;
    
    
    @JoinColumn(name = "ID_REGISTRO_CSV", referencedColumnName = "ID_REGISTRO_CSV")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ArchivoDetalleEvaDTO idRegistroCSV;
    
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ValidadoresDTO idValidador;

	/**
	 * @return the idReasignaVal
	 */
	public Long getIdReasignaVal() {
		return idReasignaVal;
	}

	/**
	 * @param idReasignaVal the idReasignaVal to set
	 */
	public void setIdReasignaVal(Long idReasignaVal) {
		this.idReasignaVal = idReasignaVal;
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
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the idUserCreacion
	 */
	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	/**
	 * @param idUserCreacion the idUserCreacion to set
	 */
	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the idUserModifica
	 */
	public Long getIdUserModifica() {
		return idUserModifica;
	}

	/**
	 * @param idUserModifica the idUserModifica to set
	 */
	public void setIdUserModifica(Long idUserModifica) {
		this.idUserModifica = idUserModifica;
	}

	/**
	 * @return the idRegistroCSV
	 */
	public ArchivoDetalleEvaDTO getIdRegistroCSV() {
		return idRegistroCSV;
	}

	/**
	 * @param idRegistroCSV the idRegistroCSV to set
	 */
	public void setIdRegistroCSV(ArchivoDetalleEvaDTO idRegistroCSV) {
		this.idRegistroCSV = idRegistroCSV;
	}

	/**
	 * @return the idValidador
	 */
	public ValidadoresDTO getIdValidador() {
		return idValidador;
	}

	/**
	 * @param idValidador the idValidador to set
	 */
	public void setIdValidador(ValidadoresDTO idValidador) {
		this.idValidador = idValidador;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public CicloValidacionDTO getIdCicloValidacion() {
		return idCicloValidacion;
	}

	public void setIdCicloValidacion(CicloValidacionDTO idCicloValidacion) {
		this.idCicloValidacion = idCicloValidacion;
	}
    
	
 }
