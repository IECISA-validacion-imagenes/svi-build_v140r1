package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.PtParametrosVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtParametrosDTO;

public interface PtParametrosDAO extends BaseDao<PtParametrosDTO> {
	/**
	 * Recupera los parametros en BD de acuerdo al id en las properties
	 * @param idPtParam
	 * @return
	 */
	PtParametrosVO obtenerParametros(Long idPtParam);
}
