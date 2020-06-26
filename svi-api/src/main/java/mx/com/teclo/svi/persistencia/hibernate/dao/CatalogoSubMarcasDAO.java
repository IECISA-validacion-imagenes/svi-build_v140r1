package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoMarcaSubVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;

public interface CatalogoSubMarcasDAO extends BaseDao<PtSubmarcasDTO>{
	
	public List<CatalogoMarcaSubVO> getCatalogoSubmarcas();
	
	public String getMarca(Long idSubMarca);
	
	public String getSubMarca(Long idModelo);

}
