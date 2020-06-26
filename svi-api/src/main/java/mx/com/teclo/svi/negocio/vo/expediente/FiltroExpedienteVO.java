package mx.com.teclo.svi.negocio.vo.expediente;

import java.util.List;

public class FiltroExpedienteVO {
	private Long idFiltro;
	private String nbEtiqueta;
	private String cdPlaca;
	private String nuExpediente;
	private int tipoBusqueda;
	private List<Long> listaEntregas;
	private List<Long> listaLotes;
	private List<Long> listaCsvs;
	private List<Long> listaMarcas;
	private List<Long> listaSubMarcas;
	private List<Long> listaPerfiles;
	private List<Long> listaIncidencias;
	
	

	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public Long getIdFiltro() {
		return idFiltro;
	}

	public void setIdFiltro(Long idFiltro) {
		this.idFiltro = idFiltro;
	}

	public String getNbEtiqueta() {
		return nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public String getCdPlaca() {
		return cdPlaca;
	}

	public void setCdPlaca(String cdPlaca) {
		this.cdPlaca = cdPlaca;
	}

	public String getNuExpediente() {
		return nuExpediente;
	}

	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}

	public List<Long> getListaEntregas() {
		return listaEntregas;
	}

	public void setListaEntregas(List<Long> listaEntregas) {
		this.listaEntregas = listaEntregas;
	}

	public List<Long> getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(List<Long> listaLotes) {
		this.listaLotes = listaLotes;
	}

	public List<Long> getListaCsvs() {
		return listaCsvs;
	}

	public void setListaCsvs(List<Long> listaCsvs) {
		this.listaCsvs = listaCsvs;
	}

	public List<Long> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Long> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public List<Long> getListaSubMarcas() {
		return listaSubMarcas;
	}

	public void setListaSubMarcas(List<Long> listaSubMarcas) {
		this.listaSubMarcas = listaSubMarcas;
	}

	public List<Long> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<Long> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	
	public List<Long> getListaIncidencias() {
		return listaIncidencias;
	}

	public void setListaIncidencias(List<Long> listaIncidencias) {
		this.listaIncidencias = listaIncidencias;
	}

	@Override
	/**
	 * El toString se ocupa para obtener el hash y comparar con algun otro filtro
	 * y decidir si se tiene duplicidad de filtro
	 */
	public String toString() {
		return "FiltroExpedienteVO [" + (idFiltro != null ? "idFiltro=" + idFiltro + ", " : "")
				+ (nbEtiqueta != null ? "nbEtiqueta=" + nbEtiqueta + ", " : "")
				+ (cdPlaca != null ? "cdPlaca=" + cdPlaca + ", " : "")
				+ (nuExpediente != null ? "nuExpediente=" + nuExpediente + ", " : "")
				+ (listaEntregas != null ? "listaEntregas=" + listaEntregas + ", " : "")
				+ (listaLotes != null ? "listaLotes=" + listaLotes + ", " : "")
				+ (listaCsvs != null ? "listaCsvs=" + listaCsvs + ", " : "")
				+ (listaMarcas != null ? "listaMarcas=" + listaMarcas + ", " : "")
				+ (listaSubMarcas != null ? "listaSubMarcas=" + listaSubMarcas + ", " : "")
				+ (listaPerfiles != null ? "listaPerfiles=" + listaPerfiles + ", " : "")
				+ (listaIncidencias != null ? "listaIncidencias=" + listaIncidencias : "") + "]";
	}

	
}
