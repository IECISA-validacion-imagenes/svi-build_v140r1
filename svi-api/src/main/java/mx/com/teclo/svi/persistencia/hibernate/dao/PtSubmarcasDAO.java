package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPerfilVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;

public interface PtSubmarcasDAO extends BaseDao<PtSubmarcasDTO> {
	List<CatPerfilVO> getCatSubMarcas();

	String getResumenFiltro(List<Long> idsSubmarcas);

}
