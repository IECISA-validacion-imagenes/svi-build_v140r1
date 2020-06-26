package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

public class ParamPropScriptVO {

	private String cdParamProvisional;
	private String queryFull;
	private String cdParametroOriginal;

	public ParamPropScriptVO(){}
	
	public ParamPropScriptVO(String cdParamProvisional, String queryFull,String cdParametroOriginal){
		this.cdParamProvisional = cdParamProvisional;
		this.queryFull = queryFull;
		this.cdParametroOriginal = cdParametroOriginal;
	}
	
	public String getCdParamProvisional() {
		return cdParamProvisional;
	}

	public void setCdParamProvisional(String cdParamProvisional) {
		this.cdParamProvisional = cdParamProvisional;
	}

	public String getQueryFull() {
		return queryFull;
	}

	public void setQueryFull(String queryFull) {
		this.queryFull = queryFull;
	}

	public String getCdParametroOriginal() {
		return cdParametroOriginal;
	}

	public void setCdParametroOriginal(String cdParametroOriginal) {
		this.cdParametroOriginal = cdParametroOriginal;
	}

}
