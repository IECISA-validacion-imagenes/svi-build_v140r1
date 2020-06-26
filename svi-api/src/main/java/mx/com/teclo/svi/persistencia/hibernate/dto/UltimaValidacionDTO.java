/**
 * 
 */
package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * 
 * @author UnitisODM2
 *
 */
@Entity
@Immutable
@Table(name = "VTCI_PT_ULTIMA_VALID")
public class UltimaValidacionDTO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 896802722948336048L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_REASIGNA_VAL")
	private Long idReasignaVal;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_REGISTRO_CSV", nullable=false)
	private ArchivoDetalleEvaDTO idRegistroCsv;

	@Basic(optional = false)
	@Column(name = "FH_CREACION")
	private Date fechaCreacion;

	@Basic(optional = false)
	@Column(name = "NU_ORDEN")
	private Long nuOrden;

	@JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ValidadoresDTO idValidador;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idReasignaVal")
    private Collection<DetalleIncidenciaDTO> incidenciasCollection;

	public Long getIdReasignaVal() {
		return idReasignaVal;
	}

	public void setIdReasignaVal(Long idReasignaVal) {
		this.idReasignaVal = idReasignaVal;
	}

	public ArchivoDetalleEvaDTO getIdRegistroCsv() {
		return idRegistroCsv;
	}

	public void setIdRegistroCsv(ArchivoDetalleEvaDTO idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public ValidadoresDTO getIdValidador() {
		return idValidador;
	}

	public void setIdValidador(ValidadoresDTO idValidador) {
		this.idValidador = idValidador;
	}

	public Collection<DetalleIncidenciaDTO> getIncidenciasCollection() {
		return incidenciasCollection;
	}

	public void setIncidenciasCollection(Collection<DetalleIncidenciaDTO> incidenciasCollection) {
		this.incidenciasCollection = incidenciasCollection;
	}

	
	

}
