package mx.com.teclo.svi.persistencia.hibernate.dao;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;

public interface ValidadoresDAO extends BaseDao<ValidadoresDTO> {
	
	Long getIdUsuario(Long idValidador);
	ValidadoresDTO getValidadorByIdValidador(Long idValidador);
	ValidadoresDTO getValidadorByIdUsuario(Long idUsuario);
	Long getTotalMaxAsignar(Long idValidador);
}
