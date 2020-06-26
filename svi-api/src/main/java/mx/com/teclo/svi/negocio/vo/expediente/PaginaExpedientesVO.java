package mx.com.teclo.svi.negocio.vo.expediente;

import java.util.List;

public class PaginaExpedientesVO {
	
	private Long totalElementos;
	private Long totalPaginas;
	private Integer tamanio;
	private Long nuElementos;
	private String cdOrder;
	List<InfoBasicaExpedienteVO> contenido;
	
	public Long getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(Long totalElementos) {
		this.totalElementos = totalElementos;
	}
	public Long getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(Long totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public Integer getTamanio() {
		return tamanio;
	}
	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}
	public Long getNuElementos() {
		return nuElementos;
	}
	public void setNuElementos(Long nuElementos) {
		this.nuElementos = nuElementos;
	}
	public String getCdOrder() {
		return cdOrder;
	}
	public void setCdOrder(String cdOrder) {
		this.cdOrder = cdOrder;
	}
	public List<InfoBasicaExpedienteVO> getContenido() {
		return contenido;
	}
	public void setContenido(List<InfoBasicaExpedienteVO> contenido) {
		this.contenido = contenido;
	}
	
	

}
