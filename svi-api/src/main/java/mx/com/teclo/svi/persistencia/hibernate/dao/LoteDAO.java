package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPtVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;

public interface LoteDAO extends BaseDao<LoteDTO>{
	
	/* EDitar status de revalidacion del lote completo */
	
	
	public List<CatPtVO> buscarCatTodosPuntosTacticos();

	List<CatPtVO> buscarCatTodosPuntosTacticos(List<Long> listaLotes);
	
	String getResumenFiltro(List<Long> idLotes);
	
	DetalleAsignacionVO getDetalleAsignacion(Long idPtLote);
}
