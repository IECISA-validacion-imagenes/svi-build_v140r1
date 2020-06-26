package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TCI030D_PT_FILTRO_DETEVA")
public class PtFiltroDetalleEvaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -590822901734969187L;

	/**
	 * 
	 */

	@Id
	@JoinColumn(name = "ID_ETIQ_REVAL", referencedColumnName = "ID_ETIQ_REVAL")
	@ManyToOne(optional = false)
	private PtEtiquetasRevalDTO idEtiqReval;
	
	@Basic(optional = false)
	@Column(name = "NB_FILTRO")
	private String nbFiltro;

	public PtEtiquetasRevalDTO getIdEtiqReval() {
		return idEtiqReval;
	}

	public void setIdEtiqReval(PtEtiquetasRevalDTO idEtiqReval) {
		this.idEtiqReval = idEtiqReval;
	}

	public String getNbFiltro() {
		return nbFiltro;
	}

	public void setNbFiltro(String nbFiltro) {
		this.nbFiltro = nbFiltro;
	}
	
	
}
