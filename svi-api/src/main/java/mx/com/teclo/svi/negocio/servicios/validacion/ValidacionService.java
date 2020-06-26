package mx.com.teclo.svi.negocio.servicios.validacion;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.archivoDetalle.ValidacionArchivoDetalleEvaVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtClasifExpedientesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtEntidadVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtPerfilesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.catValidacionSiluetasVO;
import mx.com.teclo.svi.negocio.vo.validacion.ValidacionDatosVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;


public interface ValidacionService {

	void validaRegistroEva(ValidacionArchivoDetalleEvaVO validacionArchivoDetalleEvaVO, Long  usuario);
	
	List<PtEntidadVO> getTodasEntidades();
	
	PtEntidadVO getPtEntidadVOPorId(Long idPtCatalogoEntidadVO);
	
	List<PtClasifExpedientesVO> getTodasPtClasifExpedientes();
	
	List<PtPerfilesVO> getCatalogoPerfiles();

	ValidacionDatosVO getRegistrosValidados(Long idArchivo, ValidadoresConfigDTO validador);

	ArchivoCsvDTO setArchivoValidado(Long idArchivo, Long usuario, Long idValidador);
	
	/* obter catalogo de opciones de siluetas */
	List<catValidacionSiluetasVO> getOpcionesSiluetas();	
	
}
