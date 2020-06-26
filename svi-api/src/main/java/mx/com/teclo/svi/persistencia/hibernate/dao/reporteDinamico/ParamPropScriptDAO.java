package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;

public interface ParamPropScriptDAO extends BaseDao<ParamPropScriptDTO>{

	/**
	 * Descripción: Método para consultar el
	 *  subquery que se ejecutará en la vista previa
	 * @author Jorge Luis
	 * @param Long
	 * @return ParamPropScriptDTO
	 */
	public ParamPropScriptDTO getParamPropById(Long idParamtrosProp);
	
}
