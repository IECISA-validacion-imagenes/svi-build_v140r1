package mx.com.teclo.svi.negocio.servicios.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.servicios.reporte.admin.AdministracionService;
import mx.com.teclo.svi.negocio.utils.comun.ConnectionUtilBd;
import mx.com.teclo.svi.negocio.utils.comun.RutinasTiempoImpl;
import mx.com.teclo.svi.negocio.utils.comun.documento.PeticioReporteVO;
import mx.com.teclo.svi.negocio.utils.comun.documento.PeticionReporteService;
import mx.com.teclo.svi.negocio.utils.comun.documento.PropiedadesReporte;
import mx.com.teclo.svi.negocio.vo.reporte.DependenciasVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.negocio.vo.reporte.GestionReportesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectCollectionVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectGenericVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.PerfilReporteIdVO;
import mx.com.teclo.svi.negocio.vo.reporte.PerfilesReportesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesFormatosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;
import mx.com.teclo.svi.negocio.vo.reporte.RestriccionesQueryVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoTituloVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParamPropScriptDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosPropDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PerfilesReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.RestriccionesQueryDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.admin.Perfil_SE_DAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilSeDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.RestriccionesQueryDTO;


/**
 * 
 * @author Jorge Luis
 *
 */
@Service
public class ReporteServiceImpl implements ReporteService {

	@Value("${app.config.codigo}")
	private String cdApp;

	@Autowired
	private ReportesDAO reportesDAO;

	@Autowired
	private AdministracionService administracionService;

	@Autowired
	private PerfilesReportesDAO perfilesRepDAO;

	@Autowired
	private Perfil_SE_DAO perfilSE;

	@Autowired
	private UsuarioFirmadoService userSession;

	@Autowired
	private ParametrosDAO parametroDAO;
	
	@Autowired
	@Qualifier("reporteExcel")
	private PeticionReporteService reporteExcel;
	
	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private ConnectionUtilBd conexionDB;
	
	@Autowired
	private ParametrosPropDAO paramPropiedadDAO;
	
	@Autowired
	private ParamPropScriptDAO parampropscriptDAO;
	
	@Autowired
	private RestriccionesQueryDAO restrccionDAO;
	
	@Override
	@Transactional
	public GestionReportesVO getGestionReportes() {
		GestionReportesVO voReturn = new GestionReportesVO();
		voReturn.setPerfiles(administracionService.getPerfilesActivos());
		voReturn.setReportes(obtenerReportesActivos());

		List<PerfilesReportesDTO> perfRepDTO = perfilesRepDAO.getReportePerfilAtivos();
		List<PerfilesReportesVO> pefilesRepList = new ArrayList<>();
		PerfilesReportesVO perfVO;
		if (!perfRepDTO.isEmpty()) {
			for (PerfilesReportesDTO dto : perfRepDTO) {
				perfVO = ResponseConverter.copiarPropiedadesFull(dto, PerfilesReportesVO.class);
				PerfilReporteIdVO id = new PerfilReporteIdVO();
				id.setPerfilId(dto.getPerfil());
				id.setIdReporte(dto.getReporte().getIdReporte());
				perfVO.setId(id);
				pefilesRepList.add(perfVO);
			}
		}
		voReturn.setInterseccion(pefilesRepList);
		return voReturn;
	}

	@Override
	@Transactional
	public GestionReportesVO persisteConfigReportePerf(GestionReportesVO voObject) {
		List<PerfilesReportesDTO> lPerfRepDTO = perfilesRepDAO.getTodosReportes();// lista  de perfiles recuperados de BD
		List<PerfilesReportesDTO> registered = new ArrayList<PerfilesReportesDTO>();// nueva lista de sioporte
		List<PerfilesReportesVO> interseccion = voObject.getInterseccion();// Lista de persmisos agregados
		Long idUsuario = userSession.getUsuarioFirmadoVO().getId();// id del usuarios en sesi�n
		Date fhCreacionModif = new Date();// fecha actual reusable en fhCreacion y modificacion

		for (PerfilesReportesVO vo : interseccion) {

			Long idPerfil = vo.getId().getPerfilId();
			Long idReporte = vo.getId().getIdReporte();

			PerfilesReportesDTO interDTO = perfilesRepDAO.byReporteAndPerfil(idPerfil, idReporte);
			if (interDTO == null) {
				PerfilSeDTO perfil = perfilSE.getPerfilbyIdperfil(idPerfil);
				ReportesDTO reporte = reportesDAO.obtenerReporteBy(idReporte);
				PerfilesReportesDTO dto = new PerfilesReportesDTO();
				dto.setPerfil(perfil.getPerfilId());
				dto.setReporte(reporte);
				dto.setStActivo(1);
				dto.setIdUsrCreacion(idUsuario);
				dto.setFhCreacion(fhCreacionModif);
				dto.setIdUsrModifica(idUsuario);
				dto.setFhModificacion(fhCreacionModif);
				perfilesRepDAO.save(dto);
				registered.add(dto);
			} else {
				for (PerfilesReportesDTO dto : lPerfRepDTO) {
					if (idPerfil == dto.getPerfil().longValue() && idReporte == dto.getReporte().getIdReporte().longValue()) {
						if (dto.getStActivo().intValue() == 1) {
							registered.add(dto);
						} else {
							dto.setStActivo(1);
							dto.setIdUsrModifica(idUsuario);
							dto.setFhModificacion(fhCreacionModif);
							perfilesRepDAO.save(dto);
							registered.add(dto);
						}
						break;
					}
				}
			}
		}		
		lPerfRepDTO = perfilesRepDAO.getTodosReportes();		
		for (PerfilesReportesDTO all : lPerfRepDTO) {
			boolean found = false;
			for (PerfilesReportesDTO reg : registered) {
				if (all.getPerfil() == reg.getPerfil()
						&& all.getReporte().getIdReporte().longValue() == reg.getReporte().getIdReporte().longValue()) {
					found = true;
					break;
				}
			}
			if (!found && all.getStActivo().intValue() == 1) {
				all.setStActivo(0);
				all.setIdUsrModifica(idUsuario);
				all.setFhModificacion(fhCreacionModif);
				perfilesRepDAO.save(all);
				registered.add(all);
			}
		}
		return voObject;
	}

	@Override
	@Transactional
	public List<ReportesTaqVO> obtenerReportesActivos() {
		List<ReportesDTO> listDTO = reportesDAO.obtrenerReportesActivos();
		List<ReportesTaqVO> listReturn = new ArrayList<>();
		if (listDTO.isEmpty())
			return null;
		for (ReportesDTO dto : listDTO) {
			ReportesTaqVO vo = new ReportesTaqVO();
			vo = ResponseConverter.copiarPropiedadesFull(dto, ReportesTaqVO.class);
			listReturn.add(vo);
		}
		return listReturn;
	}
	
	@Override
	public ObjectGenericVO executeQuery(HashMap<Object, Object> parametros, ReportesTaqVO vo) throws SQLException, NamingException {
		String txAviso = null;
		int maxRosRsReturn = 0;
		Connection con = conexionDB.conectarBD();
		Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stm.setFetchSize(1000);
		if(vo.getRestriccion() != null){
			maxRosRsReturn = Integer.parseInt(vo.getRestriccion().getTxValor());
			stm.setMaxRows(maxRosRsReturn);
		}
		ResultSet rs = stm.executeQuery(vo.getQueryFull());
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		
		//Obtener el tipo de titulo que tendr�n las columnas
		TipoTituloVO tipoTilo = vo.getTipoTitulo();
		boolean banTipTitulo = false;
		if(tipoTilo.getCdTipoTitulo().equals("TM"))//Todo mayuscula
			banTipTitulo = true;
		else
			banTipTitulo = false;
		
		//Lista con los valores extraidos
		List<LinkedHashMap<Object, Object>> map = resultSetToArrayMap(rs);
		if(map.isEmpty())
			return new ObjectGenericVO(parametros, null, null,null);
		
		//extraer la cabeceras de las columnas
		List<Object> cabeceras = new ArrayList<>();
		for (int i = 1; i <= columns; i++){
			String valor = (banTipTitulo == true) ? md.getColumnName(i).toUpperCase() : md.getColumnName(i).toLowerCase(); 
			cabeceras.add(valor);
		}
		
		// Lista de valores
		List<Object> listaValores = new ArrayList<>();
		List<Object> values = null;
		for (Map<Object, Object> entry : map) {
			values = new ArrayList<>();
			for (Object key : entry.keySet()) {
				values.add(entry.get(key));
			}
			listaValores.add(values);
		}
		ObjectGenericVO objReturn = new ObjectGenericVO(parametros, cabeceras, listaValores,map);
		if(listaValores.size() == maxRosRsReturn){
			txAviso = vo.getRestriccion().getTxRestriccion();
			DecimalFormat formatea = new DecimalFormat("###,###,###");
			txAviso = txAviso.replace("#", formatea.format(maxRosRsReturn)+"");
			objReturn.setMsjAvisoMax(txAviso);
		} 
		con.close();
		stm.close();
		rs.close();
		System.gc();
		return objReturn;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public ByteArrayOutputStream generateExcel(ObjectGenericVO voData, ReportesTaqVO vo) throws IOException, BusinessException, SQLException, NamingException{
		ReportesFormatosVO formato =  vo.getReporteFormato().get(0);
		
		//Obtener el tipo de titulo si es mayuscula o minuscula
		TipoTituloVO tipoTilo = vo.getTipoTitulo();
		
		//Se obtiene la columna por la que se va paginar el excel
		String columnaPaginacion = formato != null ? formato.getNbColumnaAgrupacion() : null;
		boolean paginarHoja = (columnaPaginacion != null) ? true : false;
		
		FormatoDescargaVO formatoDescarga = vo.getReporteFormato().get(0).getFormatoDescarga();
		String txExtension = formatoDescarga == null ? ".xlsx" :  formatoDescarga.getCdExtension();
		
		HashMap<Object, Object> parametrosTMP = new HashMap<Object, Object>(voData.getParametros());// Respaldo de los parametros
		HashMap<Object, Object> parametros = new HashMap<Object, Object>(voData.getParametros());// Parametros de consulta
		List<Object> cabeceras = voData.getCabeceras();// Cabeceras de respuesta
		List<Object> values = voData.getValues();// Valores de respues
		
		
		
		/*filtrar las descripciones de los parametros*/
		List<ParametrosVO> validarPaemetro = vo.getParametros();//Obtener los parametros del reporte
		if (!validarPaemetro.isEmpty()) {//validar lista de params
			ParametrosVO parametroPadre = null;//iniciliazar parametros a null
			for (ParametrosVO parametrosVO : validarPaemetro) {//iterar lista de parametros
				if (parametrosVO.getStIsCatalogo().intValue() == 1) {//comprobar si el paramtro actual es de tipo catalogo
					Map<Object, Object> getCatCollectionMapRow = parametrosService.getCatCollection(parametrosVO);//obtener el catalogo asociado al param
					Object paramObj = getCatCollectionMapRow.get("parametroVO");//obtener objeto de tipo ParametrosVO
					if (paramObj != null)//comprobar si el objeto es != null
						parametroPadre = (ParametrosVO) paramObj;//asociar el objeto recivido al parametroPadre
					if (parametroPadre.getPadres() == null) {//comprobar si el params actual tiene padres
						List<ObjectCollectionVO> listWithOueFather = (List<ObjectCollectionVO>) getCatCollectionMapRow.get("listObject");//Obtener el catalogo de valores
						//filtrar solo los parametros seleccionados en front
						if(parametrosTMP.get(parametroPadre.getCdParametro()) != null){//validar los valores nulos de las variables
							Object str = filtrarParametrosConValor(parametrosTMP.get(parametroPadre.getCdParametro()).toString(), listWithOueFather);
							parametros.put(parametroPadre.getCdParametro(), str);//actualizar el map de parametros con las descripciones
						}
					} else {//en caso de que el param tenga padres
						ParametrosVO padreActual = null;
						List<DependenciasVO> padres = parametroPadre.getPadres();//Obtener lista de ppadres
						List<Object> listaParamFiltrada = new ArrayList<>();//inicializar lista filtrada
						for(int i=0; i < padres.size(); i++){//interar padres
							for(int j=0; j < validarPaemetro.size(); j++){//recorrer paramtros para identificar al padre
								if(padres.get(i).getCdParam().toString().equals(validarPaemetro.get(j).getCdParametro().toString())){//comparar codifos de parametros
									padreActual = validarPaemetro.get(j);//obtener el paramtro padre
									break;//romper iterarcion cuando se encuentra el padre
								}
							}
							List<LinkedHashMap<Object, Object>> catValuesActual = parametroPadre.getCatValues();//obtener el catalogo actual a filtrar
							if(padreActual != null){//comprobar padre actual
								//Convertir como arraylist lo valores del parametro actual, antes separarlso por ","
								ArrayList<String> valuesParamsAsArray = new ArrayList<String>(Arrays.asList(parametrosTMP.get(parametroPadre.getCdParametro()).toString().split(",")));
								if(!valuesParamsAsArray.isEmpty()){//validar valores actuales del parametros
									for(String strActual : valuesParamsAsArray){//iterar valores actuales, se solventa con esto si es select multiple
										for(LinkedHashMap<Object, Object> mapActual : catValuesActual){//iterar catalogho actual
											if(mapActual.get("ID").toString().equals(strActual) &&//comprar los ids actuales y la columna de filtro del padre
													mapActual.get(padres.get(i).getNbColumnaFiltro()).toString().equals(parametrosTMP.get(padreActual.getCdParametro()))
												){
												//si los id coincides y el valor de la columna de filtro del padre se obtiene descripcion
												listaParamFiltrada.add(mapActual.get("DESCRIPTION"));
											}
										}
									}
								}
								//se actualizar el valor del poaramtro actual dentro del map
								parametros.put(parametroPadre.getCdParametro(), concatElements(listaParamFiltrada));
							}
						}
					}
				}
			}
		}
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTxTituloExcel(vo.getNbReporte());
		propiedadesReporte.setNbReporte(vo.getNbReporte());
		propiedadesReporte.setTxExtension(txExtension);
		List<String> subtitulos = new ArrayList<String>();
		RutinasTiempoImpl timeRutinas = new RutinasTiempoImpl();
		ByteArrayOutputStream byteArrayOutputStream = null;

		//Establecer la fecha de consulta
		subtitulos.add("Fecha de Consulta: "+timeRutinas.getStringDateFromFormta("dd/MM/yyyy HH:ss", new Date()));
		subtitulos.add(" ");
		//Obtener los parametros del reporte de BD 
		List<ParametrosVO> reporteBD = vo.getParametros();
		
		/*Comprobar si algunos de los parámetros es de tipo Lista Doble*/
		for(ParametrosVO paramVO: reporteBD){
			//preguntar si el componente actual es de tipo lista doble
			if(paramVO.getComponente().getCdComponente().equals("LISTD")){
				parametros.put(paramVO.getCdParametro(),voData.getStrValoresListDoble());
			}
		}
		//Hace el filtro que subtitulos se ver�n en el reporte
		List<LinkedHashMap<Object, Object>> parmsFilter = filtroParametrosBusqueda(reporteBD, parametros);
		
		if (parmsFilter != null && !parmsFilter.isEmpty()) {
			subtitulos.add("Filtros de Búsqueda");
			for (Map<Object, Object> entry : parmsFilter) {
				for (Object key : entry.keySet()) {
					subtitulos.add(key + ": " + entry.get(key));
				}
			}
		}
		if (paginarHoja) {
			if (tipoTilo.getCdTipoTitulo().equals("TM"))// Todo mayuscula
				columnaPaginacion.toUpperCase();
			else
				columnaPaginacion.toLowerCase();
			subtitulos.add(" ");
			values = agrupaMapMultipleHoja(columnaPaginacion, voData.getResultSetToArray(), values);
			propiedadesReporte.setTxColumnaPaginacion(columnaPaginacion);
			propiedadesReporte.setStMultiplehoja(paginarHoja);
		}
		/**Mandar un subtitulo en caso de que el 
		 * numero de resultados exceda el maximo*/
		if(voData.getMsjAvisoMax() != null){
			subtitulos.add(" ");
			subtitulos.add("NOTA: " + voData.getMsjAvisoMax());
			subtitulos.add(" ");
		}
		
		propiedadesReporte.setSubtitulos(subtitulos);
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO(propiedadesReporte, cabeceras, values);
		try {
			byteArrayOutputStream = reporteExcel.generaReporte(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.gc();
		return byteArrayOutputStream;
	}

	@Override
	@Transactional
	public ReportesTaqVO compruebaSiExisteReporteById(HashMap<Object, Object> parametros) {
		ReportesDTO dto = reportesDAO.getReporteById(Long.parseLong((String) parametros.get("idReporte")), cdApp);
		if (dto == null)
			return null;
		ReportesTaqVO vo = new ReportesTaqVO();
		vo = ResponseConverter.copiarPropiedadesFull(dto, ReportesTaqVO.class);
		RestriccionesQueryDTO restriccionDTO = restrccionDAO.getRestriccion();
		if(restriccionDTO != null){
			RestriccionesQueryVO voRestriccion = new RestriccionesQueryVO();
			ResponseConverter.copiarPropriedades(voRestriccion, restriccionDTO);
			vo.setRestriccion(voRestriccion);
		}
		vo.setQueryFull(makeQuery(vo, parametros));
		return vo;
	}

	@Override
	@Transactional
	public List<ParametrosVO> getParametrosByReporte(Long idReporte) {
		List<ParametrosDTO> params = parametroDAO.getParametrosByReporte(idReporte);
		List<ParametrosVO> listVORetunr = new ArrayList<>();
		ParametrosVO vo = null;
		if (params.isEmpty())
			return null;
		for (ParametrosDTO dto : params) {
			vo = new ParametrosVO();
			vo = ResponseConverter.copiarPropiedadesFull(dto, ParametrosVO.class);
			listVORetunr.add(vo);
		}
		return listVORetunr;
	}

	@Override
	public List<LinkedHashMap<Object, Object>> filtroParametrosBusqueda(List<ParametrosVO> parmsVO, HashMap<Object, Object> paramMap) {
		if ((parmsVO != null && !parmsVO.isEmpty()) && (paramMap != null && !paramMap.isEmpty())) {
			List<LinkedHashMap<Object, Object>> mapReturn = new ArrayList<>();
			LinkedHashMap<Object, Object> paramNoNull = null;
			for (ParametrosVO vo : parmsVO) {
				paramNoNull = new LinkedHashMap<>();			
				/*comprobar si el tipo de componente es de tipo switcher,
				* en caso de ser verdadero, se utilizará en la cabecera del reporte 
				* el label que se haya checado en el switch*/
				if(vo.getComponente().getCdComponente().equals("SWITCH")){
				String[] txValor = vo.getTxValor().split(Pattern.quote(","));
				String [] txLabelUno = txValor[0].split(Pattern.quote("|"));
				String [] txLabelDos = txValor[1].split(Pattern.quote("|"));

				String valueCheck = (String) paramMap.get(vo.getCdParametro());
				if(valueCheck.equals(txLabelUno[1])){
				paramNoNull.put(vo.getNbParametro(), txLabelUno[0]);
				                       mapReturn.add(paramNoNull);
				}else{
				paramNoNull.put(vo.getNbParametro(), txLabelDos[0]);
				                       mapReturn.add(paramNoNull);
				}
				continue;

				}
				for (Object key : paramMap.keySet()) {
					if (vo.getCdParametro().equals(key) && paramMap.get(key) != null) {
                        paramNoNull.put(vo.getNbParametro(), paramMap.get(key));
                        mapReturn.add(paramNoNull);
                    }
				}
			}
			return mapReturn;
		}else{
			return null;
		}
	}
	
	@Override
	public String makeQuery(ReportesTaqVO reporteVO, HashMap<Object, Object> parametros) {
		StringBuilder strBuildQuery = new StringBuilder();
		StringBuilder select = new StringBuilder(reporteVO.getScriptSelect());
		StringBuilder from = new StringBuilder(reporteVO.getScritFrom());
		StringBuilder where = new StringBuilder(reporteVO.getScriptWhere());
		strBuildQuery.append("SELECT " + select + " ");
		strBuildQuery.append("FROM " + from + " ");
		strBuildQuery.append("WHERE " + where + " ");
		
		String queryFullStr = strBuildQuery.toString();
		List<ParametrosVO> paramBD = reporteVO.getParametros();
		for (Entry<Object, Object> entry : parametros.entrySet()){
			for (ParametrosVO objectParam : paramBD) {
				if (objectParam.getCdParametro().contains((CharSequence) entry.getKey()) && objectParam.getStMultipleValores() == 1) {
					if(entry.getValue() == null){
						queryFullStr = queryFullStr.replace("#{" + entry.getKey() + "}", "NULL");
					}else{
						queryFullStr = queryFullStr.replace("#{" + entry.getKey() + "}", (CharSequence) getFormatMultiplesValores(entry.getValue().toString()));
					}
				}else if(objectParam.getCdParametro().contains((CharSequence) entry.getKey())){
					if(entry.getValue() == null){
						queryFullStr = queryFullStr.replace("#{" + entry.getKey() + "}", "NULL");
					}else{
						queryFullStr = queryFullStr.replace("#{" + entry.getKey() + "}", "'" +(CharSequence) entry.getValue() +"'");
					}
					
				}
			}		
		}
		return queryFullStr;
	}

	@Override
	public String getFormatMultiplesValores(String str) {
		String[] strSeparado = str.split(",");
        ArrayList<String> idsList = new ArrayList<String>(Arrays.asList(strSeparado));
        StringBuilder strReturn = new StringBuilder();
        for (int i = 0; i < idsList.size(); i++) {
            if (i > 0) {
                strReturn.append(",");
            }
            String element = idsList.get(i);
            strReturn.append("'" + element + "'");
        }
        return strReturn.toString();
	}

	@Override
	public List<LinkedHashMap<Object, Object>> resultSetToArrayMap(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<LinkedHashMap<Object, Object>> list = new ArrayList<LinkedHashMap<Object, Object>>();
        LinkedHashMap<Object, Object> row = null;
        while (rs.next()) {
            row = new LinkedHashMap<Object, Object>();
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);    
        }
		return list;
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Object> agrupaMapMultipleHoja(String columnPaginacion,List<LinkedHashMap<Object, Object>> listFilerRS, List<Object> valores) {
		LinkedHashMap<Object, List<Object>> listMapAgrupacion = new LinkedHashMap<Object, List<Object>>();
        List<Object> listObjectReturn= new ArrayList<>();
        
        for (Map<Object, Object> elmentoActual : listFilerRS) {
            Object valUnico = elmentoActual.get(columnPaginacion);
            listMapAgrupacion.put(valUnico, null);
        }

        Object llaveActual = null;
        List<Object> listValores = null;
        List<Object> listValorIndividual = null;
        Set<Object> headerUniques = listMapAgrupacion.keySet();
        List<Object> headerObject = new ArrayList<>();
        for (Object h : headerUniques) {
            headerObject.add(h);
        }
        LinkedHashMap<Object, List<Object>> hashMapReturn = new LinkedHashMap<Object, List<Object>>();
        for (int h = 0; h < headerObject.size(); h++) {
            llaveActual = headerObject.get(h);
            listValores = new ArrayList<>();
            for (int i = 0; i < valores.size(); i++) {
                listValorIndividual = (List<Object>) valores.get(i);
                for (Object valorEnList : listValorIndividual) {
                    if (((valorEnList == null) && (llaveActual == null)) || ((llaveActual != null) && (llaveActual.equals(valorEnList)))) {
                        listValores.add(listValorIndividual);
                        valores.remove(i);
                        i--;
                        break;
                    }
                }
            }
            hashMapReturn.put(llaveActual, listValores);
            headerObject.remove(h);
            h--;
        }
        System.gc();
        listObjectReturn.addAll(hashMapReturn.entrySet());
        return listObjectReturn;
	}

	@Override
	public Object filtrarParametrosConValor(String valoresActuales, List<ObjectCollectionVO> listaRecuperadaBD) {
		if (!valoresActuales.isEmpty() && !listaRecuperadaBD.isEmpty()) {
			ArrayList<String> valuesParamsAsArray = new ArrayList<String>(Arrays.asList(valoresActuales.split(",")));
			List<Object> listTmp = new ArrayList<>();
			for (int i = 0; i < valuesParamsAsArray.size(); i++) {
				for (int j = 0; j < listaRecuperadaBD.size(); j++) {
					if (valuesParamsAsArray.get(i).toString().equals(listaRecuperadaBD.get(j).getId().toString())) {
						listTmp.add(listaRecuperadaBD.get(j).getDescription());
					}
				}
			}
			return concatElements(listTmp);
		} else {
			return null;
		}
	}

	@Override
	public String concatElements(List<Object> listElements){
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < listElements.size(); i++){
			if (i > 0)
				stb.append(",");
			stb.append(listElements.get(i));
		}
		return stb.toString();
	}

	@Override
	public List<LinkedHashMap<Object, Object>> executeSubQuery(long idParametro, String valores)throws SQLException, NamingException {
		ParamPropScriptDTO paramPropScriptDTO = null;
		ParametrosPropDTO parametrosPropDTO = null;
		List<ParametrosPropDTO> listPropDTO = paramPropiedadDAO.getParametrosPropiedad(idParametro);
		if(!listPropDTO.isEmpty()){
			for(ParametrosPropDTO dto : listPropDTO){
				paramPropScriptDTO =  parampropscriptDAO.getParamPropById(dto.getIdParamtrosProp());
				if(paramPropScriptDTO != null){
					parametrosPropDTO = dto;
					break;
				}			
			}
		}
		String qwery = makeSubQuery(paramPropScriptDTO.getScript(),valores,parametrosPropDTO.getParametro().getIdParamtro(),parametrosPropDTO.getTxValor());
		Connection con = conexionDB.conectarBD();
		Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stm.setFetchSize(1000);
		ResultSet rs = stm.executeQuery(qwery);
		//Lista con los valores extraidos
		List<LinkedHashMap<Object, Object>> map = resultSetToArrayMap(rs);
        con.close();
		stm.close();
		rs.close();
		return map;
	}

	@Override
	public String makeSubQuery(String qwery,String valores,Long idParametro, String txSeparador) {
		String queryFullStr = "";
		queryFullStr = qwery.replace("#{busquePrevia" + idParametro + "}",separarValores(valores,txSeparador));
		return queryFullStr;
	}

	@Override
	public String separarValores(String valores, String txSeparador) {
		String[] valoreSeparados = valores.split(Pattern.quote(txSeparador));
		List<String> valoresArray = Arrays.asList(valoreSeparados);
		StringBuilder strRetur = new StringBuilder(); 
		for(int i=0; i < valoresArray.size(); i++){
			if(i > 0)
			    strRetur.append(",");
			strRetur.append("'"+valoresArray.get(i)+"'");
		}
		return strRetur.toString().trim();
	}


}
