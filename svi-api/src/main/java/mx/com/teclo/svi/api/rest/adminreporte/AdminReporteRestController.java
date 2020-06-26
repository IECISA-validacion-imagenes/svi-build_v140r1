package mx.com.teclo.svi.api.rest.adminreporte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

import javassist.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.exception.PersistenceException;
import mx.com.teclo.svi.negocio.servicios.reporte.AdmiReporteService;
import mx.com.teclo.svi.negocio.servicios.reporte.AdminReporteCatalogosService;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionAllCatalogosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.SqlTestResultaVO;

@RestController
@RequestMapping("/adminReporteController")
public class AdminReporteRestController {

	@Autowired
	private AdmiReporteService admiReporteService;
	
	@Autowired
	private AdminReporteCatalogosService adminReporteCatalogosService;
	
	@RequestMapping(value="/checkParams", method = RequestMethod.GET)
	public ResponseEntity<List<ParametrosVO>> identificacionParametro(@RequestParam(value = "cadena") String cadena)throws NotFoundException{
		return new ResponseEntity<List<ParametrosVO>>(admiReporteService.identificacionParametro(cadena), HttpStatus.OK);
	}
	
	@RequestMapping(value="/crearTestQuery", method = RequestMethod.GET)
	public ResponseEntity<SqlTestResultaVO> queryValue(@RequestParam(value = "arrayParamValue", required=false) String jsonn,
											 @RequestParam(value="cadena")String cadena) throws NotFoundException, SQLException, NamingException, IOException{
		SqlTestResultaVO message = admiReporteService.testUserQuery(jsonn, cadena);
		return new ResponseEntity<SqlTestResultaVO>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/ejecutarQueryCat", method= RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<SqlTestResultaVO> ejecutarQueryPrevia(@RequestParam(value="query")String query) throws NotFoundException, SQLException, NamingException{
		SqlTestResultaVO objectReturn = admiReporteService.executeQueryCat(query);
		return new ResponseEntity<SqlTestResultaVO>(objectReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value="/guadaReporte", method= RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('GUARDA_NEW_REPORTE_TAQ')")
	public ResponseEntity<Boolean> guadaReporte(@RequestBody ReportesTaqNewReporteVO reporteVO) throws PersistenceException{
		ReportesTaqNewReporteVO reporteTaqVO = admiReporteService.guadaReporte(reporteVO);
		if(reporteTaqVO == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getAllCatalogos", method= RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<AgrupacionAllCatalogosVO> getAgrupacionAllCatalogosVO() throws PersistenceException{
		AgrupacionAllCatalogosVO catalogos = adminReporteCatalogosService.getAllCatalogosVO();
		return new ResponseEntity<AgrupacionAllCatalogosVO>(catalogos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaTipoReportes", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<TipoReporteVO>> getListaTipoReporte() throws NotFoundException {
		List<TipoReporteVO> listaTipoReportes = adminReporteCatalogosService.obtenerListaTipoReporte();
		if(listaTipoReportes.isEmpty())
			return new ResponseEntity<List<TipoReporteVO>>(listaTipoReportes, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<TipoReporteVO>>(listaTipoReportes, HttpStatus.OK);
	}
	
	
}
