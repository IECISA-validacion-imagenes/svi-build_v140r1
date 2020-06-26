package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;

public interface ActualizaPropiedadesParamService {

	
	/**
	 * Descripción: Método para consultar todos los parámetros
	 * asociados a un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosNewReporteVO>
	 */
	public Boolean comprobarPropiedadesParam(List<PropiedadesVO> propiedades, ParametrosDTO parametrosDTO);
	
	/**
	 * Descripción: Método para guardar nuevas propiedades
	 * que serán asociadas a un parámetro
	 * @author Jorge Luis
	 * @param Long
	 * @return Boolean
	 */
	public Boolean guardarNuevaPropiedad(List<PropiedadesVO> propiedades, ParametrosDTO parametrosDTO);
	
}
