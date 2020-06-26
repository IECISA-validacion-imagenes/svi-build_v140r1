package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.EntregaDTO;

public interface EntregaDAO extends BaseDao<EntregaDTO> {
	public List<CatalogoVO> catalogoPeriodos();

	public List<CsvExpedienteVO> getAllEntregas();

	public List<CatalogoVO> catalogoPeriodos(List<Long> listaEntregas);
	
	String getResumenFiltro(List<Long> idsEntregas);
}
