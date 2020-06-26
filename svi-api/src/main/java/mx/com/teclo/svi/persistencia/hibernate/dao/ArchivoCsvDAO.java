package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.catalogo.CatCsvVO;
import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

public interface ArchivoCsvDAO extends BaseDao<ArchivoCsvDTO>{
	
	public void actualizarArchivo(ArchivoCsvDTO archivo);
	public List<ArchivoCsvDTO> generarArchivosParaExcel(Long idPt);
	ArchivoCsvDTO getArchivoCsvById(Long idArchivo);

	public Boolean updateArchivoCsvDTOByID(Long idArchivo, Long usuario);
	public Long getIDLoteByIdArchivo(Long idArchivo);
	
	/* Consultar total de lotes por Pt donde no se haya registrado */
	public Long getLotesinRevalidacion(Long idPtLote);
	
	public Long getLotesconRevalidacion(Long idPtLote);
	
	List<ArchivosCsvRespuestaVO> getTodosArchivosNoAsignados(List<Long> listaIdAsignados);
	List<ArchivosCsvRespuestaVO> getTodosArchivosSiAsignados(List<Long> listaIdArchivosAsignados);
	
	/**
	 * Obtiene todos los archivos csv
	 * 
	 * @return
	 */
	List<CsvExpedienteVO> getAllArchivosCsv();
	public List<CatCsvVO> buscarCatTodosArchivos();
	public List<ConsultaDetalleIncidenciaPTVO> consultarDetalleIncidenciasPT(Long idPt);
	public List<CatCsvVO> buscarCatTodosArchivos(List<Long> listaCsvs);
	String getResumenFiltro(List<Long> idsCsvs);

	DetalleAsignacionVO getDetalleAsignacion(Long idPtCsv);
	
}
