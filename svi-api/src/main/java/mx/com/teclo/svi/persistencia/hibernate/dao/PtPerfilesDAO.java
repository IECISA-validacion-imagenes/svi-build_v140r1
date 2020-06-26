package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPerfilVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;

public interface PtPerfilesDAO extends BaseDao<PtPerfilesDTO> {
	
	public List<PtPerfilesDTO> catalogoPerfiles();
	
	List<CatPerfilVO> getCatPerfiles();
	
	List<CatPerfilVO> getCatPerfilesDistinct();

	String getResumenFiltro(List<Long> idsPerfiles);

}
