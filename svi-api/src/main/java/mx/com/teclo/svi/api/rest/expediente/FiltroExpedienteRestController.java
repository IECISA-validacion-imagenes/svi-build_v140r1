package mx.com.teclo.svi.api.rest.expediente;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svi.negocio.servicios.expediente.FiltroExpedienteService;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO;
import mx.com.teclo.svi.negocio.vo.expediente.EtiquetaExpedienteVO;

@RestController
public class FiltroExpedienteRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroExpedienteRestController.class);

	@Autowired
	private FiltroExpedienteService filtroExpedienteService;

	@PostMapping("/expedientes/filtros")
	public ResponseEntity<EtiquetaExpedienteVO> crearFiltro(@RequestBody EtiquetaExpedienteVO etiquetaExpedienteVO)
			throws NotFoundException, BusinessException, JsonProcessingException {
		LOGGER.info(etiquetaExpedienteVO.toString());
		
		return new ResponseEntity<EtiquetaExpedienteVO>(filtroExpedienteService.registrarFiltro(etiquetaExpedienteVO),
				HttpStatus.OK);

	}

	@GetMapping("/expedientes/filtros/{id}")
	public ResponseEntity<DetalleFiltroVO> getExpediente(@PathVariable("id") Long id)
			throws NotFoundException, SQLException, NamingException {
		LOGGER.debug("idFiltro: " + id);

		DetalleFiltroVO detalleExpedienteVO = filtroExpedienteService.getFiltro(id);

		return new ResponseEntity<DetalleFiltroVO>(detalleExpedienteVO, HttpStatus.OK);
	}

}
