package mx.com.teclo.svi.negocio.servicios.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.NamingException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.vo.reporte.GestionReportesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectCollectionVO;
import mx.com.teclo.svi.negocio.vo.reporte.ObjectGenericVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;
import mx.com.teclo.svi.negocio.vo.reporte.admireporte.ReporteVO;

/**
 * 
 * @author javier07
 *
 */
public interface ReporteService {

	/**
	 * @author javier07
	 * @param empledoId
	 * @return
	 */
//	List<ReporteVO> obtenerListaLinks(Long empledoId);
//	
	/**
	 * Descripción: Método para consultar todos los reportes
	 * activos
	 * @author Jorge Luis
	 * @return List<ReportesTaqVO>
	 */
	public List<ReportesTaqVO> obtenerReportesActivos();
	
	
	/**
	 * Descripci�n: M�todo para consultar todos los perfiles
	 * que est�n ligados a sus reportes
	 * @author Jorge Luis
	 * @return List<GestionReportesVO>
	 */
	public GestionReportesVO getGestionReportes();
	
	/**
	 * Descripci�n: M�todo para guardar la configuraci�n de permisos
	 * sobre los reportes
	 * @author Jorge Luis
	 * @param GestionReportesVO
	 * @return GestionReportesVO
	 */
	public GestionReportesVO persisteConfigReportePerf(GestionReportesVO voObject);
	
	/**
	 * Descripci�n: M�todo para ejecutar la consulta con JDBC,
	 * la consulta dinamica. Se desarroll� est� m�todo 
	 * independiente para que no interfiera con el tiempo m�ximo de 
	 * transacci�n de hibernate.
	 * @author Jorge Luis
	 * @param HashMap<Object, Object>, ReportesTaqVO
	 * @return ObjectGenericVO
	 */
	public ObjectGenericVO executeQuery(HashMap<Object, Object> parametros, ReportesTaqVO vo)throws SQLException, NamingException;
	
	/**
	 * Descripci�n: M�todo para generar y descargar el 
	 * reporte en excel de los datos consultados, 
	 * las cabeceras y los titulos son dinamicos, 
	 * se adecuan con los datos que tiene el 
	 * reporte configurados en base de datos
	 * 
	 * @author Jorge Luis
	 * @param ObjectGenericVO, ReportesTaqVO
	 * @return ByteArrayOutputStreamthrows IOException, BusinessException, SQLException, NamingException
	 * @throws IOException
	 */
	public ByteArrayOutputStream generateExcel(ObjectGenericVO objectData,ReportesTaqVO reporteVO) throws IOException, BusinessException, SQLException, NamingException;
	
	/**
	 * Descripci�n: M�todo para comprobar si el 
	 * reporte que se va consultar existe.
	 * @author Jorge Luis
	 * @param HashMap<Object, Object>
	 * @return ReportesTaqVO
	 */
	public ReportesTaqVO compruebaSiExisteReporteById(HashMap<Object, Object> params);
	
	/**
	 * Descripci�n: M�todo para consultar los parametros 
	 * de un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametroVO>
	 */
	public List<ParametrosVO> getParametrosByReporte(Long idReporte);
	
	/**
	 * Descripci�n: M�todo para filtrar los parametros de b�squeda,
	 * compara los que recibe de la consulta en front VS los que 
	 * recupera de la base de datos
	 * 
	 * @author Jorge Luis
	 * @param List<ParametroVO>, HashMap<Object, Object>
	 * @return List<LinkedHashMap<Object, Object>>
	 */
	public List<LinkedHashMap<Object, Object>> filtroParametrosBusqueda(List<ParametrosVO> parmsVO, HashMap<Object, Object> paramMap);
	
	/**
	 * Descripci�n: M�todo para construir el query que tiene asociado el reporte
	 * en base de datos
	 * 
	 * @author Jorge Luis
	 * @param ReportesTaqVO, HashMap<String, String>
	 * @return String
	 */
	public String makeQuery(ReportesTaqVO reporteVO, HashMap<Object, Object> parametros);
	
	/**
	 * Descripci�n: Esre m�todo se hizo especialmente para los
	 * parametros que soportan multiples valores en la 
	 * calusula IN del QUERY
	 * 
	 * @author Jorge Luis
	 * @param String
	 * @return String
	 */
	public String getFormatMultiplesValores(String str);
	
	/**
	 * Descripci�n: M�todo para extraer todos 
	 * los resultados obtenidos de la cosnulta 
	 * con JDBC 
	 * @author Jorge Luis
	 * @param ResultSet
	 * @return List<LinkedHashMap<Object, Object>>
	 */
	public List<LinkedHashMap<Object, Object>> resultSetToArrayMap(ResultSet rs) throws SQLException;
	
	/**
	 * Descripci�n: M�todo para filtrar lo 
	 * resultados del ResultMap cuando el 
	 * reporte tiene alguna paginaci�n configurada
	 * @author Jorge Luis
	 * @param String, List<LinkedHashMap<Object, Object>>, List<Object>
	 * @return List<Object>
	 */
	public List<Object> agrupaMapMultipleHoja(String columnPaginacion, List<LinkedHashMap<Object, Object>> listFilerRS,List<Object> valores);
	
	/**
	 * Descripci�n: Método para filtrar solo los parametros que 
	 * si vienen como parametro y eso aparecerá en el reporte
	 * @author Jorge Luis
	 * @param String, List<ObjectCollectionVO>
	 * @return Object
	 */
	public Object filtrarParametrosConValor(String valoresActuales, List<ObjectCollectionVO> listaRecuperadaBD);
	
	
	/**
	 * Descripción: Metodo para ejecutar la subconsulta con jdbc,
	 * la consulta dinamica. Se desarroll� est� m�todo 
	 * independiente para que no interfiera con el tiempo m�ximo de 
	 * transacci�n de hibernate.
	 * @author 
	 * @param HashMap<Object, Object>, ReportesTaqVO
	 * @return ObjectGenericVO
	 */
	public 	List<LinkedHashMap<Object, Object>> executeSubQuery(long idParametro,String valores)throws SQLException, NamingException;
	
	
	/**
	 * Descripci�n: M�todo para construir el query que tiene asociado el reporte
	 * en base de datos
	 * 
	 * @author Jorge Luis
	 * @param String, String, Long, String
	 * @return String
	 */
	public String makeSubQuery(String qwery,String valores,Long idParametro, String txSeparador);
	
	/**
	 * Descripció: Método para separar los 
	 * parametros recibidos de Front
	 * @author Jorge Luis
	 * @param String,String
	 * @return String
	 */
	public String separarValores(String valores,String txSeparador);
	
	/**
	 * Descripció: Método para concatenar objetos de una lista con comas ","
	 * @author Jorge Luis
	 * @param List<Object>
	 * @return String
	 */
	public String concatElements(List<Object> listElements);
	
}
