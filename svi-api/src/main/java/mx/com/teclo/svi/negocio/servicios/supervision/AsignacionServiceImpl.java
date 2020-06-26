package mx.com.teclo.svi.negocio.servicios.supervision;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.RollbackException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.servicios.expediente.CatalogoExpedienteService;
import mx.com.teclo.svi.negocio.utils.comun.Messages;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.ArchivoRevalidacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignaRevalidacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.AsignacionRevaExpedientesVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.GrupoExpedientesVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CicloValidacionDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleIncidenciaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleRevalidaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoDetRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoRevalidaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtEtiquetasRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtFiltroDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresConfigDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignIncidenciasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.CicloValidacionDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleRevalidaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoDetRevalDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoRevalidaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEtiquetasRevalDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;

@Service
public class AsignacionServiceImpl implements AsignacionService {

	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;

	@Autowired
	private UsuarioFirmadoService contexto;

	@Autowired
	private CatalogoExpedienteService catalogoExpedienteService;

	@Autowired
	private PtFiltroDetalleEvaDAO filtroDAO;
	
	@Autowired
	private LoteDAO loteDAO;
	
	@Autowired
	private ArchivoCsvDAO csvDAO;
	
	@Autowired
	private Messages messages;
	
	@Autowired
	CicloValidacionDAO cicloValidacionDAO; 
	
	@Autowired
	MotivoRevalidaDAO motivoRevalidaDAO;
	
	@Autowired
	ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired
	MotivoCsvDAO motivoCsvDAO;
	
	@Autowired
	AsignIncidenciasDAO asignIncidenciasDAO;
	
	@Autowired
	DetalleRevalidaDAO detalleRevalidaDAO; 
	
	@Autowired
	MotivoDetRevalDAO motivoDetRevalDAO; 
	
	@Autowired
	DetalleIncidenciaDAO detalleIncidenciaDAO; 
	
	@Autowired
	PtEtiquetasRevalDAO ptEtiquetasRevalDAO; 
	

	@Transactional(readOnly = true)
	public List<InfoBasicaExpValidadoVO> getExpedientesValidados(FiltroExpedienteVO filtro, Short nivel)
			throws NotFoundException, BusinessException, RollbackException {
		if (!isFiltroValido(filtro)) {
			throw new IllegalArgumentException("El filtro de b\u00FAsqueda es inv\u00E1lido");
		}
		return archivoDetalleEvaDAO.getExpedientesValidados(filtro, nivel);
	}

	@Transactional(readOnly = true)
	@Override
	public DetalleAsignacionVO getDetalleNoAsignado(Long idPtLote, Long idPtCsv, Long idEtiqueta,
			FiltroExpedienteVO filtro) throws NotFoundException, BusinessException {
		
		DetalleAsignacionVO detalleAsignacionVO = getItemAsignado(idPtLote, idPtCsv, idEtiqueta, filtro);
		detalleAsignacionVO.setFiltroAplicado(filtro);
		FiltroExpedienteVO filtroSeleccionado = (idEtiqueta != null) ? EnumObjectMapper.INSTANCE
				.fromJson(filtroDAO.findOne(idEtiqueta).getNbFiltro(), new TypeReference<FiltroExpedienteVO>() {
				}) : filtro;

		detalleAsignacionVO
				.setAsignaciones(archivoDetalleEvaDAO.getDetalleNoAsignado(idPtLote, idPtCsv, filtroSeleccionado));
		return detalleAsignacionVO;
	}

	private boolean isFiltroValido(FiltroExpedienteVO filtro) {
		if (filtro == null) {
			throw new IllegalArgumentException("El filtro de b\u00F3squeda es requerido");
		}

		return !(StringUtils.isBlank(filtro.getCdPlaca()) && StringUtils.isBlank(filtro.getNuExpediente())
				&& filtro.getListaCsvs().isEmpty() && filtro.getListaEntregas().isEmpty()
				&& filtro.getListaLotes().isEmpty() && filtro.getListaMarcas().isEmpty()
				&& filtro.getListaSubMarcas().isEmpty() && filtro.getListaPerfiles().isEmpty()
				&& filtro.getListaIncidencias().isEmpty());

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro, Short nivel)
			throws NotFoundException, BusinessException, RollbackException {
		Map<String, Object> res = catalogoExpedienteService.getCatalogos();
		if (filtro != null) {
			res.put("expedientes", getExpedientesValidados(filtro, nivel));
		}
		return res;
	}
	
	
	private DetalleAsignacionVO getItemAsignado(Long idPtLote, Long idPtCsv, Long idEtiqueta, FiltroExpedienteVO filtro) throws BusinessException {
		if (idPtLote == null && idPtCsv == null && idEtiqueta == null && filtro == null) {
			throw new BusinessException(messages.get("asignacion.detalle.nulo"));
		}
		
		if(idPtLote != null) {
			return loteDAO.getDetalleAsignacion(idPtLote);
		}
		
		if(idPtCsv != null) {
			return csvDAO.getDetalleAsignacion(idPtCsv);
		}
		
		if(idEtiqueta != null) {
			return filtroDAO.getDetalleAsignacion(idEtiqueta);
		}
		return null;
		
	}
	
	//Adicionado Gibran
	@Transactional(readOnly = true)
	@Override
	public AsignaRevalidacionVO asignacionPT(DetalleAsignacionVO detalleAsignacionVO, 
			AsignaRevalidacionVO asignaRevaLoteVO, FiltroExpedienteVO filtroExpedienteVO) throws BusinessException {
		
		AsignaRevalidacionVO asignacion = contructCSVVOForAsignacionPT(detalleAsignacionVO, asignaRevaLoteVO);
		
		List<ArchivoRevalidacionVO> listaArchivosCsv = new ArrayList<ArchivoRevalidacionVO>();
		try {
			listaArchivosCsv = constructListaArchivosCSVForAsignacion(asignacion.getListaArchivosCsv(), filtroExpedienteVO);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asignacion.setListaArchivosCsv(listaArchivosCsv);
		
		return asignacion;
		
//		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
//		CicloValidacionDTO  cicloValDTO= cicloValidacionDAO.getCicloVigente();//TCI028C_PT_CICLO_VALIDACION
//		
//		Boolean seReasigno = procesoDeAsignacionRevalidacion(listaArchivosCsv, cicloValDTO, idUsuario);
//			
//		return seReasigno;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public AsignaRevalidacionVO asignacionPTCSV(AsignaRevalidacionVO asignacion, FiltroExpedienteVO filtroExpedienteVO) throws BusinessException {
		
		List<ArchivoRevalidacionVO> listaArchivosCsv = new ArrayList<ArchivoRevalidacionVO>();
		try {
			listaArchivosCsv = constructListaArchivosCSVForAsignacion(asignacion.getListaArchivosCsv(), filtroExpedienteVO);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asignacion.setListaArchivosCsv(listaArchivosCsv);

		return asignacion;
	}
	
	@Transactional
	@Override
	public AsignaRevalidacionVO asignaProceso(AsignaRevalidacionVO asignacion) {
		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
		CicloValidacionDTO  cicloValDTO= cicloValidacionDAO.getCicloVigente();//TCI028C_PT_CICLO_VALIDACION
		asignacion = procesoDeAsignacionRevalidacion2(asignacion, cicloValDTO, idUsuario);
		return asignacion;
	}
	
	
	
	
	
	@Transactional
	public void insertaNivelArchivo(LoteDTO lDTO, ArchivoCsvDTO acsvDTO, MotivoCsvDTO mcsvDTO, AsignIncidenciasDTO aiDTO) {
		loteDAO.update(lDTO);
		
		archivoCsvDAO.saveOrUpdate(acsvDTO);
		
		motivoCsvDAO.save(mcsvDTO);
		
		asignIncidenciasDAO.save(aiDTO);
	}
	
	@Transactional
	public void insertaNivelExpediente(List<DetalleRevalidaDTO> listdetrevDTO, 
			List<MotivoDetRevalDTO> listmodetrevDTO) {
		System.out.println("Inicio transaccion expediente: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		for(int i=0;i<listdetrevDTO.size();i++) {
			detalleRevalidaDAO.save(listdetrevDTO.get(0));
		}
		
		for(int i=0;i<listmodetrevDTO.size();i++) {
			motivoDetRevalDAO.save(listmodetrevDTO.get(0));
		}
		System.out.println("Final transaccion expediente: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
	} 
	
	public AsignaRevalidacionVO procesoDeAsignacionRevalidacion2(AsignaRevalidacionVO asignacion, 
			CicloValidacionDTO  cicloValDTO, Long idUsuario) {
		for(int i=0;i<asignacion.getListaArchivosCsv().size();i++) {
			ValidadoresConfigDTO vcDTO = validadoresConfigDAO.getValidadorConfigByIdValidador(asignacion.getListaArchivosCsv().get(i).getIdValidador());
			
			ArchivoCsvDTO acsvDTO = archivoCsvDAO.findOne(asignacion.getListaArchivosCsv().get(i).getIdArchivo());
			MotivoRevalidaDTO mrDTO = motivoRevalidaDAO.findOne(asignacion.getListaArchivosCsv().get(i).getIdMotivo());
			
			MotivoCsvDTO mcsvDTO = new MotivoCsvDTO();
			mcsvDTO.setIdArchivoCsv(acsvDTO);
			mcsvDTO.setIdMotivoReva(mrDTO);
			mcsvDTO.setIdValidador(vcDTO.getIdValidador());
			mcsvDTO.setStActivo(Boolean.TRUE);
			mcsvDTO.setFhCreacion(new Date());
			mcsvDTO.setIdUsrCreacion(idUsuario);
			mcsvDTO.setFhModificacion(new Date()); 
			mcsvDTO.setIdUsrModifica(idUsuario);
			mcsvDTO.setIdCicloValidacion(cicloValDTO);
			
			AsignIncidenciasDTO aiDTO = new AsignIncidenciasDTO();
			aiDTO.setIdValidadorConfig(vcDTO);
			aiDTO.setIdArchivoCsv(acsvDTO.getIdArchivoCsv());
			aiDTO.setIdPtLote(null);
			aiDTO.setStActivo(1L);
			aiDTO.setStValidacion(0L);
			aiDTO.setFhCreacion(new Date());
			aiDTO.setIdUsrCreacion(idUsuario);
			aiDTO.setFhModificacion(new Date());
			aiDTO.setIdUsrModifica(idUsuario);
			
			try{
				
				acsvDTO.setStRevalidacion((short)1);
				acsvDTO.setIdUsrModifica(idUsuario);
				acsvDTO.setFhModificacion(new Date());
				
				LoteDTO lDTO = acsvDTO.getIdPtLote();
				
				lDTO.setStRevalidacion((short) 1);
				lDTO.setIdUsrModifica(idUsuario);
				lDTO.setFhModificacion(new Date());
				
				loteDAO.update(lDTO);
				archivoCsvDAO.saveOrUpdate(acsvDTO);
				motivoCsvDAO.save(mcsvDTO);
				asignIncidenciasDAO.save(aiDTO);
				asignacion.getListaArchivosCsv().get(i).setIdMotivoCsv(mcsvDTO.getIdMotivoCsv());
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{

			}		
		}
		return asignacion;
	}
	
	@Override
	public List<AsignacionRevaExpedientesVO> continueProcesoDeAsignacionRevalidacion2(AsignaRevalidacionVO asignacion) {
		
		List<AsignacionRevaExpedientesVO> listasignaExpedientes = new ArrayList<AsignacionRevaExpedientesVO>();
		AsignacionRevaExpedientesVO asignaExpedientes = null;
		for(int i=0;i<asignacion.getListaArchivosCsv().size();i++) {
			List<GrupoExpedientesVO> listaGrupoExpedientes = asignacion.getListaArchivosCsv().get(i).getListaGrupoExpedientes();
			//Recorremos cada grupo
			for(int j=0;j<listaGrupoExpedientes.size();j++) {
				//recorremos todos expedientes por cada grupo
				List<Long> listaExpedientes = listaGrupoExpedientes.get(j).getListaExpedientes();
				
				DetalleRevalidaDTO detrevDTO = null;//TCI026D_PT_DETALLE_REVALIDA
				MotivoDetRevalDTO modetrevDTO = null;//TCI027D_PT_MOTIVO_DET_REVAL
				
				for(int k=0;k<listaExpedientes.size();k++) {
					asignaExpedientes =  new AsignacionRevaExpedientesVO();
					asignaExpedientes.setIdArchivo(asignacion.getListaArchivosCsv().get(i).getIdArchivo());
					asignaExpedientes.setIdMotivoCSV(asignacion.getListaArchivosCsv().get(i).getIdMotivoCsv());
					asignaExpedientes.setIdExpediente(listaExpedientes.get(k));
					asignaExpedientes.setIdMotivoExpediente(listaGrupoExpedientes.get(j).getIdMotivo());
					listasignaExpedientes.add(asignaExpedientes);
				}
			}
		}
		
		return listasignaExpedientes;
	}

	
	@Transactional
	@Override
	public Boolean updateMasivo(AsignaRevalidacionVO asignacion) {
		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
		
		for(int i=0;i<asignacion.getListaArchivosCsv().size();i++) {
			List<GrupoExpedientesVO> listaGrupoExpedientes = asignacion.getListaArchivosCsv().get(i).getListaGrupoExpedientes();
			//Recorremos cada grupo
			for(int j=0;j<listaGrupoExpedientes.size();j++) {
				//recorremos todos expedientes por cada grupo
				List<Long> listaExpedientes = listaGrupoExpedientes.get(j).getListaExpedientes();
				archivoDetalleEvaDAO.habilitarRevalidacion(listaExpedientes, idUsuario);
				detalleIncidenciaDAO.deshabilitarIncidenciasPorRegistros(listaExpedientes, idUsuario);
			}
		}
		
		return true;
	}
	
	
	
	
	@Transactional
	@Override
	public Boolean insertaNivelExpediente2(List<AsignacionRevaExpedientesVO> subListasignacion) {
		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
		CicloValidacionDTO  cicloValDTO= cicloValidacionDAO.getCicloVigente();//TCI028C_PT_CICLO_VALIDACION
		
		System.out.println("Inicio transaccion expediente: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		
		DetalleRevalidaDTO detrevDTO = null;
		MotivoDetRevalDTO modetrevDTO = null;
		ArchivoDetalleEvaDTO adetevaDTO = null;
		MotivoCsvDTO mcsvDTO = null;
		
		for(int i=0;i<subListasignacion.size();i++) {
			System.out.println(i);
			detrevDTO = new DetalleRevalidaDTO();
			adetevaDTO = archivoDetalleEvaDAO.findOne(subListasignacion.get(i).getIdExpediente());
			mcsvDTO = motivoCsvDAO.findOne(subListasignacion.get(i).getIdMotivoCSV());
			
			detrevDTO.setIdRegistroCSV(adetevaDTO);
			detrevDTO.setIdMotivoCSV(mcsvDTO);
			detrevDTO.setIdCicloValidacion(cicloValDTO);
			detrevDTO.setStActivo(Boolean.TRUE);
			detrevDTO.setFechaCreacion(new Date());
			detrevDTO.setIdUserCreacion(idUsuario);
			detrevDTO.setFechaModificacion(new Date()); 
			detrevDTO.setIdUserModifica(idUsuario);
			
			detalleRevalidaDAO.save(detrevDTO);
			
			MotivoRevalidaDTO mr2DTO = motivoRevalidaDAO.findOne(subListasignacion.get(i).getIdMotivoExpediente());
			
			modetrevDTO = new MotivoDetRevalDTO();
			modetrevDTO.setIdDetalleReval(detrevDTO);
			modetrevDTO.setIdMotivoReva(mr2DTO);//aqui corregir
			modetrevDTO.setStActivo(Boolean.TRUE);
			modetrevDTO.setFechaCreacion(new Date());
			modetrevDTO.setIdUserCreacion(idUsuario);
			modetrevDTO.setFechaModificacion(new Date()); 
			modetrevDTO.setIdUserModifica(idUsuario);
			
			motivoDetRevalDAO.save(modetrevDTO);
			
		}
		return true;
	} 
	
	
	public Boolean procesoDeAsignacionRevalidacion(List<ArchivoRevalidacionVO> listaArchivosCsv, 
			CicloValidacionDTO  cicloValDTO, Long idUsuario) {
		Boolean res = true;
		for(int i=0;i<listaArchivosCsv.size();i++) {
			ValidadoresConfigDTO vcDTO = validadoresConfigDAO.findOne(listaArchivosCsv.get(i).getIdValidador());
			
			ArchivoCsvDTO acsvDTO = archivoCsvDAO.findOne(listaArchivosCsv.get(i).getIdArchivo());
			MotivoRevalidaDTO mrDTO = motivoRevalidaDAO.findOne(listaArchivosCsv.get(i).getIdMotivo());
			
			MotivoCsvDTO mcsvDTO = new MotivoCsvDTO();
			mcsvDTO.setIdArchivoCsv(acsvDTO);
			mcsvDTO.setIdMotivoReva(mrDTO);
			mcsvDTO.setIdValidador(vcDTO.getIdValidador());
			mcsvDTO.setStActivo(Boolean.TRUE);
			mcsvDTO.setFhCreacion(new Date());
			mcsvDTO.setIdUsrCreacion(idUsuario);
			mcsvDTO.setFhModificacion(new Date()); 
			mcsvDTO.setIdUsrModifica(idUsuario);
			mcsvDTO.setIdCicloValidacion(cicloValDTO);
			
			AsignIncidenciasDTO aiDTO = new AsignIncidenciasDTO();
			aiDTO.setIdValidadorConfig(vcDTO);
			aiDTO.setIdArchivoCsv(acsvDTO.getIdArchivoCsv());
			aiDTO.setIdPtLote(null);
			aiDTO.setStActivo(1L);
			aiDTO.setStValidacion(0L);
			aiDTO.setFhCreacion(new Date());
			aiDTO.setIdUsrCreacion(idUsuario);
			aiDTO.setFhModificacion(new Date());
			aiDTO.setIdUsrModifica(idUsuario);
			
			try{
				
				acsvDTO.setStRevalidacion((short)1);
				acsvDTO.setIdUsrModifica(idUsuario);
				acsvDTO.setFhModificacion(new Date());
				
				LoteDTO lDTO = acsvDTO.getIdPtLote();
				
				lDTO.setStRevalidacion((short) 1);
				lDTO.setIdUsrModifica(idUsuario);
				lDTO.setFhModificacion(new Date());
				
//				loteDAO.update(lDTO);
//				
//				archivoCsvDAO.saveOrUpdate(acsvDTO);
//				
//				motivoCsvDAO.save(mcsvDTO);
//				
//				asignIncidenciasDAO.save(aiDTO);
//				insertaNivelArchivo(lDTO, acsvDTO, mcsvDTO, aiDTO);
				
				loteDAO.update(lDTO);
				archivoCsvDAO.saveOrUpdate(acsvDTO);
				motivoCsvDAO.save(mcsvDTO);
				asignIncidenciasDAO.save(aiDTO);
				
				//Aqui insertar en TCI026D_PT_DETALLE_REVALIDA y TCI027D_PT_MOTIVO_DET_REVAL
				List<DetalleRevalidaDTO> listdetrevDTO = null;
				List<MotivoDetRevalDTO> listmodetrevDTO = null;
				
				DetalleRevalidaDTO detrevDTO = null;//TCI026D_PT_DETALLE_REVALIDA
				MotivoDetRevalDTO modetrevDTO = null;//TCI027D_PT_MOTIVO_DET_REVAL
				ArchivoDetalleEvaDTO adetevaDTO = null;
				
				List<GrupoExpedientesVO> listaGrupoExpedientes = listaArchivosCsv.get(i).getListaGrupoExpedientes();
				//Recorremos cada grupo
				for(int j=0;j<listaGrupoExpedientes.size();j++) {
					
					listdetrevDTO = new ArrayList<DetalleRevalidaDTO>();
					listmodetrevDTO = new ArrayList<MotivoDetRevalDTO>();
					
					List<Long> listaExpedientes = listaGrupoExpedientes.get(j).getListaExpedientes();
					//Recorremos los expedientes de cada grupo
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
 
					//Creamos la lista que se dividira para seccionar los commits
					LocalDateTime nowListados = LocalDateTime.now();  
					System.out.println("Inicia nowListados: "+dtf.format(nowListados));
					for(int k=0;k<listaExpedientes.size();k++) {
						System.out.println(k);
						detrevDTO = new DetalleRevalidaDTO();
						adetevaDTO = archivoDetalleEvaDAO.findOne(listaExpedientes.get(k));
						
						detrevDTO.setIdRegistroCSV(adetevaDTO);
						detrevDTO.setIdMotivoCSV(mcsvDTO);
						detrevDTO.setIdCicloValidacion(cicloValDTO);
						detrevDTO.setStActivo(Boolean.TRUE);
						detrevDTO.setFechaCreacion(new Date());
						detrevDTO.setIdUserCreacion(idUsuario);
						detrevDTO.setFechaModificacion(new Date()); 
						detrevDTO.setIdUserModifica(idUsuario);
						
						listdetrevDTO.add(detrevDTO);
//						detalleRevalidaDAO.save(detrevDTO);
						
						MotivoRevalidaDTO mr2DTO = motivoRevalidaDAO.findOne(listaGrupoExpedientes.get(j).getIdMotivo());
						
						modetrevDTO = new MotivoDetRevalDTO();
						modetrevDTO.setIdDetalleReval(detrevDTO);
						modetrevDTO.setIdMotivoReva(mr2DTO);//aqui corregir
						modetrevDTO.setStActivo(Boolean.TRUE);
						modetrevDTO.setFechaCreacion(new Date());
						modetrevDTO.setIdUserCreacion(idUsuario);
						modetrevDTO.setFechaModificacion(new Date()); 
						modetrevDTO.setIdUserModifica(idUsuario);
						
						listmodetrevDTO.add(modetrevDTO);
//						motivoDetRevalDAO.save(modetrevDTO);
	
					}
					//Creamos subArreglos para realizar los commits sobre la transacción
					LocalDateTime nowSubLista1 = LocalDateTime.now();  
					System.out.println("Inicia nowSubLista: "+dtf.format(nowSubLista1));
					int contador=0;
					List<DetalleRevalidaDTO> sublistdetrevDTO = new ArrayList<DetalleRevalidaDTO>();
					List<MotivoDetRevalDTO> sublistmodetrevDTO = new ArrayList<MotivoDetRevalDTO>();
					for(int rec=0;rec<listdetrevDTO.size();i++) {
						contador++;
						sublistdetrevDTO.add(listdetrevDTO.get(rec));
						sublistmodetrevDTO.add(listmodetrevDTO.get(rec));
												
						if (contador % 300 == 0||contador==listaExpedientes.size()) {
							insertaNivelExpediente(sublistdetrevDTO, sublistmodetrevDTO);
							sublistdetrevDTO = new ArrayList<DetalleRevalidaDTO>();
							sublistmodetrevDTO = new ArrayList<MotivoDetRevalDTO>();
						}
					}
					LocalDateTime nowSubLista2 = LocalDateTime.now();  
					System.out.println("Final nowSubLista: "+dtf.format(nowSubLista2));
					//End
					//
					LocalDateTime nowhabRev = LocalDateTime.now();  
					System.out.println("Inicia hanilitarRevalidacion: "+nowhabRev);
					archivoDetalleEvaDAO.habilitarRevalidacion(listaExpedientes, idUsuario);
					LocalDateTime nowhabRev2 = LocalDateTime.now();  
					System.out.println("Fin hanilitarRevalidacion: "+nowhabRev2);
					//
					LocalDateTime nowdeshabRev = LocalDateTime.now();  
					System.out.println("Inicia deshabilitarRevalidacion: "+nowdeshabRev);
					detalleIncidenciaDAO.deshabilitarIncidenciasPorRegistros(listaExpedientes, idUsuario);
					LocalDateTime nowdeshabRev2 = LocalDateTime.now();  
					System.out.println("fin deshabilitarRevalidacion: "+nowdeshabRev2);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
				res = false;
			}finally{

			}
		}
		res = true;
		return res;
	}
	
	
	@Transactional(readOnly = true)
	public List<ArchivoRevalidacionVO> constructListaArchivosCSVForAsignacion(List<ArchivoRevalidacionVO> listaArchivos, 
			FiltroExpedienteVO filtroExpedienteVO) throws NotFoundException, BusinessException{
		
		
				
		DetalleAsignacionVO detalleArchivo = null;
		for(int i=0;i<listaArchivos.size();i++) {
			for(int j=0;j<listaArchivos.get(i).getListaGrupoExpedientes().size();j++) {
				if(listaArchivos.get(i).getListaGrupoExpedientes().get(j).getListaExpedientes()==null||listaArchivos.get(i).getListaGrupoExpedientes().get(j).getListaExpedientes().isEmpty()) {
					FiltroExpedienteVO filtroExpediente2VO = StringUtils.isNotBlank(listaArchivos.get(i).getListaGrupoExpedientes().get(j).getFiltro())
							? EnumObjectMapper.INSTANCE.fromJson(listaArchivos.get(i).getListaGrupoExpedientes().get(j).getFiltro(), new TypeReference<FiltroExpedienteVO>() {
							})
							: null;
							
					detalleArchivo = getDetalleNoAsignado(null, listaArchivos.get(i).getIdArchivo(), null, filtroExpediente2VO);
					List<Long> listaExpedientes = new ArrayList<Long>();
					for(int k=0;k<detalleArchivo.getAsignaciones().size();k++) {
						listaExpedientes.add(detalleArchivo.getAsignaciones().get(k).getIdRegistroCsv());
					}
					listaArchivos.get(i).getListaGrupoExpedientes().get(j).setListaExpedientes(listaExpedientes);
				}
			}
		}
		return listaArchivos;
	} 
	
	public AsignaRevalidacionVO contructCSVVOForAsignacionPT(DetalleAsignacionVO detalleAsignacionVO, AsignaRevalidacionVO asignaRevaLoteVO) {
	
		List<ArchivoRevalidacionVO> listaArchivosCsv = new ArrayList<ArchivoRevalidacionVO>();
		List<GrupoExpedientesVO> listaGruposExpedientes;
		
		ArchivoRevalidacionVO archivoCsv = null;
		GrupoExpedientesVO grupoEXpediente = null;
		
		for(int i=0;i<detalleAsignacionVO.getAsignaciones().size();i++) {
			listaGruposExpedientes = new ArrayList<GrupoExpedientesVO>();
			
			grupoEXpediente = new GrupoExpedientesVO();
			grupoEXpediente.setIdMotivo(asignaRevaLoteVO.getIdMotivoDetalleLote());
			listaGruposExpedientes.add(grupoEXpediente);
			
			archivoCsv = new ArchivoRevalidacionVO();
			archivoCsv.setIdArchivo(detalleAsignacionVO.getAsignaciones().get(i).getIdArchivoCsv());
			archivoCsv.setIdValidador(asignaRevaLoteVO.getIdValidadorLote());
			archivoCsv.setIdMotivo(asignaRevaLoteVO.getIdMotivoGeneralLote());
			archivoCsv.setListaGrupoExpedientes(listaGruposExpedientes);
			listaArchivosCsv.add(archivoCsv);
		}
		asignaRevaLoteVO.setListaArchivosCsv(listaArchivosCsv);
		return asignaRevaLoteVO;
}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getExpedientesEtiquetados(Long idEtiqueta, Short nivel)
			throws NotFoundException, BusinessException, RollbackException {
		if (idEtiqueta == null) {
			throw new IllegalArgumentException("El filtro de b\u00FAsqueda es inv\u00E1lido");
		}
		FiltroExpedienteVO filtroEtiquetado = getFiltroEtiquetado(idEtiqueta);
		if (filtroEtiquetado != null) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("filtro", filtroEtiquetado);			
			res.put("expedientes", getExpedientesValidados(filtroEtiquetado, nivel));	
			return res;
		}
		throw new BusinessException("No fue posible recuperar el filtro etiquetado");
	}
	
	@Transactional
	@Override
	public Boolean deshabilitarEtiqueta(Long idEtiqueta){
		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
		
		PtEtiquetasRevalDTO etiquetaDTO = ptEtiquetasRevalDAO.findOne(idEtiqueta);
		etiquetaDTO.setStActivo(false);
		etiquetaDTO.setIdUsrModifica(idUsuario);
		etiquetaDTO.setFhModificacion(new Date());
		ptEtiquetasRevalDAO.update(etiquetaDTO);
		return true;
	}
	
	
	

	@Override
	@Transactional
	public void reiniciarBanderasRevalidacion(FiltroExpedienteVO filtro) {
		archivoDetalleEvaDAO.actualizarBanderasRevalidacion(filtro, contexto.getUsuarioFirmadoVO().getId());		
	}

	private FiltroExpedienteVO getFiltroEtiquetado(Long idFiltro) {
		FiltroExpedienteVO filtroEtiquetado = EnumObjectMapper.INSTANCE.fromJson((filtroDAO.findOne(idFiltro)).getNbFiltro(),
				new TypeReference<FiltroExpedienteVO>() {
				});
		filtroEtiquetado.setIdFiltro(idFiltro);
		filtroEtiquetado.setNbEtiqueta(filtroEtiquetado.getNbEtiqueta());
		return filtroEtiquetado;
	}

}
