package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatIncidenciasVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.CatIncidenciaDTO;

public interface CatalogoIncidenciasDAO extends BaseDao<CatIncidenciaDTO> {
	List<CatIncidenciasVO> getCatIncidencias();

	String getResumenFiltro(List<Long> idsIncidencias);
}
