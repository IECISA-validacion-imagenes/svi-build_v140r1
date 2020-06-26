package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

import java.io.Serializable;

import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;

public class ParametrosConsultaReporteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoReporteVO tipoReporteVO;
	private String nbReporte;

	public TipoReporteVO getTipoReporteVO() {
		return tipoReporteVO;
	}

	public void setTipoReporteVO(TipoReporteVO tipoReporteVO) {
		this.tipoReporteVO = tipoReporteVO;
	}

	public String getNbReporte() {
		return nbReporte;
	}

	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}

}
