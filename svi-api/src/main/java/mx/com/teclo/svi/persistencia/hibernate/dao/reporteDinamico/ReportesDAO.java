package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;

public interface ReportesDAO extends BaseDao<ReportesDTO>{
	
	public Long selectMaximo();
	
	

	/**
	 * Descripción: Método para consultar el reporte por su identificador
	 * @author Jorge Luis
	 * @param Long, String
	 * @return ReportesDTO
	 */
	public ReportesDTO getReporteById(Long idReporte, String cdApp);
	
	/**
	 * Descripción: Método para consultar todos los reportes activos en
	 * en la tabla TAQ004D_AR_REPORTES
	 * @author Jorge Luis
	 * @return EmpleadoEcoVO
	 */
	public List<ReportesDTO> obtrenerReportesActivos();
	
	/**
	 * Descripción: Método para consultar todos los reportes activos en
	 * en la tabla TAQ004D_AR_REPORTES
	 * @author Fernando Octavio 
	 * @return ReportesDTO
	 */
	public ReportesDTO obtenerReporteBy(Long idReporte);
	
}
