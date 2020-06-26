package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

public class ArchivoRevalidacionVO {
	
	private Long idArchivo;
	private Long idValidador;
	private Long idMotivo;
	private Long idMotivoCsv;
	
	
	public Long getIdMotivoCsv() {
			return idMotivoCsv;
		}
	public void setIdMotivoCsv(Long idMotivoCsv) {
		this.idMotivoCsv = idMotivoCsv;
	}
	//	private Long id
	private List<GrupoExpedientesVO> listaGrupoExpedientes;
	
	
	
	public Long getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Long idMotivo) {
		this.idMotivo = idMotivo;
	}
	public Long getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}
	public Long getIdValidador() {
		return idValidador;
	}
	public void setIdValidador(Long idValidador) {
		this.idValidador = idValidador;
	}
	public List<GrupoExpedientesVO> getListaGrupoExpedientes() {
		return listaGrupoExpedientes;
	}
	public void setListaGrupoExpedientes(List<GrupoExpedientesVO> listaGrupoExpedientes) {
		this.listaGrupoExpedientes = listaGrupoExpedientes;
	}
	
	
}
