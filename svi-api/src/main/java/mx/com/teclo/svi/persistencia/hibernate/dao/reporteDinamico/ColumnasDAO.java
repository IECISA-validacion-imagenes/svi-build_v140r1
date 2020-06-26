package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;


public interface ColumnasDAO extends BaseDao<ColumnasDTO> {

	/**
	 * Descripción: Método para obtener todas las columnas pertenecientes los
	 * objetos de base de datos
	 * 
	 * @author Jorge Luis
	 * @return List<ColumnasDTO>
	 */
	public List<ColumnasDTO> getCatalogoColumnas();

	/**
	 * Descripción: Método para consultar el 
	 * la tabla padre y columna de filtracion
	 * 
	 * @author Jorge Luis
	 * @return ColumnasDTO
	 */
	public ColumnasDTO getColumnaDeFiltro(Long idTabla, String strColumnaReal);
}
