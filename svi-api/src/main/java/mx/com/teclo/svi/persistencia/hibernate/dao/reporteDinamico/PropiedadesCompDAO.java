package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;

public interface PropiedadesCompDAO extends BaseDao<PropiedadesCompDTO> {

	/**
	 * Descripción: Método para consultar las propiedades de los compoentes
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return List<PropiedadesCompDTO>
	 */
	public List<PropiedadesCompDTO> getComponentesPropiedad(Long idComponente);

	/**
	 * Descripción: Método para consultar todas las propiedades de todos los
	 * componentes
	 * 
	 * @author Jorge Luis
	 * @return List<PropiedadesCompDTO>
	 */
	public List<PropiedadesCompDTO> getComponentesPropiedad();

}
