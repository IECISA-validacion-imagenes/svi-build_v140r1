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
@Table(name = "TAQ007C_AR_COMPONENTES")
public class ComponentesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5948777719896414761L;

	@Id
	@SequenceGenerator(name = "SQAQ006C", sequenceName="SQAQ006C_AR_TIPO_PARAM", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ006C")
	@Column(name = "ID_COMPONENTE", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idComponente;

	@Column(name = "CD_COMPONENTE", nullable = false, length = 30)
	private String cdComponente;

	@Column(name = "NB_COMPONENTE", nullable = false, length = 50)
	private String nbComponente;

	@Column(name = "TX_COMPONENTE", nullable = false, length = 300)
	private String txComponente;

	@Column(name = "ST_IS_CATALOGO", nullable = false, length = 1)
	private Integer stIsCatalogo;

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

	public Long getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}

	public String getCdComponente() {
		return cdComponente;
	}

	public void setCdComponente(String cdComponente) {
		this.cdComponente = cdComponente;
	}

	public String getNbComponente() {
		return nbComponente;
	}

	public void setNbComponente(String nbComponente) {
		this.nbComponente = nbComponente;
	}

	public String getTxComponente() {
		return txComponente;
	}

	public void setTxComponente(String txComponente) {
		this.txComponente = txComponente;
	}

	public Integer getStIsCatalogo() {
		return stIsCatalogo;
	}

	public void setStIsCatalogo(Integer stIsCatalogo) {
		this.stIsCatalogo = stIsCatalogo;
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
