package mx.com.teclo.svi.negocio.servicios.supervision;

import java.util.List;
import java.util.Map;

import javax.transaction.RollbackException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.ArchivoRevalidacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignaRevalidacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignacionRevaExpedientesVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;
import mx.com.teclo.svi.negocio.vo.supervision.ItemAsignacionVO;

public interface AsignacionService {

	/**
	 * Obtiene el listado de expedientes que coinciden con los parametros de
	 * b&uacute;squeda en el filtro y que ya han sido validados para que puedan ser
	 * reasingados
	 * 
	 * @param filtro
	 * @param nivel
	 * @return List<InfoBasicaExpValidadoVO>
	 * @throws IllegalArgumentException
	 * @throws NotFoundException
	 * @throws BusinessException
	 * @throws RollbackException
	 */
	List<InfoBasicaExpValidadoVO> getExpedientesValidados(FiltroExpedienteVO filtro, Short nivel)
			throws IllegalArgumentException, NotFoundException, BusinessException, RollbackException;

	
	/**
	 * Obtiene la lista de csvs disponibles a reasignar si el filtro y idPtLote son proporcionados 
	 * Obtiene la lista de expedientes disponibles a reasignar si el filtro y idPtCsv son proporcionados 
	 * Obtiene la lista de expedientes disponibles a reasignar si el nombre de la etiqueta es proporcionado
	 * 
	 * Las opciones anteriores son mutuamente excluyentes
	 * 
	 * @param idPtLote
	 * @param idPtCsv
	 * @param idEtiqueta
	 * @param filtro
	 * @return
	 * @throws NotFoundException
	 */
	DetalleAsignacionVO getDetalleNoAsignado(Long idPtLote, Long idPtCsv, Long idEtiqueta, FiltroExpedienteVO filtro)
			throws NotFoundException, BusinessException;

	/**
	 * Obtiene cat&aacute;logos y lista de expedientes previamente cargados de
	 * acuerdo al &uacute;ltimo filtro
	 * 
	 * @param filtro
	 * @param nivel
	 * @return
	 * @throws NotFoundException
	 * @throws BusinessException
	 * @throws RollbackException
	 */
	Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro, Short nivel)
			throws NotFoundException, BusinessException, RollbackException;

	public AsignaRevalidacionVO asignacionPT(DetalleAsignacionVO detalleAsignacionVO, AsignaRevalidacionVO asignaRevaLoteVO,
			FiltroExpedienteVO filtroExpedienteVO) throws BusinessException;

	public List<ArchivoRevalidacionVO> constructListaArchivosCSVForAsignacion(List<ArchivoRevalidacionVO> listaArchivos,
			FiltroExpedienteVO filtroExpedienteVO) throws NotFoundException, BusinessException;

	/**
	 * Obtiene expedientes a trav&aacute;s de una etiqueta
	 * @param idEtiqueta
	 * @param nivel
	 * @return
	 * @throws NotFoundException
	 * @throws BusinessException
	 * @throws RollbackException
	 */
	 Map<String, Object> getExpedientesEtiquetados(Long idEtiqueta, Short nivel)
				throws NotFoundException, BusinessException, RollbackException;


	public AsignaRevalidacionVO asignacionPTCSV(AsignaRevalidacionVO asignaRevaLoteVO, FiltroExpedienteVO filtroExpedienteVO)
			throws BusinessException;

	public AsignaRevalidacionVO asignaProceso(AsignaRevalidacionVO asignacion);


	public List<AsignacionRevaExpedientesVO> continueProcesoDeAsignacionRevalidacion2(AsignaRevalidacionVO asignacion);


	public Boolean insertaNivelExpediente2(List<AsignacionRevaExpedientesVO> subListasignacion);
	
	/**
	 * Resetea las banderas de los expedientes que coinciden con el filtro
	 * @param filtro
	 */
	public void reiniciarBanderasRevalidacion(FiltroExpedienteVO filtro);


	public Boolean updateMasivo(AsignaRevalidacionVO asignacion);

	public Boolean deshabilitarEtiqueta(Long idEtiqueta);
}
