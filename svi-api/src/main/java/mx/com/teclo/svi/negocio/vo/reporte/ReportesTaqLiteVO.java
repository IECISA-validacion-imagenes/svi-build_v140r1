package mx.com.teclo.svi.negocio.vo.reporte;


public class ReportesTaqLiteVO {

	private Long idReporte;
	private String nbReporte;
	private String url;
	private Long idTipoReporte;
	private String txUrlDinamic;

	public ReportesTaqLiteVO() {
	}

	public ReportesTaqLiteVO(Long idReporte, String nbReporte, String url, Long idTipoReporte, String txUrlDinamic) {
		this.idReporte = idReporte;
		this.nbReporte = nbReporte;
		this.url = url;
		this.idTipoReporte = idTipoReporte;
		this.txUrlDinamic = txUrlDinamic;

	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public String getNbReporte() {
		return nbReporte;
	}

	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getIdTipoReporte() {
		return idTipoReporte;
	}

	public void setIdTipoReporte(Long idTipoReporte) {
		this.idTipoReporte = idTipoReporte;
	}

	public String getTxUrlDinamic() {
		return txUrlDinamic;
	}

	public void setTxUrlDinamic(String txUrlDinamic) {
		this.txUrlDinamic = txUrlDinamic;
	}

}
