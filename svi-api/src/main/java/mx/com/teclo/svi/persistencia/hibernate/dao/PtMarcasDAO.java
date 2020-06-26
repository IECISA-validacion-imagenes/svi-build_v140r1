package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPerfilVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtMarcasDTO;

public interface PtMarcasDAO extends BaseDao<PtMarcasDTO> {
	List<CatPerfilVO> getCatMarcas();

	String getResumenFiltro(List<Long> idsMarcas);

}
