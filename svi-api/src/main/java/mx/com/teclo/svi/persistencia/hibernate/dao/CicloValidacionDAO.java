package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.CicloValidacionDTO;

public interface CicloValidacionDAO extends BaseDao<CicloValidacionDTO>{

	CicloValidacionDTO getCicloVigente();
	
	Boolean isCicloAnteriorCerrado();

}
