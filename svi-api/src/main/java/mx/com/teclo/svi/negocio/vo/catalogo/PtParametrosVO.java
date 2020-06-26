package mx.com.teclo.svi.negocio.vo.catalogo;

public class PtParametrosVO {
	private Long idPtParam;
	private String txStorageWeb;
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
