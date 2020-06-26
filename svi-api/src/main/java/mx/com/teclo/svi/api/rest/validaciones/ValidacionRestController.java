package mx.com.teclo.svi.api.rest.validaciones;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.servicios.validacion.ValidacionService;
import mx.com.teclo.svi.negocio.vo.archivoDetalle.ValidacionArchivoDetalleEvaVO;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoMarcaSubVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtClasifExpedientesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtEntidadVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtPerfilesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.catValidacionSiluetasVO;
import mx.com.teclo.svi.negocio.vo.validacion.ValidacionDatosVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CatalogoSubMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresConfigDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;

	

@RestController
@RequestMapping("/validacion")
public class ValidacionRestController {

	@Autowired
	private ValidacionService validacionService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private ValidadoresDAO validadoresHDAO;
	
	@Autowired
	private ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	private CatalogoSubMarcasDAO catalogoSubmarca;
	
	
    
	@RequestMapping(value = "/validacionRegistro", method = RequestMethod.PUT, produces = "application/json")
//	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	public @ResponseBody ResponseEntity<ValidacionArchivoDetalleEvaVO> pruebaPersistencia (@RequestBody ValidacionArchivoDetalleEvaVO validacionArchivoDetalleEvaVO) {
		
		Long usuario=  usuarioFirmadoService.getUsuarioFirmadoVO().getId(); 
		
	//	Prueba p = new Prueba();
		
		HttpStatus estado = HttpStatus.OK;
        
		validacionService.validaRegistroEva(validacionArchivoDetalleEvaVO, usuario);
		ResponseEntity<ValidacionArchivoDetalleEvaVO> result = new ResponseEntity<ValidacionArchivoDetalleEvaVO>(validacionArchivoDetalleEvaVO, estado);
		return result;
	}
	
	@RequestMapping(value = "/archivoEstaValidado", method = RequestMethod.POST, 
			produces = "application/json", consumes = "application/json")
//	@PreAuthorize("hasAnyAuthority('VALIDACION_REGISTRO_EXPEDIENTES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<ValidacionDatosVO> archivoEstaValidado (@RequestBody ValidacionDatosVO datos) {
		
		Long usuario=  usuarioFirmadoService.getUsuarioFirmadoVO().getId(); 
		ValidadoresConfigDTO validador = MC_getTipoConfiguracionValidador(usuario);
		
		ValidacionDatosVO datosv = validacionService.getRegistrosValidados(datos.getIdArchivo(), validador);
		if(datosv.getArchivoValidado()) {
			ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(usuario);
			ArchivoCsvDTO archivo=validacionService.setArchivoValidado(datos.getIdArchivo(), usuario, validadorActivo.getIdValidador());
		}
		
		HttpStatus estado = HttpStatus.OK;
		ResponseEntity<ValidacionDatosVO> result = new ResponseEntity<ValidacionDatosVO>(datosv, estado);
		return result;
	}
	
	@RequestMapping(value = "/todasEntidades", method = RequestMethod.GET, produces = "application/json")
	//@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<List<PtEntidadVO>> getPtEntidadVO() {
		List<PtEntidadVO> listaRetornar = new ArrayList<PtEntidadVO>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetornar = validacionService.getTodasEntidades();
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las Entidades para la UI: "+ex.getMessage());
			listaRetornar = new ArrayList<PtEntidadVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<PtEntidadVO>> result = new ResponseEntity<List<PtEntidadVO>>(listaRetornar,estado);
		
		return result;
	}
	
	@RequestMapping(value = "/todoClasifExpedientes", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	//@Transactional
	public @ResponseBody ResponseEntity<List<PtClasifExpedientesVO>> getPtClasifExpedientesVO() {
		List<PtClasifExpedientesVO> listaRetornar = new ArrayList<PtClasifExpedientesVO>();
		HttpStatus estado = HttpStatus.OK;	
		try {
			listaRetornar = validacionService.getTodasPtClasifExpedientes();
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las Entidades para la UI: "+ex.getMessage());
			listaRetornar = new ArrayList<PtClasifExpedientesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<PtClasifExpedientesVO>> result =
				new ResponseEntity<List<PtClasifExpedientesVO>>(listaRetornar,estado);
		
		return result;
	}	
	
	
	@RequestMapping(value = "/getCatalogoPerfiles", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PtPerfilesVO>> getcatalogoPerfilesVO() {
		List<PtPerfilesVO> listaRetornar = new ArrayList<PtPerfilesVO>();
		HttpStatus estado = HttpStatus.OK;
		try {
			listaRetornar = validacionService.getCatalogoPerfiles();		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las Entidades para la UI: "+ex.getMessage());
			listaRetornar = new ArrayList<PtPerfilesVO>();
			estado = HttpStatus.CONFLICT;
		}
		ResponseEntity<List<PtPerfilesVO>> result = new ResponseEntity<List<PtPerfilesVO>>(listaRetornar,estado);		
		
		return result;
	}	
	
	@RequestMapping(value = "/getCatalogoValidacionSiluetas", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<catValidacionSiluetasVO>> getCatValidacionSiluetas() {
		List<catValidacionSiluetasVO> listaRetornar = new ArrayList<catValidacionSiluetasVO>();
		HttpStatus estado = HttpStatus.OK;
		try {
			listaRetornar = validacionService.getOpcionesSiluetas();		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las Entidades para la UI: "+ex.getMessage());
			listaRetornar = new ArrayList<catValidacionSiluetasVO>();
			estado = HttpStatus.CONFLICT;
		}
		ResponseEntity<List<catValidacionSiluetasVO>> result = new ResponseEntity<List<catValidacionSiluetasVO>>(listaRetornar,estado);		
		
		return result;
	}
	
	/*Metodo para consultar catalogo de marcas submarca y perfil */
	@RequestMapping(value = "/getCatalogoMarcasPerfil", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<CatalogoMarcaSubVO>> getCatalogoMarcasPerfil() {
		List<CatalogoMarcaSubVO> listaRetornar = new ArrayList<CatalogoMarcaSubVO>();
		HttpStatus estado = HttpStatus.OK;
		try {
			listaRetornar = catalogoSubmarca.getCatalogoSubmarcas();		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las Entidades para la UI: "+ex.getMessage());
			estado = HttpStatus.CONFLICT;
		}
		ResponseEntity<List<CatalogoMarcaSubVO>> result = new ResponseEntity<List<CatalogoMarcaSubVO>>(listaRetornar,estado);			
		return result;
	}	
	
	private ValidadoresConfigDTO MC_getTipoConfiguracionValidador(long idUsuario) {
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		ValidadoresConfigDTO validadorConfig = validadoresConfigDAO.getValidadorConfigByIdValidador(validadorActivo.getIdValidador());
		return validadorConfig;
	}
}
