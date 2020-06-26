package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;

public class ReasignaPTVO {

	private List<ValidadorReasignaVO> lista;
	private CatalogoVO motivo;
	
	public List<ValidadorReasignaVO> getLista() {
		return lista;
	}
	public void setLista(List<ValidadorReasignaVO> lista) {
		this.lista = lista;
	}
	public CatalogoVO getMotivo() {
		return motivo;
	}
	public void setMotivo(CatalogoVO motivo) {
		this.motivo = motivo;
	}
}
