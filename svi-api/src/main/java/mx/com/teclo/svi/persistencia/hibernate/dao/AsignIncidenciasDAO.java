package mx.com.teclo.svi.persistencia.hibernate.dao;


import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignIncidenciasDTO;

public interface AsignIncidenciasDAO  extends BaseDao<AsignIncidenciasDTO>{
	
	public AsignIncidenciasDTO getInconsistenciaAsignada(Long IdUsuario);

	int getAllInconsistenciasAsignadas(Long IdUsuario);

}
