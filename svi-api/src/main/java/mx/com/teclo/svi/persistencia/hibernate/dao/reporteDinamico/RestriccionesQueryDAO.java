package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.RestriccionesQueryDTO;

public interface RestriccionesQueryDAO extends BaseDao<RestriccionesQueryDTO>{

	/**
	 * Descripción: Método para obtener todas 
	 * las restriccione que tendrá el Query
	 * @author Jorge Luis
	 * @return RestriccionesQuery
	 */
	public RestriccionesQueryDTO getRestriccion(); 
	
}
