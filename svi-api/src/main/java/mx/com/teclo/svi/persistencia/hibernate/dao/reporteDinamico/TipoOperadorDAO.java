package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;

public interface TipoOperadorDAO extends BaseDao<TipoOperadorDTO> {

	/**
	 * Descripción: Método para consultar todos los tipos de operador en BD
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return List<TipoOperadorDTO>
	 */
	public List<TipoOperadorDTO> getCatTipoOperador();

}
