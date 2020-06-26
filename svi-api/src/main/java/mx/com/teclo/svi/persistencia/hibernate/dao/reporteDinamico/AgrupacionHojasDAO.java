package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.AgrupacionHojasDTO;


public interface AgrupacionHojasDAO extends BaseDao<AgrupacionHojasDTO> {

	/**
	 * Descripción: Método para obtener de base de datos los tipos de agrupacion
	 * de hojas en el excel
	 * 
	 * @author Jorge Luis
	 * @return List<AgrupacionHojasDTO>
	 */
	public List<AgrupacionHojasDTO> getReportesFormato();

	/**
	 * Descripción: Método para obtener el tipo de agrupación de hojas por su
	 * identificador unico en base de datos
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return AgrupacionHojasDTO
	 */
	public AgrupacionHojasDTO getTipoAgrupacionById(Long idTipoAgrupacion);

}
