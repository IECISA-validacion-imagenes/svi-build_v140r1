package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.admin;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.reporte.PerfilesAdminVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilSeDTO;

public interface Perfil_SE_DAO extends BaseDao<PerfilSeDTO>{
	
	public List<PerfilesAdminVO> getPerfiles();
	
	PerfilSeDTO getPerfilbyIdperfil(Long idPerfil);
	
}
