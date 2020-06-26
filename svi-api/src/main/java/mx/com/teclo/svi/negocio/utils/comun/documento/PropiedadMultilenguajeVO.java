package mx.com.teclo.svi.negocio.utils.comun.documento;


public class PropiedadMultilenguajeVO {

	private String totalRegistros;
	private String tituloGenerico;
	private String tituloPreposicionPeriodo;
	private String tituloPeriodo;

	public PropiedadMultilenguajeVO() {
		super();
	}

	public PropiedadMultilenguajeVO(String totalRegistros, String tituloGenerico, String tituloPreposicionPeriodo,
			String tituloPeriodo) {
		super();
		this.totalRegistros = totalRegistros;
		this.tituloGenerico = tituloGenerico;
		this.tituloPreposicionPeriodo = tituloPreposicionPeriodo;
		this.tituloPeriodo = tituloPeriodo;
	}

	public String getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public String getTituloGenerico() {
		return tituloGenerico;
	}

	public void setTituloGenerico(String tituloGenerico) {
		this.tituloGenerico = tituloGenerico;
	}

	public String getTituloPreposicionPeriodo() {
		return tituloPreposicionPeriodo;
	}

	public void setTituloPreposicionPeriodo(String tituloPreposicionPeriodo) {
		this.tituloPreposicionPeriodo = tituloPreposicionPeriodo;
	}

	public String getTituloPeriodo() {
		return tituloPeriodo;
	}

	public void setTituloPeriodo(String tituloPeriodo) {
		this.tituloPeriodo = tituloPeriodo;
	}

}
