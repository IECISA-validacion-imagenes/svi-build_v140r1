package mx.com.teclo.svi.persistencia.hibernate.dao.perfilDAO;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilDTO;


public interface PerfilDAO extends BaseDao<PerfilDTO> {

	/**
	 * @author javier07 Obtiene una lista de perfiles no asociadas al
	 *         Administrador
	 * @param codeApplication
	 *            Codigo de la aplicacion
	 * @return List<PerfilVO>
	 */
	List<PerfilDTO> obtieneListaPerfiles(String codeApplication);

	/**
	 * @author javier07 Obtiene una lista de perfiles asociadas al Administrador
	 * @param codeApplication
	 *            Codigo de la aplicacion
	 * @return List<PerfilVO>
	 */
	List<PerfilDTO> obtieneListaPerfilesTCLAdministrador(String codeApplication);

//	/**
//	 * @author javier07 Obtiene una lista de perfiles asociadas al Administrador
//	 * @param codeApplication
//	 *            Codigo de la aplicacion
//	 * @return List<PerfilVO>
//	 */
//	List<PerfilDTO> obtieneListaPerfilesTCLSoporteEspecial(String codeApplication);

	/**
	 * Obtener perfiles mediante el id y codigo de la aplicacion,
	 * @author Fernando Castillo
	 * @return List<PerfilDTO>
	 * */
	PerfilDTO getPerfilById(Long idPerfil,String cdApp);
	
	/**
	 * @author Javier Flores
	 * @param nombrePerfil
	 * @return PerfilDTO
	 */
	PerfilDTO buscarPerfilPorNombre(String nombrePerfil,String codigoAplicacion);

	/**
	 * Descripcian: Matodo para obtener todos los perfiles asociados a esta app
	 * y que estan activos
	 * @author Jorge Luis
	 * @param String
	 * @return List<PerfilDTO>
	 */
	 public List<PerfilDTO> getPerfilActiveBycdApp(String cdApp);
	 
	 /**
	  * Descripcian: Matodo para obtener reporte con el id
	  * @author Fernando Octavio
	  * @param Long idPerfil
	  * @return List<PerfilDTO>
	 */
	public PerfilDTO getPerfilbyIdperfil(Long idPerfil);
	 
	 
}
