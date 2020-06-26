package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParamCompDTO;

public interface TipoParamCompDAO extends BaseDao<TipoParamCompDTO> {

	/**
	 * Descripción: Método para obtener los registros relacionados parametro -
	 * componentes
	 * 
	 * @author Jorge Luis
	 * @return List<TipoParamCompDTO>
	 */
	public List<TipoParamCompDTO> getRelacionParamComponen();
}
