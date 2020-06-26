package mx.com.teclo.svi.negocio.servicios.reporte;


import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteDinamicoVO;

public interface ReporteDinamicoIService {
	
	ReporteDinamicoVO obtenerListaReportes(Long empledoId) throws NotFoundException;
	

}
