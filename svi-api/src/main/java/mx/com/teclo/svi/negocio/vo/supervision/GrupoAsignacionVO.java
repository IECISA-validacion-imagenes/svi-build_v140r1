package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

public class GrupoAsignacionVO {
	private Long idmotivo;
	private List<Long> listaRegistros;
	
	public Long getIdmotivo() {
		return idmotivo;
	}
	public void setIdmotivo(Long idmotivo) {
		this.idmotivo = idmotivo;
	}
	public List<Long> getListaRegistros() {
		return listaRegistros;
	}
	public void setListaRegistros(List<Long> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}
	
	
}
