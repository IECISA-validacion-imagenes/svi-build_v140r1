package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoReportesDTO;

public interface TipoReportesDAO extends BaseDao<TipoReportesDTO> {

	/**
	 * Metodo para obtener los tipos de reportes
	 * 
	 * @author Daniel Pitalua
	 * @return TipoReporteDTO
	 */
	public List<TipoReportesDTO> listaTipoReporte();

	/**
	 * Descripción: Método para obtener todos los tipos de reporytes
	 * 
	 * @author Jorge Luis
	 * @return List<TipoReporteDTO>
	 */
	public List<TipoReportesDTO> getAllReports();

	/**
	 * Descripción: Método para un tipo de reportes por su código
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return TipoReporteDTO
	 */
	public TipoReportesDTO getTipoReporteById(Long idTipoReporte);

}
