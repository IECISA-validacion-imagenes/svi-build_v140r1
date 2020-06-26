package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;


public interface ParametrosPropDAO extends BaseDao<ParametrosPropDTO>{


	/**
	 * Descripción: Método para consultar las propiedades de 
	 * cada uno de los parametros
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosPropDTO>
	 */
	public List<ParametrosPropDTO> getParametrosPropiedad(Long idParametro);
	
	/**
	 * Descripción: Método para consultar las propiedades de 
	 * cada uno de los parametros
	 * @author Jorge Luis
	 * @param Long, Long
	 * @return ParametrosPropDTO
	 */
	public ParametrosPropDTO getParametroPropiedad(Long idParametro, Long idPropiedad);
	
}
