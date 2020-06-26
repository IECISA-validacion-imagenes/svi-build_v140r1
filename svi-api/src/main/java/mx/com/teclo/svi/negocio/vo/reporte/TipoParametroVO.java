package mx.com.teclo.svi.negocio.vo.reporte;

public class TipoParametroVO {

	private Long idTipoParametro;
	private String cdTipoParametro;
	private String nbTipoParametro;
	private String txTipoParametro;
	private Integer stActivo;

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

}
