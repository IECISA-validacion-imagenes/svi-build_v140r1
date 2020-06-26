package mx.com.teclo.svi.negocio.vo.supervision;

public class CatEtiquetqasRevalVO {

	private Long idEtiqReval;
	private String nbEtiqueta;
	private String filtro;
	private boolean stActivo;
	
	
	
	public boolean isStActivo() {
		return stActivo;
	}
	public void setStActivo(boolean stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdEtiqReval() {
		return idEtiqReval;
	}
	public void setIdEtiqReval(Long idEtiqReval) {
		this.idEtiqReval = idEtiqReval;
	}
	public String getNbEtiqueta() {
		return nbEtiqueta;
	}
	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}
