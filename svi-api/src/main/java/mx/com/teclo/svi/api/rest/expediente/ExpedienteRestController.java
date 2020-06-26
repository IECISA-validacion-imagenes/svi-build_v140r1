package mx.com.teclo.svi.api.rest.expediente;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.servicios.expediente.ExpedienteService;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;

@RestController
@RequestMapping("/expedientes")
public class ExpedienteRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteRestController.class);

	@Autowired
	private ExpedienteService expedienteService;

	@GetMapping("/inicio")
	public ResponseEntity<Map<String, Object>> getCargaInicial(
			@RequestParam(value = "filtro", required = false) String filtro)
			throws NotFoundException, BusinessException {

		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro)
				? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		try {
			return new ResponseEntity<Map<String, Object>>(expedienteService.getCargaInicial(filtroExpedienteVO),
					HttpStatus.OK);
		} catch (RollbackException e) {
			throw new BusinessException(
					"El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}
	}

	@GetMapping(value = "/busqueda")
	public ResponseEntity<List<InfoBasicaExpedienteVO>> getExpedientes(
			@RequestParam(value = "filtro", required = true) String filtro)
			throws IllegalArgumentException, BusinessException, NotFoundException {
		LOGGER.debug("filtro: " + filtro);

		FiltroExpedienteVO filtroExpedienteVO = EnumObjectMapper.INSTANCE.fromJson(filtro,
				new TypeReference<FiltroExpedienteVO>() {
				});
		List<InfoBasicaExpedienteVO> detalleExpedientes;
		try {
			detalleExpedientes = expedienteService.getExpedientes(filtroExpedienteVO);
		} catch (RollbackException e) {
			throw new BusinessException(
					"El tiempo para atender su búsqueda fue demasiado prolongado. Por favor reconfigure su selección");
		}
		return new ResponseEntity<List<InfoBasicaExpedienteVO>>(detalleExpedientes, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}/detalle")
	public ResponseEntity<DetalleExpedienteVO> getExpediente(@PathVariable("id") Long id,
			@RequestParam(value = "filtro", required = false) String filtro)
			throws NotFoundException, SQLException, NamingException {
		LOGGER.debug("idRegistroCsv: " + id);

		FiltroExpedienteVO filtroExpedienteVO = StringUtils.isNotBlank(filtro)
				? EnumObjectMapper.INSTANCE.fromJson(filtro, new TypeReference<FiltroExpedienteVO>() {
				})
				: null;
		DetalleExpedienteVO detalleExpedienteVO = expedienteService.getExpediente(id, filtroExpedienteVO);

		return new ResponseEntity<DetalleExpedienteVO>(detalleExpedienteVO, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity marcarExpediente(@PathVariable("id") Long id)
			throws NotFoundException, BusinessException, JsonProcessingException {
		expedienteService.marcarExpediente(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
