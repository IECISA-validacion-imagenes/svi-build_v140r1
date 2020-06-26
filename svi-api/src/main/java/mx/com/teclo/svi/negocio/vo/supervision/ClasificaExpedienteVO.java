package mx.com.teclo.svi.negocio.vo.supervision;

public class ClasificaExpedienteVO {

	private String idClasificacion;
	private String nbClasificacion;
	
	public ClasificaExpedienteVO(String idClasificacion, String nbClasificacion){
		this.idClasificacion = idClasificacion;
		this.nbClasificacion = nbClasificacion;
	}
	
	public String getIdClasificacion() {
		return idClasificacion;
	}
	public void setIdClasificacion(String idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	public String getNbClasificacion() {
		return nbClasificacion;
	}
	public void setNbClasificacion(String nbClasificacion) {
		this.nbClasificacion = nbClasificacion;
	}
}
