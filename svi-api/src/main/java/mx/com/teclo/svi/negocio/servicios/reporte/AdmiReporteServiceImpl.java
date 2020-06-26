package mx.com.teclo.svi.negocio.servicios.reporte;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.naming.NamingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.utils.comun.ConnectionUtilBd;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesFormatosVO;
import mx.com.teclo.svi.negocio.vo.reporte.TablasVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoOperadorVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParamCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoTituloVO;
import mx.com.teclo.svi.negocio.vo.reporte.admireporte.ParamValueVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.DependenciasSelectVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParamPropScriptVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametroTipoCatConfigVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosConsultaReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.SqlTestResultaVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.AgrupacionHojasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ColumnasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ComponenteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParamPropScriptDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosColumnDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosPropDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosTablasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PerfilesReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PropiedadesCompDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesFormatosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoParamCompDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoParametroDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoTitulosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.AgrupacionHojasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesFormatosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParamCompDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParametroDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoTitulosDTO;

@Service
public class AdmiReporteServiceImpl implements AdmiReporteService {

	@Autowired
	private TipoReportesDAO tipoReporteDAO;

	@Autowired
	private ComponenteDAO componenteDAO;

	@Autowired
	private TipoParametroDAO tipoParametroDAO;

	@Autowired
	private TipoTitulosDAO tipoTituloDAO;

	@Autowired
	private ConnectionUtilBd conexionDB;

	@Autowired
	private TipoParamCompDAO tipoParamDAO;
	
	@Autowired
	private AgrupacionHojasDAO agrupacionHojasDAO;

	@Autowired
	private PropiedadesCompDAO propiedadComponenteDAO;
	
	@Autowired
	private ReportesDAO reportesDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmado;
	
	@Autowired
	private ReportesFormatosDAO reportesFormatosDAO;
	
	@Autowired
	private ParametrosDAO parametrosDAO;
	
	@Autowired
	private ParametrosPropDAO parametrosPropDAO;
	
	@Autowired
	private ColumnasDAO columnasDAO;
	
	@Autowired
	private ParametrosTablasDAO parametrosTablasDAO;
	
	@Autowired
	private ParametrosColumnDAO parametrosColumnDAO;
	
	@Autowired
	private ParamPropScriptDAO paramPropScriptDAO;
	
	@Autowired
	private PerfilesReportesDAO perfilesReporteDAO;
	
	@Autowired
	private GuadaColumnasParamService guadaColumnasParamService; 
	
	@Autowired
	private AdminReporteFormatosService adminReporteFormatosService;
	
	@Autowired
	private ActualizaPropiedadesParamService actualizaPropiedadesParamService;
	
	@Value("${app.config.codigo}")
	private String cdApp;
	
	@Autowired
	private ActualizaParametrosService actualizaParametrosService;

	@Override
	public List<ParametrosVO> identificacionParametro(String cadena) {
		List<ParametrosVO> response = new ArrayList<>();
		Map<String, String> cdParamNotRepited = new LinkedHashMap<>();
		StringTokenizer st = new StringTokenizer(cadena, "#{}");
		String[] token = new String[st.countTokens()];
		while (st.hasMoreTokens()) {
			for (int i = 0; i < token.length; i++) {
				token[i] = st.nextToken();
				if (i % 2 != 0) {
					cdParamNotRepited.put(token[i], token[i]);
				}
			}
		}
		if(!cdParamNotRepited.isEmpty()){
			for(Map.Entry<String, String> entry : cdParamNotRepited.entrySet()){
				response.add(new ParametrosVO(entry.getKey()));
			}
		}
		return response;
	}

	@Override
	public SqlTestResultaVO testUserQuery(String parametros, String sql) throws SQLException, NamingException, IOException {
		if(parametros == null){
			return executeUserQuery(sql);
		}
		String queryValue = sql;
		List<ParamValueVO> listJson = converJson("[" + parametros + "]");
		HashMap<String, String> hashParam = new HashMap<String, String>();
		for (int i = 0; i < listJson.size(); i++) {
			hashParam.put("#{" + listJson.get(i).getParam() + "}", listJson.get(i).getValue());
		}
		for (Entry<String, String> entry : hashParam.entrySet()) {
			queryValue = queryValue.replace(entry.getKey(), entry.getValue());
		}
		return executeUserQuery(queryValue);
	}

	@Override
	public List<ParamValueVO> converJson(String query) throws IOException{
		List<ParamValueVO> listJson = EnumObjectMapper.INSTANCE.fromJson(query.toString(), new TypeReference<List<ParamValueVO>>() {});
		return listJson;
	}

	@Override
	@Transactional
	public List<TipoParamCompVO> getRelacionParamComponen() {
		List<TipoParamCompDTO> listaTiposComp = tipoParamDAO.getRelacionParamComponen();
		List<TipoParamCompVO> listaReturn = new ArrayList<>();
		if (!listaTiposComp.isEmpty()) {
			TipoParamCompVO vo = null;
			for (TipoParamCompDTO dto : listaTiposComp) {
				vo = new TipoParamCompVO(dto.getIdTipoParamComp(), dto.getTipoParametro().getIdTipoParametro(),
						dto.getComponente().getIdComponente());
				listaReturn.add(vo);
			}
		}
		return listaReturn;
	}

	@Override
	@Transactional
	public List<PropiedadesCompVO> getComponentesPropiedades() {
		List<PropiedadesCompDTO> listaCompProp = propiedadComponenteDAO.getComponentesPropiedad();
		List<PropiedadesCompVO> listReturn = new ArrayList<>();
		if (!listaCompProp.isEmpty()) {
			PropiedadesCompVO vo = null;
			for (PropiedadesCompDTO dto : listaCompProp) {
				vo = new PropiedadesCompVO(dto.getIdPropiedadComp(), dto.getPropiedad().getIdPropiedad(),
						dto.getComponente().getIdComponente());
				listReturn.add(vo);
			}
		}
		return listReturn;
	}

	@Override
	@Transactional
	public ReportesTaqNewReporteVO guadaReporte(ReportesTaqNewReporteVO reporteVO) {
		
		/*Lista temporal para identificar los parametros dependientes*/
		List<ParametrosTablasDTO> listaTMP = new ArrayList<>();
		
		/*Obtener dependencias de los reportes dinamicos*/
		Long idUserInSession = usuarioFirmado.getUsuarioFirmadoVO().getId();
		TipoReportesDTO tipoReporteDTO = tipoReporteDAO.getTipoReporteById(reporteVO.getTipoReporte().getIdTipoReporte());
		TipoTitulosDTO tipoTituloDTO = tipoTituloDAO.getTipoTituloById(reporteVO.getTipoTitulo().getIdTipoTitulo());
		
		//AplicacionDTO aplicacionDTO = reportesDAO.idApp();//cambiar esto proque id es long no DTO 
		
		Long aplicacionDTO = 14L ;//reportesDAO.idApp();
		
		/*se crea nuevo objeto que va persistir*/
		ReportesDTO reporteDTOSave = new ReportesDTO();
		reporteDTOSave.setTipoReporte(tipoReporteDTO);
		reporteDTOSave.setTipoTitulo(tipoTituloDTO);
		reporteDTOSave.setAplicacion(aplicacionDTO);
		reporteDTOSave.setCdReporte(generarCdReporte(reporteVO.getNbReporte()));
		reporteDTOSave.setNbReporte(reporteVO.getNbReporte());
		reporteDTOSave.setTxReporte(reporteVO.getTxReporte());
		reporteDTOSave.setUrl(reporteVO.getUrl());
		reporteDTOSave.setScriptSelect(reporteVO.getScriptSelect());
		reporteDTOSave.setScritFrom(reporteVO.getScritFrom());
		reporteDTOSave.setScriptWhere(reporteVO.getScriptWhere());
		reporteDTOSave.setStActivo(1);
		reporteDTOSave.setIdUsrCreacion(idUserInSession);
		reporteDTOSave.setFhCreacion(new Date());
		reporteDTOSave.setIdUsrModifica(idUserInSession);
		reporteDTOSave.setFhModificacion(new Date());
		reporteDTOSave.setTxUrlDinamic(reporteVO.getUrl());
		reporteDTOSave.setTxLayout(reporteVO.getTxLayout());
		reportesDAO.save(reporteDTOSave);
		
		/*Obtener los formatos de descarga que tendrá el reporte*/
		List<FormatoDescargaVO> listFormatos = reporteVO.getReporteFormato();
		
		
		for (FormatoDescargaVO voFormatoDesc : listFormatos) {
			adminReporteFormatosService.guadarNuevoFormatoDescarga(reporteDTOSave,
					reporteVO.getTipoAgrupacion().getIdTipoAgrupacion(), voFormatoDesc,
					reporteVO.getTxColumnaPaginacionHojas());
		}
		/*Objeto temporales provenientes del FRONT*/
		List<ParametrosNewReporteVO> parametrosVO = reporteVO.getParametros();
		
		/*Crear objeto de parametros que persitirán*/
		ParametrosDTO parametrosDTO = null;
		
		/*Inicializar objetos dependientes*/
		TipoParametroDTO tipoParametroDTO = null;
		ComponentesDTO componentesDTO = null;
		
		for(ParametrosNewReporteVO parametroVO : parametrosVO){
			parametrosDTO = new ParametrosDTO();
			tipoParametroDTO = new TipoParametroDTO();
			componentesDTO = new ComponentesDTO();
			
			tipoParametroDTO = tipoParametroDAO.gettipoParametroById(parametroVO.getTipoParametro().getIdTipoParametro());
			componentesDTO = componenteDAO.getComponentesById(parametroVO.getComponente().getIdComponente());
			
			parametrosDTO.setReporte(reporteDTOSave);
			parametrosDTO.setTipoParametro(tipoParametroDTO);
			parametrosDTO.setComponente(componentesDTO);
			parametrosDTO.setCdParametro(parametroVO.getCdParametro());
			parametrosDTO.setNbParametro(parametroVO.getNbParametro());
			parametrosDTO.setTxValor(parametroVO.getTxValor());
			parametrosDTO.setTxParametro(parametroVO.getTxParametro());
			parametrosDTO.setStIsCatalogo(parametroVO.getStIsCatalogo());
			parametrosDTO.setStActivo(1);
			parametrosDTO.setIdUsrCreacion(idUserInSession);
			parametrosDTO.setFhCreacion(new Date());
			parametrosDTO.setIdUsrModifica(idUserInSession);
			parametrosDTO.setFhModificacion(new Date());
			parametrosDTO.setStMultipleValores(parametroVO.getStMultipleValores());
			parametrosDTO.setNuOrden(parametroVO.getNuOrden());
			parametrosDTO.setTxAyuda(parametroVO.getTxAyuda());
			parametrosDAO.saveOrUpdate(parametrosDTO);
			
			List<PropiedadesVO> listProVo = parametroVO.getPropieades();
			
			/*Guardar las posibles propiedades aplicables al componente*/
			if(listProVo != null)
				actualizaPropiedadesParamService.guardarNuevaPropiedad(listProVo, parametrosDTO);
			
			
			/**En caso de que el parámetro es de tipo catálogo*/
			if(parametroVO.getStIsCatalogo().intValue() == 1){
				ParametrosTablasDTO parametrosTablasDTO = new ParametrosTablasDTO();
				parametrosTablasDTO.setParametro(parametrosDTO);
				TablasDTO tablasDTO = new TablasDTO();
				ResponseConverter.copiarPropriedades(tablasDTO, parametroVO.getConfigParamTipoCatVO().getTablaActual());
				parametrosTablasDTO.setTabla(tablasDTO);
				parametrosTablasDTO.setStActivo(1);
				parametrosTablasDTO.setIdUsrCreacion(idUserInSession);
				parametrosTablasDTO.setFhCreacion(new Date());
				parametrosTablasDTO.setIdUsrModifica(idUserInSession);
				parametrosTablasDTO.setFhModificacion(new Date());
				parametrosTablasDAO.saveOrUpdate(parametrosTablasDTO);//persisir la relación entre el parametro y la tabla catalogo
				listaTMP.add(parametrosTablasDTO);
				
				/**Guardar las columnas que servirán como los filtros de búsqueda dentro del catálogo*/
				guadaColumnasParamService.guardarIdentificador(parametroVO.getConfigParamTipoCatVO().getIdentificador(),parametrosTablasDTO);
				guadaColumnasParamService.guardarDescripcion(parametroVO.getConfigParamTipoCatVO().getDescripciones(),parametrosTablasDTO);
				guadaColumnasParamService.guardarRestriccion(parametroVO.getConfigParamTipoCatVO().getRestricciones(),parametrosTablasDTO);
				guadaColumnasParamService.guardarOrden(parametroVO.getConfigParamTipoCatVO().getOrdenes(),parametrosTablasDTO);
			}
		}
		if(!listaTMP.isEmpty() && (reporteVO.getDependenciasSelect() != null && !reporteVO.getDependenciasSelect().isEmpty())){
			guadaColumnasParamService.guardaRestriccionDependiente(listaTMP, reporteVO.getDependenciasSelect());
		}
		return reporteVO;
	}

	@Override
	@Transactional
	public List<AgrupacionHojasVO> getTipoAgrupacion() {
		List<AgrupacionHojasDTO> listaDTO = agrupacionHojasDAO.getReportesFormato();
		List<AgrupacionHojasVO> listVOReturn = new ArrayList<>();
		if (!listaDTO.isEmpty()){
			listVOReturn = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,AgrupacionHojasVO.class);
		}
		return listVOReturn;
	}
	
	@Override
	public List<Object> extractColumnInRS(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		//extraer la cabeceras de las columnas
		List<Object> cabeceras = new ArrayList<>();
		for (int i = 1; i <= columns; i++){
			String valor = md.getColumnName(i); 
			cabeceras.add(valor);
		}
		return cabeceras;
	}

	@Override
	public String generarCdReporte(String nbReporte) {
        int maxlengt = 10;
        String str = "";
        String strReplace = nbReporte.replaceAll("[^A-Za-z0-9]+", "");
        if (nbReporte.length() < maxlengt-1 && strReplace.length() < maxlengt-1) {
            str = nbReporte;
        } else {
            str = strReplace.substring(0, maxlengt-1);
        }
        return str;
    }

	@Override
	public SqlTestResultaVO executeQueryCat(String queryVal) throws SQLException, NamingException{
		return executeUserQuery(queryVal);
	}
	
	/**
	 * Descripción: Método generico para ejecutar un query
	 * @author Jorge Luis
	 * @return SqlTestResultaVO
	 * @throws SQLException
	 * @throws NamingException
	 * */
	private SqlTestResultaVO executeUserQuery (String sqlquery) throws SQLException, NamingException{
		Connection con = conexionDB.conectarBD();
		Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stm.setFetchSize(1000);
		ResultSet rs = null;
		SqlTestResultaVO voReturn = null;
		try {
			rs = stm.executeQuery(sqlquery);
			List<Object> metadata = extractColumnInRS(rs);
			voReturn = new SqlTestResultaVO("correcto", "0", true,metadata);
			rs.close();
		} catch (SQLException ex) {
			voReturn = new SqlTestResultaVO(ex.getMessage(), ex.getCause(), false, new ArrayList<>());
		}
		stm.close();
		con.close();
		return voReturn;
	}

	@Transactional
	@Override
	public List<ReportesTaqNewReporteVO> listaReportesDinamicos(ParametrosConsultaReporteVO paramsConsultaVO) throws NotFoundException {
		Long idPerfil = usuarioFirmado.getUsuarioFirmadoVO().getPerfilId();
		List<PerfilesReportesDTO> listaReportePerfilDTO = new ArrayList<>();
		
		if(paramsConsultaVO.getTipoReporteVO().getCdTipoReporte().equals("TODOS")){
			listaReportePerfilDTO = perfilesReporteDAO.ontenerReportesPorPerfil(idPerfil);
		}else{
			listaReportePerfilDTO = perfilesReporteDAO.getPReporteByTipoYNb(idPerfil, 
					cdApp, paramsConsultaVO.getTipoReporteVO().getIdTipoReporte(), paramsConsultaVO.getNbReporte());
		}
		
		if(listaReportePerfilDTO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		ReportesDTO reporteDTO = null;
		ReportesTaqNewReporteVO reporteVO = null;
		List<ReportesTaqNewReporteVO> listReturn = new ArrayList<>();
		List<ReportesFormatosVO> listaFormatosVO = null;
		TipoReporteVO tipoReporteVO = null;
		TipoTituloVO tipoTituloVO = null;
		
		for(PerfilesReportesDTO pReporteDTO: listaReportePerfilDTO){
			reporteDTO = pReporteDTO.getReporte();
			
		List<ReportesFormatosDTO> reporteFormatoDTO = reportesFormatosDAO.getReportesFormatosById(reporteDTO.getIdReporte());
		listaFormatosVO = new ArrayList<>();
		listaFormatosVO = ResponseConverter.converterLista(new ArrayList<>(), reporteFormatoDTO, ReportesFormatosVO.class);
		
		tipoReporteVO = new TipoReporteVO();
		ResponseConverter.copiarPropriedades(tipoReporteVO, reporteDTO.getTipoReporte());
		
		tipoTituloVO = new TipoTituloVO();
		ResponseConverter.copiarPropriedades(tipoTituloVO, reporteDTO.getTipoTitulo());
		
			reporteVO = new ReportesTaqNewReporteVO(
					reporteDTO.getIdReporte(), 
					reporteDTO.getCdReporte(), 
					tipoReporteVO,
					tipoTituloVO,
					reporteDTO.getNbReporte(), 
					reporteDTO.getTxReporte(), 
					reporteDTO.getScriptSelect(), 
					reporteDTO.getScritFrom(), 
					reporteDTO.getScriptWhere(),
					reporteDTO.getTxUrlDinamic(), 
					listaFormatosVO);
			reporteVO.setFhCreacion(reporteDTO.getFhCreacion());
			
			listReturn.add(reporteVO);
		}
		return listReturn;
	}

	@Transactional
	@Override
	public Boolean deleteReporteDinamico(Long idReporte) {
		ReportesDTO reporteDelete = reportesDAO.getReporteById(idReporte,cdApp);
		if(reporteDelete == null)
			return false;
		/*primero se debe eliminar la asociacion entre el perfil y reporte*/
		List<PerfilesReportesDTO> perfilReporte = perfilesReporteDAO.getRegistrosByIdReporte(idReporte);
		if(!perfilReporte.isEmpty()){
			for(PerfilesReportesDTO perfilRepDTO: perfilReporte){
				perfilesReporteDAO.delete(perfilRepDTO);
			}
		}
		/*delete la asociacion de los reportes con sus formatos*/
		List<ReportesFormatosDTO> reporteFormatos = reporteDelete.getReporteFormato();
		if(!reporteFormatos.isEmpty()){
			for(ReportesFormatosDTO reporteFormDTO: reporteFormatos){
				reportesFormatosDAO.delete(reporteFormDTO);
			}
		}
		/*Borrar los parámetros que están asociados a los reportes*/
		List<ParametrosDTO> parametros = reporteDelete.getParametros();
		if(!parametros.isEmpty()){
			for(ParametrosDTO paramDTO: parametros){
				/*Obtener las propiedades de los parámetros*/
				List<ParametrosPropDTO> propiedadesParam = parametrosPropDAO.getParametrosPropiedad(paramDTO.getIdParamtro());
				if(!propiedadesParam.isEmpty()){
					for(ParametrosPropDTO paramPropDTO: propiedadesParam){
						/*Obtener las propiedades dell script en caso de que lo tenga*/
						ParamPropScriptDTO paramPropScriptDTO = paramPropScriptDAO.getParamPropById(paramPropDTO.getIdParamtrosProp());
						/*eliminar el registro su existe*/
						if(paramPropScriptDTO != null)
							paramPropScriptDAO.delete(paramPropScriptDTO);
						/*Eliminar la propiedad*/
						parametrosPropDAO.delete(paramPropDTO);
					}
				}
				/*Obener las asociaciones con los catálogos, en caso de 
				 * que el parámetro es de tipo catalogo*/
				ParametrosTablasDTO propiedadesTabDTO = parametrosTablasDAO.getParametrosTablas(paramDTO.getIdParamtro());
				if(propiedadesTabDTO != null){
					/*Obtener los parametros columnas*/
					List<ParametrosColumnDTO> paramsColumn =  parametrosColumnDAO.getAllColumns(propiedadesTabDTO.getIdParametroTabla());
					if(!paramsColumn.isEmpty()){
						for(ParametrosColumnDTO paramColumnDTO: paramsColumn){
							parametrosColumnDAO.delete(paramColumnDTO);
						}
					}
					parametrosTablasDAO.delete(propiedadesTabDTO);
				}
				/*Depues de haber eliminado todas las dependencias
				 * de los parametros borramos el parametros*/
				parametrosDAO.delete(paramDTO);
			}
		}
		/*Finalmente se debe eliminar el reporte*/
		reportesDAO.delete(reporteDelete);	
		return true;
	}

	@Transactional
	@Override
	public ReportesTaqNewReporteVO getReporteEdit(Long idReporte) throws NotFoundException {
		ReportesDTO reporteDTO = reportesDAO.getReporteById(idReporte,cdApp);
		if(reporteDTO == null)
			throw new NotFoundException("No se encontró el reporte en base de datos");
		return extrarerValoresDTO(reporteDTO);
	}
	
	
	@Transactional
	private ReportesTaqNewReporteVO extrarerValoresDTO(ReportesDTO dto){
		ReportesTaqNewReporteVO voReturn = new ReportesTaqNewReporteVO();
		voReturn.setIdReporte(dto.getIdReporte());
		
		TipoReporteVO tipoReporteVO = new TipoReporteVO();
		ResponseConverter.copiarPropriedades(tipoReporteVO, dto.getTipoReporte());
		
		TipoTituloVO tipoTituloVO = new TipoTituloVO();
		ResponseConverter.copiarPropriedades(tipoTituloVO, dto.getTipoTitulo());
		
		voReturn.setTipoReporte(tipoReporteVO);
		voReturn.setTipoTitulo(tipoTituloVO);
		voReturn.setCdReporte(dto.getCdReporte());
		voReturn.setNbReporte(dto.getNbReporte());
		voReturn.setTxReporte(dto.getTxReporte());
		voReturn.setUrl(dto.getUrl());
		voReturn.setScriptSelect(dto.getScriptSelect());
		voReturn.setScriptWhere(dto.getScriptWhere());
		voReturn.setScritFrom(dto.getScritFrom());
		voReturn.setStActivo(dto.getStActivo());
		
		StringBuilder strB = new StringBuilder(dto.getScriptSelect()+" "+ dto.getScriptWhere()+ " " + dto.getScritFrom());
		voReturn.setQueryFull(strB.toString());
		
		List<ReportesFormatosDTO> reporteFormatoDTO = reportesFormatosDAO.getReportesFormatosById(dto.getIdReporte());
		List<ReportesFormatosVO> listaFormatosVO = new ArrayList<>();
		listaFormatosVO = ResponseConverter.converterLista(new ArrayList<>(), reporteFormatoDTO, ReportesFormatosVO.class);
		voReturn.setListaReportesFormatos(listaFormatosVO);
		
		/*extraer los formato de descarga*/
		List<FormatoDescargaVO> formatosDescargaVO = new ArrayList<>();
		for(ReportesFormatosVO formatoDescargaVO : listaFormatosVO){
			formatosDescargaVO.add(formatoDescargaVO.getFormatoDescarga());
		}
		voReturn.setReporteFormato(formatosDescargaVO);
		voReturn.setTxUrlDinamic(dto.getTxUrlDinamic());
		
		AgrupacionHojasVO tipoAgrupacion = listaFormatosVO.get(0).getAgrupacionHojas();
		voReturn.setTipoAgrupacion(tipoAgrupacion);
		voReturn.setTxColumnaPaginacionHojas(listaFormatosVO.get(0).getNbColumnaAgrupacion());
		voReturn.setFhCreacion(dto.getFhCreacion());
		voReturn.setTxLayout(dto.getTxLayout());
		
		List<ParametrosDTO> parametrosDTO = dto.getParametros();
		ParametrosNewReporteVO paramVO = null;
		List<ParametrosNewReporteVO> paramVOList = new ArrayList<>();
		PropiedadesVO propiedadesVO = null;
		List<PropiedadesVO> propiedadesVOLits= null;
		ParamPropScriptVO paramPropScriptVO = null;
		ParametroTipoCatConfigVO configParamTipoCatVO = null;
		TablasVO tablaVO = null;
		List<ColumnasVO> columnasListVO = null;
		ColumnasVO columnasVO = null;
		ColumnasVO columnasVODependencia = null;
		TipoOperadorVO tipoOperadorVO =null;
		TipoOperadorDTO tipoOperadorDTO = null;
		List<DependenciasSelectVO> dependenciasSelect = new ArrayList<>();
		DependenciasSelectVO dependenciaVO = null;
		ColumnasDTO columnaDTO = null;
		/*List<ColumnasVO> identificador = null;
		List<ColumnasVO> descripciones = null;
		List<ColumnasVO> restricciones = null;
		List<ColumnasVO> ordenes = null;*/
		
		if(!parametrosDTO.isEmpty()){
			for(ParametrosDTO paramDTO: parametrosDTO){
				paramVO = new ParametrosNewReporteVO();
				paramVO = ResponseConverter.copiarPropiedadesFull(paramDTO, ParametrosNewReporteVO.class);
				List<ParametrosPropDTO> parametroPropDTO = parametrosPropDAO.getParametrosPropiedad(paramDTO.getIdParamtro());
				propiedadesVOLits= new ArrayList<>();
				if(!parametroPropDTO.isEmpty()){
					for(ParametrosPropDTO pPropDTO : parametroPropDTO){
						propiedadesVO = new PropiedadesVO();
						ResponseConverter.copiarPropriedades(propiedadesVO, pPropDTO.getPropiedad());
						propiedadesVO.setValue(pPropDTO.getTxValor());
						//en caso de que la propiedad es de lista doble se deberá buscar 
						//el query de busqueda previa
						ParamPropScriptDTO paramPropScriptDTO = paramPropScriptDAO.getParamPropById(pPropDTO.getIdParamtrosProp());
						if(paramPropScriptDTO != null){
							paramPropScriptVO = new ParamPropScriptVO(null, paramPropScriptDTO.getScript(), paramDTO.getCdParametro());
							propiedadesVO.setParamPropScriptVO(paramPropScriptVO);
						}
						propiedadesVOLits.add(propiedadesVO);
					}
					paramVO.setPropieades(propiedadesVOLits);
				}
				
				if(paramDTO.getStIsCatalogo() == 1){
					configParamTipoCatVO = new ParametroTipoCatConfigVO();
					ParametrosTablasDTO parametrosTabDTO = parametrosTablasDAO.getParametrosTablas(paramDTO.getIdParamtro());
					tablaVO = new TablasVO();
					ResponseConverter.copiarPropriedades(tablaVO, parametrosTabDTO.getTabla());
					configParamTipoCatVO.setTablaActual(tablaVO);
					List<ParametrosColumnDTO> columnas = parametrosColumnDAO.getAllColumns(parametrosTabDTO.getIdParametroTabla());
					if(!columnas.isEmpty()){
						
						List<ParametrosColumnDTO> keysCollection = new ArrayList<>();//inicializar listas para filtrar keys, desc, wheres y orders
						List<ParametrosColumnDTO> descCollection = new ArrayList<>();
						List<ParametrosColumnDTO> whereCollection = new ArrayList<>();
						List<ParametrosColumnDTO> orderCollection = new ArrayList<>();

						for (ParametrosColumnDTO pramsColumnDTO : columnas) {//ciclo para filtrar los keys, desc, wheres y orders
							if (pramsColumnDTO.getStIsKey() == 1) {
								keysCollection.add(pramsColumnDTO);
							} else if (pramsColumnDTO.getStIsDesc() == 1) {
								descCollection.add(pramsColumnDTO);
							} else if (pramsColumnDTO.getStIsWhere() == 1) {
								whereCollection.add(pramsColumnDTO);
							} else if (pramsColumnDTO.getStIsOrder() == 1) {
								orderCollection.add(pramsColumnDTO);
							}
						}
						columnasListVO = new ArrayList<ColumnasVO>();
						for(ParametrosColumnDTO c: keysCollection){
							columnasVO = new ColumnasVO();
							columnasVO.setIdTabla(c.getColumna().getTabla().getIdTabla());
							columnasVO.setNuOrden(c.getNuOrden());
							ResponseConverter.copiarPropriedades(columnasVO, c.getColumna());
							columnasListVO.add(columnasVO);
						}
						configParamTipoCatVO.setIdentificador(columnasListVO);
						
						
						columnasListVO = new ArrayList<ColumnasVO>();
						for(ParametrosColumnDTO c: descCollection){
							columnasVO = new ColumnasVO();
							columnasVO.setIdTabla(c.getColumna().getTabla().getIdTabla());
							columnasVO.setNuOrden(c.getNuOrden());
							ResponseConverter.copiarPropriedades(columnasVO, c.getColumna());
							columnasListVO.add(columnasVO);
						}
						configParamTipoCatVO.setDescripciones(columnasListVO);
						
						columnasListVO = new ArrayList<ColumnasVO>();
						for(ParametrosColumnDTO c: whereCollection){
							columnasVO = new ColumnasVO();
							columnasVO.setIdTabla(c.getColumna().getTabla().getIdTabla());
							columnasVO.setNuOrden(c.getNuOrden());
							tipoOperadorDTO = c.getTipoOperador();
							if(c.getTxValorWhere() != null){
								columnasVO.setValor(c.getTxValorWhere());	
							}else if(c.getTxValorWhere() == null && c.getIdParamTabDependency() != null){
								/*En este caso es un par´metro dependiente*/
								ParametrosTablasDTO paramPadre = parametrosTablasDAO.getParametrosTablaById(c.getIdParamTabDependency());
								dependenciaVO = new DependenciasSelectVO();
								dependenciaVO.setCdParametroHijo(c.getParametroTabla().getParametro().getCdParametro());
								dependenciaVO.setCdParametroPadre(paramPadre.getParametro().getCdParametro());
								
								columnasVODependencia = new ColumnasVO();
								ResponseConverter.copiarPropriedades(columnasVODependencia, c.getColumna());
								dependenciaVO.setColumnaHijo(columnasVODependencia);
								
								columnaDTO = columnasDAO.getColumnaDeFiltro(paramPadre.getTabla().getIdTabla(), c.getColumna().getNbReal());
								columnasVODependencia = new ColumnasVO();
								ResponseConverter.copiarPropriedades(columnasVODependencia, columnaDTO);
								dependenciaVO.setColumnaPadre(columnasVODependencia);
								dependenciaVO.setIdPadreDependency(c.getIdParamTabDependency());
								dependenciaVO.setIdParametroRegistroTabla(c.getIdParamtetroColumn());
								dependenciasSelect.add(dependenciaVO);
							}
							if(tipoOperadorDTO != null){
								tipoOperadorVO = new TipoOperadorVO();
								ResponseConverter.copiarPropriedades(tipoOperadorVO, tipoOperadorDTO);
								columnasVO.setTipoOperador(tipoOperadorVO);
							}
							ResponseConverter.copiarPropriedades(columnasVO, c.getColumna());
							columnasListVO.add(columnasVO);
						}
						configParamTipoCatVO.setRestricciones(columnasListVO);
						
						columnasListVO = new ArrayList<ColumnasVO>();
						for(ParametrosColumnDTO c: orderCollection){
							columnasVO = new ColumnasVO();
							columnasVO.setIdTabla(c.getColumna().getTabla().getIdTabla());
							columnasVO.setNuOrden(c.getNuOrden());
							columnasVO.setTipoOrdenamiento(c.getTpOrder());
							ResponseConverter.copiarPropriedades(columnasVO, c.getColumna());
							columnasListVO.add(columnasVO);
						}
						configParamTipoCatVO.setOrdenes(columnasListVO);
					}
					paramVO.setConfigParamTipoCatVO(configParamTipoCatVO);
				}
				paramVOList.add(paramVO);
			}
			voReturn.setParametros(paramVOList);
			voReturn.setDependenciasSelect(dependenciasSelect);
		}else{
			voReturn.setParametros(paramVOList);
		}
		return voReturn;
	}

	@Transactional
	@Override
	public Boolean guardarEdicion(ReportesTaqNewReporteVO reporteVO) throws BusinessException {

		/*Obtener dependencias de los reportes dinamicos*/
		Long idUserInSession = usuarioFirmado.getUsuarioFirmadoVO().getId();
		TipoReportesDTO tipoReporteDTO = tipoReporteDAO.getTipoReporteById(reporteVO.getTipoReporte().getIdTipoReporte());
		TipoTitulosDTO tipoTituloDTO = tipoTituloDAO.getTipoTituloById(reporteVO.getTipoTitulo().getIdTipoTitulo());
		
		Long aplicacionDTO = 14L; //reportesDAO.idApp();
		
		/*se crea nuevo objeto que va persistir*/
		
		ReportesDTO reporteDTOSave = reportesDAO.getReporteById(reporteVO.getIdReporte(), cdApp);
		reporteDTOSave.setTipoReporte(tipoReporteDTO);
		reporteDTOSave.setTipoTitulo(tipoTituloDTO);
		reporteDTOSave.setAplicacion(aplicacionDTO);
		reporteDTOSave.setCdReporte(generarCdReporte(reporteVO.getNbReporte()));
		reporteDTOSave.setNbReporte(reporteVO.getNbReporte());
		reporteDTOSave.setTxReporte(reporteVO.getTxReporte());
		reporteDTOSave.setUrl(reporteVO.getUrl());
		reporteDTOSave.setScriptSelect(reporteVO.getScriptSelect());
		reporteDTOSave.setScritFrom(reporteVO.getScritFrom());
		reporteDTOSave.setScriptWhere(reporteVO.getScriptWhere());
		reporteDTOSave.setStActivo(1);
		reporteDTOSave.setIdUsrModifica(idUserInSession);
		reporteDTOSave.setFhModificacion(new Date());
		reporteDTOSave.setTxUrlDinamic(reporteVO.getTxUrlDinamic());
		reporteDTOSave.setTxLayout(reporteVO.getTxLayout());
		reportesDAO.update(reporteDTOSave);
		
		/*Obtener los formatos de descarga que tendrá el reporte*/
		List<FormatoDescargaVO> listFormatos = reporteVO.getReporteFormato();
		
		adminReporteFormatosService.comprobarFormatoDescarga(listFormatos, reporteVO.getIdReporte(),
				reporteVO.getTipoAgrupacion(), reporteVO.getTxColumnaPaginacionHojas());
		
		actualizaParametrosService.comprobarCambiosParametros(reporteVO, reporteVO.getParametros(), reporteDTOSave);
		
		return true;
	}
	
}
