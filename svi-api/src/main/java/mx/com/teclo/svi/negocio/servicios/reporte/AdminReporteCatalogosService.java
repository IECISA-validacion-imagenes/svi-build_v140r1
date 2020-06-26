package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionAllCatalogosVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;

public interface AdminReporteCatalogosService {
	/**
	 * Descripción: Método para consultar todos los catálogos en 
	 * una sola peticion HTTP
	 * @author Jorge Luis
	 * @return AgrupacionAllCatalogosVO
	 */
	public AgrupacionAllCatalogosVO getAllCatalogosVO();
	
	
	/**
	 * DescripciÃ³n: MÃ©todo para obtener la lista de los tipos de reportes
	 * existentes en BD.
	 * 
	 * @author Jorge Luis
	 * @return List<TipoReporteVO>
	 */
	public List<TipoReporteVO> obtenerListaTipoReporte();
	
	
}
