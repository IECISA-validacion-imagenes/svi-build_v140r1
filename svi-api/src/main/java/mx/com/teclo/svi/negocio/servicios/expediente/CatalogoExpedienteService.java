package mx.com.teclo.svi.negocio.servicios.expediente;

import java.util.List;
import java.util.Map;

import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;

public interface CatalogoExpedienteService {

	/**
	 * Obtiene el cat&aacute;logo de archivos csv
	 * @return
	 */
	List<CsvExpedienteVO> getCatCsv();
	
	/**
	 * Obtiene los cat&aacute;logos que se ocupan en el m&oacute;dulo de expediente
	 * 
	 * @return
	 */
	Map<String, Object> getCatalogos();

}
