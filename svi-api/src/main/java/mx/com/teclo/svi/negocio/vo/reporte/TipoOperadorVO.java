package mx.com.teclo.svi.negocio.vo.reporte;

public class TipoOperadorVO {

	private Long idTipoOperador;
	private String cdOperador;
	private String txOperador;
	private String nbOperador;
	private String txValor;
	private Long stValorRequerido;

	public Long getIdTipoOperador() {
		return idTipoOperador;
	}

	public void setIdTipoOperador(Long idTipoOperador) {
		this.idTipoOperador = idTipoOperador;
	}

	public String getCdOperador() {
		return cdOperador;
	}

	public void setCdOperador(String cdOperador) {
		this.cdOperador = cdOperador;
	}

	public String getTxOperador() {
		return txOperador;
	}

	public void setTxOperador(String txOperador) {
		this.txOperador = txOperador;
	}

	public String getNbOperador() {
		return nbOperador;
	}

	public void setNbOperador(String nbOperador) {
		this.nbOperador = nbOperador;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	public Long getStValorRequerido() {
		return stValorRequerido;
	}

	public void setStValorRequerido(Long stValorRequerido) {
		this.stValorRequerido = stValorRequerido;
	}

}
