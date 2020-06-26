package mx.com.teclo.svi.negocio.servicios.supervision;

import java.util.List;
import java.util.Map;

import javax.transaction.RollbackException;
import javax.transaction.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.CatEtiquetqasRevalVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorVO;

public interface SupervisionService {
	public List<Object> consultarIncidencias(FiltroExpedienteVO filtroExpedienteVO);
	public Map<String, Object> consultarDetalleIncidenciasPT(Long idPT);
	public Map<String, Object> consultarDetalleIncidenciasCSV(Long idCSV);
	public Map<String, Object> consultarDetalleIncidenciasCSV(List<Long> listIdRegistro);
	public Map<String, Object> obtenerValidadoresVO();
	public Boolean habilitarOtraValidacionIncidencia(Long idTipo,Long[] incidencias);
	public Boolean confirmaValidacionIncidencia(Long idArchivo);
	public List<CatalogoVO>catalogoReporteAsignacion();
	public Map<String, Object> catalogoIncidencia(FiltroExpedienteVO filtro)throws NotFoundException, BusinessException, RollbackException;
	public List<ConsultaAsignacionVO> consultaAsignaciones(Long idTipo, String valor);
	public List<Object>consultaDetalleReasignacion(Long idTipo, Long valor);
	public Boolean cancelaAsignacion(Long idAsigna);
	public Boolean verificaPuntoTactico(Long idPtLote);
	public Boolean reasignaPuntoTactico(ReasignaPTVO reasignaPTVO) throws BusinessException;
	public Boolean reasignaCSV(ReasignaCSVVO reasignaCSVVO) throws BusinessException;
	public Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro)
			throws NotFoundException, BusinessException, RollbackException;
	public List<CatEtiquetqasRevalVO> getCatEtiquetas() throws NotFoundException, BusinessException, RollbackException;
	public Boolean estaCicloCerrado() throws BusinessException;
	public List<CatalogoVO> getMotivosPorFiltro(Long tipoExclusionInclusion);
	public List<ValidadorVO> getValidadores();
}
