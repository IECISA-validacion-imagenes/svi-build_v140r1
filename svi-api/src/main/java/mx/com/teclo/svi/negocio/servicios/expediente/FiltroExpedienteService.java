package mx.com.teclo.svi.negocio.servicios.expediente;

import com.fasterxml.jackson.core.JsonProcessingException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO;
import mx.com.teclo.svi.negocio.vo.expediente.EtiquetaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;

public interface FiltroExpedienteService {
	
	EtiquetaExpedienteVO registrarFiltro(EtiquetaExpedienteVO etiquetaExpedienteVO) throws JsonProcessingException, BusinessException;
	
	Long crearEtiqueta(EtiquetaExpedienteVO etiquetaExpedienteVO) throws JsonProcessingException;
	
	EtiquetaExpedienteVO crearFiltro(Long idEtiqReval, FiltroExpedienteVO filtroExpedienteVO) throws JsonProcessingException, BusinessException;
	
	DetalleFiltroVO getFiltro(Long idFiltro);

	
}
