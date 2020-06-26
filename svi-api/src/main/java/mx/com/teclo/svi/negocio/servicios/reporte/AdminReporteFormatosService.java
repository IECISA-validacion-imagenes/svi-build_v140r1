package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;

public interface AdminReporteFormatosService {

	
	/**
	 * Descripción: Método para consultar todos los 
	 * formatos de descarga disponibles
	 * @author Jorge Luis
	 * @param List<FormatoDescargaVO>
	 * @param Long
	 * @param AgrupacionHojasVO
	 * @param String
	 * @return Boolean
	 */
	public Boolean comprobarFormatoDescarga(List<FormatoDescargaVO> formatosPeticion, 
											Long idReporte, 
											AgrupacionHojasVO agrupacion,
											String strcolPaginacion);
	
	
	/**
	 * Descripción: Método para guardar un nuevo formato
	 * de descarga que estará disponiboble para el reporte
	 * @author Jorge Luis
	 * @param ReportesDTO
	 * @param Long
	 * @param FormatoDescargaVO
	 * @param String
	 * @return Boolean
	 */
	public void guadarNuevoFormatoDescarga(ReportesDTO reporteDTO,
			   Long idTipoAgrupacion,
			   FormatoDescargaVO formatToSave, 
			   String colPaginacion);
	
}
