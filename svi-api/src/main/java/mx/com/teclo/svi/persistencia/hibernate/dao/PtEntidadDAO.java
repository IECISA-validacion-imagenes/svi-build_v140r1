package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEntidadDTO;

public interface PtEntidadDAO extends BaseDao<PtEntidadDTO>{
	
	PtEntidadDTO getPtEntidadById(Long idPtCatalogoEntidad);
	
	
	public List<PtEntidadDTO> getCatalogoEntidad();
	
	
}
