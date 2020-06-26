package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ008C_AR_PROPIEDADES")
public class PropiedadesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473635715930845480L;

	@Id
	@SequenceGenerator(name = "SQAQ008C", sequenceName="SQAQ008C_AR_PROPIEDAD", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ008C")
	@Column(name = "ID_PROPIEDAD", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPropiedad;

	@Column(name = "CD_PROPIEDAD", nullable = false, length = 10)
	private String cdPropiedad;

	@Column(name = "NB_PROPIEDAD", nullable = false, length = 50)
	private String nbPropiedad;

	@Column(name = "TX_PROPIEDAD", nullable = false, length = 300)
	private String txPropiedad;

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

	@Column(name = "NB_HTML_PROPIEDAD", nullable = false, length = 100)
	private String nbHtmlPropiedad;

	@Column(name = "ST_VALOR_REQUERIDO", nullable = false, precision = 1, scale = 0)
	private Long stValorRequerido;

	public Long getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(Long idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getCdPropiedad() {
		return cdPropiedad;
	}

	public void setCdPropiedad(String cdPropiedad) {
		this.cdPropiedad = cdPropiedad;
	}

	public String getNbPropiedad() {
		return nbPropiedad;
	}

	public void setNbPropiedad(String nbPropiedad) {
		this.nbPropiedad = nbPropiedad;
	}

	public String getTxPropiedad() {
		return txPropiedad;
	}

	public void setTxPropiedad(String txPropiedad) {
		this.txPropiedad = txPropiedad;
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

	public String getNbHtmlPropiedad() {
		return nbHtmlPropiedad;
	}

	public void setNbHtmlPropiedad(String nbHtmlPropiedad) {
		this.nbHtmlPropiedad = nbHtmlPropiedad;
	}

	public Long getStValorRequerido() {
		return stValorRequerido;
	}

	public void setStValorRequerido(Long stValorRequerido) {
		this.stValorRequerido = stValorRequerido;
	}

}
