package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoRevalidaDTO;

public interface MotivoRevalidaDAO extends BaseDao<MotivoRevalidaDTO>{
	public List<CatalogoVO> buscarCatMotivo();

	public List<CatalogoVO> buscarCatMotivoFiltrado(Long tipoExclusionInclusion);
}
