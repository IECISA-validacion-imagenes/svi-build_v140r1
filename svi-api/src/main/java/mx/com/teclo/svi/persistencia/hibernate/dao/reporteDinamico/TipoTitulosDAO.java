package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoTitulosDTO;

public interface TipoTitulosDAO extends BaseDao<TipoTitulosDTO> {

	/**
	 * Descripción: Método para consultar los tipos de títulos disponibles en bd
	 * 
	 * @author Jorge Luis
	 * @return List<TipoTitulosDTO>
	 */
	public List<TipoTitulosDTO> listaTipoTitulo();

	/**
	 * Descripción: Método para consultar el tipo de título por un código
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return TipoTitulosDTO
	 */
	public TipoTitulosDTO getTipoTituloById(Long idTipoTitulo);
}
