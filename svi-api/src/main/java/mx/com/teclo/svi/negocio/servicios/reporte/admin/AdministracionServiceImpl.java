package mx.com.teclo.svi.negocio.servicios.reporte.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.svi.negocio.vo.reporte.PerfilesAdminVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.admin.Perfil_SE_DAO;

@Service
public class AdministracionServiceImpl implements AdministracionService {
	
	@Autowired
	private Perfil_SE_DAO perfilNuevo;

	@Override
	public List<PerfilesAdminVO> getPerfilesActivos() {
		List<PerfilesAdminVO> perfilesVO = new ArrayList<PerfilesAdminVO>();
		perfilesVO = perfilNuevo.getPerfiles();
		return perfilesVO;
	}

}
