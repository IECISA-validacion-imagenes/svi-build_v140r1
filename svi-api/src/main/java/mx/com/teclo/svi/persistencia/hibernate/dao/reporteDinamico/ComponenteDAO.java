package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ComponentesDTO;


public interface ComponenteDAO extends BaseDao<ComponentesDTO> {

	public List<ComponentesDTO> listaComponentes();

	/**
	 * Descripción: Método para obtener el componente seleccionado en front por
	 * su identificador unico
	 * 
	 * @author Jorge Luis
	 * @param Long
	 * @return ComponentesDTO
	 */
	public ComponentesDTO getComponentesById(Long idComponente);

}
