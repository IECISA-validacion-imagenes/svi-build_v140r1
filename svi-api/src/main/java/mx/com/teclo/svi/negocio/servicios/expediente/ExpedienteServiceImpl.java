package mx.com.teclo.svi.negocio.servicios.expediente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.transaction.RollbackException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.BadRequestHttpResponse;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqReporteRV;
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.vo.catalogo.PtParametrosVO;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchDetBitEvaDAOImpl;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignRegValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtParametrosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresConfigDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;


@Service
public class ExpedienteServiceImpl implements ExpedienteService{
	
	@Autowired
	private ValidadoresDAO validadoresHDAO;
	@Autowired
	private AsignValidadorDAO asignValidadorDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAOImpl;
	
	@Autowired
	private ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	private AsignRegValidadorDAO asignRegValidadorDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;
	
	@Autowired
	private ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired 
	private ArchDetBitEvaDAOImpl archDetBitEvaDAO;
	
	@Autowired
	private ParametrosComponente parametrosComponente;
	
	@Autowired
	private PtParametrosDAO ptParametrosDAO;
	
	@Autowired
	private UsuarioFirmadoService contexto;
	
	Semaphore lock = new Semaphore(1);
	@Autowired
	private CatalogoExpedienteService catalogoExpedienteService;
	private List<ArchivosCsvRespuestaVO> lista = null;
	
	@Transactional(readOnly = true)
	public Boolean tieneAsignacionesElUsuario_deprecated(Long idUsuario) {
		Boolean retorno = true;
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
//		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
		
		if(listaArchivosRespuesta.isEmpty())
			retorno = false;
		else
			retorno = true;
		
		return retorno;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean tieneAsignacionesElUsuario(Long idUsuario) {
		Boolean retorno = true;
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		ValidadoresConfigDTO validadorConfig = validadoresConfigDAO.getValidadorConfigByIdValidador(validadorActivo.getIdValidador());
		
		
		if(validadorConfig.getIdConfiguracion().getIdConfiguracion().equals(EnumTipoBusqReporteRV.REGISTRO.getId())) {
			
			List<Long> listaArchivosRespuesta = asignRegValidadorDAO.getTodosRegistrosAsignadosActivos(validadorActivo.getIdValidador());
			if(listaArchivosRespuesta.isEmpty())
				retorno = false;
			else
				retorno = true;
		}else if(validadorConfig.getIdConfiguracion().getIdConfiguracion().equals(EnumTipoBusqReporteRV.CSV.getId())) {
			
			List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
			if(listaArchivosRespuesta.isEmpty())
				retorno = false;
			else
				retorno = true;
		}
		
		
//		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		//List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
		
		
		
		return retorno;
	}
	
	
	
	@Override
	public synchronized List<ArchivoCsvDetallesVO> MC_getArchivosInicialesVO(Long idUsuario){
		List<ArchivoCsvDetallesVO> res = null;
		ValidadoresConfigDTO validador = MC_getTipoConfiguracionValidador(idUsuario);
		
		//Buscamos si el usuario tiene asignaciones
		List<ArchivoCsvDetallesVO> listaDetallesEVA = buscarAsignacionesDeUsuario(validador,idUsuario);
		if(!listaDetallesEVA.isEmpty()) {
			//Se retorna la lista de asignacinoes
			return listaDetallesEVA;
		}
		//Si el usuario aún no tienne asignaciones, le asignamos y devolvemos los detalles
		List<ArchivoCsvDetallesVO> detalles = realizarAsignacionValidador(validador, idUsuario);
				
		
		return detalles;
	}
	
	@Override
	public List<ArchivoCsvDetallesVO> getArchivosInicialesVO(Long idUsuario){
		List<ArchivoCsvDetallesVO> res = null;
		try{
			//El primero que llegue, realiza las repercusiones.
			lock.acquire();
			
			ArchivosCsvRespuestaVO acsvrVO;
			if(lista == null){
				List<ArchivosCsvRespuestaVO> nuevaLista = buscarTodosArchivosNoAsignados();
				lista = new ArrayList<>();
				lista.addAll(nuevaLista); 
			}else{
				if(lista.isEmpty()){
					List<ArchivosCsvRespuestaVO> nuevaLista = buscarTodosArchivosNoAsignados();
					lista = new ArrayList<>();
					lista.addAll(nuevaLista); 
				}
			}
			
			List<ArchivosCsvRespuestaVO> imagenesAsignadas = buscarAsignacionesPorUsuario(idUsuario);
			
			if(!lista.isEmpty()||!imagenesAsignadas.isEmpty()){
//				List<ArchivosCsvRespuestaVO> imagenesAsignadas = buscarAsignacionesPorUsuario(idUsuario);
				if (imagenesAsignadas.size() != 0) {// El validador tiene asignaciones activas.
					res = buscarDetallesArchivo(imagenesAsignadas);
					lock.release();
					return res;
				}
				//En este nivel, no tiene asignaciones
				acsvrVO = ResponseConverter.copiarPropiedadesFull(lista.get(0), ArchivosCsvRespuestaVO.class);
				lista.remove(0);
				res = getArchivosInicialesPrivadoVO(idUsuario, acsvrVO);
				//lista = asignValidadorDAO.getTodosArchivosNoAsignados();
				lock.release();
				return res;
			}else{
				res = new ArrayList<>();
				lock.release();
				return res;
			}
		}catch(InterruptedException ie){
			ie.printStackTrace();
			lock.release();
			return res;
		}
	}
	
	@Transactional(readOnly=false)
	private List<ArchivosCsvRespuestaVO> buscarTodosArchivosNoAsignados(){
		return asignValidadorDAO.getTodosArchivosNoAsignados();
	}
	
	@Transactional(readOnly=false)
	private List<ArchivoCsvDetallesVO> buscarDetallesArchivo(List<ArchivosCsvRespuestaVO> imagenesAsignadas){
		return asignValidadorDAO.getDetallesArchivo(imagenesAsignadas);
	}
	
	@Transactional(readOnly=false)
	private List<ArchivosCsvRespuestaVO> buscarAsignacionesPorUsuario(Long idUsuario){
		return asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
	}
	
	@Transactional(readOnly=false)
	private List<ArchivoCsvDetallesVO> getArchivosInicialesPrivadoVO(Long idUsuario, ArchivosCsvRespuestaVO acsvrVO) {
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		asignValidadorDAO.asignarArchivoValidador(acsvrVO, validadorActivo.getIdValidador(), idUsuario);//Asignar las imágenes al validador
		
		return asignValidadorDAO.getDetalleArchivo(acsvrVO);
	}
	
	private List<ArchivoCsvDetallesVO> getArchivosInicialesPrivadoVO(Long idUsuario) {
		List<ArchivoCsvDetallesVO> detallesArchivos = null;
		
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
	//	ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		List<ArchivosCsvRespuestaVO> imagenesAsignadas = asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
			
			 
	//	List<ImagenesDTO> imagenesDTOEnUI = null;
		if (imagenesAsignadas.size() != 0) {// El validador tiene asignaciones activas.
			detallesArchivos =asignValidadorDAO.getDetallesArchivo(imagenesAsignadas);
			return detallesArchivos;
		}
		//Llegado a este punto, el validador no tiene ninguna asignación
		Long cantidadTotalMaximaAsignar = validadoresHDAO.getTotalMaxAsignar(validadorActivo.getIdValidador());
	//	int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();
		int cantidadImagenesRetornar=1;
		List<ArchivosCsvRespuestaVO> todasLasImagenesNoAsignadasValidas = new ArrayList<ArchivosCsvRespuestaVO>();
		List<ArchivosCsvRespuestaVO> todasLasImagenesNoAsignadas = asignValidadorDAO.getTodosArchivosNoAsignados();
	//	
		for(int i=0;i<todasLasImagenesNoAsignadas.size();i++) {
			if(todasLasImagenesNoAsignadas.get(i).getNuRegistrosCsv()>0) {
				todasLasImagenesNoAsignadasValidas.add(todasLasImagenesNoAsignadas.get(i));
			}		
		}
		
		int sizeListaImagenesNoAsignadas = todasLasImagenesNoAsignadasValidas.size();
		if(sizeListaImagenesNoAsignadas == 0) {
			/*No hay imágenes para asignar, debo retornar una lista vacía*/
			List<ArchivoCsvDetallesVO> listaRetornoVacia = new ArrayList<>();
			return listaRetornoVacia;
		}
		int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
				? cantidadTotalMaximaAsignar
				: sizeListaImagenesNoAsignadas);
	//
		List<ArchivosCsvRespuestaVO> imagenesParaAsignar = todasLasImagenesNoAsignadasValidas.subList(0, cantImagenesAsignar);//lista de imágenes con las imágenes que se asignarán al validador
			asignValidadorDAO.asignarArchivosValidador(imagenesParaAsignar, validadorActivo.getIdValidador(), idUsuario);//Asignar las imágenes al validador
	
		detallesArchivos =asignValidadorDAO.getDetallesArchivo(imagenesParaAsignar);
		
		return detallesArchivos;
	}
	
	@Transactional(readOnly = true)
	public List<InfoBasicaExpedienteVO> getExpedientes(FiltroExpedienteVO filtro)
			throws NotFoundException, BusinessException, RollbackException {
		if (!isFiltroValido(filtro)) {
			throw new IllegalArgumentException("El filtro de b\u00FAsqueda es inv\u00E1lido");

		}
		PtParametrosVO parametros = ptParametrosDAO.obtenerParametros(parametrosComponente.getIdPtParam());
		
		Long conteo = archivoDetalleEvaDAOImpl.contarResultadosDeLaBusqueda(filtro);
		if (conteo == 0L) {
			throw new NotFoundException("No se encontraron resultados");
		} else if (conteo.longValue() > parametros.getMaxResultados().longValue()) {
			throw new BusinessException(
					"El resultado de su b\u00FAsqueda es muy amplio. Por favor reconfigure su selecci\u00F3n.");
		}
		
		return archivoDetalleEvaDAOImpl.getExpedientes(filtro);
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public DetalleExpedienteVO getExpediente(Long id, FiltroExpedienteVO filtro) throws NotFoundException {
		if (id == null) {
			throw new IllegalArgumentException("El id es requerido");
		}
		
		DetalleExpedienteVO detalleExpediente = archivoDetalleEvaDAOImpl.getExpediente(id);
		if (detalleExpediente == null) {
			throw new NotFoundException("No se encontraron resultados");
		}

		detalleExpediente.setDetalleHistorico(archDetBitEvaDAO.getDetalleHistorico(detalleExpediente.getIdRegistroCsv()));
		if(filtro != null) {
			detalleExpediente.setListaNavegacion(archivoDetalleEvaDAOImpl.obtenerListaDeNavegacion(filtro));
			detalleExpediente.setFiltroAplicado(filtro);
		}

		return detalleExpediente;
	}

	private ValidadoresConfigDTO MC_getTipoConfiguracionValidador(long idUsuario) {
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		ValidadoresConfigDTO validadorConfig = validadoresConfigDAO.getValidadorConfigByIdValidador(validadorActivo.getIdValidador());
		return validadorConfig;
	}
	
public List<ArchivoCsvDetallesVO> buscarAsignacionesDeUsuario(ValidadoresConfigDTO validador, Long idUsuario) {
		
		List<ArchivoCsvDetallesVO> listaDetallesEVA = new ArrayList<ArchivoCsvDetallesVO>(); 
		
		if(validador.getIdConfiguracion().getIdConfiguracion().equals( 
				EnumTipoBusqReporteRV.REGISTRO.getId())) {
			
			List<Long> listaRegistrosAsignados = asignRegValidadorDAO.MC_getTodosRegistrosAsignadosActivos(validador.getIdValidador().getIdValidador());
			if(!listaRegistrosAsignados.isEmpty()) {
				listaDetallesEVA = archivoDetalleEvaDAO.getTodosDetallesArchivoPorRegistro(listaRegistrosAsignados);
				//Ids de los archivos que tiene asignados ese usuario
				
			}
				
		}else if(validador.getIdConfiguracion().getIdConfiguracion().equals(
				EnumTipoBusqReporteRV.CSV.getId())) {
			
			List<ArchivosCsvRespuestaVO> listaArchivosAsignados = asignValidadorDAO.getTodosArchivosAsignadosActivos(idUsuario);
			if(!listaArchivosAsignados.isEmpty()) {
				listaDetallesEVA = asignValidadorDAO.getDetallesArchivo(listaArchivosAsignados);
			}
			
		}
		return listaDetallesEVA;
	}

	public List<Long> MC_getIdsArchivosAsignadosPorRegistro() {
		List<Long> listIdRegistrosActivos = asignRegValidadorDAO.getTodosIdsRegistrosAsignadosActivos();
		List<Long> listaIdCsvAsignados = archivoDetalleEvaDAO.getTodosIdArchivoQueEstenAsignadosSusRegistros(listIdRegistrosActivos);
		return listaIdCsvAsignados;
	}

	public List<ArchivoCsvDetallesVO> realizarAsignacionValidador(ValidadoresConfigDTO validador, Long idUsuario) {
	
		List<ArchivoCsvDetallesVO> archivoDetalle = null;
		
		//ARCHIVOS ASIGNADOS
		List<Long> listaIdCsvAsignados = MC_getIdsArchivosAsignados();
		//Archivos Asignados por configuracion de registro 
		List<Long> listaIdCsvAsignadosPorRegistro = MC_getIdsArchivosAsignadosPorRegistro();
		//Obtenemos un archivo disponible
		ArchivosCsvRespuestaVO archivoOne = getOneArchivoDisponible(listaIdCsvAsignados, listaIdCsvAsignadosPorRegistro);
		
		if(archivoOne==null) {
			return archivoDetalle;
		}
				
		if(validador.getIdConfiguracion().getIdConfiguracion().equals( 
				EnumTipoBusqReporteRV.REGISTRO.getId())) {
			 
			Long cantidadTotalMaximaAsignar = validador.getIdConfiguracion().getNuMax();
			//****Si hay registros disponibles en archivos previamente asignados
			List<Long> listIdRegistrosActivos = asignRegValidadorDAO.getTodosIdsRegistrosAsignadosActivos();
			archivoDetalle = asignarRegistrosDeArchivoConAsignacionesPrevias(listaIdCsvAsignadosPorRegistro, cantidadTotalMaximaAsignar, validador, idUsuario, listIdRegistrosActivos);
			
			if(!archivoDetalle.isEmpty()) {
				return archivoDetalle;
			}
			//****Si no hay registros disponibles y se requiere tomar registros de un nuevo archivo
			archivoDetalle = asignarRegistrosDeArchivoNuevo(archivoOne, cantidadTotalMaximaAsignar, validador, idUsuario);
			
				
		}else if(validador.getIdConfiguracion().getIdConfiguracion().equals(
				EnumTipoBusqReporteRV.CSV.getId())) {
			
			asignValidadorDAO.asignarArchivoValidador(archivoOne, validador, idUsuario);//Asignar las imágenes al validador
			archivoDetalle = asignValidadorDAO.getDetalleArchivo(archivoOne);
		}
		return archivoDetalle;
	}
	
	private List<Long> MC_getIdsArchivosAsignados() {
		return asignValidadorDAO.MC_getTodosIdsDeArchivosAsignadosActivos();
	}
	
	public ArchivosCsvRespuestaVO getOneArchivoDisponible(List<Long> listaIdCsvAsignados, List<Long> listaIdCsvAsignadosPorRegistro) {
		ArchivosCsvRespuestaVO archivo = null;
		List<Long> listaIdCsvOcupados = new ArrayList<Long>();
		if(listaIdCsvAsignados.size()>0) {
			listaIdCsvOcupados.addAll(listaIdCsvAsignados);
		}
		if(listaIdCsvAsignadosPorRegistro.size()>0) {
			listaIdCsvOcupados.addAll(listaIdCsvAsignadosPorRegistro);		
		}
		
		List<ArchivosCsvRespuestaVO> archivoDisponible = archivoCsvDAO.getTodosArchivosNoAsignados(listaIdCsvOcupados);
		if(!archivoDisponible.isEmpty()) {
			archivo = archivoDisponible.get(0);
		}else {
			//Yano hay archivos disponibles para asignar
		}
		return archivo;
	}
	
	public List<ArchivoCsvDetallesVO> asignarRegistrosDeArchivoConAsignacionesPrevias(List<Long> listaIdCsvAsignadosPorRegistro, 
			Long cantidadTotalMaximaAsignar, ValidadoresConfigDTO validador, Long idUsuario, List<Long> listIdRegistrosActivos) {
		
		List<ArchivoCsvDetallesVO> archivoDetalle = new ArrayList<ArchivoCsvDetallesVO>();
		
		for(int i=0;i<listaIdCsvAsignadosPorRegistro.size();i++) {
			List<Long> registrosAsignar = new ArrayList<Long>();
			List<Long> registosDisponiblesParaAsignar = new ArrayList<Long>();
			registosDisponiblesParaAsignar = archivoDetalleEvaDAO.getTodosIdRegistrosPorIdArchivo(listaIdCsvAsignadosPorRegistro.get(i), listIdRegistrosActivos);
			
			if(registosDisponiblesParaAsignar.size()>0) {
				
				int sizeListaImagenesNoAsignadas = registosDisponiblesParaAsignar.size();
				int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
						? cantidadTotalMaximaAsignar
						: sizeListaImagenesNoAsignadas);
				
				registrosAsignar = registosDisponiblesParaAsignar.subList(0, cantImagenesAsignar);
				for(int j=0; j<registrosAsignar.size();j++) {
					asignarRegistroValidadorService(registrosAsignar.get(j), validador, idUsuario);
				}
				archivoDetalle = archivoDetalleEvaDAO.getTodosDetallesArchivoPorRegistro(registrosAsignar);
				break;
			}
		}
		return archivoDetalle;
	}
	
	public List<ArchivoCsvDetallesVO> asignarRegistrosDeArchivoNuevo(ArchivosCsvRespuestaVO archivoOne,
			Long cantidadTotalMaximaAsignar, ValidadoresConfigDTO validador, Long idUsuario) {
		
		List<Long> registosDisponiblesParaAsignar = archivoDetalleEvaDAO.getTodosIdRegistrosPorIdArchivo(archivoOne.getIdArchivoCsv(), null);
		List<Long> registrosAsignar = new ArrayList<Long>();
		List<ArchivoCsvDetallesVO> archivoDetalle = new ArrayList<ArchivoCsvDetallesVO>();
		
		if(registosDisponiblesParaAsignar.size()>0) {
			
			int sizeListaImagenesNoAsignadas = registosDisponiblesParaAsignar.size();
			int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
					? cantidadTotalMaximaAsignar
					: sizeListaImagenesNoAsignadas);
			
			registrosAsignar = registosDisponiblesParaAsignar.subList(0, cantImagenesAsignar);
			for(int j=0; j<registrosAsignar.size();j++) {
				asignarRegistroValidadorService(registrosAsignar.get(j), validador, idUsuario);
			}
			archivoDetalle = archivoDetalleEvaDAO.getTodosDetallesArchivoPorRegistro(registrosAsignar);
		}
		return archivoDetalle;
		
		
	}
	
	
	@Transactional(readOnly=false)
	private Long asignarRegistroValidadorService(Long idRegistro, ValidadoresConfigDTO validadorConfig, Long idUsuario) {
		asignRegValidadorDAO.asignarRegistroValidador(idRegistro, validadorConfig, idUsuario); 
		return null;
	}
	
	
	private boolean isFiltroValido(FiltroExpedienteVO filtro) {
		if (filtro == null) {
			throw new IllegalArgumentException("El filtro de b\u00F3squeda es requerido");
		}

		return !(StringUtils.isBlank(filtro.getCdPlaca()) && StringUtils.isBlank(filtro.getNuExpediente())
				&& filtro.getListaCsvs().isEmpty() && filtro.getListaEntregas().isEmpty()
				&& filtro.getListaLotes().isEmpty() && filtro.getListaMarcas().isEmpty() && filtro.getListaSubMarcas().isEmpty() 
				&& filtro.getListaPerfiles().isEmpty() && filtro.getListaIncidencias().isEmpty());

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro) throws NotFoundException, BusinessException, RollbackException {
		Map<String, Object> res = catalogoExpedienteService.getCatalogos();
		if (filtro != null) {
			res.put("expedientes", getExpedientes(filtro));
		}
		return res;
	}

	@Override
	@Transactional
	public void marcarExpediente(Long idRegistroCsv) {
		archivoDetalleEvaDAOImpl.marcarExpediente(idRegistroCsv, contexto.getUsuarioFirmadoVO().getId());		
	}

}
