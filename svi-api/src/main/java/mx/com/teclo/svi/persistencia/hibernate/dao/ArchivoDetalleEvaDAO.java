package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteRVConsultaVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;
import mx.com.teclo.svi.negocio.vo.supervision.ItemAsignacionVO;
import mx.com.teclo.svi.negocio.vo.validacion.ValidacionDatosVO;
import mx.com.teclo.svi.negocio.vo.validacion.registrosInconsistenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;

public interface ArchivoDetalleEvaDAO extends BaseDao<ArchivoDetalleEvaDTO> {
	public List<ArchivoDetalleEvaDTO> getRegistrosInconsistentes(Long idArchivoSCV);

	public List<ArchivoDetalleEvaDTO> getRegistrosValidados(long idArchivoSCV);

	public Boolean validarSiPermitirDescargar(Long idPt);

//	public List<ReporteRVConsultaVO> consultarReporteResultadoValidacion(String valor, String periodo, Long idTipoBusq);
	public List<ReporteRVConsultaVO> consultarReporteResultadoValidacion(String fhInicio, String fhFin, Long idEntrega);

	public List<Object> consultarIncidencias(FiltroExpedienteVO filtroExpedienteVO);

	public Long getNuRegistros(Long idPeriodo, String nbLote);

	public List<ConsultaDetalleIncidenciaCSVVO> consultarDetalleIncidenciasCSV(Long idArchivo);

	public List<ConsultaDetalleIncidenciaCSVVO> consultarDetalleIncidenciasCSV(List<Long> listIdRegistro);

	public List<ArchivoDetalleEvaDTO> generarListArchivosParaExcel(Long idArhivoCSV);

	public ValidacionDatosVO archivoEstaValidado(Long idArchivo, ValidadoresConfigDTO validador);

	public Boolean habilitarRevalidacion(Long idArchivoCsv, Long idUsuario);

	public Boolean habilitarRevalidacion(List<Long> idLista, Long idUsuario);

	public void deshabilitarAntiguasIncidencias(Long idArchivoCsv, Long idUsuario);

	public String test();

	List<ArchivoCsvDetallesVO> getTodosDetallesArchivoPorRegistro(List<Long> idRegistros);

	List<Long> getTodosIdArchivoQueEstenAsignadosSusRegistros(List<Long> idRegistros);

	Long getNuRegistrosByIdCSV(Long idCSV);

	List<Long> getTodosIdRegistrosPorIdArchivo(Long idArchivo, List<Long> listIdRegistrosActivos);

	public String obtenerHost();

	/**
	 * Hace la b&uacute;squeda de expedientes por placa, expediente o csv
	 * 
	 * @param filtro
	 * @return
	 */
	List<InfoBasicaExpedienteVO> getExpedientes(FiltroExpedienteVO filtro);

	/**
	 * Obtiene el detalle de un expediente
	 * 
	 * @param id
	 * @return
	 */
	DetalleExpedienteVO getExpediente(Long id);

	/**
	 * Recupera los ids de expedientes que cumplen con los valores del filtro y con
	 * el cual se navega en la pantalla de detalle
	 * 
	 * @param filtro
	 * @return
	 */
	List<Long> obtenerListaDeNavegacion(FiltroExpedienteVO filtro);

	/**
	 * Hace el conteo de los resultados obtenidos de la b&uacute;squeda de
	 * expedientes
	 * 
	 * @param filtro
	 * @return
	 */
	Long contarResultadosDeLaBusqueda(FiltroExpedienteVO filtro);

	Long getRegistrosInconsistentesAsignacion(Long idArchivoSCV);

	Long getRegistrosInconsistentesValidadosAsignacion(Long idArchivoSCV);

	void marcarExpediente(Long idRegistroCsv, Long idUsuarioModifica);

	/**
	 * Hace la b&uacute;squeda de expedientes validados por valores del filtro y
	 * agrupa los resultados por alguno de los siguientes niveles que son lote, csv
	 * 
	 * @param filtro
	 * @param nivel
	 * @return
	 */
	List<InfoBasicaExpValidadoVO> getExpedientesValidados(FiltroExpedienteVO filtro, Short nivel);

	/**
	 * Obtiene la lista de elementos disponibles para revalidar que pertenece al
	 * lote o csv o filtro etiquetado seleccionado
	 * 
	 * @param idPtLote
	 * @param idPtCsv
	 * @param filtro
	 * @return
	 */
	List<ItemAsignacionVO> getDetalleNoAsignado(Long idPtLote, Long idPtCsv, FiltroExpedienteVO filtro);
	
	/**
	 * Reinicia banderas requeridad para la revalidaci&oacute;n de expedientes 
	 * de forma masiva
	 * @param idPtLote
	 * @param idUsuario
	 */
	void updateMarcadoMasivo(Long idPtLote, Long idUsuario);
	
	
	/**
	 * Reinicia las banderas de revalidaci&oacute;n de los expedienes que coinciden con el filtro
	 * y que a&uacute;n no han sido asignados en el ciclo vigente 
	 * @param filtro
	 * @param idUsuario
	 */
	void actualizarBanderasRevalidacion(FiltroExpedienteVO filtro, Long idUsuario);
	/**
	 * Consulta expedientes con inconsistencias para validaciones, se implementa sql nativo por optimizacion 
	 * @param idArchivoSCV 
	 * @return List<registrosInconsistenciaVO> 
	 * @author Beatriz
	 */
	public List<registrosInconsistenciaVO> getExpedientesInconsistentes(Long idArchivoSCV);
}
