package mx.com.teclo.svi.negocio.servicios.validacion;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.validacion.RevalidacionArchivoDetEvaVO;
import mx.com.teclo.svi.negocio.vo.validacion.registrosInconsistenciaVO;

public interface ValidacionInconsistenciaService {
	
	
	/*@author: Maverick 
	 *@param: IdUsuario
	 *@Return List<ArchivoCsvDetallesVO> 
	 *Metodo que trae los CSV o Fichereos a re validar */
		List<registrosInconsistenciaVO> getElementosInconsistentes(long IdUsuario);
	
	
	/*@author: Maverick 
	 *@param: ValidacionArchivoDetalleEvaVO elemToValidate
	 *@Return void
	 *Metodo para realizar la validacion de los elementos  */
		Long saveReValidacion(RevalidacionArchivoDetEvaVO elemToValidate, Long IdUsuario);

}
