package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI014C_PT_PARAMETROS")
public class PtParametrosDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1117320452932733079L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_PT_PARAM")
	private Long idPtParam;

	@Column(name = "TX_STORAGE_WEB")
	private String txStorageWeb;
	
	@Column(name = "MAX_RESULTADOS")
	private Long maxResultados;

	public Long getIdPtParam() {
		return idPtParam;
	}

	public void setIdPtParam(Long idPtParam) {
		this.idPtParam = idPtParam;
	}

	public String getTxStorageWeb() {
		return txStorageWeb;
	}

	public void setTxStorageWeb(String txStorageWeb) {
		this.txStorageWeb = txStorageWeb;
	}

	public Long getMaxResultados() {
		return maxResultados;
	}

	public void setMaxResultados(Long maxResultados) {
		this.maxResultados = maxResultados;
	}

	
	
	
}
