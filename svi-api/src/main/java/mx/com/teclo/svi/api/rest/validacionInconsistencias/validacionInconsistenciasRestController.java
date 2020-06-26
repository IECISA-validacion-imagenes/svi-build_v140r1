package mx.com.teclo.svi.api.rest.validacionInconsistencias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.servicios.validacion.ValidacionInconsistenciaService;
import mx.com.teclo.svi.negocio.vo.archivoDetalle.ValidacionArchivoDetalleEvaVO;
import mx.com.teclo.svi.negocio.vo.validacion.RevalidacionArchivoDetEvaVO;
import mx.com.teclo.svi.negocio.vo.validacion.registrosInconsistenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresConfigDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignIncidenciasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;

@RestController
@RequestMapping("/validacionInconsistencia")
public class validacionInconsistenciasRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired 
	private ValidacionInconsistenciaService validarInconsistencias;
	
	@Autowired
	private ValidadoresDAO validadoresHDAO;
	
	@Autowired
	private ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	private AsignIncidenciasDAO asignIncidenciasDAO;
	
	
	@RequestMapping(value = "/getArchivosAsignadosInconsistentes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	public @ResponseBody ResponseEntity<List<registrosInconsistenciaVO>> getValidacionInconsistencias() {
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		List<registrosInconsistenciaVO> listaArchivosVO =  new ArrayList<registrosInconsistenciaVO>();
		HttpStatus estado = HttpStatus.OK;
		try {
			listaArchivosVO = validarInconsistencias.getElementosInconsistentes(idUsuario);
		}catch(Exception ex) {
			ex.printStackTrace();
			estado = HttpStatus.CONFLICT;
		}
		ResponseEntity<List<registrosInconsistenciaVO>> result = new ResponseEntity<List<registrosInconsistenciaVO>>(listaArchivosVO,estado);		
		return result;
	}
	
	
	@RequestMapping(value = "/revalidacionRegistro", method = RequestMethod.PUT, produces = "application/json")
//	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	public @ResponseBody ResponseEntity<RevalidacionArchivoDetEvaVO> pruebaPersistencia (@RequestBody RevalidacionArchivoDetEvaVO archivoRevalidacion) {
		Long usuario=  usuarioFirmadoService.getUsuarioFirmadoVO().getId(); 
		HttpStatus estado = HttpStatus.OK;
		Long totalValidadas = validarInconsistencias.saveReValidacion(archivoRevalidacion, usuario);
		archivoRevalidacion.setRevalidacion(1L);
		archivoRevalidacion.setRegValidados(totalValidadas);
		ResponseEntity<RevalidacionArchivoDetEvaVO> result = new ResponseEntity<RevalidacionArchivoDetEvaVO>(archivoRevalidacion, estado);
		return result;
	}
	
	@RequestMapping(value = "/hayInconsistenciasPorValidar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	public @ResponseBody ResponseEntity<Integer>getInconsistenciasPorValidar() {
		
		Long usuario=  usuarioFirmadoService.getUsuarioFirmadoVO().getId(); 
		
		int totalAsignacionesInidencias = asignIncidenciasDAO.getAllInconsistenciasAsignadas(usuario); //TRAER PT O CSV ASIGNADO
		HttpStatus estado = HttpStatus.OK;
		ResponseEntity<Integer> result = new ResponseEntity<Integer>(totalAsignacionesInidencias, estado);
		return result;
	}
	
	private ValidadoresConfigDTO MC_getTipoConfiguracionValidador(long idUsuario) {
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		ValidadoresConfigDTO validadorConfig = validadoresConfigDAO.getValidadorConfigByIdValidador(validadorActivo.getIdValidador());
		return validadorConfig;
	}
}
