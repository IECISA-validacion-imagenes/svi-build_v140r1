package mx.com.teclo.svi.negocio.vo.reporte;

import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParamPropScriptVO;

public class PropiedadesVO {

	private Long idPropiedad;
	private String cdPropiedad;
	private String nbPropiedad;
	private String txPropiedad;
	private String nbHtmlPropiedad;
	private Long stValorRequerido;
	private String value;// valor que se le aplicará a la propiedad
	private ParamPropScriptVO paramPropScriptVO;// objeto para solventer la consulta de las búsqueda pervias

	public Long getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(Long idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getCdPropiedad() {
		return cdPropiedad;
	}

	public void setCdPropiedad(String cdPropiedad) {
		this.cdPropiedad = cdPropiedad;
	}

	public String getNbPropiedad() {
		return nbPropiedad;
	}

	public void setNbPropiedad(String nbPropiedad) {
		this.nbPropiedad = nbPropiedad;
	}

	public String getTxPropiedad() {
		return txPropiedad;
	}

	public void setTxPropiedad(String txPropiedad) {
		this.txPropiedad = txPropiedad;
	}

	public String getNbHtmlPropiedad() {
		return nbHtmlPropiedad;
	}

	public void setNbHtmlPropiedad(String nbHtmlPropiedad) {
		this.nbHtmlPropiedad = nbHtmlPropiedad;
	}

	public Long getStValorRequerido() {
		return stValorRequerido;
	}

	public void setStValorRequerido(Long stValorRequerido) {
		this.stValorRequerido = stValorRequerido;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ParamPropScriptVO getParamPropScriptVO() {
		return paramPropScriptVO;
	}

	public void setParamPropScriptVO(ParamPropScriptVO paramPropScriptVO) {
		this.paramPropScriptVO = paramPropScriptVO;
	}

}
