package mx.com.teclo.svideskwsw.service.carga;

import java.util.List;

import mx.com.teclo.svideskwsw.persistencia.vo.carga.DetalleLoteVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.InformacionEnviarVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.LotesyDetalleVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.infoConteosDatosCSVO;

public interface cargaInformacionService {
	
	//public void cargaCSVOriginal(List<LotesyDetalleVO> detalleCSV);
	
	
	/* Metodo para Obtener detalle de
	 * archivos PT para generar rutas */
	public List<infoConteosDatosCSVO> obtenerDetallePT();
	
	
	/* Metodo para obtener el detalle del lote tabla LoteDTO*/
	
	public List<DetalleLoteVO> getDetalleLote(String tipo);	
	
	public void guardarDetalles(List<LotesyDetalleVO> informacion);
	
	public void guardarDetallesPreviendoErroresConexion(LotesyDetalleVO informacion);
	

}


//WEB