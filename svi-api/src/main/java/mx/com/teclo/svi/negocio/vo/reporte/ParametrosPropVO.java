package mx.com.teclo.svi.negocio.vo.reporte;

public class ParametrosPropVO {

	private Long idParamtrosProp;
	private ParametrosVO parametro;
	private PropiedadesVO propiedad;
	private String txValor;

	public Long getIdParamtrosProp() {
		return idParamtrosProp;
	}

	public void setIdParamtrosProp(Long idParamtrosProp) {
		this.idParamtrosProp = idParamtrosProp;
	}

	public ParametrosVO getParametro() {
		return parametro;
	}

	public void setParametro(ParametrosVO parametro) {
		this.parametro = parametro;
	}

	public PropiedadesVO getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadesVO propiedad) {
		this.propiedad = propiedad;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

}
