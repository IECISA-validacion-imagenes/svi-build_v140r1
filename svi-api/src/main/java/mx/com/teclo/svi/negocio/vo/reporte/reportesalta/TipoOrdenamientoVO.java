package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

public class TipoOrdenamientoVO {

	private Integer idOrdenamiento;
	private String nbOrdenamiento;
	private String txValor;

	public TipoOrdenamientoVO() {

	}

	public TipoOrdenamientoVO(Integer idOrdenamiento, String nbOrdenamiento, String txValor) {
		this.idOrdenamiento = idOrdenamiento;
		this.nbOrdenamiento = nbOrdenamiento;
		this.txValor = txValor;
	}

	public Integer getIdOrdenamiento() {
		return idOrdenamiento;
	}

	public void setIdOrdenamiento(Integer idOrdenamiento) {
		this.idOrdenamiento = idOrdenamiento;
	}

	public String getNbOrdenamiento() {
		return nbOrdenamiento;
	}

	public void setNbOrdenamiento(String nbOrdenamiento) {
		this.nbOrdenamiento = nbOrdenamiento;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

}
