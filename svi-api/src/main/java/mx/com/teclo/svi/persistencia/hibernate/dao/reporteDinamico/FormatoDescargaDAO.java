package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;

public interface FormatoDescargaDAO extends BaseDao<FormatoDescargaDTO> {

	public List<FormatoDescargaDTO> listaFormatoDescarga();

	/**
	 * Descripción: Método para obtener un formato de descarga por su
	 * identificador primario
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return FormatoDescargaDTO
	 */
	public FormatoDescargaDTO getFormatoDescargaById(Long idFormatoDescarga);

}
