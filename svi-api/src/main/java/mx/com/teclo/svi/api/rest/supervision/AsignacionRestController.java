package mx.com.teclo.svi.api.rest.supervision;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.transaction.RollbackException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.exception.PersistenceException;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.servicios.supervision.AsignacionService;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignaRevalidacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignacionRevaExpedientesVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsignacionRestController.class);

	@Autowired
	private AsignacionService asignacionService;

	@GetMapping("/inicio")
	public ResponseEntity<Map<String, Object>> getCargaInicial(
			@RequestParam(value = "filtro", required = false) String filtro,
			@RequestParam(value = "nivel", required = false) Short nivel) throws NotFoundException, BusinessException {

		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro)
				? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		try {
			return new ResponseEntity<Map<String, Object>>(asignacionService.getCargaInicial(filtroExpedienteVO, nivel),
					HttpStatus.OK);
		} catch (RollbackException e) {
			throw new BusinessException(
					"El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}
	}

	@GetMapping(value = "/busqueda")
	public ResponseEntity<List<InfoBasicaExpValidadoVO>> getExpedientes(
			@RequestParam(value = "filtro", required = true) String filtro,
			@RequestParam(value = "nivel", required = false) Short nivel)
			throws IllegalArgumentException, BusinessException, NotFoundException {
		LOGGER.debug("filtro: " + filtro);

		FiltroExpedienteVO filtroExpedienteVO = EnumObjectMapper.INSTANCE.fromJson(filtro,
				new TypeReference<FiltroExpedienteVO>() {
				});
		List<InfoBasicaExpValidadoVO> detalleExpedientes;
		try {
			detalleExpedientes = asignacionService.getExpedientesValidados(filtroExpedienteVO, nivel);
		} catch (RollbackException e) {
			throw new BusinessException(
					"El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}
		return new ResponseEntity<List<InfoBasicaExpValidadoVO>>(detalleExpedientes, HttpStatus.OK);

	}

	@GetMapping(value = "/lotes/{id}/detalle")
	public ResponseEntity<DetalleAsignacionVO> getLote(@PathVariable("id") Long id,
			@RequestParam(value = "filtro", required = false) String filtro)
			throws NotFoundException, SQLException, NamingException, BusinessException {
		LOGGER.debug("idRegistroCsv: " + id);

		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro)
				? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		return new ResponseEntity<DetalleAsignacionVO>(
				asignacionService.getDetalleNoAsignado(id, null, null, filtroExpedienteVO), HttpStatus.OK);
	}

	@GetMapping(value = "/csvs/{id}/detalle")
	public ResponseEntity<DetalleAsignacionVO> getCsv(@PathVariable("id") Long id,
			@RequestParam(value = "filtro", required = false) String filtro)
			throws NotFoundException, SQLException, NamingException, BusinessException {
		LOGGER.debug("idRegistroCsv: " + id);

		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro)
				? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		return new ResponseEntity<DetalleAsignacionVO>(
				asignacionService.getDetalleNoAsignado(null, id, null, filtroExpedienteVO), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity marcarExpediente(@PathVariable("id") Long id)
			throws NotFoundException, BusinessException, JsonProcessingException {

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	//Parte realizada Gibran
	
	@RequestMapping(value="/reasignaPTLote", method= RequestMethod.PUT)
	//@PreAuthorize("hasAnyAuthority('GUARDAR_MODIF_REPORTE_DINAMICO')")
	public ResponseEntity<Boolean> reasignaPTLote(@RequestBody AsignaRevalidacionVO asignaRevaLoteVO) throws PersistenceException, BusinessException, NotFoundException{
		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(asignaRevaLoteVO.getFiltro())
				? EnumObjectMapper.INSTANCE.fromJson(asignaRevaLoteVO.getFiltro(), new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		Boolean valueResponse = false;		
		if(asignaRevaLoteVO.getIdTiporeval().equals(5L)) {
			DetalleAsignacionVO detalleAsignacionVO = asignacionService.getDetalleNoAsignado(asignaRevaLoteVO.getIdPtLote(), null, null, filtroExpedienteVO);
			asignaRevaLoteVO = asignacionService.asignacionPT(detalleAsignacionVO, asignaRevaLoteVO, filtroExpedienteVO);
			asignaRevaLoteVO = asignacionService.asignaProceso(asignaRevaLoteVO);
			List<AsignacionRevaExpedientesVO> listaInsercion = asignacionService.continueProcesoDeAsignacionRevalidacion2(asignaRevaLoteVO);
			valueResponse = continueNextProcesoDeAsignacionRevalidacion2(listaInsercion);
			valueResponse = asignacionService.updateMasivo(asignaRevaLoteVO);
			
		}else if(asignaRevaLoteVO.getIdTiporeval().equals(6L) || asignaRevaLoteVO.getIdTiporeval().equals(4L)) {
			asignaRevaLoteVO = asignacionService.asignacionPTCSV(asignaRevaLoteVO, filtroExpedienteVO);
			asignaRevaLoteVO = asignacionService.asignaProceso(asignaRevaLoteVO);
			List<AsignacionRevaExpedientesVO> listaInsercion = asignacionService.continueProcesoDeAsignacionRevalidacion2(asignaRevaLoteVO);
			valueResponse = continueNextProcesoDeAsignacionRevalidacion2(listaInsercion);
			valueResponse = asignacionService.updateMasivo(asignaRevaLoteVO);
		}
				
		
		return new ResponseEntity<Boolean>(valueResponse, HttpStatus.OK);
	}
	
	@GetMapping("/etiquetas/{id}/filtro")
	public ResponseEntity<Map<String, Object>> getExpedientesEtiquetados(@PathVariable("id") Long id,
			@RequestParam(value = "nivel", required = false) Short nivel)
			throws IllegalArgumentException, BusinessException, NotFoundException, RollbackException {
		LOGGER.debug("filtro: " + id);
		return new ResponseEntity<Map<String, Object>>(
				asignacionService.getExpedientesEtiquetados(id, nivel), HttpStatus.OK);

	}
	
	@GetMapping("/deshabilitarEtiqueta")
	public ResponseEntity<Boolean> deshabilitarEtiqueta(
			@RequestParam(value = "id", required = true) Long id){
		return new ResponseEntity<Boolean>(
				asignacionService.deshabilitarEtiqueta(id), HttpStatus.OK);

	}
	
	
	public Boolean continueNextProcesoDeAsignacionRevalidacion2(
			List<AsignacionRevaExpedientesVO> listasignacion) {
		
		List<AsignacionRevaExpedientesVO> subListasignacion = new ArrayList<AsignacionRevaExpedientesVO>();
		int contador=0;
		for(int i=0;i<listasignacion.size();i++) {
			contador++;
			subListasignacion.add(listasignacion.get(i));
									
			if (contador % 20 == 0||contador==listasignacion.size()) {
				asignacionService.insertaNivelExpediente2(subListasignacion);
				subListasignacion = new ArrayList<AsignacionRevaExpedientesVO>();
			}
		}
		
		return true;
	}

}
