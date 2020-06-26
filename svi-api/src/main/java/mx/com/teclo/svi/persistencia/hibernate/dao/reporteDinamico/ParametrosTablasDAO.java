package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;


import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;

public interface ParametrosTablasDAO extends BaseDao<ParametrosTablasDTO>{

	/**
	 * Descripción: Método para obtener todso los parametros
	 *  que estén asociados a un catalogo
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosTablasDTO
	 */
	public ParametrosTablasDTO getParametrosTablas(Long idParametro);
	
	
	/**
	 * Descripción: Método para obtener el registro 
	 * por su identificador unico
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosTablasDTO
	 */
	public ParametrosTablasDTO getParametrosTablaById(Long idParametroTabla);

}
