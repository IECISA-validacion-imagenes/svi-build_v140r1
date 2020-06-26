package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;

public interface PtSiluetasDAO extends BaseDao<PtSiluetasDTO> {
	
	public List<PtSiluetasDTO> getCatValidacionSiluetas();

}
