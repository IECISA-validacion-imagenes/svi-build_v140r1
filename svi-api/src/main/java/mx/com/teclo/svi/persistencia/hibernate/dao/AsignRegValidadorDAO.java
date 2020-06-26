package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignRegValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

public interface AsignRegValidadorDAO extends BaseDao<AsignRegValidadorDTO> {
	List<Long> getTodosRegistrosAsignadosActivos(Long idUsuario);
	List<Long> MC_getTodosRegistrosAsignadosActivos(Long idValidador);
	List<Long> getTodosIdsRegistrosAsignadosActivos();
	Long asignarRegistroValidador(Long listRegistros, ValidadoresConfigDTO validadoresConfig, Long idUsuario);
	AsignRegValidadorDTO findByIdRegistro(Long idRegistroCsv);
	List<AsignRegValidadorDTO> getTodosArchivosDeRegistrosAsignadosActivos2();
}
