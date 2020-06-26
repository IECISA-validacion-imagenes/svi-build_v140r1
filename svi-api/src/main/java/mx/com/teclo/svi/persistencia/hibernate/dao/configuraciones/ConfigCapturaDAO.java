package mx.com.teclo.svi.persistencia.hibernate.dao.configuraciones;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.ConfigCapturaDTO;


public interface ConfigCapturaDAO extends BaseDao<ConfigCapturaDTO> {
	
	void guardarConfiguracion(String configuracionVO);
}
