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
@Table(name = "TAQ015C_AR_COLUMNAS")
public class ColumnasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2758883596345702265L;

	@Id
    @SequenceGenerator(name = "SQAQ015C", sequenceName="SQAQ015C_AR_COLUMNAS", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ015C")
	@Column(name = "ID_COLUMNA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idColumna;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TABLA", nullable = false)
	private TablasDTO tabla;

	@Column(name = "NB_ALIAS", nullable = false, length = 50)
	private String nbAlias;

	@Column(name = "NB_REAL", nullable = false, length = 50)
	private String nbReal;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	public Long getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(Long idColumna) {
		this.idColumna = idColumna;
	}

	public TablasDTO getTabla() {
		return tabla;
	}

	public void setTabla(TablasDTO tabla) {
		this.tabla = tabla;
	}

	public String getNbAlias() {
		return nbAlias;
	}

	public void setNbAlias(String nbAlias) {
		this.nbAlias = nbAlias;
	}

	public String getNbReal() {
		return nbReal;
	}

	public void setNbReal(String nbReal) {
		this.nbReal = nbReal;
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
