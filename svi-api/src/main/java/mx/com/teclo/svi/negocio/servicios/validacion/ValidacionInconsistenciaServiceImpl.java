package mx.com.teclo.svi.negocio.servicios.validacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svi.negocio.enumerable.EnumValidacion;
import mx.com.teclo.svi.negocio.utils.comun.RutinasTiempoImpl;
import mx.com.teclo.svi.negocio.vo.validacion.RevalidacionArchivoDetEvaVO;
import mx.com.teclo.svi.negocio.vo.validacion.registrosInconsistenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtClasifExpedientesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtPerfilesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSiluetasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSubmarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignIncidenciasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtMarcasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;


@Service
public class ValidacionInconsistenciaServiceImpl implements ValidacionInconsistenciaService {
	
	@Autowired
	private AsignIncidenciasDAO asignIncidenciasDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;
	
	@Autowired
	private ArchivoCsvDAO archivoCsvDAO;
			
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
	
//	@Autowired
//	private ValidadoresDAO validadoresDAO;
	
	@Autowired
	private ValidacionIncidenciasService incidenciasService;
	
	@Autowired
	private DetalleValidadorService detalleValidadorService;
		
	
	@Autowired LoteDAO loteDAO;
	
	private static short REVALIDADO = (short)2;
	private static Long VALIDADO = 1L;
	//private static Long ACTIVO = 1L;
	
	private static Long PLACA_DISTINTA = 1L;
	private static Long ENTIDAD_DISTINTA = 2L;
	private static Long ERR_CLASIFICACION = 3L;
	private static Long SIN_CAMBIO = 4L;
	
	

	@Override 
	@Transactional
	public List<registrosInconsistenciaVO> getElementosInconsistentes(long IdUsuario) {
		List<registrosInconsistenciaVO> response = new ArrayList<registrosInconsistenciaVO>();
  		AsignIncidenciasDTO csvAsignado = asignIncidenciasDAO.getInconsistenciaAsignada(IdUsuario); //TRAER PT O CSV ASIGNADO 
		
		/** Traer de DetalleEVA lista de registros que estan en segunda validacion **/
		if(csvAsignado!=null){
 			response = archivoDetalleEvaDAO.getExpedientesInconsistentes(csvAsignado.getIdArchivoCsv());			
 		}	
		return response;
	}

	@Override
	@Transactional
	public Long saveReValidacion(RevalidacionArchivoDetEvaVO elemToValidate,Long IdUsuario) {
		ArchivoDetalleEvaDTO archivoDetalleEvaDTO;  //Registro Original de detalle eva
		
		//DetalleIncidenciaDTO incidenciaDTO = new DetalleIncidenciaDTO();	
		PtSubmarcasDTO ptSubmarcasDTO;
		PtMarcasDTO ptMarcasDTO;
		PtPerfilesDTO ptPerfilesDTO;
		ArchivoCsvDTO archivoCsvDTO;
		PtClasifExpedientesDTO ptClasifExpedientesDTO;
		PtSiluetasDTO ptSiluetasDTO;
		int contador=0;
		
		/* Funciones para consultar FECHA */
		RutinasTiempoImpl fechas = new RutinasTiempoImpl();
		Date fechaModificacion = fechas.getFechaActualFormatDate();
		
		// Consultar el registro original 
		archivoDetalleEvaDTO = archivoDetalleEvaDAO.findOne(elemToValidate.getIdRegistroCsv());
			
		//Consultar Tipos de Clasificacion de expedientes 
		ptClasifExpedientesDTO = ptClasifExpedientes.findOne(elemToValidate.getIdPtClasifExpe());
		// Consultar Tipos de Siluetas 
		ptSiluetasDTO = ptSiluetasDAO.findOne(elemToValidate.getIdCatValidacion());
		// Consultar SubMarcas 
		ptSubmarcasDTO = ptSubmarcasDAO.findOne(elemToValidate.getIdSubMarca());
		//Consultar Perfiles de Auto 
		ptPerfilesDTO = catPerfiles.findOne(elemToValidate.getIdPerfil());
		//Consultar Marcas Autos 
		ptMarcasDTO = ptMarcasDAO.findOne(elemToValidate.getIdMarca());
		//Consultar Validador por ID de Usuario 
		//ValidadoresDTO validador = validadoresDAO.getValidadorByIdUsuario(IdUsuario);
 		
		archivoCsvDTO=archivoCsvDAO.getArchivoCsvById(elemToValidate.getIdArchivoCsv());
		
		archivoDetalleEvaDTO.setIdArchivoCsv(archivoCsvDTO);
		archivoDetalleEvaDTO.setStValPlacaDelantera(elemToValidate.getStValPlacaDelantera());
		archivoDetalleEvaDTO.setStValEntidadDelantera(elemToValidate.getStValEntidadDelantera());
		archivoDetalleEvaDTO.setStValPlacaTrasera(elemToValidate.getStValPlacaTrasera());
		archivoDetalleEvaDTO.setStValEntidadTrasera(elemToValidate.getStValEntidadTrasera());
		archivoDetalleEvaDTO.setStPochomovil(elemToValidate.getStPochomovil());
		archivoDetalleEvaDTO.setStPlacaOfiDelantera(elemToValidate.getStPlacaOfiDelantera());
		archivoDetalleEvaDTO.setStPlacaOfiTrasera(elemToValidate.getStPlacaOfiTrasera());
		archivoDetalleEvaDTO.setStDoblePlaca(elemToValidate.getStDoblePlaca());
		
		archivoDetalleEvaDTO.setIdPerfil(ptPerfilesDTO);
		archivoDetalleEvaDTO.setIdMarca(ptMarcasDTO);
		
		archivoDetalleEvaDTO.setStValPerfil(esPerfilValido(elemToValidate.getCdPerfil()));
		
		if(elemToValidate.getIdPtClasifExpe().equals(EnumValidacion.Grupo5.getId())) {
			archivoDetalleEvaDTO.setStValImagenMal(true);
		}else {
			archivoDetalleEvaDTO.setStValImagenMal(false);
		}
		
		archivoDetalleEvaDTO.setIdSubMarca(ptSubmarcasDTO);
		archivoDetalleEvaDTO.setFhModificacion(new Date());
		archivoDetalleEvaDTO.setIdUsrModifica(IdUsuario);
		archivoDetalleEvaDTO.setSt2daValidacion((short)2);
		
		int difPerfil=0;
		if(elemToValidate.getStDifPerfil()) {
			difPerfil=1;
		}
		archivoDetalleEvaDTO.setStDifPerfil((short) difPerfil);
		
		
		archivoDetalleEvaDTO.setCdPlacaDelantera(retireNullData(archivoDetalleEvaDTO.getCdPlacaDelantera())); 
		archivoDetalleEvaDTO.setCdEntidadDelantera(retireNullData(archivoDetalleEvaDTO.getCdEntidadDelantera()));
		archivoDetalleEvaDTO.setCdPlacaTrasera(retireNullData(archivoDetalleEvaDTO.getCdPlacaTrasera()));
		archivoDetalleEvaDTO.setCdEntidadTrasera(retireNullData(archivoDetalleEvaDTO.getCdEntidadTrasera()));
		archivoDetalleEvaDTO.setCdPerfil(retireNullData(archivoDetalleEvaDTO.getCdPerfil()));
	    
		if(!archivoDetalleEvaDTO.getCdPlacaDelantera().equalsIgnoreCase(elemToValidate.getCdPlacaDelantera())) {
			//se actualiza el DTO
			archivoDetalleEvaDTO.setCdPlacaDelantera(elemToValidate.getCdPlacaDelantera());
			archivoDetalleEvaDTO.setStDifPlacaDelantera((short) 1);
			contador++;	
		} if(!archivoDetalleEvaDTO.getCdEntidadDelantera().equalsIgnoreCase(elemToValidate.getCdEntidadDelantera())) {
			archivoDetalleEvaDTO.setCdEntidadDelantera(elemToValidate.getCdEntidadDelantera());
			archivoDetalleEvaDTO.setStDifEntidadDelantera((short) 1);
			contador++;
		} if(!archivoDetalleEvaDTO.getCdPlacaTrasera().equalsIgnoreCase(elemToValidate.getCdPlacaTrasera())) {	
			archivoDetalleEvaDTO.setCdPlacaTrasera(elemToValidate.getCdPlacaTrasera());	
			archivoDetalleEvaDTO.setStDifPlacaTrasera((short) 1);
			contador++;
		}if(!archivoDetalleEvaDTO.getCdEntidadTrasera().equalsIgnoreCase(elemToValidate.getCdEntidadTrasera())) {	 
			archivoDetalleEvaDTO.setCdEntidadTrasera(elemToValidate.getCdEntidadTrasera());	
			archivoDetalleEvaDTO.setStDifEntidadTrasera((short) 1);
			contador++;		
		}if(!archivoDetalleEvaDTO.getCdPerfil().equalsIgnoreCase(elemToValidate.getCdPerfil())) {
			archivoDetalleEvaDTO.setStDifPerfil((short) 1);
			archivoDetalleEvaDTO.setCdPerfil(elemToValidate.getCdPerfil());	
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
		
		//Guardar Dato en tabla de relacion quien y que se cambio T22 //
      		Long idDetalleValidador = detalleValidadorService.saveDetalleValidador(archivoDetalleEvaDTO.getIdRegistroCsv());
		if(archivoDetalleEvaDTO.getStInconsistencia() == BigDecimal.ONE.shortValue()){
			saveInconsistencias(archivoDetalleEvaDTO, idDetalleValidador);
		}
		
		Long totalValidas = archivoDetalleEvaDAO.getRegistrosInconsistentesValidadosAsignacion(elemToValidate.getIdArchivoCsv());
		totalValidas=elemToValidate.getTotalRegistros()-totalValidas;

//		Long totalValidas = elemToValidate.getRegValidados() + 1L; 
		
		if(totalValidas.equals(elemToValidate.getTotalRegistros())){
			//se modificaran datos Tablas donde se termina la re validacion 
			AsignIncidenciasDTO csvAsignado = asignIncidenciasDAO.getInconsistenciaAsignada(IdUsuario); //Trae datos del ARchivo a Validar 
			csvAsignado.setStValidacion(VALIDADO);
			csvAsignado.setFhModificacion(fechaModificacion);
			csvAsignado.setIdUsrModifica(IdUsuario);
			asignIncidenciasDAO.update(csvAsignado);
			
			Boolean isUpdated = archivoCsvDAO.updateArchivoCsvDTOByID(csvAsignado.getIdArchivoCsv(),IdUsuario);
			Long idLote = archivoCsvDAO.getIDLoteByIdArchivo(csvAsignado.getIdArchivoCsv());
			//En lote cambiar a estatus 2 indicando que ya se revalido TCI003D_PT_ARCHIVO_CSV
//			ArchivoCsvDTO ArchivoCSV = archivoCsvDAO.getArchivoCsvById(csvAsignado.getIdArchivoCsv());
//			ArchivoCSV.setStRevalidacion(REVALIDADO);
//			ArchivoCSV.setFhModificacion(fechaModificacion);
//			ArchivoCSV.setIdUsrModifica(IdUsuario);
//			archivoCsvDAO.actualizarArchivo(ArchivoCSV);	

			Long totCSVxPT = archivoCsvDAO.getLotesinRevalidacion(idLote);
			Long totCSVxPTRevalidados = archivoCsvDAO.getLotesconRevalidacion(idLote);
			if(totCSVxPTRevalidados.equals(totCSVxPT)){
				LoteDTO lote = loteDAO.findOne(idLote);
				lote.setStRevalidacion(REVALIDADO);
				lote.setFhModificacion(fechaModificacion);
				lote.setIdUsrModifica(IdUsuario);
				loteDAO.update(lote);
			}
	//		getElementosInconsistentes(IdUsuario);
			
		}
		return totalValidas;
		
	}	
	/*Metodo para Eliminar cadenas Vacias */
	public String retireNullData(String value) {
		return value==null? "" : value;
	}
	
	/**/
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
