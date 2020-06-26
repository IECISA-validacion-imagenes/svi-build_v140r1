package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;


public interface ParametrosColumnDAO extends BaseDao<ParametrosColumnDTO>{

	/**
	 * Descripción: Método para obtener todas las columnas
	 * que estén asociados a una tabla y a su ves esta 
	 * tabla pertene a un parametro
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getAllColumns(Long idParametroTabla);
	
	
	/**
	 * Descripción: Método para obtener todos 
	 * registros que dependen de la columna actual
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getHijos(Long idParametroTabla);
	
	/**
	 * Descripción: Método para obtener el objeto identificador
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosColumnDTO
	 */
	public ParametrosColumnDTO getIdentificador(Long idColumna, Long idParametroTabla);

	/**
	 * Descripción: Método para obtener el objeto descripcion
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosColumnDTO
	 */
	public ParametrosColumnDTO getDescripcion(Long idColumna, Long idParametroTabla);
	
	/**
	 * Descripción: Método para obtener el objeto restriccion
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosColumnDTO
	 */
	public ParametrosColumnDTO getRestriccion(Long idColumna, Long idParametroTabla);
	
	/**
	 * Descripción: Método para obtener el objeto orden
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosColumnDTO
	 */
	public ParametrosColumnDTO getOrden(Long idColumna, Long idParametroTabla);


	/**
	 * Descripción: Método para obtener la lista de descripciones
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getDescripcionList(Long idParametroTabla);
	
	/**
	 * Descripción: Método para obtener la lista de restricciones
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getRestriccionList(Long idParametroTabla);
	/**
	 * Descripción: Método para obtener la lista de ordenes
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getOrdenList(Long idParametroTabla);
	
	/**
	 * Descripción: Método para obtener todos las columnas
	 * que dependen de otro
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getColumnasDependientes(Long idParametroTabla);
	
	
	/**
	 * Descripción: Método para obtener el objeto identificador
	 * @author Jorge Luis
	 * @return ParametrosColumnDTO
	 */
	public ParametrosColumnDTO getIdentificador(Long idParametroTabla);

}
