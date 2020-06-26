package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleValidadorDTO;

public interface DetalleValidadorDAO extends BaseDao<DetalleValidadorDTO>{
	
	Long countByIdRegistroCSV(Long idRegistroCsv);

}
