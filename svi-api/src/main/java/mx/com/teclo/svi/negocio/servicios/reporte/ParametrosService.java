package mx.com.teclo.svi.negocio.servicios.reporte;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import mx.com.teclo.svi.negocio.vo.reporte.ParametrosVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;

public interface ParametrosService {

	/**
	 * Descripción: Método para construir el query 
	 * que se ejecutará para ontener los datos 
	 * que van a poblar los componentes seleccionables
	 * @author Jorge Luis
	 * @param ParametrosTablasDTO, List<ParametrosColumnDTO>
	 * @return String
	 */
	public String makeQueryForCollecction(ParametrosTablasDTO parametrosTabla, List<ParametrosColumnDTO> paramsColumnas);
	
	
	/**
	 * Descripci�n: M�todo para obtener el 
	 * reporte por su identificador unicos
	 * @author Jorge Luis
	 * @param Long
	 * @return ReportesTaqVO
	 */
	public ReportesTaqVO getReporteById(Long idReporte) throws SQLException, NamingException;
	
	/**
	 * Descripción: Método para obtener los 
	 * registros del catalogo
	 * @author Jorge Luis
	 * @param Long
	 * @return ReportesTaqVO
	 */
	public Map<Object, Object> getCatCollection(ParametrosVO parametroVO) throws SQLException, NamingException;
	
	/**
	 * Descripción: Método para ordenar los elementos en la lista
	 * @author Jorge Luis
	 * @param List<ParametrosColumnDTO>
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> sortElementByNumOrden(List<ParametrosColumnDTO> elementos);
}
