package mx.com.teclo.svi.negocio.vo.catalogo;

public class catValidacionSiluetasVO {
	
	private long idCatValidacion;
	private String  cdValidacionSilueta;
	private String txtDescripcion;
	
	/**
	 * @return the idCatValidacion
	 */
	public long getIdCatValidacion() {
		return idCatValidacion;
	}
	/**
	 * @param idCatValidacion the idCatValidacion to set
	 */
	public void setIdCatValidacion(long idCatValidacion) {
		this.idCatValidacion = idCatValidacion;
	}
	/**
	 * @return the cdValidacionSilueta
	 */
	public String getCdValidacionSilueta() {
		return cdValidacionSilueta;
	}
	/**
	 * @param cdValidacionSilueta the cdValidacionSilueta to set
	 */
	public void setCdValidacionSilueta(String cdValidacionSilueta) {
		this.cdValidacionSilueta = cdValidacionSilueta;
	}
	/**
	 * @return the txtDescripcion
	 */
	public String getTxtDescripcion() {
		return txtDescripcion;
	}
	/**
	 * @param txtDescripcion the txtDescripcion to set
	 */
	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
}
