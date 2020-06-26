package mx.com.teclo.svi.negocio.servicios.expediente;

import java.util.List;
import java.util.Map;

import javax.transaction.RollbackException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;

public interface ExpedienteService {

	Boolean tieneAsignacionesElUsuario(Long idUsuario);
	List<ArchivoCsvDetallesVO> getArchivosInicialesVO(Long idUsuario);	
	
	List<ArchivoCsvDetallesVO> MC_getArchivosInicialesVO(Long idUsuario);
	
	/**
	 * Obtiene el listado de expedientes que coinciden con los parametros
	 * de b&uacute;squeda en el filtro
	 * @param filtro
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NotFoundException
	 * @throws BusinessException
	 * @throws RollbackException 
	 */
	List<InfoBasicaExpedienteVO> getExpedientes(FiltroExpedienteVO filtro) throws IllegalArgumentException, NotFoundException, BusinessException, RollbackException;
	/**
	 * Obtiene el detalle de un expediente y la posible lista de navegaci&oacute;n
	 * que es un conjunto de ids de expedientes que cumplen las condiciones del filtro
	 * @param id
	 * @param filtro
	 * @return
	 * @throws NotFoundException 
	 */
	DetalleExpedienteVO getExpediente(Long id, FiltroExpedienteVO filtro) throws NotFoundException;
	
	/**
	 * Obtiene cat&aacute;logos y lista de expedientes previamente cargados 
	 * de acuerdo al &uacute;ltimo filtro
	 * @return
	 * @throws RollbackException 
	 */
	Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro) throws NotFoundException, BusinessException, RollbackException;
	
	/**
	 * Actualiza el archivo detalle eva como un expediente que requiere ir a
	 * revalidaci&oacute;n
	 * 
	 * @param idRegistroCsv
	 * @return
	 */
	void marcarExpediente(Long idRegistroCsv);
}
