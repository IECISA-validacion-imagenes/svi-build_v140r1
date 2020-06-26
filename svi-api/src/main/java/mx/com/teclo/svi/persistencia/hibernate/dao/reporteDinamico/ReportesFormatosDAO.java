package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesFormatosDTO;

public interface ReportesFormatosDAO extends BaseDao<ReportesFormatosDTO> {
	
	/**
	 * Descripción: Método para obtener todos los 
	 * formatos disponibles para el reporte
	 * @author Jorge Luis
	 * @return List<ReportesFormatosDTO>
	 * */
	public List<ReportesFormatosDTO> getReportesFormatosById(Long idReporte);
	
	
	/**
	 * Descripción: Método para obtener todos los 
	 * formatos disponibles para el reporte
	 * @param Long
	 * @author Jorge Luis
	 * @return ReportesFormatosDTO
	 * */
	public ReportesFormatosDTO getReportesFormatosByIdReporteFormato(Long idReporteFormato);

	
}
