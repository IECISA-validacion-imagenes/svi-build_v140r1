package mx.com.teclo.svi.negocio.servicios.reporte;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.exception.PersistenceException;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParamCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.admireporte.ParamValueVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosConsultaReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.SqlTestResultaVO;

public interface AdmiReporteService {

	

	/**
	 * Descripción: Método para filtrar y obtener los parámetros que tendrá el
	 * SQL del reporte a ejecutar
	 * 
	 * @author Jorge Luis
	 * @return List<ParametrosVO>
	 */
	public List<ParametrosVO> identificacionParametro(String cadena);

	/**
	 * Descripción: Método para ejecutar y hacer el test del Query que se haya
	 * mandado de front con sus parámetros correspondientes
	 * 
	 * @author Jorge Luis
	 * @return SqlTestResultaVO
	 */
	public SqlTestResultaVO testUserQuery(String query, String cadena) throws SQLException, NamingException, IOException;

	/**
	 * Descripción: Método para obtener registros relacionados entre el tipo de
	 * parámetros y los componentes ligados
	 * 
	 * @author Jorge Luis
	 * @return List<TipoParamCompVO>
	 */
	public List<TipoParamCompVO> getRelacionParamComponen();

	/**
	 * Descripción: Método para obtener todos los registros asociados entres el
	 * tipo de componente y sus correspondientes propiedades
	 * 
	 * @author Jorge Luis
	 * @return List<TipoParamCompVO>
	 */
	public List<PropiedadesCompVO> getComponentesPropiedades();

	/**
	 * Descripción: Método para guardar nuevo reporte configurado en frontEnd
	 * 
	 * @author Jorge Luis
	 * @return ReportesTaqVO
	 */
	public ReportesTaqNewReporteVO guadaReporte(ReportesTaqNewReporteVO reporteVO);

	/**
	 * Descripción: Método para obtener el catálogo de los tipos de agrupación
	 * registradas en base de datos
	 * 
	 * @author Jorge Luis
	 * @return List<AgrupacionHojasVO>
	 */
	public List<AgrupacionHojasVO> getTipoAgrupacion();

	/**
	 * Descripción: Método para obtener nombre de las columnas de Metadata del
	 * ResultSet
	 * 
	 * @author Jorge Luis
	 * @return List<Object>
	 * @throws SQLException
	 */
	public List<Object> extractColumnInRS(ResultSet rs) throws SQLException;

	/**
	 * Descripción: Método para general el código del reporte a través del
	 * nomebre del reporte
	 * 
	 * @author Jorge Luis
	 * @return String
	 */
	public String generarCdReporte(String nbReporte);

	/**
	 * Descripción: Método para ejecutar el query de 
	 * consulta de catálogo
	 * @author Jorge Luis
	 * @param String
	 * @throws SQLException
	 * @throws NamingException
	 * @return SqlTestResultaVO 
	 */
	public SqlTestResultaVO executeQueryCat(String queryValue)throws SQLException, NamingException;
	
	/**
	 * Descripción: Método para consultar los reportes dinamicos
	 * asociados a ciertos perfiles
	 * @author Jorge Luis
	 * @param ParametrosConsultaReporteVO
	 * @return List<ReportesTaqNewReporteVO>
	 * @throws NotFoundException
	 */
	public List<ReportesTaqNewReporteVO> listaReportesDinamicos(ParametrosConsultaReporteVO paramsConsultaVO)throws NotFoundException;
	
	/**
	 * Descripción: Método para eliminar un reporte
	 * @author Jorge Luis
	 * @throws PersistenceException
	 * @return Boolean
	 */
	public Boolean deleteReporteDinamico(Long idReporte);
	
	/**
	 * Descripción: Método para obtener un reportes 
	 * por su identificador unico
	 * @author Jorge Luis
	 * @param Long
	 * @throws NotFoundException
	 * @return ReportesTaqNewReporteVO
	 */
	public ReportesTaqNewReporteVO getReporteEdit(Long idReporte)throws NotFoundException;
	
	/**
	 * Descripción: Método para actualiar el reporte dinamico
	 * @author Jorge Luis
	 * @param ReportesTaqNewReporteVO
	 * @throws BusinessException
	 * @return Boolean
	 */
	public Boolean guardarEdicion(ReportesTaqNewReporteVO reporteVO) throws BusinessException;
	
	/**
	 * Descripción: Método para convertir String a Objecto VO
	 * @author Jorge Luis
	 * @param String
	 * @throws IOException
	 * @return List<ParamValueVO>
	 */
	public List<ParamValueVO> converJson(String query) throws IOException;
}
