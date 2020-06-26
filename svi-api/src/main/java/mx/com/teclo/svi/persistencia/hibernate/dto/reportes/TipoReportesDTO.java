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
@Table(name = "TAQ020C_AR_TIPO_REPORTES")
public class TipoReportesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9151542825594188064L;

	@Id
    @SequenceGenerator(name = "SQAQ020C", sequenceName="SQAQ020C_AR_TIPO_REP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ020C")
	@Column(name = "ID_TIPO_REPORTE", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoReporte;

	@Column(name = "CD_TIPO_REPORTE", nullable = false, length = 10)
	private String cdTipoReporte;

	@Column(name = "NB_TIPO_REPORTE", nullable = false, length = 50)
	private String nbTipoReporte;

	@Column(name = "TX_TIPO_REPORTE", nullable = false, length = 300)
	private String txTipoReporte;

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

	public Long getIdTipoReporte() {
		return idTipoReporte;
	}

	public void setIdTipoReporte(Long idTipoReporte) {
		this.idTipoReporte = idTipoReporte;
	}

	public String getCdTipoReporte() {
		return cdTipoReporte;
	}

	public void setCdTipoReporte(String cdTipoReporte) {
		this.cdTipoReporte = cdTipoReporte;
	}

	public String getNbTipoReporte() {
		return nbTipoReporte;
	}

	public void setNbTipoReporte(String nbTipoReporte) {
		this.nbTipoReporte = nbTipoReporte;
	}

	public String getTxTipoReporte() {
		return txTipoReporte;
	}

	public void setTxTipoReporte(String txTipoReporte) {
		this.txTipoReporte = txTipoReporte;
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
