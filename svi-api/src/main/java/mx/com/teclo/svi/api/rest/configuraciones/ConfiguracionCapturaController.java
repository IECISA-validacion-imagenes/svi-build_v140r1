package mx.com.teclo.svi.api.rest.configuraciones;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.servicios.configuraciones.ConfigCapturaService;
import mx.com.teclo.svi.negocio.vo.configuraciones.FormularioRevalidacionVO;

@RestController
@RequestMapping("/aplicacion/capturas")
public class ConfiguracionCapturaController {

	@Autowired
	private ConfigCapturaService configCapturaService;

	@PutMapping(value = "/{id}/configuracion")
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_CONFIGURACION_APLICACION')")
	public ResponseEntity<FormularioRevalidacionVO> guardarConfigCaptura(@PathVariable("id") Long id,
			@RequestBody FormularioRevalidacionVO configuracionVO) throws NotFoundException, BusinessException {
		configCapturaService.guardarConfigCaptura(configuracionVO);
		return new ResponseEntity<FormularioRevalidacionVO>(configuracionVO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/configuracion")
	public ResponseEntity<FormularioRevalidacionVO> obtenerConfigCaptura(@PathVariable("id") Long id)
			throws NotFoundException, BusinessException {
		return new ResponseEntity<FormularioRevalidacionVO>(configCapturaService.obtenerConfigCaptura(id),
				HttpStatus.OK);
	}
}
