package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtFiltroDetalleEvaDTO;

public interface PtFiltroDetalleEvaDAO extends BaseDao<PtFiltroDetalleEvaDTO> {
	DetalleAsignacionVO getDetalleAsignacion(Long idFiltro);
}
