package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;


public interface ParametrosDAO extends BaseDao<ParametrosDTO>{

	/**
	 * Descripción: Método para consultar los parametros 
	 * de un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametroDTO>
	 */
	public List<ParametrosDTO> getParametrosByReporte(Long idReporte);
	
	
	/**
	 * Descripción: Método para obtener el parametro por su id 
	 * de un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametroDTO
	 */
	public ParametrosDTO getParametrosById(Long idParametro);
	
}
