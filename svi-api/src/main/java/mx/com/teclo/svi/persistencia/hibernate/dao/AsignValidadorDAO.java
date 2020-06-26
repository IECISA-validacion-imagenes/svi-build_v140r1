package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

public interface AsignValidadorDAO extends BaseDao<AsignValidadorDTO> {
	List<ArchivosCsvRespuestaVO> getTodosArchivosAsignadosActivos(Long idUsuario);
	List<ArchivoCsvDetallesVO> getDetallesArchivo(List<ArchivosCsvRespuestaVO> imagenesAsignadas);
	List<ArchivosCsvRespuestaVO> getTodosArchivosNoAsignados();
	void asignarArchivosValidador(List<ArchivosCsvRespuestaVO> archivosAdicionar, Long idValidador, Long idUsuario);
	Long asignarArchivoValidador(ArchivosCsvRespuestaVO archivo, Long idValidador, Long idUsuario);
	public List<ArchivoCsvDetallesVO> getDetalleArchivo(ArchivosCsvRespuestaVO archivoCsvRespuestaVO);
	void actualizarArchivo(AsignValidadorDTO asignacion);
	public List<ConsultaAsignacionVO> buscarAsignacionesPorCriterio(Long idTipo, String valor);
	public Boolean cancelaAsignacion(Long idAsigna);
	
	public void actualizaEstatusValidacion(Long idArchivo, Long usuario, Long idValidador);
	List<Long> MC_getTodosIdsDeArchivosAsignadosActivos();
	Long asignarArchivoValidador(ArchivosCsvRespuestaVO archivo, ValidadoresConfigDTO validadoresConfig, Long idUsuario);
	
	
}
