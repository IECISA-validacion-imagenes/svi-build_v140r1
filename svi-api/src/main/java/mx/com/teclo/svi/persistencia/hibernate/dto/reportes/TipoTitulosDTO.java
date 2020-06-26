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
@Table(name = "TAQ019C_AR_TIPO_TITULOS")
public class TipoTitulosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4476574821346327535L;

	@Id
	@SequenceGenerator(name = "SQAQ019C", sequenceName="SQAQ019C_AR_TIP_TITUL", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ019C")
	@Column(name = "ID_TIPO_TITULO", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoTitulo;

	@Column(name = "CD_TIPO_TITULO", nullable = false, length = 10)
	private String cdTipoTitulo;

	@Column(name = "NB_TIPO_TITULO", nullable = false, length = 50)
	private String nbTipoTitulo;

	@Column(name = "TX_TIPO_TITULO", nullable = false, length = 300)
	private String txTipoTitulo;

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

	public Long getIdTipoTitulo() {
		return idTipoTitulo;
	}

	public void setIdTipoTitulo(Long idTipoTitulo) {
		this.idTipoTitulo = idTipoTitulo;
	}

	public String getCdTipoTitulo() {
		return cdTipoTitulo;
	}

	public void setCdTipoTitulo(String cdTipoTitulo) {
		this.cdTipoTitulo = cdTipoTitulo;
	}

	public String getNbTipoTitulo() {
		return nbTipoTitulo;
	}

	public void setNbTipoTitulo(String nbTipoTitulo) {
		this.nbTipoTitulo = nbTipoTitulo;
	}

	public String getTxTipoTitulo() {
		return txTipoTitulo;
	}

	public void setTxTipoTitulo(String txTipoTitulo) {
		this.txTipoTitulo = txTipoTitulo;
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
