package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO;
import mx.com.teclo.svi.negocio.vo.expediente.EtiquetaExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEtiquetasRevalDTO;

public interface PtEtiquetasRevalDAO extends BaseDao<PtEtiquetasRevalDTO> {

	Long countByCdUnicidad(Long cdUnicidad);
	
	EtiquetaExpedienteVO findByCdUnicidad(Long cdUnicidad);
	
	Long countByNbEtiqueta(String etiqueta);
	
	EtiquetaExpedienteVO findByNbEtiqueta(String etiqueta);
	
	List<PtEtiquetasRevalDTO> getAllEtiquetas();
	
	DetalleFiltroVO getDetalleFiltro(Long idFiltro);

}
