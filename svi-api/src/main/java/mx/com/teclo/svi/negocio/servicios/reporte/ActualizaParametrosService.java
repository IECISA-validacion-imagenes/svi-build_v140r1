package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;

public interface ActualizaParametrosService {

	/**
	 * Descripción: Método para comprobar los 
	 * cambios de los parámetros
	 * @author Jorge Luis
	 * @param List<ParametrosNewReporteVO>
	 * @param ReportesDTO
	 * @return Boolean
	 */
	public Boolean comprobarCambiosParametros(ReportesTaqNewReporteVO reporteVO,List<ParametrosNewReporteVO> parametrosVO, ReportesDTO reporteDTO);
	
	/**
	 * Descripción: Método para guardar un nuevo parámetro con
	 * todas las dependencias que son requeridas
	 * @author Jorge Luis
	 * @param List<ParametrosNewReporteVO>
	 * @param ReportesDTO
	 * @return Boolean
	 */
	public Boolean guardarNuevoParametro(ReportesTaqNewReporteVO reporteVO, List<ParametrosNewReporteVO> parametrosVO, ReportesDTO reporteDTO);
	
	
	/**
	 * Descripción: Método para eliminar parametros de los reportes
	 * @author Jorge Luis
	 * @param List<ParametrosDTO>
	 * @param ReportesDTO
	 * @return Boolean
	 */
	public Boolean eliminarParametro(ParametrosDTO parametroDTO);
	
	
	
	
	
}
