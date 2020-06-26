package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoCsvDTO;

public interface MotivoCsvDAO extends BaseDao<MotivoCsvDTO>{
	Long getMaxCicloByIdArchivoCsv(Long idArchivoCsv);
}
