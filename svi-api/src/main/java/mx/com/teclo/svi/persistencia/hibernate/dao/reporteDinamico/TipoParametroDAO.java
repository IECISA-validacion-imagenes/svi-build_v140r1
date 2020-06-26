package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParametroDTO;

public interface TipoParametroDAO extends BaseDao<TipoParametroDTO> {

	public List<TipoParametroDTO> listaTiposParametro();

	/**
	 * Descripción: Metódo para obtener el tipo de parametro selecciona en frot,
	 * esto para cada uno de los parametros
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return TipoParametroDTO
	 */
	public TipoParametroDTO gettipoParametroById(Long idTipoParametro);

}
