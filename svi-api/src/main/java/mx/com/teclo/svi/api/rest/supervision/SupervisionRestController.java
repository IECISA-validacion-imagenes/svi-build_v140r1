package mx.com.teclo.svi.api.rest.supervision;

import java.util.List;
import java.util.Map;

import javax.transaction.RollbackException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqAsignacion;
import mx.com.teclo.svi.negocio.servicios.supervision.SupervisionService;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.CatEtiquetqasRevalVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorVO;

@RestController
public class SupervisionRestController {
	
	@Autowired
	SupervisionService supervisionService;

	@RequestMapping("/consultaIncidencia")
	public ResponseEntity<List<Object>> consultaIncidencia(@RequestParam(value = "filtro", required = false) String filtro) 
			throws NotFoundException,BusinessException{
		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro) ? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {}): null;
		return new ResponseEntity<List<Object>>(supervisionService.consultarIncidencias(filtroExpedienteVO), HttpStatus.OK);
		
	}
	
	@RequestMapping("/consultaDetalleIncidenciaPT")
	public ResponseEntity<Map<String, Object>> consultaDetalleIncidenciaPT(@RequestParam("idPt") Long idPt){
		return new ResponseEntity<Map<String, Object>>(supervisionService.consultarDetalleIncidenciasPT(idPt), HttpStatus.OK);
	}
	
	@RequestMapping("/consultaDetalleIncidenciaCSV")
	public ResponseEntity<Map<String, Object>> consultaDetalleIncidenciCSV(@RequestParam("idCsv") Long idCsv){
		return new ResponseEntity<Map<String, Object>>(supervisionService.consultarDetalleIncidenciasCSV(idCsv), HttpStatus.OK);
	}
	
	@RequestMapping("/consultaDetalleIncidenciaCSV2")
	public ResponseEntity<Map<String, Object>> consultaDetalleIncidenciCSV2(@RequestParam("listIdRegistro") List<Long> listIdRegistro){
		return new ResponseEntity<Map<String, Object>>(supervisionService.consultarDetalleIncidenciasCSV(listIdRegistro), HttpStatus.OK);
	}
	
	@RequestMapping("/catValidadoresIncidencia")
	public ResponseEntity<Map<String, Object>> catValidadoresIncidencia(){
		return new ResponseEntity<Map<String, Object>>(supervisionService.obtenerValidadoresVO(), HttpStatus.OK);
	}
	
	@RequestMapping("/habilitarOtraValidacionIncidencia")
	public ResponseEntity<Boolean> habilitarOtraValidacionIncidencia(@RequestParam("idTipoRevalida") Long idTipo, @RequestParam("incidencias") Long[] incidencias){
		return new ResponseEntity<Boolean>(supervisionService.habilitarOtraValidacionIncidencia(idTipo, incidencias), HttpStatus.OK);
	}
	
	@RequestMapping("/confirmaValidacionIncidencia")
	public ResponseEntity<Boolean> confirmaValidacionIncidencia(@RequestParam("idArchivo") Long idArchivo){
		return new ResponseEntity<Boolean>(supervisionService.confirmaValidacionIncidencia(idArchivo), HttpStatus.OK);
	}
	
	@RequestMapping("/catTipoBusqReporteAsignacion")
	public ResponseEntity<List<CatalogoVO>> catalogoReporteAsignacion(){ 
		return new ResponseEntity<List<CatalogoVO>>(supervisionService.catalogoReporteAsignacion(), HttpStatus.OK);
	}
	
	@RequestMapping("/catTipoBusqIncidencia")
	public ResponseEntity<Map<String, Object>> catalogoIncidencia(@RequestParam(value = "filtro", required = false) String filtro)
	throws NotFoundException,BusinessException{
		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro) ? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {}): null;
		try {
			return new ResponseEntity<Map<String, Object>>(supervisionService.catalogoIncidencia(filtroExpedienteVO), HttpStatus.OK);
		} catch (RollbackException e) {
			throw new BusinessException("El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}
		
	}
	
	@RequestMapping("/consultaAsignaciones")
	public ResponseEntity<List<ConsultaAsignacionVO>> consultaAsignaciones(@RequestParam("idTipoBusq") Long idTipo, @RequestParam("valor") String valor){
		return new ResponseEntity<List<ConsultaAsignacionVO>>(supervisionService.consultaAsignaciones(idTipo, valor), HttpStatus.OK);
	}
	
	@RequestMapping("/consultaDetalleReasignacion")
	public ResponseEntity<List<Object>> consultaDetalleReasignacion(@RequestParam("idTipoBusq") Long idTipo, @RequestParam("valor") Long valor){
		return new ResponseEntity<List<Object>>(supervisionService.consultaDetalleReasignacion(idTipo, valor), HttpStatus.OK);
	}
	
	@RequestMapping("/cancelaAsignacion")
	public ResponseEntity<Boolean> cancelaAsignacion(@RequestParam("idAsigna") Long idAsigna){
		return new ResponseEntity<Boolean>(supervisionService.cancelaAsignacion(idAsigna), HttpStatus.OK);
	}
	
	@RequestMapping("/verificaPuntoTactico")
	public ResponseEntity<Boolean> verificaPuntoTactico(@RequestParam("idPtLote") Long idPtLote){
		return new ResponseEntity<Boolean>(supervisionService.verificaPuntoTactico(idPtLote), HttpStatus.OK);
	}
	
	@RequestMapping(value="/reasignaPT", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> reasignaPuntoTactico(@RequestBody ReasignaPTVO reasignaPTVO) throws BusinessException{
		return new ResponseEntity<Boolean>(supervisionService.reasignaPuntoTactico(reasignaPTVO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/reasignaCSV", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> reasignaCSV(@RequestBody ReasignaCSVVO reasignaCSVVO) throws BusinessException{
		return new ResponseEntity<Boolean>(supervisionService.reasignaCSV(reasignaCSVVO), HttpStatus.OK);
	}
	
	@GetMapping("/getCataalogosFiltro")
	public ResponseEntity<Map<String, Object>> getCargaInicial(@RequestParam(value = "filtro", required = false) String filtro)
			throws NotFoundException,BusinessException {
		
		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro) ? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {}): null;
		try {
			return new ResponseEntity<Map<String, Object>>(supervisionService.getCargaInicial(filtroExpedienteVO),HttpStatus.OK);
		} catch (RollbackException e) {
			throw new BusinessException("El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}	
	}
	
	@GetMapping("/getEtiquetas")
	public List<CatEtiquetqasRevalVO> getEtiquetas()
			throws NotFoundException,BusinessException {
		try {
			return supervisionService.getCatEtiquetas();
		} catch (RollbackException e) {
			throw new BusinessException("El tiempo para obtener el catálogo de etiquetas fue demasiado largo");
		}
	}

	@RequestMapping("/getAcccess")
	public ResponseEntity<Boolean> getAcccess() throws BusinessException{
		return new ResponseEntity<Boolean>(supervisionService.estaCicloCerrado(), HttpStatus.OK);
	}
	
	//Adicionado Gibran
	@RequestMapping("/getCatalogoMotivoRevalidacionGeneral")
	public ResponseEntity<List<CatalogoVO>> getCatalogoMotivoRevalidacionGeneral(){
		Long tipoExclusionInclusion = EnumTipoBusqAsignacion.MOTIVO_CSV.getId();
		return new ResponseEntity<List<CatalogoVO>>(supervisionService.getMotivosPorFiltro(tipoExclusionInclusion), HttpStatus.OK);
	}
	
	@RequestMapping("/getCatalogoMotivoRevalidacionDetalle")
	public ResponseEntity<List<CatalogoVO>> getCatalogoMotivoRevalidacionDetalle(){
		Long tipoExclusionInclusion = EnumTipoBusqAsignacion.MOTIVO_EXPEDIENTES.getId();
		return new ResponseEntity<List<CatalogoVO>>(supervisionService.getMotivosPorFiltro(tipoExclusionInclusion), HttpStatus.OK);
	}
	
	@RequestMapping("/getCatalogoValidadores")
	public ResponseEntity<List<ValidadorVO>> getCatalogoValidadores(){
		return new ResponseEntity<List<ValidadorVO>>(supervisionService.getValidadores(), HttpStatus.OK);
	}
	
	
}
