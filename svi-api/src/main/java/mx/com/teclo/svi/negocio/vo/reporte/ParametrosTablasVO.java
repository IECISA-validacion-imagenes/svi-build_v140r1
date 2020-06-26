package mx.com.teclo.svi.negocio.vo.reporte;

public class ParametrosTablasVO {

	private Long idParametroTabla;
	private ParametrosVO parametro;
	private TablasVO tabla;

	public Long getIdParametroTabla() {
		return idParametroTabla;
	}

	public void setIdParametroTabla(Long idParametroTabla) {
		this.idParametroTabla = idParametroTabla;
	}

	public ParametrosVO getParametro() {
		return parametro;
	}

	public void setParametro(ParametrosVO parametro) {
		this.parametro = parametro;
	}

	public TablasVO getTabla() {
		return tabla;
	}

	public void setTabla(TablasVO tabla) {
		this.tabla = tabla;
	}

}
