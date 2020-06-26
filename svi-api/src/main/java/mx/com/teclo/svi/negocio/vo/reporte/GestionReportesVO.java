package mx.com.teclo.svi.negocio.vo.reporte;

import java.util.List;
 
public class GestionReportesVO {

	private List<PerfilesAdminVO> perfiles;
	private List<ReportesTaqVO> reportes;
	private List<PerfilesReportesVO> interseccion;

	public List<PerfilesAdminVO> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<PerfilesAdminVO> perfiles) {
		this.perfiles = perfiles;
	}

	public List<PerfilesReportesVO> getInterseccion() {
		return interseccion;
	}

	public void setInterseccion(List<PerfilesReportesVO> interseccion) {
		this.interseccion = interseccion;
	}

	public List<ReportesTaqVO> getReportes() {
		return reportes;
	}

	public void setReportes(List<ReportesTaqVO> reportes) {
		this.reportes = reportes;
	}

}
