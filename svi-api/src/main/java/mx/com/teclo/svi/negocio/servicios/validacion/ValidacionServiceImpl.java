package mx.com.teclo.svi.negocio.servicios.validacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqReporteRV;
import mx.com.teclo.svi.negocio.enumerable.EnumValidacion;
import mx.com.teclo.svi.negocio.utils.Utils;
import mx.com.teclo.svi.negocio.vo.archivoDetalle.ValidacionArchivoDetalleEvaVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtClasifExpedientesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtEntidadVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtPerfilesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.catValidacionSiluetasVO;
import mx.com.teclo.svi.negocio.vo.validacion.ValidacionDatosVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignRegValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtClasifExpedientesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtEntidadDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtPerfilesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSiluetasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSubmarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresConfigDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignRegValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEntidadDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtMarcasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;


@Service
public class ValidacionServiceImpl implements ValidacionService {

	@Autowired
	private ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired
	private AsignValidadorDAO asignValidadorDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;

	@Autowired
	private PtEntidadDAO ptEntidadDAO;
	
	@Autowired
	private PtClasifExpedientesDAO ptClasifExpedientes;
	
	@Autowired
	private PtPerfilesDAO catPerfiles;
	
	@Autowired 	
	private PtSiluetasDAO ptSiluetasDAO;
	
	@Autowired 	
	private PtMarcasDAO ptMarcasDAO;
	
	@Autowired 	
	private PtSubmarcasDAO ptSubmarcasDAO;
	
	@Autowired
	AsignRegValidadorDAO asignRegValidadorDAO;

	@Autowired
	private DetalleValidadorService detalleValidadorService;
	
	@Autowired
	private ValidadoresDAO validadoresHDAO;
	
	@Autowired
	private ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	DetalleValidadorDAO detalleValidadorDAO;
	
	//Validar incidencias 
	@Autowired
	private ValidacionIncidenciasService incidenciasService;

	private static Long PLACA_DISTINTA = 1L;
	private static Long ENTIDAD_DISTINTA = 2L;
	private static Long ERR_CLASIFICACION = 3L;
	private static Long SIN_CAMBIO = 4L;
	
	@Override
	@Transactional
	public void validaRegistroEva(ValidacionArchivoDetalleEvaVO validacionArchivoDetalleEvaVO, Long usuario) {

		ArchivoDetalleEvaDTO archivoDetalleEvaDTO;
		
		AsignRegValidadorDTO asignRegValidadorDTO;
		
		PtSubmarcasDTO ptSubmarcasDTO;
		
		PtMarcasDTO ptMarcasDTO;
		PtPerfilesDTO ptPerfilesDTO;
		
		
		ArchivoCsvDTO archivoCsvDTO;
		
		PtClasifExpedientesDTO ptClasifExpedientesDTO;
		
		PtSiluetasDTO ptSiluetasDTO;
		
		int contador=0;
				
		archivoDetalleEvaDTO = archivoDetalleEvaDAO.findOne(validacionArchivoDetalleEvaVO.getIdRegistroCsv());	
		
		ptClasifExpedientesDTO = ptClasifExpedientes.findOne(validacionArchivoDetalleEvaVO.getIdPtClasifExpe());
		
		ptSiluetasDTO = ptSiluetasDAO.findOne(validacionArchivoDetalleEvaVO.getIdCatValidacion());
		
		ptSubmarcasDTO = ptSubmarcasDAO.findOne(validacionArchivoDetalleEvaVO.getIdSubMarca());
		
		ptPerfilesDTO = catPerfiles.findOne(validacionArchivoDetalleEvaVO.getIdPerfil());
		ptMarcasDTO = ptMarcasDAO.findOne(validacionArchivoDetalleEvaVO.getIdMarca());
		
		
 		archivoCsvDTO=archivoCsvDAO.getArchivoCsvById(validacionArchivoDetalleEvaVO.getIdArchivoCsv());
		
		archivoDetalleEvaDTO.setIdArchivoCsv(archivoCsvDTO);
		
		archivoDetalleEvaDTO.setStValPlacaDelantera(validacionArchivoDetalleEvaVO.getStValPlacaDelantera());
		archivoDetalleEvaDTO.setStValEntidadDelantera(validacionArchivoDetalleEvaVO.getStValEntidadDelantera());
		archivoDetalleEvaDTO.setStValPlacaTrasera(validacionArchivoDetalleEvaVO.getStValPlacaTrasera());
		archivoDetalleEvaDTO.setStValEntidadTrasera(validacionArchivoDetalleEvaVO.getStValEntidadTrasera());
		archivoDetalleEvaDTO.setStPochomovil(validacionArchivoDetalleEvaVO.getStPochomovil());
		archivoDetalleEvaDTO.setStPlacaOfiDelantera(validacionArchivoDetalleEvaVO.getStPlacaOfiDelantera());
		archivoDetalleEvaDTO.setStPlacaOfiTrasera(validacionArchivoDetalleEvaVO.getStPlacaOfiTrasera());
		archivoDetalleEvaDTO.setStDoblePlaca(validacionArchivoDetalleEvaVO.getStDoblePlaca());
		
		archivoDetalleEvaDTO.setIdPerfil(ptPerfilesDTO);
		archivoDetalleEvaDTO.setIdMarca(ptMarcasDTO);
		
		archivoDetalleEvaDTO.setStValPerfil(esPerfilValido(validacionArchivoDetalleEvaVO.getCdPerfil()));
		
		if(validacionArchivoDetalleEvaVO.getIdPtClasifExpe().equals(EnumValidacion.Grupo5.getId())) {
			archivoDetalleEvaDTO.setStValImagenMal(true);
		}else {
			archivoDetalleEvaDTO.setStValImagenMal(false);
		}
		
		
		archivoDetalleEvaDTO.setIdSubMarca(ptSubmarcasDTO);
		archivoDetalleEvaDTO.setFhModificacion(new Date());
		archivoDetalleEvaDTO.setIdUsrModifica(usuario);
		
		int difPerfil=0;
		if(validacionArchivoDetalleEvaVO.getStDifPerfil()) {
			difPerfil=1;
		}
		archivoDetalleEvaDTO.setStDifPerfil((short) difPerfil);
		
		archivoDetalleEvaDTO.setCdPlacaDelantera(retireNullData(archivoDetalleEvaDTO.getCdPlacaDelantera())); 
		archivoDetalleEvaDTO.setCdEntidadDelantera(retireNullData(archivoDetalleEvaDTO.getCdEntidadDelantera()));
		archivoDetalleEvaDTO.setCdPlacaTrasera(retireNullData(archivoDetalleEvaDTO.getCdPlacaTrasera()));
		archivoDetalleEvaDTO.setCdEntidadTrasera(retireNullData(archivoDetalleEvaDTO.getCdEntidadTrasera()));
		archivoDetalleEvaDTO.setCdPerfil(retireNullData(archivoDetalleEvaDTO.getCdPerfil()));
	    
		if(!archivoDetalleEvaDTO.getCdPlacaDelantera().equalsIgnoreCase(validacionArchivoDetalleEvaVO.getCdPlacaDelantera())) {
			//se actualiza el DTO
			archivoDetalleEvaDTO.setCdPlacaDelantera(validacionArchivoDetalleEvaVO.getCdPlacaDelantera());			
			archivoDetalleEvaDTO.setStDifPlacaDelantera((short) 1);
			contador++;
			
		} if(!archivoDetalleEvaDTO.getCdEntidadDelantera().equalsIgnoreCase(validacionArchivoDetalleEvaVO.getCdEntidadDelantera())) {
			
			archivoDetalleEvaDTO.setCdEntidadDelantera(validacionArchivoDetalleEvaVO.getCdEntidadDelantera());
			archivoDetalleEvaDTO.setStDifEntidadDelantera((short) 1);
			contador++;
			
		} if(!archivoDetalleEvaDTO.getCdPlacaTrasera().equalsIgnoreCase(validacionArchivoDetalleEvaVO.getCdPlacaTrasera())) {			
			archivoDetalleEvaDTO.setCdPlacaTrasera(validacionArchivoDetalleEvaVO.getCdPlacaTrasera());	
			archivoDetalleEvaDTO.setStDifPlacaTrasera((short) 1);			
			contador++;
			
		}
		 if(!archivoDetalleEvaDTO.getCdEntidadTrasera().equalsIgnoreCase(validacionArchivoDetalleEvaVO.getCdEntidadTrasera())) {			 
			archivoDetalleEvaDTO.setCdEntidadTrasera(validacionArchivoDetalleEvaVO.getCdEntidadTrasera());
			archivoDetalleEvaDTO.setStDifEntidadTrasera((short) 1);
			contador++;
			
		}if(!archivoDetalleEvaDTO.getCdPerfil().equalsIgnoreCase(validacionArchivoDetalleEvaVO.getCdPerfil())) {			
			archivoDetalleEvaDTO.setStDifPerfil((short) 1);			
			archivoDetalleEvaDTO.setCdPerfil(validacionArchivoDetalleEvaVO.getCdPerfil());
			contador++;
			
		}
		archivoDetalleEvaDTO.setStValidacion((short) 1);
		archivoDetalleEvaDTO.setIdPtClasifExpe(ptClasifExpedientesDTO);
		archivoDetalleEvaDTO.setIdPtSilueta(ptSiluetasDTO);
		archivoDetalleEvaDTO.setStConCambios((contador > 0));
		archivoDetalleEvaDTO.setStInconsistencia(isInconsistente(archivoDetalleEvaDTO, (contador > 0)));
		archivoDetalleEvaDTO.setStPreSeleccion(Boolean.FALSE.booleanValue());
				
		archivoDetalleEvaDTO.setStValidacion((short) 1);
		archivoDetalleEvaDAO.update(archivoDetalleEvaDTO);
		archivoDetalleEvaDAO.flush();
		
		Long idDetalleValidador = detalleValidadorService.saveDetalleValidador(archivoDetalleEvaDTO.getIdRegistroCsv());
		if(archivoDetalleEvaDTO.getStInconsistencia() == BigDecimal.ONE.shortValue()){
			saveInconsistencias(archivoDetalleEvaDTO, idDetalleValidador);
		}
		
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(usuario);
		ValidadoresConfigDTO validadorConfig = validadoresConfigDAO.getValidadorConfigByIdValidador(validadorActivo.getIdValidador());
		
		
		if(validadorConfig.getIdConfiguracion().getIdConfiguracion().equals(EnumTipoBusqReporteRV.REGISTRO.getId())) {
			//Revisar que si este trayendo un registro aqui abajo
			asignRegValidadorDTO = asignRegValidadorDAO.findByIdRegistro(validacionArchivoDetalleEvaVO.getIdRegistroCsv());
			asignRegValidadorDTO.setStValidacion((short) 1);
			asignRegValidadorDTO.setFhCreacion(new Date());
			asignRegValidadorDTO.setIdUsrModifica(usuario);
			asignRegValidadorDAO.update(asignRegValidadorDTO);			
		}

	}


	@Override
	public List<PtEntidadVO> getTodasEntidades() {
		List<PtEntidadDTO> listaDTO = ptEntidadDAO.getCatalogoEntidad();	
		List<PtEntidadVO> listaRetornar = new ArrayList<>();
		Utils.llenadoDatosListaPtEntidadDTOtoListaPtEntidadVO(listaDTO, listaRetornar);
		
		return listaRetornar;
	}
	
	@Override
	public ValidacionDatosVO getRegistrosValidados(Long idArchivo, ValidadoresConfigDTO validador) {
		ValidacionDatosVO datosv = archivoDetalleEvaDAO.archivoEstaValidado(idArchivo, validador);	
		return datosv;
	}
	
	@Override
	@Transactional
	public ArchivoCsvDTO setArchivoValidado(Long idArchivo, Long usuario, Long idValidador) {
		
		ArchivoCsvDTO archivoCsvDTO;
		
//		AsignValidadorDTO asignValidadorDTO;
//		
//		int contador=0;
		
		archivoCsvDTO = archivoCsvDAO.findOne(idArchivo);	
		//archivoCsvDTO.setIdArchivoCsv(idArchivo);
		archivoCsvDTO.setStValidacion((short) 1);
		archivoCsvDTO.setFhModificacion(new Date());
		archivoCsvDTO.setIdUsrModifica(usuario);
		archivoCsvDAO.actualizarArchivo(archivoCsvDTO);
		
		asignValidadorDAO.actualizaEstatusValidacion(idArchivo, idValidador, usuario);
		
		return archivoCsvDTO;
	}


	@Override
	public PtEntidadVO getPtEntidadVOPorId(Long idPtCatalogoEntidadVO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<PtClasifExpedientesVO> getTodasPtClasifExpedientes() {
		List<PtClasifExpedientesDTO> listaExpedientesDTO = ptClasifExpedientes.getClasificacionExpediente();
		List<PtClasifExpedientesVO> listaRetornar = new ArrayList<>();
		Utils.llenadoDatosListaPtClasifExpedientesDTOtoListaPtClasifExpedientesVO(listaExpedientesDTO, listaRetornar);
		
		return listaRetornar;
	}


	@Override
	public List<PtPerfilesVO> getCatalogoPerfiles() {
		List<PtPerfilesDTO> listaPerfilesDTO = catPerfiles.catalogoPerfiles();
		List<PtPerfilesVO> listaRetornar = new ArrayList<>();
		Utils.llenadoDatosListasPtPerfilesDTO(listaPerfilesDTO, listaRetornar);
		return listaRetornar;
	}


	@Override
	public List<catValidacionSiluetasVO> getOpcionesSiluetas() {
		List<PtSiluetasDTO> catalogoDTO = ptSiluetasDAO .getCatValidacionSiluetas();
		List<catValidacionSiluetasVO> catalogoVO = new ArrayList<>();
		Utils.catValidacionSiluetasDTO(catalogoDTO, catalogoVO);
		return catalogoVO;
	}
	
	public String retireNullData(String value) {
		return value==null? "" : value;
	}
	
	public Boolean esPerfilValido(String valor) {
		List<String> perfilesInvalidos = new ArrayList<String>();
		perfilesInvalidos.add("BICICLETA");
		perfilesInvalidos.add("PERSONAS");
		perfilesInvalidos.add("OTROS");
		perfilesInvalidos.add("OTROSOBJ");
		perfilesInvalidos.add("OTROS OBJETOS");
		
		boolean esValido=true;
		if(valor!=null) {
			if(perfilesInvalidos.contains(valor)) {
				esValido=false;
			}
		}else {
			esValido=false;
		}
		return esValido;
	}   
	
	private short isInconsistente(ArchivoDetalleEvaDTO archivoDetalleEvaDTO, boolean conCambios) {
		boolean placaDistinta = (!(archivoDetalleEvaDTO.getStPochomovil() || archivoDetalleEvaDTO.getCdPerfil().toUpperCase().equals("MOTOCICLETA"))
				&& !archivoDetalleEvaDTO.getCdPlacaDelantera().equalsIgnoreCase(archivoDetalleEvaDTO.getCdPlacaTrasera()));
		boolean entidadDistinta = (!(archivoDetalleEvaDTO.getStPochomovil() || archivoDetalleEvaDTO.getCdPerfil().toUpperCase().equals("MOTOCICLETA"))
				&& !archivoDetalleEvaDTO.getCdEntidadDelantera().equalsIgnoreCase(archivoDetalleEvaDTO.getCdEntidadTrasera()));

		boolean clasificacionInvalida = !incidenciasService.validaClasificacionExpedientes(archivoDetalleEvaDTO);

		return (short) ((!conCambios || placaDistinta || entidadDistinta || clasificacionInvalida) ? 1 : 0);
	}
	
	private void saveInconsistencias(final ArchivoDetalleEvaDTO archivoDetalleEvaDTO, Long idDetalleValidador) {
		// Tipos de Incidencia
		if (!archivoDetalleEvaDTO.getStConCambios()) {
			incidenciasService.createIncidenciaDTO(archivoDetalleEvaDTO, idDetalleValidador, SIN_CAMBIO);
		}
		if (!(archivoDetalleEvaDTO.getStPochomovil() || archivoDetalleEvaDTO.getCdPerfil().toUpperCase().equals("MOTOCICLETA"))
				&& !archivoDetalleEvaDTO.getCdPlacaDelantera().equalsIgnoreCase(archivoDetalleEvaDTO.getCdPlacaTrasera())) {
			incidenciasService.createIncidenciaDTO(archivoDetalleEvaDTO, idDetalleValidador, PLACA_DISTINTA);
		}
		if (!(archivoDetalleEvaDTO.getStPochomovil() || archivoDetalleEvaDTO.getCdPerfil().toUpperCase().equals("MOTOCICLETA"))
				&& !archivoDetalleEvaDTO.getCdEntidadDelantera().equalsIgnoreCase(archivoDetalleEvaDTO.getCdEntidadTrasera())) {
			incidenciasService.createIncidenciaDTO(archivoDetalleEvaDTO, idDetalleValidador, ENTIDAD_DISTINTA);
		}
		if (!incidenciasService.validaClasificacionExpedientes(archivoDetalleEvaDTO)) {
			incidenciasService.createIncidenciaDTO(archivoDetalleEvaDTO, idDetalleValidador, ERR_CLASIFICACION);
		}
	}
}

