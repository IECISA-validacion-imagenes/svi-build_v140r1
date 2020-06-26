package mx.com.teclo.svi.api.rest.evas;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.servicios.evaresportes.EvaReportesService;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteRVConsultaVO;

@RestController	

public class EvaRestController {
	
	@Autowired
	EvaReportesService evaReportesService;
	
	@RequestMapping("/catTipoBusqReporteValidacion")
	public ResponseEntity<List<CatalogoVO>> catalogoReporteValidacion(){ 		
		List<CatalogoVO> listaEntregas = evaReportesService.catalogoReporteValidacion();
		return new ResponseEntity<List<CatalogoVO>>(listaEntregas, HttpStatus.OK);
	}
	
	@RequestMapping("/generarZIPCSV")
	public ResponseEntity<byte[]> generarReporte(@RequestParam("idPtLote") Long idPt) throws BusinessException{ 
		
		Map res = evaReportesService.generarResultadoValidacion(idPt);
    	final byte[] bytes = ((ByteArrayOutputStream) res.get("archivo")).toByteArray();
    	String filename = (String) res.get("nombre");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
	
//	@RequestMapping("/consultaReporteResultValidacion")
//	public ResponseEntity<List<ReporteRVConsultaVO>> consultaReporteResultValidacion(@RequestParam("idTipoBusq")Long tipoBusq, @RequestParam("valor")String valor, @RequestParam("periodo")String periodo){
//		List<ReporteRVConsultaVO> listaReportes = evaReportesService.consultaResultadoValidacion(valor, periodo.trim().equals("") ? null : periodo, tipoBusq);
//		return new ResponseEntity<List<ReporteRVConsultaVO>>(listaReportes, HttpStatus.OK);
//	}
	
	@RequestMapping("/consultaReporteResultValidacion")
	public ResponseEntity<List<ReporteRVConsultaVO>> consultaReporteResultValidacion(Long identregable) throws BusinessException{
		
		List<ReporteRVConsultaVO> listaReportes = evaReportesService.consultaReportesPts(null, null, identregable);
		return new ResponseEntity<List<ReporteRVConsultaVO>>(listaReportes, HttpStatus.OK);
	}
}
