package mx.com.teclo.svi.negocio.vo.reporte;

import java.util.List;

public class ReporteDinamicoVO {

	List<TipoReporteVO> tipoReporte;
	List<ReportesTaqLiteVO> reportes;

	public List<TipoReporteVO> getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(List<TipoReporteVO> tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List<ReportesTaqLiteVO> getReportes() {
		return reportes;
	}

	public void setReportes(List<ReportesTaqLiteVO> reportes) {
		this.reportes = reportes;
	}

}
