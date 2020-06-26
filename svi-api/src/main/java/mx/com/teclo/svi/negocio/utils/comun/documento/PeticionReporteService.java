package mx.com.teclo.svi.negocio.utils.comun.documento;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PeticionReporteService {

	/**
	 * Descripción: Método para generar reporte 
	 * en diferentes formatos según se requiera 
	 * el tipo de documento
	 * 
	 * @author Jorge Luis
	 * @param PeticioReporteVO
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generaReporte(PeticioReporteVO peticioReporteVO) throws IOException;
	
}
