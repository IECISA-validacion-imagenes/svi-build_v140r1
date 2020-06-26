package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

public class GrupoExpedientesVO {
	
	private Long idMotivo;
	private String filtro;
	private List<Long> listaExpedientes;
	
	public Long getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Long idMotivo) {
		this.idMotivo = idMotivo;
	}
	public List<Long> getListaExpedientes() {
		return listaExpedientes;
	}
	public void setListaExpedientes(List<Long> listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}
