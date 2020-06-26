package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

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
@Table(name = "TAQ010D_AR_PROPIEDADES_COMP")
public class PropiedadesCompDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4862849274191046135L;

	@Id
	@SequenceGenerator(name = "SQAQ010D", sequenceName="SQAQ010D_AR_PROPIEDADES_COMP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ010D")
	@Column(name = "ID_PROPIEDAD_COMP", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPropiedadComp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROPIEDAD", nullable = false)
	private PropiedadesDTO propiedad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COMPONENTE", nullable = false)
	private ComponentesDTO componente;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdPropiedadComp() {
		return idPropiedadComp;
	}

	public void setIdPropiedadComp(Long idPropiedadComp) {
		this.idPropiedadComp = idPropiedadComp;
	}

	public PropiedadesDTO getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadesDTO propiedad) {
		this.propiedad = propiedad;
	}

	public ComponentesDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesDTO componente) {
		this.componente = componente;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

}
