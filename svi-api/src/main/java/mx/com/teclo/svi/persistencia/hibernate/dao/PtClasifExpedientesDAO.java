package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;

public interface PtClasifExpedientesDAO extends BaseDao<PtClasifExpedientesDTO> {
	
	public List<PtClasifExpedientesDTO> getClasificacionExpediente();
		

}

