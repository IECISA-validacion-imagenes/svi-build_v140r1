package mx.com.teclo.svi.negocio.servicios.evaresportes;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteRVConsultaVO;

public interface EvaReportesService {

//	List<ReporteRVConsultaVO> consultaResultadoValidacion(String valor, String periodo, Long idTipoBusq);
	List<ReporteRVConsultaVO> consultaReportesPts(String fhInicial, String fhFinal, Long idEntregable);
//	Map<String, Object> catalogoReporteValidacion();
	List<CatalogoVO> catalogoReporteValidacion();
	public Map generarResultadoValidacion(Long idPt) throws BusinessException;
	
}
