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
@Table(name = "TAQ009C_AR_TABLAS")
public class TablasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9041226820023194232L;

	@Id
	@SequenceGenerator(name = "SQAQ009C", sequenceName="SQAQ009C_AR_TABLAS", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ009C")
	@Column(name = "ID_TABLA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTabla;

	@Column(name = "CD_TABLA", nullable = false, length = 10)
	private String cdTabla;

	@Column(name = "NB_ALIAS", nullable = false, length = 50)
	private String nbAlias;

	@Column(name = "NB_REAL", nullable = false, length = 50)
	private String nbReal;

	@Column(name = "TX_TABLA", nullable = false, length = 300)
	private String txTabla;

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

	public Long getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(Long idTabla) {
		this.idTabla = idTabla;
	}

	public String getCdTabla() {
		return cdTabla;
	}

	public void setCdTabla(String cdTabla) {
		this.cdTabla = cdTabla;
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

	public String getTxTabla() {
		return txTabla;
	}

	public void setTxTabla(String txTabla) {
		this.txTabla = txTabla;
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
