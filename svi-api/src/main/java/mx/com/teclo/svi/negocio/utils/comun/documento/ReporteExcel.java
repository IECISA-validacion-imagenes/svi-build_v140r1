package mx.com.teclo.svi.negocio.utils.comun.documento;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reporteExcel")
public class ReporteExcel extends AbstractReporteService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReporteExcel.class);

	public static int DEFAULT_WINDOW_SIZE = 50000;
	public static int LIMITE_HOJA = 64980;
	
	@Autowired
	private StyleComponenteExcel estilo;

	@Override
	public ByteArrayOutputStream generaReporte(PeticioReporteVO voPeticion) throws IOException {
		
		Workbook  libroExcel = new SXSSFWorkbook(DEFAULT_WINDOW_SIZE);
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		estilo.formato(libroExcel);
		
		if(voPeticion.getPropiedadesReporte().isStMultiplehoja()){
			estilo.generarHojasPaginable(libroExcel, voPeticion);
		}else{
			estilo.generarHojas(libroExcel, voPeticion);
		}
		libroExcel.write(response);
		response.flush();
		return response;
		//incializr libro
		
		
	}
	
}
