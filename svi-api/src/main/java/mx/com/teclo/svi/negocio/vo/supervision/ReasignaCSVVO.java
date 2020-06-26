package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;

public class ReasignaCSVVO {

	private List<GrupoAsignacionVO> lista;
	private ValidadorVO validador;
	private Long idArchivoCsv;
	private CatalogoVO motivo;
	private Long idEtiqueta;
	
	
	
	
	public Long getIdEtiqueta() {
		return idEtiqueta;
	}
	public void setIdEtiqueta(Long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}
	public List<GrupoAsignacionVO> getLista() {
		return lista;
	}
	public void setLista(List<GrupoAsignacionVO> lista) {
		this.lista = lista;
	}
	public ValidadorVO getValidador() {
		return validador;
	}
	public void setValidador(ValidadorVO validador) {
		this.validador = validador;
	}
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public CatalogoVO getMotivo() {
		return motivo;
	}
	public void setMotivo(CatalogoVO motivo) {
		this.motivo = motivo;
	}
}
