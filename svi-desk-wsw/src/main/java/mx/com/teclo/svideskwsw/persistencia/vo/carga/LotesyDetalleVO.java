package mx.com.teclo.svideskwsw.persistencia.vo.carga;

import java.util.List;

public class LotesyDetalleVO {
	
	private List<DetalleArchivoPTVO> detalleArchivosCSV;
    private infoConteosDatosCSVO listaDetalleElemento;
    
	/**
	 * @return the detalleArchivosCSV
	 */
	public List<DetalleArchivoPTVO> getDetalleArchivosCSV() {
		return detalleArchivosCSV;
	}

	/**
	 * @param detalleArchivosCSV the detalleArchivosCSV to set
	 */
	public void setDetalleArchivosCSV(List<DetalleArchivoPTVO> detalleArchivosCSV) {
		this.detalleArchivosCSV = detalleArchivosCSV;
	}

	/**
	 * @return the listaDetalleElemento
	 */
	public infoConteosDatosCSVO getListaDetalleElemento() {
		return listaDetalleElemento;
	}

	/**
	 * @param listaDetalleElemento the listaDetalleElemento to set
	 */
	public void setListaDetalleElemento(infoConteosDatosCSVO listaDetalleElemento) {
		this.listaDetalleElemento = listaDetalleElemento;
	}


    
//    public LotesyDetalleVO (List<DetalleArchivoPTVO> detalleArchivosCSV,  infoConteosDatosCSVO listaDetalleElemento){
//    	this.detalleArchivosCSV= detalleArchivosCSV;
//    	this.listaDetalleElemento = listaDetalleElemento;
//    	
//    }

}
