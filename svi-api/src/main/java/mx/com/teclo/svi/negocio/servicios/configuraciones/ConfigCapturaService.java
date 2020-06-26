package mx.com.teclo.svi.negocio.servicios.configuraciones;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.vo.configuraciones.FormularioRevalidacionVO;

public interface ConfigCapturaService {

	void guardarConfigCaptura(FormularioRevalidacionVO configuracionVO) throws BusinessException;
	
	FormularioRevalidacionVO obtenerConfigCaptura(Long id);

}
