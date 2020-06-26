package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.CaracteresDTO;


public interface CaracteresDAO extends BaseDao<CaracteresDTO>{

	/**
	 * Descripción: Método para consultar los caracteres en BD
	 * para separar el String en la búsqueda previa
	 * @author Jorge Luis
	 * @return List<CaracteresDTO>
	 */
	public List<CaracteresDTO> getCaracteresSeperacion();
	
}
