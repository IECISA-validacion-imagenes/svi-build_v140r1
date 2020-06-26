package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesDTO;

public interface PropiedadesDAO extends BaseDao<PropiedadesDTO> {

	public List<PropiedadesDTO> listaPropiedades();

	/**
	 * Descripción: Método para consultar la propiedad por su identiifcador
	 * primerio
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return PropiedadesDTO
	 */
	public PropiedadesDTO getPropiedadById(Long idPropiedad);

}
