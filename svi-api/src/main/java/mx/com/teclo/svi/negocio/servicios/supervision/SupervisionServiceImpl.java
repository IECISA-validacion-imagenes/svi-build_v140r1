package mx.com.teclo.svi.negocio.servicios.supervision;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqAsignacion;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqReporteRV;
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.utils.comun.Messages;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtParametrosVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.CatEtiquetqasRevalVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleReasignaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.GrupoAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ReasignaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorReasignaVO;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.AsignValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CicloValidacionDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleIncidenciaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleRevalidaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoDetRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoRevalidaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtEtiquetasRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtFiltroDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtParametrosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtPerfilesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSubmarcasDAO;
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
import mx.com.teclo.svi.persistencia.hibernate.dto.PtFiltroDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;

@SuppressWarnings("finally")
@Service
public class SupervisionServiceImpl implements SupervisionService{

	@Autowired
	ArchivoDetalleEvaDAO archivoDetalleEvaDAO;
	
	@Autowired
	AsignValidadorDAO asignValidadorDAO;
	
	@Autowired
	ValidadoresConfigDAO validadoresConfigDAO;
	
	@Autowired
	ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired
	EntregaDAO entregaDAO;
	
	@Autowired
	LoteDAO loteDAO;
	
	@Autowired
	AsignIncidenciasDAO asignIncidenciasDAO;
	
	@Autowired
	MotivoRevalidaDAO motivoRevalidaDAO;
	
	@Autowired
	MotivoCsvDAO motivoCsvDAO;
	
	@Autowired
	UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	DetalleIncidenciaDAO detalleIncidenciaDAO; 
	
	@Autowired
	DetalleRevalidaDAO detalleRevalidaDAO; 
	
	@Autowired
	MotivoDetRevalDAO motivoDetRevalDAO; 
	
	@Autowired
	CicloValidacionDAO cicloValidacionDAO; 
	
	@Autowired
	PtEtiquetasRevalDAO ptEtiquetasRevalDAO;
	
	@Autowired
	PtFiltroDetalleEvaDAO ptFiltroDetalleEvaDAO;
	
	@Autowired
	private PtParametrosDAO ptParametrosDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAOImpl;
	
	@Autowired
	private ParametrosComponente parametrosComponente;
	
	@Autowired
	private PtMarcasDAO ptMarcasDAO;
	@Autowired
	private PtSubmarcasDAO ptSubmarcasDAO;
	@Autowired
	private PtPerfilesDAO ptPerfilesDAO;
	@Autowired
	private CicloValidacionService cicloService;
	@Autowired
	private Messages messages;
	
	
//	Long idTipoBusq, Long idPeriodo, Long[] valor
	@Override
	@Transactional
	public List<Object> consultarIncidencias(FiltroExpedienteVO filtroExpedienteVO){
		List<Object> incidencias = archivoDetalleEvaDAO.consultarIncidencias(filtroExpedienteVO);
		List<Object> res = new ArrayList<Object>();
		
		if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.PT.getId()){
			for(int i=0;i<incidencias.size();i++) {
				ConsultaIncidenciaPTVO ciVO  = (ConsultaIncidenciaPTVO) incidencias.get(i);
				ciVO.setNuRegistros(archivoDetalleEvaDAO.getNuRegistros(filtroExpedienteVO.getListaEntregas().get(0), ciVO.getNbPtLote()));
				res.add(ciVO);
			}
		}
		if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.CSV.getId()){
			return incidencias;
		}
		return res;
	}
	
	@Override
	@Transactional
	public Map<String, Object> consultarDetalleIncidenciasPT(Long idPT){
		Map<String, Object> res = new HashMap<String, Object>();
		List<ConsultaDetalleIncidenciaPTVO> detIncidencias = archivoCsvDAO.consultarDetalleIncidenciasPT(idPT);
		for(int i=0;i<detIncidencias.size();i++) {
			detIncidencias.get(i).setNuRegistros(archivoDetalleEvaDAO.getNuRegistrosByIdCSV(detIncidencias.get(i).getIdArchivoCsv()));			
		}
		res.put("detallePT", detIncidencias);
		res.put("catValidadores", validadoresConfigDAO.obtenerValidadoresVO());
		res.put("catMotivo", motivoRevalidaDAO.buscarCatMotivo());
		return res;
	}
	
	@Override
	@Transactional
	public Map<String, Object> consultarDetalleIncidenciasCSV(Long idCSV){ 
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("detalleCSV", archivoDetalleEvaDAO.consultarDetalleIncidenciasCSV(idCSV));
		res.put("catValidadores", validadoresConfigDAO.obtenerValidadoresVO());
		res.put("catMotivo", motivoRevalidaDAO.buscarCatMotivo());
		return res;
	}
	
	@Override
	@Transactional
	public Map<String, Object> consultarDetalleIncidenciasCSV(List<Long> listIdRegistro){ 
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("detalleCSV", archivoDetalleEvaDAO.consultarDetalleIncidenciasCSV(listIdRegistro));
		res.put("catValidadores", validadoresConfigDAO.obtenerValidadoresVO());
		res.put("catMotivo", motivoRevalidaDAO.buscarCatMotivo());
		return res;
	}
	
	@Override
	@Transactional
	public Map<String, Object> obtenerValidadoresVO(){
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("catValidadores", validadoresConfigDAO.obtenerValidadoresVO());
		res.put("catMotivo", motivoRevalidaDAO.buscarCatMotivo());
		return res;
	}
	
	@Override
	@Transactional
	public Boolean habilitarOtraValidacionIncidencia(Long idTipo,Long[] incidencias){
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean confirmaValidacionIncidencia(Long idArchivo){
		return Boolean.TRUE;
	}
	
	@Override
	public List<CatalogoVO>catalogoReporteAsignacion(){
		return EnumTipoBusqAsignacion.getCatalog();
	}
	
	@Override
	@Transactional
	public Map<String, Object> catalogoIncidencia(FiltroExpedienteVO filtro)throws NotFoundException, BusinessException, RollbackException {
		Map<String, Object> res = new HashMap<String, Object>();
		if (isFiltroValido(filtro)) {
			res.put("catPT", loteDAO.buscarCatTodosPuntosTacticos(filtro.getListaLotes()));
			res.put("catCSV", archivoCsvDAO.buscarCatTodosArchivos(filtro.getListaCsvs()));
			res.put("catPeriodo", entregaDAO.catalogoPeriodos(filtro.getListaEntregas()));
		}else {
			res.put("catPT", loteDAO.buscarCatTodosPuntosTacticos());
			res.put("catCSV", archivoCsvDAO.buscarCatTodosArchivos());
			res.put("catPeriodo", entregaDAO.catalogoPeriodos());
		}
		return res;
	}
	
	private boolean isFiltroValido(FiltroExpedienteVO filtro) {
		if (filtro == null) {
			return false;
		}
		return !(filtro.getListaCsvs().isEmpty() && filtro.getListaEntregas().isEmpty()
				&& filtro.getListaLotes().isEmpty() && filtro.getListaMarcas().isEmpty() && filtro.getListaSubMarcas().isEmpty() 
				&& filtro.getListaPerfiles().isEmpty());
	}
	
	@Override
	@Transactional
	public List<ConsultaAsignacionVO> consultaAsignaciones(Long idTipo, String valor){
		return asignValidadorDAO.buscarAsignacionesPorCriterio(idTipo, valor);
	}
	
	@Override
	@Transactional
	public List<Object>consultaDetalleReasignacion(Long idTipo, Long valor){
		if(EnumTipoBusqReporteRV.PT.getId() == idTipo){
			List<DetalleReasignaPTVO> res = new ArrayList<DetalleReasignaPTVO>();
			
		}else if(EnumTipoBusqReporteRV.CSV.getId() == idTipo){
			
		}
		return null;
	}
	
	@Override
	@Transactional
	public Boolean cancelaAsignacion(Long idAsigna){
		return asignValidadorDAO.cancelaAsignacion(idAsigna);
	}
	
	@Override
	@Transactional
	public Boolean verificaPuntoTactico(Long idPtLote){
		LoteDTO lDTO = loteDAO.findOne(idPtLote);
		lDTO.setStValidacion((short) 1);
		if(loteDAO.update(lDTO)!=null) {
			return true;
		}
		return false; 
	}
	
	@Override
	@Transactional
	public List<CatalogoVO> getMotivosPorFiltro(Long tipoExclusionInclusion){
		List<CatalogoVO> catalogomotivos = motivoRevalidaDAO.buscarCatMotivoFiltrado(tipoExclusionInclusion);
		return catalogomotivos;
	}
	
	@Override
	@Transactional
	public List<ValidadorVO> getValidadores(){
		List<ValidadorVO> validadores = validadoresConfigDAO.obtenerValidadoresVO();
		return validadores;
	}
	
	

//	public List<ReasignaCSVVO> contructListOfCSV(ReasignaPTVO reasignaPTVO) {
//		
//		List<ReasignaCSVVO> listaReasignaCsv = new ArrayList<ReasignaCSVVO>();
//		List<GrupoAsignacionVO> listaGrupos = null;
//		
//		ReasignaCSVVO reasignaCsv = null;
//		GrupoAsignacionVO grupo = null;
//		
//		for(int i=0;i<reasignaPTVO.getLista().size();i++) {
//			listaGrupos = new ArrayList<GrupoAsignacionVO>();
//			
//			reasignaCsv = new ReasignaCSVVO();
//			grupo = new GrupoAsignacionVO();
//			
//			List<Long> registrosAll = new ArrayList<Long>();
//			registrosAll = archivoDetalleEvaDAO.getTodosIdRegistrosPorIdArchivo_Reva(reasignaPTVO.getLista().get(i).getIdArchivoCsv());		
//			grupo.setIdmotivo(reasignaPTVO.getMotivo2().getIdCat());
//			grupo.setListaRegistros(registrosAll);
//			listaGrupos.add(grupo);
//			
//			reasignaCsv.setLista(listaGrupos);
//			reasignaCsv.setIdArchivoCsv(reasignaPTVO.getLista().get(i).getIdArchivoCsv());
//			reasignaCsv.setMotivo(reasignaPTVO.getMotivo());
//			reasignaCsv.setValidador(reasignaPTVO.getLista().get(i).getValidador());
//			listaReasignaCsv.add(reasignaCsv);
//		}
//		return listaReasignaCsv;
//	}
	
	@Override
	@Transactional
	public Boolean estaCicloCerrado() throws BusinessException{
		Boolean res = false;
		if(!cicloService.isCicloAnteriorCerrado()) {
			res = false;
		}else {
			res = true;
		}
		return res;
	}
	
	
//	@Override
//	@Transactional
//	public Boolean reasignaPuntoTactico(ReasignaPTVO reasignaPTVO) throws BusinessException{
////		if(!cicloService.isCicloAnteriorCerrado()) {
////			throw new BusinessException(messages.get("supervision.reasignacion.cicloAnteriorNoCerrado"));
////		}
//		Boolean res = false;
//		
//		List<ReasignaCSVVO> listaReasignaCSV =  contructListOfCSV(reasignaPTVO);
//		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
//		CicloValidacionDTO  cicloValDTO= cicloValidacionDAO.getCicloVigente();//TCI028C_PT_CICLO_VALIDACION
//		MotivoRevalidaDTO mrDTO = motivoRevalidaDAO.findOne(reasignaPTVO.getMotivo().getIdCat());
//		
//		
//		for(int i=0;i<listaReasignaCSV.size();i++) {
//			ValidadoresConfigDTO vcDTO = validadoresConfigDAO.getValidadorConfigByIdValidador(listaReasignaCSV.get(i).getValidador().getIdValidador());
//			res = insertaTablas26y27(listaReasignaCSV,mrDTO, cicloValDTO, vcDTO, idUsuario);
//		}
//		
//		return res;
//	}
	
	public List<ReasignaCSVVO> complementarGrupos(List<ReasignaCSVVO> listaArchivosAgrupados, ReasignaCSVVO reasignaCSVVO){
		//Asignamos los grupos y motivos de grupo a cada archivo CSV
		for (int i=0;i<listaArchivosAgrupados.size();i++) {
			
			List<GrupoAsignacionVO> listagrupos = new ArrayList<GrupoAsignacionVO>();
			List<Long> listaRegistros = null;
			
			for(int j=0;j<reasignaCSVVO.getLista().size();j++) {//NIvel Grupo
				GrupoAsignacionVO grupo = new GrupoAsignacionVO();
				grupo.setIdmotivo(reasignaCSVVO.getLista().get(j).getIdmotivo());
				listaRegistros = new ArrayList<Long>();
				
				ArchivoDetalleEvaDTO adetevaDTO = null;
				for(int k=0;k<reasignaCSVVO.getLista().get(j).getListaRegistros().size();k++) {//Nivel registros
					Long idRegistro =reasignaCSVVO.getLista().get(j).getListaRegistros().get(k);
					adetevaDTO = archivoDetalleEvaDAO.findOne(idRegistro);
					if(adetevaDTO!=null&&
							adetevaDTO.getIdArchivoCsv().getIdArchivoCsv().equals(listaArchivosAgrupados.get(i).getIdArchivoCsv())) {
						
						listaRegistros.add(adetevaDTO.getIdRegistroCsv());
						
					}
				}
				
				if(listaRegistros!=null&&listaRegistros.size()>0) {
					grupo.setListaRegistros(listaRegistros);
					listagrupos.add(grupo);
				}
				
			}
			listaArchivosAgrupados.get(i).setLista(listagrupos);
		}
		
		
		
		return listaArchivosAgrupados;
	}
	
//	public List<ConsultaIncidenciaPTVO> getListOfUniquePT(List<DataExpedienteByFiltroVO> listaExpedientes){
//		Set<Long> listOfuniquePTs = new LinkedHashSet<>();
//		for(int i=0;i<listaExpedientes.size();i++) {
//			listOfuniquePTs.add(listaExpedientes.get(i).getIdPtLote()); 
//		}
//		//Busco toda la info en BD
//		
//		List<ConsultaIncidenciaPTVO> listapt = archivoDetalleEvaDAO.consultarIncidenciasPTForAsignacion(listOfuniquePTs);
//		for(int i=0;i<listapt.size();i++) {
//			Set<Long> listArchivoId = new LinkedHashSet<>();
//			for(int j=0;j<listaExpedientes.size();j++) {
//				if(listapt.get(i).getIdPtLote().equals(listaExpedientes.get(j).getIdPtLote())) {
//					listArchivoId.add(listaExpedientes.get(j).getIdArchivoCsv());
////					listaExpedientes.remove(j);
//				}
//			}
//			listapt.get(i).setListCSV(listArchivoId);
//		}
//		return listapt;
//	}
	
	public List<ReasignaCSVVO> getListOfUniqueCSV(ReasignaCSVVO reasignaCSVVO){
		//Obtengo lista de idsCSV unicos
		Set<Long> listOfuniqueCSV = new LinkedHashSet<>();
		ArchivoDetalleEvaDTO adetevaDTO = null;
		for(int i=0;i<reasignaCSVVO.getLista().size();i++) {
			for(int j=0;j<reasignaCSVVO.getLista().get(i).getListaRegistros().size();j++) {
				adetevaDTO = archivoDetalleEvaDAO.findOne(reasignaCSVVO.getLista().get(i).getListaRegistros().get(j));
				if(adetevaDTO!=null) {
					listOfuniqueCSV.add(adetevaDTO.getIdArchivoCsv().getIdArchivoCsv());
				}		
			}
		}
		
		List<ReasignaCSVVO> listaArchivosAgrupados = new ArrayList<ReasignaCSVVO>();
		ReasignaCSVVO objeto =null;
		//Convierto esa lista en objetos con los que voy a trabajar
		for (Long i : listOfuniqueCSV) {
			objeto = new ReasignaCSVVO();
			objeto.setIdArchivoCsv(i);
			objeto.setMotivo(reasignaCSVVO.getMotivo());
			objeto.setValidador(reasignaCSVVO.getValidador());
			objeto.setIdEtiqueta(reasignaCSVVO.getIdEtiqueta());
			listaArchivosAgrupados.add(objeto);
        }
		return listaArchivosAgrupados;
	}
	
//	@Override
//	@Transactional
//	public Boolean reasignaCSV(ReasignaCSVVO reasignaCSVVO) throws BusinessException{
////		if(!cicloService.isCicloAnteriorCerrado()) {
////			throw new BusinessException(messages.get("supervision.reasignacion.cicloAnteriorNoCerrado"));
////		}	
//		Boolean res = false;
//		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
//		ValidadoresConfigDTO vcDTO = validadoresConfigDAO.getValidadorConfigByIdValidador(reasignaCSVVO.getValidador().getIdValidador());
//		CicloValidacionDTO  cicloValDTO= cicloValidacionDAO.getCicloVigente();//TCI028C_PT_CICLO_VALIDACION
//		MotivoRevalidaDTO mrDTO = motivoRevalidaDAO.findOne(reasignaCSVVO.getMotivo().getIdCat());
//		
//		
//		List<ReasignaCSVVO> listaArchivosAgrupados = null;
//			
//		if(reasignaCSVVO.getIdEtiqueta()!=null) {//Si es por Etiqueta
//			listaArchivosAgrupados = getListOfUniqueCSV(reasignaCSVVO);
//			listaArchivosAgrupados = complementarGrupos(listaArchivosAgrupados, reasignaCSVVO);
//			//Aprovechamos para desactivar la etiqueta
//			PtEtiquetasRevalDTO etiquetaDTO = ptEtiquetasRevalDAO.findOne(reasignaCSVVO.getIdEtiqueta());
//			etiquetaDTO.setStActivo(false);
//			etiquetaDTO.setIdUsrModifica(idUsuario);
//			etiquetaDTO.setFhModificacion(new Date());
//			ptEtiquetasRevalDAO.update(etiquetaDTO);
//			
//			
//		}else {//Si es por detalle de archivo CSV
//			List<GrupoAsignacionVO> listaGrupos = new ArrayList<GrupoAsignacionVO>();
//			GrupoAsignacionVO grupo = new GrupoAsignacionVO();
//			List<Long> registrosAll = new ArrayList<Long>();
//			registrosAll = archivoDetalleEvaDAO.getTodosIdRegistrosPorIdArchivo_Reva(reasignaCSVVO.getIdArchivoCsv());		
//			
//			grupo.setIdmotivo(mrDTO.getIdMotivoReva());
//			grupo.setListaRegistros(registrosAll);
//			listaGrupos.add(grupo);
//			reasignaCSVVO.setLista(listaGrupos);
//			listaArchivosAgrupados = new ArrayList<ReasignaCSVVO>();
//			listaArchivosAgrupados.add(reasignaCSVVO);
//		}
//		
//		res = insertaTablas26y27(listaArchivosAgrupados, mrDTO, cicloValDTO,  vcDTO, idUsuario);
//		
//		return res;
//	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getCargaInicial(FiltroExpedienteVO filtro) throws NotFoundException, BusinessException, RollbackException {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("catMarca", ptMarcasDAO.getCatMarcas());
		res.put("catSubMarca", ptSubmarcasDAO.getCatSubMarcas());
		res.put("catPerfil", ptPerfilesDAO.getCatPerfiles());	
		res.put("catPerfilDistinct", ptPerfilesDAO.getCatPerfilesDistinct());
		return res;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CatEtiquetqasRevalVO> getCatEtiquetas() throws NotFoundException, BusinessException, RollbackException {
		
		List<CatEtiquetqasRevalVO> listcatalogo = new ArrayList<CatEtiquetqasRevalVO>();
		CatEtiquetqasRevalVO catalogo = null;
		
		List<PtEtiquetasRevalDTO> ptEtRevalDTO =ptEtiquetasRevalDAO.getAllEtiquetas();
		for(int i=0;i<ptEtRevalDTO.size();i++) {
			catalogo = new CatEtiquetqasRevalVO();
			
			PtFiltroDetalleEvaDTO ptFiltroDetEvaDTO = ptFiltroDetalleEvaDAO.findOne(ptEtRevalDTO.get(i).getIdEtiqReval());
			
			catalogo.setIdEtiqReval(ptEtRevalDTO.get(i).getIdEtiqReval());
			catalogo.setNbEtiqueta(ptEtRevalDTO.get(i).getNbEtiqueta());
			catalogo.setFiltro(ptFiltroDetEvaDTO.getNbFiltro());
			catalogo.setStActivo(ptFiltroDetEvaDTO.getIdEtiqReval().getStActivo());
			
			listcatalogo.add(catalogo);
		}
		return listcatalogo;
	}
	
	public Boolean insertaTablas26y27(List<ReasignaCSVVO> listaArchivosAgrupados,
			MotivoRevalidaDTO mrDTO, CicloValidacionDTO  cicloValDTO, ValidadoresConfigDTO vcDTO, Long idUsuario) {
		
		Boolean res = false;
		
		for(int i=0;i<listaArchivosAgrupados.size();i++) {
			ReasignaCSVVO reasignaCSVVO_row = listaArchivosAgrupados.get(i);
			
			ArchivoCsvDTO acsvDTO = archivoCsvDAO.findOne(reasignaCSVVO_row.getIdArchivoCsv());

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
				
				//Aqui insertar en TCI026D_PT_DETALLE_REVALIDA y TCI027D_PT_MOTIVO_DET_REVAL

				DetalleRevalidaDTO detrevDTO = null;//TCI026D_PT_DETALLE_REVALIDA
				MotivoDetRevalDTO modetrevDTO = null;//TCI027D_PT_MOTIVO_DET_REVAL
				ArchivoDetalleEvaDTO adetevaDTO = null;
				
				for(int j=0;j<reasignaCSVVO_row.getLista().size();j++) {
					for(int k=0;k<reasignaCSVVO_row.getLista().get(j).getListaRegistros().size();k++) {
						
						detrevDTO = new DetalleRevalidaDTO();
						adetevaDTO = archivoDetalleEvaDAO.findOne(reasignaCSVVO_row.getLista().get(j).getListaRegistros().get(k));
						
						detrevDTO.setIdRegistroCSV(adetevaDTO);
						detrevDTO.setIdMotivoCSV(mcsvDTO);
						detrevDTO.setIdCicloValidacion(cicloValDTO);
						detrevDTO.setStActivo(Boolean.TRUE);
						detrevDTO.setFechaCreacion(new Date());
						detrevDTO.setIdUserCreacion(idUsuario);
						detrevDTO.setFechaModificacion(new Date()); 
						detrevDTO.setIdUserModifica(idUsuario);
						
						detalleRevalidaDAO.save(detrevDTO);
						
						MotivoRevalidaDTO mr2DTO = motivoRevalidaDAO.findOne(reasignaCSVVO_row.getLista().get(j).getIdmotivo());
						
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
				
				}
				
				if(reasignaCSVVO_row.getLista().isEmpty()){
					archivoDetalleEvaDAO.habilitarRevalidacion(reasignaCSVVO_row.getIdArchivoCsv(), idUsuario);
					archivoDetalleEvaDAO.deshabilitarAntiguasIncidencias(reasignaCSVVO_row.getIdArchivoCsv(), idUsuario);
				}else{
					for(int j=0;j<reasignaCSVVO_row.getLista().size();j++) {
						archivoDetalleEvaDAO.habilitarRevalidacion(reasignaCSVVO_row.getLista().get(j).getListaRegistros(), idUsuario);
						detalleIncidenciaDAO.deshabilitarIncidenciasPorRegistros(reasignaCSVVO_row.getLista().get(j).getListaRegistros(), idUsuario);
					}
				}
//				archivoDetalleEvaDAO.habilitarRevalidacion(reasignaCSVVO.getLista(), idUsuario);
//				detalleIncidenciaDAO.deshabilitarIncidenciasPorRegistros(reasignaCSVVO.getLista(), idUsuario);
				
				res = true;
				
				
				
			}catch(Exception e){
				e.printStackTrace();
				res = false;
			}finally{
				return res;	
			}
		}
		return res;
	}

	@Override
	public Boolean reasignaPuntoTactico(ReasignaPTVO reasignaPTVO) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reasignaCSV(ReasignaCSVVO reasignaCSVVO) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	@Transactional(readOnly = true)
//	public List<ConsultaIncidenciaPTVO> getExpedientesForAsignacion(FiltroExpedienteVO filtro)
//			throws NotFoundException, BusinessException, RollbackException {
//		if (!isFiltroValido(filtro)) {
//			throw new IllegalArgumentException("El filtro de b\u00FAsqueda es inv\u00E1lido");
//
//		}
//		PtParametrosVO parametros = ptParametrosDAO.obtenerParametros(parametrosComponente.getIdPtParam());
//		
//		Long conteo = archivoDetalleEvaDAOImpl.contarResultadosDeLaBusqueda(filtro);
//		if (conteo == 0L) {
//			throw new NotFoundException("No se encontraron resultados");
//		} else if (conteo.longValue() > parametros.getMaxResultados().longValue()) {
//			throw new BusinessException(
//					"El resultado de su b\u00FAsqueda es muy amplio. Por favor reconfigure su selecci\u00F3n.");
//		}
//		List<DataExpedienteByFiltroVO> listaRegistros = archivoDetalleEvaDAOImpl.getExpedientesForAsinacion(filtro);
//		
//		List<ConsultaIncidenciaPTVO> listpt = getListOfUniquePT(listaRegistros);
//		
//		return listpt;
//		
//		
//		
//	}
	
}
