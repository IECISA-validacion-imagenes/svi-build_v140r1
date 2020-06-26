package mx.com.teclo.svi.negocio.vo.catalogo;

import java.util.List;

public class CatalogoExpedienteVO {
	private String tipoCatalogo;
	private List<CsvExpedienteVO> catalogo;
	public String getTipoCatalogo() {
		return tipoCatalogo;
	}
	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}
	public List<CsvExpedienteVO> getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(List<CsvExpedienteVO> catalogo) {
		this.catalogo = catalogo;
	}
	
}
