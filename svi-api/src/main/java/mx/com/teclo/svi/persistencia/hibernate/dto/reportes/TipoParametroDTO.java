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
@Table(name = "TAQ006C_AR_TIPO_PARAMETROS")
public class TipoParametroDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1665600936352313998L;

	@Id
	@SequenceGenerator(name = "SQAQ006C", sequenceName="SQAQ006C_AR_TIPO_PARAM", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ006C")
	@Column(name = "ID_TIPO_PARAMETRO", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoParametro;

	@Column(name = "CD_TIPO_PARAMETRO", nullable = false, length = 10)
	private String cdTipoParametro;

	@Column(name = "NB_TIPO_PARAMETRO", nullable = false, length = 50)
	private String nbTipoParametro;

	@Column(name = "TX_TIPO_PARAMETRO", nullable = false, length = 300)
	private String txTipoParametro;

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

	public Long getIdTipoParametro() {
		return idTipoParametro;
	}

	public void setIdTipoParametro(Long idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}

	public String getCdTipoParametro() {
		return cdTipoParametro;
	}

	public void setCdTipoParametro(String cdTipoParametro) {
		this.cdTipoParametro = cdTipoParametro;
	}

	public String getNbTipoParametro() {
		return nbTipoParametro;
	}

	public void setNbTipoParametro(String nbTipoParametro) {
		this.nbTipoParametro = nbTipoParametro;
	}

	public String getTxTipoParametro() {
		return txTipoParametro;
	}

	public void setTxTipoParametro(String txTipoParametro) {
		this.txTipoParametro = txTipoParametro;
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
