package mx.com.teclo.svi.negocio.vo.reporte;

public class TipoParamCompVO {

	private Long idTipoParamComp;
	private Long tipoParametro;
	private Long componente;

	public TipoParamCompVO(Long idTipoParamComp, Long tipoParametro, Long componente) {
		this.idTipoParamComp = idTipoParamComp;
		this.tipoParametro = tipoParametro;
		this.componente = componente;
	}

	public TipoParamCompVO() {
	}

	public Long getIdTipoParamComp() {
		return idTipoParamComp;
	}

	public void setIdTipoParamComp(Long idTipoParamComp) {
		this.idTipoParamComp = idTipoParamComp;
	}

	public Long getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(Long tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public Long getComponente() {
		return componente;
	}

	public void setComponente(Long componente) {
		this.componente = componente;
	}

}
