package mx.com.teclo.svi.negocio.vo.reporte;

public class AgrupacionHojasVO {

	private Long idTipoAgrupacion;
	private String cdTipoAgrupacion;
	private String txObservacion;

	public Long getIdTipoAgrupacion() {
		return idTipoAgrupacion;
	}

	public void setIdTipoAgrupacion(Long idTipoAgrupacion) {
		this.idTipoAgrupacion = idTipoAgrupacion;
	}

	public String getCdTipoAgrupacion() {
		return cdTipoAgrupacion;
	}

	public void setCdTipoAgrupacion(String cdTipoAgrupacion) {
		this.cdTipoAgrupacion = cdTipoAgrupacion;
	}

	public String getTxObservacion() {
		return txObservacion;
	}

	public void setTxObservacion(String txObservacion) {
		this.txObservacion = txObservacion;
	}

}
