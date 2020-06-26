package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.supervision.IncidenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleIncidenciaDTO;

public interface DetalleIncidenciaDAO extends BaseDao<DetalleIncidenciaDTO>{
	public List<IncidenciaVO> buscarIncidenciasPorRegistro(Long idRegistro);
	public Boolean deshabilitarIncidenciasPorRegistros(List<Long> ids, Long idUsuario);
}
