package mx.com.teclo.svi.negocio.servicios.reporte;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.utils.comun.ConnectionUtilBd;
import mx.com.teclo.svi.negocio.vo.reporte.DependenciasVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectCollectionVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosPropVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesFormatosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;
import mx.com.teclo.svi.negocio.vo.reporte.RestriccionesQueryVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosColumnDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosPropDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosTablasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.RestriccionesQueryDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.RestriccionesQueryDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;


@Service
public class ParametrosServiceImpl implements ParametrosService{

	@Value("${app.config.codigo}")
	private String cdApp;

	@Autowired
	private ReportesDAO reportesDAO;
	
	@Autowired
	private ParametrosPropDAO paramPropiedadDAO;
	
	@Autowired
	private ParametrosTablasDAO parametrosTablasDAO;
	
	@Autowired
	private ParametrosColumnDAO parametrosColumnDAO;
	
	@Autowired
	private ConnectionUtilBd conexionDB;
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private RestriccionesQueryDAO restrccionDAO;
	
	@Override
	@Transactional
	public ReportesTaqVO getReporteById(Long idReporte) throws SQLException, NamingException{
		ReportesDTO dto = reportesDAO.getReporteById(idReporte, cdApp);
		if (dto == null)
			return null;
		ReportesTaqVO vo = new ReportesTaqVO();
		vo = ResponseConverter.copiarPropiedadesFull(dto, ReportesTaqVO.class);
		if(!dto.getParametros().isEmpty()){
			List<ParametrosVO> parametrosAux = ResponseConverter.converterLista(new ArrayList<>(), dto.getParametros(), ParametrosVO.class);
			if(!parametrosAux.isEmpty()){
				for(ParametrosVO parametroVO : parametrosAux){
					parametroVO.setIdReporte(idReporte);
					/*Empieza asociazión de propiedades de los parametros*/
					List<ParametrosPropDTO> listPropDTO = paramPropiedadDAO.getParametrosPropiedad(parametroVO.getIdParamtro());
					if(!listPropDTO.isEmpty()){
						List<ParametrosPropVO> listProVo = new ArrayList<>();
						for(ParametrosPropDTO parametrosPropDTO : listPropDTO){
							ParametrosPropVO parametrosPropVO = new ParametrosPropVO();
							parametrosPropVO = ResponseConverter.copiarPropiedadesFull(parametrosPropDTO, ParametrosPropVO.class);
							listProVo.add(parametrosPropVO);
						}
						parametroVO.setParametrosPropAux(listProVo);
					}
					/*Termina asociazión de propiedades de los parametros*/
					/*Comprobar si el parametro actual es de tipo catalogo*/
					if(parametroVO.getStIsCatalogo() == 1){
						Map<Object, Object> getMapRowParamVO = getCatCollection(parametroVO);
						//Obtener el catalogo que se va mostrar en los select
						//List<ObjectCollectionVO> obj = (List<ObjectCollectionVO>) getMapRowParamVO.get("listObject");//getCatCollection(parametroVO);
						ParametrosVO paramRecivenInMap = (ParametrosVO) getMapRowParamVO.get("parametroVO");
						List<LinkedHashMap<Object,Object>> map = paramRecivenInMap.getCatValues();
						if(paramRecivenInMap != null){
							//igual el parametro actual con que se recivio en el mapa, 
							//para que el actual tambien tenga la columna dependientes
							parametroVO = paramRecivenInMap;
						}
						if(map != null && !map.isEmpty()){
							parametroVO.setCatValues(map);
						}
					}
				}
			}
			vo.setParametrosAux(parametrosAux);
		}
		if(!dto.getReporteFormato().isEmpty()){
			List<ReportesFormatosVO> repFormatAux = ResponseConverter.converterLista(new ArrayList<>(), dto.getReporteFormato(), ReportesFormatosVO.class);
			vo.setReporteFormatoAux(repFormatAux);
		}
		RestriccionesQueryDTO restriccionDTO = restrccionDAO.getRestriccion();
		if(restriccionDTO != null){
			RestriccionesQueryVO voRestriccion = new RestriccionesQueryVO();
			ResponseConverter.copiarPropriedades(voRestriccion, restriccionDTO);
			vo.setRestriccion(voRestriccion);
		}
		return vo;
	}
	
	@Transactional
	@Override
	public Map<Object, Object> getCatCollection(ParametrosVO parametroVO) throws SQLException, NamingException {
		ParametrosTablasDTO parametrosTabla = parametrosTablasDAO.getParametrosTablas(parametroVO.getIdParamtro());
		if(parametrosTabla == null)return null;//retornar null su no hay relación
		
		List<ParametrosColumnDTO> columnas = parametrosColumnDAO.getAllColumns(parametrosTabla.getIdParametroTabla());
		if(columnas.isEmpty())return new HashMap<>();//Si no se encuentran columas retornar lista vacía
		
		List<DependenciasVO> padres = new ArrayList<>();
		DependenciasVO padre = null;
		for(ParametrosColumnDTO columnasDTO : columnas){
			if(columnasDTO.getIdParamTabDependency() != null && columnasDTO.getIdParamTabDependency() != null){
				ParametrosTablasDTO paramColumnPadre = parametrosTablasDAO.getParametrosTablaById(columnasDTO.getIdParamTabDependency());
				if(paramColumnPadre != null){
					padre = new DependenciasVO(paramColumnPadre.getParametro().getIdParamtro(),
							paramColumnPadre.getParametro().getCdParametro(),
							columnasDTO.getColumna().getNbReal());
					padres.add(padre);
				}
			}
		}
		if(!padres.isEmpty())parametroVO.setPadres(padres);
		
		String query = makeQueryForCollecction(parametrosTabla,columnas);
		//List<ObjectCollectionVO> listObjectReturn = new ArrayList<>();
		 Map<Object, Object> mapReturn = new HashMap<>();
		if(query != null){
			Connection con = conexionDB.conectarBD();
			Statement stm = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stm.setFetchSize(1000);
			ResultSet rs = stm.executeQuery(query);
			//Get values from RS
			List<LinkedHashMap<Object, Object>> map = reporteService.resultSetToArrayMap(rs);
			parametroVO.setCatValues(map);
			// Lista de valores
			List<ObjectCollectionVO> listObjectReturn = new ArrayList<>();
			ObjectCollectionVO object = null;
			if(!map.isEmpty()){
				for (Map<Object, Object> entry : map) {
					object = new ObjectCollectionVO(entry.get("ID"),entry.get("DESCRIPTION"));
					listObjectReturn.add(object);
				}
			}
			stm.close();
			con.close();
			rs.close();
			mapReturn.put("listObject", listObjectReturn);
			
			/**Verificar si el parametro actual 
			 * tiene hijos, esto se hiz principalmente 
			 * para solventar los parametros de componentes selects
			 * con el evento on-change
			 * */
			/**Obtener todos los parametros_column 
			 * que dependen de la columna actual
			 * */	
			List<ParametrosColumnDTO> dependenciasHijo = parametrosColumnDAO.getHijos(parametrosTabla.getIdParametroTabla());
			if(!dependenciasHijo.isEmpty()){
				List<DependenciasVO> hijosVO = new ArrayList<DependenciasVO>();
				for(ParametrosColumnDTO hijosDTO: dependenciasHijo){
					DependenciasVO dependencia = null;
					dependencia = new DependenciasVO(hijosDTO.getParametroTabla().getParametro().getIdParamtro(),
							hijosDTO.getParametroTabla().getParametro().getCdParametro(),
							hijosDTO.getColumna().getNbReal());
					hijosVO.add(dependencia);
				}
				parametroVO.setDependencias(hijosVO);
			}
			mapReturn.put("parametroVO", parametroVO);
		}
		return mapReturn;
	}
	
	@Override
	public String makeQueryForCollecction(ParametrosTablasDTO parametrosTabla,List<ParametrosColumnDTO> paramsColumnas) {
		String nbTablaReal = parametrosTabla.getTabla().getNbReal();//Obtener el nombre real de la tabla a consultar
		List<ParametrosColumnDTO> keysCollection = new ArrayList<>();//inicializar listas para filtrar keys, desc, wheres y orders
		List<ParametrosColumnDTO> descCollection = new ArrayList<>();
		List<ParametrosColumnDTO> whereCollection = new ArrayList<>();
		List<ParametrosColumnDTO> orderCollection = new ArrayList<>();

		for (ParametrosColumnDTO pramsColumnDTO : paramsColumnas) {//ciclo para filtrar los keys, desc, wheres y orders
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
		if(!descCollection.isEmpty())
			sortElementByNumOrden(descCollection);
		if(!orderCollection.isEmpty())
			sortElementByNumOrden(orderCollection);
		

		StringBuilder strSelect = new StringBuilder().append("SELECT DISTINCT ");//inicilizar el string a retornar
		// filtrar los campos key a seleccionar
		for (ParametrosColumnDTO key : keysCollection) {//Obtener el key
			ColumnasDTO columnDTO = key.getColumna();
			strSelect.append(columnDTO.getNbReal() + " AS id, ");
		}
		// filtrar los campos descripcion a seleccionar
		List<String> descripciones = new ArrayList<>();
		StringBuilder strDescripciones = new StringBuilder();
		for (ParametrosColumnDTO desc : descCollection) {
			descripciones.add(desc.getColumna().getNbReal());// obteniendo todas las descripciones
		}
		for (int i = 0; i < descripciones.size(); i++) {//ciclo para concatenar las descripciones
			if (i > 0) {
				strDescripciones.append("||' '||");
			}
			strDescripciones.append(descripciones.get(i));
		}
		strDescripciones.append(" AS description ");//concatenar las descripciones
		strSelect.append(strDescripciones.toString());

		StringBuilder strFrom = new StringBuilder().append(" FROM ");
		strFrom.append(nbTablaReal);//Concatenar el from
	
		StringBuilder strWhere = new StringBuilder();
		if (!whereCollection.isEmpty()) {//comprobar los filtros de búsqueda
			for(ParametrosColumnDTO dtpPC: whereCollection){
				if(dtpPC.getIdParamTabDependency() == null && dtpPC.getTxValorWhere() != null){
					strWhere.append(" WHERE ");
					break;
				}
			}
			for (int i = 0; i < whereCollection.size(); i++) {
				if((whereCollection.get(i).getTxValorWhere() != null) || (whereCollection.get(i).getIdParamTabDependency() == null && whereCollection.get(i).getTxValorWhere() == null)){
					if (i > 0) {
						strWhere.append(" AND ");
					}
					String rest = filtroTipoOperadorValues(whereCollection.get(i).getTipoOperador(), whereCollection.get(i).getTxValorWhere());
					strWhere.append(whereCollection.get(i).getColumna().getNbReal() +" "+ rest);
					/**append ejemplo: ID_ARTICULO = 1*/
					/*strWhere.append(whereCollection.get(i).getColumna().getNbReal() + //nombre real de la columna
									whereCollection.get(i).getTipoOperador().getTxValor()+ //tipo operador ej. IN, =, etc.
									"'"+ whereCollection.get(i).getTxValorWhere() + "'");//valor de ña restricción
					*/
				}else if(whereCollection.get(i).getIdParamTabDependency() != null && whereCollection.get(i).getIdParamTabDependency() != 0){
					strSelect.append(", "+whereCollection.get(i).getColumna().getNbReal());
				}
			}
		}
		
		StringBuilder strOrders = new StringBuilder();
		if(!orderCollection.isEmpty()){
			strOrders.append(" ORDER BY ");
			for (int i = 0; i < orderCollection.size(); i++) {
				if (i > 0) {
					strOrders.append(",");
				}
				String tpOrder = orderCollection.get(i).getTpOrder() != null ? orderCollection.get(i).getTpOrder(): "";
				strOrders.append(orderCollection.get(i).getColumna().getNbReal() +" " + tpOrder);
			}
		}
		strSelect.append(strFrom.toString());
		strSelect.append(strWhere.toString());
		strSelect.append(strOrders.toString());
		return strSelect.toString();//retornar el string que se ejecutará con jdbc
	}

	@Override
	public List<ParametrosColumnDTO> sortElementByNumOrden(List<ParametrosColumnDTO> elementos) {
		Collections.sort(elementos, new Comparator<ParametrosColumnDTO>() {
            public int compare(ParametrosColumnDTO o1, ParametrosColumnDTO o2) {
                return o1.getNuOrden().compareTo(o2.getNuOrden());
            }
        });
		return elementos;
	}
	
	public String filtroTipoOperadorValues(TipoOperadorDTO operadorDTO, String valores) {
		String strReturn = "";
		if (operadorDTO != null && operadorDTO.getStValorRequerido().equals(1L)) {
			switch (operadorDTO.getCdOperador()) {
			case "IGUAL":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "DIF":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "MAY":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "MAYEQUAL":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "MEN":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "MENEQUAL":
				strReturn += operadorDTO.getTxValor() + " \'" + valores + "\'";
				break;
			case "LIKE":
				strReturn += operadorDTO.getTxValor() + " \'%" + valores + "%\'";
				break;
			case "BETWEEN":
				String[] valoreSeparados = valores.split(Pattern.quote("|"));
				strReturn += operadorDTO.getTxValor() + " " + valoreSeparados[0] + " AND " + valoreSeparados[1];
				break;
			case "IN":
				strReturn = inNotInStr(operadorDTO.getTxValor(), valores);
				break;
			case "NOTIN":
				strReturn = inNotInStr(operadorDTO.getTxValor(), valores);
				break;
			default:
				break;
			}
		}else if(operadorDTO != null && operadorDTO.getStValorRequerido().equals(0L)){
			strReturn += operadorDTO.getTxValor();
		}
		return strReturn;
	}
	public String inNotInStr(String operador, String valores){
		String[] valoreSeparados = valores.split(Pattern.quote("|"));
		String strReturn = "";
		String val = "";
		strReturn += operador + "(";
		for(int i=0; i < valoreSeparados.length; i++){
			if(i > 0)
				val += ",";
			val += "\'" + valoreSeparados[i] + "\'";
		}
		strReturn += val + ") ";
		return strReturn;
	}
}
