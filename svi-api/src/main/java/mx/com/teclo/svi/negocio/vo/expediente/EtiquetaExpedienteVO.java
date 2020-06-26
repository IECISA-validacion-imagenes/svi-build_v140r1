package mx.com.teclo.svi.negocio.vo.expediente;

public class EtiquetaExpedienteVO {
	private Long idEtiqueta;
	private String nbEtiqueta;
	private FiltroExpedienteVO filtro;
	private Boolean isDuplicada;

	public Long getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(Long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public String getNbEtiqueta() {
		return nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public FiltroExpedienteVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroExpedienteVO filtro) {
		this.filtro = filtro;
	}

	public Boolean getIsDuplicada() {
		return isDuplicada;
	}

	public void setIsDuplicada(Boolean isDuplicada) {
		this.isDuplicada = isDuplicada;
	}

}
