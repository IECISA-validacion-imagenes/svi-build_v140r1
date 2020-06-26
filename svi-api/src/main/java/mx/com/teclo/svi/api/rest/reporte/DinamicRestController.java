package mx.com.teclo.svi.api.rest.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.exception.PersistenceException;
import mx.com.teclo.svi.negocio.servicios.reporte.AdmiReporteService;
import mx.com.teclo.svi.negocio.servicios.reporte.ReporteService;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectGenericVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosConsultaReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;


@RestController	
@RequestMapping("/consulta")
public class DinamicRestController {
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private AdmiReporteService admiReporteService;
	
	@RequestMapping(value = "/reporte", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('GET_REPORTES_DINAMICOS')")
	public ResponseEntity<ObjectGenericVO> consultaDinamica(@RequestBody ObjectGenericVO params) throws NotFoundException, SQLException, NamingException{
		/**
		 * Comprueba si existe el reporte
		 */
		ReportesTaqVO reporteExiste = reporteService.compruebaSiExisteReporteById(params.getParametros());
		if (reporteExiste == null)
			throw new NotFoundException("Es posible que el reporte no exista en el sistema");
		/**
		 * Si el reporte es encontrado se ejecuta el mybatis
		 */
		ObjectGenericVO objectReturn = reporteService.executeQuery(params.getParametros(), reporteExiste);
		if ((objectReturn.getParametros() != null) && objectReturn.getValues() == null && objectReturn.getCabeceras() == null)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<ObjectGenericVO>(objectReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('GET_DESCARGA_REPORTE_DINAMICO')")
	public ResponseEntity<byte[]> descargaExcel(@RequestBody ObjectGenericVO params) throws NotFoundException, IOException, SQLException, NamingException, BusinessException{
		/**
		 * Comprueba si existe el reporte
		 */
		ReportesTaqVO reporteExiste = reporteService.compruebaSiExisteReporteById(params.getParametros());
		if (reporteExiste == null)
			throw new NotFoundException("Es posible que el reporte no exista en el sistema");
		/**
		 * Si el reporte es encontrado se ejecuta el mybatis
		 */
		ObjectGenericVO objectReturn = reporteService.executeQuery(params.getParametros(), reporteExiste);
		if ((objectReturn.getParametros() != null) && objectReturn.getValues() == null && objectReturn.getCabeceras() == null)
			throw new NotFoundException("No se encontraron registros");
		objectReturn.setStrValoresListDoble(params.getStrValoresListDoble());//en caso de que sea lista doble agregar su valor
		ByteArrayOutputStream reporteObject = reporteService.generateExcel(objectReturn, reporteExiste);
		final byte[] bytes = reporteObject.toByteArray();

		FormatoDescargaVO formatoDescarga = reporteExiste.getReporteFormato().get(0).getFormatoDescarga();
		String txExtension = formatoDescarga == null ? ".xlsx" :  formatoDescarga.getCdExtension();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.add("Content-Disposition", "attachment; filename=" + reporteExiste.getNbReporte() + txExtension);
		headers.add("filename", reporteExiste.getNbReporte() + txExtension);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaPrevia", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('GET_DESCARGA_REPORTE_DINAMICO')")
	public ResponseEntity<List<LinkedHashMap<Object, Object>>> consultaPrevia (@RequestParam(name="idParametro") Long idParametro,
			@RequestParam(name="valores")String valores) throws SQLException, NamingException, NotFoundException{
		List<LinkedHashMap<Object,Object>> mapReturn = reporteService.executeSubQuery(idParametro, valores);
		/*si tiene una sub consulta*/
		if (mapReturn.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<LinkedHashMap<Object, Object>>>(mapReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getReporteListaDinamic", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('GET_DATA_REPORTES_DINAMICOS')")
	public ResponseEntity<List<ReportesTaqNewReporteVO>> getReporteListaDinamic (@RequestBody ParametrosConsultaReporteVO paramsConsultaVO)throws NotFoundException{
		List<ReportesTaqNewReporteVO> lista = admiReporteService.listaReportesDinamicos(paramsConsultaVO);
		if (lista.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<ReportesTaqNewReporteVO>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteReporteDinamico", method = RequestMethod.DELETE)
	//@PreAuthorize("hasAnyAuthority('DEL_REPORTE_DINAMICO')")
	public ResponseEntity<Boolean> deleteReporteDinamico(@RequestParam(name="idReporte") Long idReporte)throws NotFoundException{
		//List<ReportesTaqNewReporteVO> lista = admiReporteService.listaReportesDinamicos();
		Boolean banderaReturn = admiReporteService.deleteReporteDinamico(idReporte);
		if (!banderaReturn)
			throw new NotFoundException("Ocurrió un error al eliminar el reporte, favor de validar");
		return new ResponseEntity<Boolean>(banderaReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getReporteEdit", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('GUARDAR_MODIF_REPORTE_DINAMICO')")
	public ResponseEntity<ReportesTaqNewReporteVO> getReporteEdit(@RequestParam(name="idReporte")Long idReporte)throws NotFoundException{
		ReportesTaqNewReporteVO lista = admiReporteService.getReporteEdit(idReporte);
		if (lista == null)
			throw new NotFoundException("No se encontró el reporte en base de datos");
		return new ResponseEntity<ReportesTaqNewReporteVO>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/guadaEdicionReporte", method= RequestMethod.PUT)
	//@PreAuthorize("hasAnyAuthority('GUARDAR_MODIF_REPORTE_DINAMICO')")
	public ResponseEntity<Boolean> guadaEdicionReporte(@RequestBody ReportesTaqNewReporteVO reporteVO) throws PersistenceException, BusinessException{
		Boolean reporteTaqVO = admiReporteService.guardarEdicion(reporteVO);
		if(!reporteTaqVO)
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
