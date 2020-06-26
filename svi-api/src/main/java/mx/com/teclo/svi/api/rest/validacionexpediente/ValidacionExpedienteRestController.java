package mx.com.teclo.svi.api.rest.validacionexpediente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.servicios.expediente.ExpedienteService;

@RestController
@RequestMapping("/validacionExpediente")
public class ValidacionExpedienteRestController {

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private ExpedienteService expedienteService;
	
	
	@RequestMapping(value = "/tieneAsignaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	public @ResponseBody ResponseEntity<Boolean> tieneAsignaciones() {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		try {
			resp = expedienteService.tieneAsignacionesElUsuario(idUsuario); 
		}catch(Exception ex) {
			resp=false;	
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al buscar las asignaciones del validador: "+ex.getMessage());
		}

		ResponseEntity<Boolean> result =
				new ResponseEntity<Boolean>(resp, estado);
		
		return result;
	}
	
	
	@RequestMapping(value = "/asignacionInicialVO_deprecated", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<List<ArchivoCsvDetallesVO>> getImagenesInicialesVO_deprecated() {
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		List<ArchivoCsvDetallesVO> listaArchivosVO = null;
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaArchivosVO = expedienteService.getArchivosInicialesVO(idUsuario);
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: "+ex.getMessage());
			listaArchivosVO = new ArrayList<ArchivoCsvDetallesVO>();
			estado = HttpStatus.CONFLICT;
		}
		//System.out.println("exito");
		ResponseEntity<List<ArchivoCsvDetallesVO>> result =
				new ResponseEntity<List<ArchivoCsvDetallesVO>>(listaArchivosVO,estado);
		
		return result;
	}
	
	@RequestMapping(value = "/asignacionInicialVO", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
//	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<List<ArchivoCsvDetallesVO>> getImagenesInicialesVO() {
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		List<ArchivoCsvDetallesVO> listaArchivosVO = null;
		HttpStatus estado = HttpStatus.OK;
		 
		try {
			listaArchivosVO = expedienteService.MC_getArchivosInicialesVO(idUsuario);
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: "+ex.getMessage());
			listaArchivosVO = new ArrayList<ArchivoCsvDetallesVO>();
			estado = HttpStatus.CONFLICT;
		}
		//System.out.println("exito");
		ResponseEntity<List<ArchivoCsvDetallesVO>> result =
				new ResponseEntity<List<ArchivoCsvDetallesVO>>(listaArchivosVO,estado);
		
		return result;
	}
	
}
