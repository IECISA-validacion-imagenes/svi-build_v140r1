package mx.com.teclo.svideskwsw.persistencia.vo.carga;

import java.util.List;

public class InformacionEnviarVO {
	
	private List<LotesyDetalleVO> datos;
	
	public InformacionEnviarVO(List<LotesyDetalleVO> datos){
		this.setDatos(datos);
	}

	public List<LotesyDetalleVO> getDatos() {
		return datos;
	}

	public void setDatos(List<LotesyDetalleVO> datos) {
		this.datos = datos;
	}
	
}
