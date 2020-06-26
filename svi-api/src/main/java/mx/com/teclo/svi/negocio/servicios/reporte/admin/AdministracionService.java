package mx.com.teclo.svi.negocio.servicios.reporte.admin;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.PerfilesAdminVO;

public interface AdministracionService {
	
	public List<PerfilesAdminVO>  getPerfilesActivos();

}
