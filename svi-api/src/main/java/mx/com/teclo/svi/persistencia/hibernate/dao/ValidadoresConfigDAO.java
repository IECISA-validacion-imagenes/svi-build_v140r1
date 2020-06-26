package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;

public interface ValidadoresConfigDAO extends BaseDao<ValidadoresConfigDTO>{

	ValidadoresConfigDTO getValidadorConfigByIdValidador(Long idValidador);
	public List<ValidadorVO> obtenerValidadoresVO();
	public Long getValidadorConfigByIdValidador2(Long idValidador);
}
