package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TablasDTO;

public interface TablasDAO extends BaseDao<TablasDTO> {

	/**
	 * Descripción: Método para consultar el catalogo de tablas de base de datos
	 * 
	 * @author Jorge Luis
	 * @return List<TablasDTO>
	 */
	public List<TablasDTO> getCatalogoTablas();
}
