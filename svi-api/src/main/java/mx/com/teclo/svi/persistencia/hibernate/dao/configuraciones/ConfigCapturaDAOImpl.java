package mx.com.teclo.svi.persistencia.hibernate.dao.configuraciones;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.configuraciones.FormularioRevalidacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ConfigCapturaDTO;

@Repository
public class ConfigCapturaDAOImpl extends BaseDaoHibernate<ConfigCapturaDTO> implements ConfigCapturaDAO {

	@Override
	public void guardarConfiguracion(String configuracionVO) {
		
		
	}
}
