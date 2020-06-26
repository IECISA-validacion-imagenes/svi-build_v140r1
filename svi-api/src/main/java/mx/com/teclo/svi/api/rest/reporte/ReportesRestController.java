package mx.com.teclo.svi.api.rest.reporte;


import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.servicios.reporte.ParametrosService;
import mx.com.teclo.svi.negocio.servicios.reporte.ReporteService;
import mx.com.teclo.svi.negocio.vo.reporte.GestionReportesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;




/**
 * @author javier07
 */
@RestController	
@RequestMapping("/reportes")
public class ReportesRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
//	@Autowired
//	private EmpleadoService empleadoService;
//	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ParametrosService parametrosService;
	
	@RequestMapping(value = "/getDataPefilesReportes", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('GET_DATA_PERFILES_REPORTES')")
	public ResponseEntity<GestionReportesVO> obtenerPerfilesActivos() {
		GestionReportesVO objectReturn = reporteService.getGestionReportes();
		if(objectReturn == null)
			return new ResponseEntity<GestionReportesVO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<GestionReportesVO>(objectReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/persisteConfigReportePerf", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('GET_DATA_PERFILES_REPORTES')")
	public ResponseEntity<GestionReportesVO> persisteConfigReportePerf(@RequestBody GestionReportesVO voObject) {
		GestionReportesVO objectReturn = reporteService.persisteConfigReportePerf(voObject);
		return new ResponseEntity<GestionReportesVO>(objectReturn, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getReporte", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('GET_REPORTE_BY_ID')")
	public ResponseEntity<ReportesTaqVO> getReporteDatosGeneral(@RequestParam("idReporte") Long idReporte) throws SQLException, NamingException {
		ReportesTaqVO voReturn = parametrosService.getReporteById(idReporte);
		if(voReturn== null)
			return new ResponseEntity<ReportesTaqVO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ReportesTaqVO>(voReturn, HttpStatus.OK);
	}
}
