package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;

public interface PerfilesReportesDAO extends BaseDao<PerfilesReportesDTO>{

	/**
	 * Descripción: Método para consultar todos los perfiles
	 * que están ligados a sus reportes
	 * @author Jorge Luis
	 * @param String
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> getReportePerfilAtivos();
	
	/**
	 * Descripción: Método para obtener los pefiles y reportes por los
	 * id´s de las mismas
	 * @author Jorge Luis
	 * @param Long, Long, String
	 * @return PerfilesReportesDTO
	 */
	public PerfilesReportesDTO byReporteAndPerfil(Long perfilId, Long reporteId);
	
	/**
	 * Descripción: Método para obtener los 
	 * reportes por perfil de acceso
	 * @author Jorge Luis
	 * @param Long
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> ontenerReportesPorPerfil(Long idPerfil);
	
	/**
	 * Descripción: Método para obtener los 
	 * perfiles-reportes por el id del reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> getRegistrosByIdReporte(Long idReporte);
	
	
	/**
	 * Descripción: Método para obtener los reportes por su tipo 
	 * de agrupación
	 * @author Jorge Luis
	 * @param perfilId,cdApp,tipoReporte,nbReporte
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> getPReporteByTipoYNb(Long perfilId, String cdApp, Long tipoReporte, String nbReporte);
	
	/**/
	public List<PerfilesReportesDTO> getTodosReportes();
}
