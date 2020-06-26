package mx.com.teclo.svi.negocio.vo.reporte.admireporte;

import java.util.ArrayList;

import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.ComponentesVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParametroVO;


public class ConfigParamVO {

	private String cdParametro;
	private String nbParametro;
	private String nbEtiquetaParam;
	private TipoParametroVO tipoParametroVO;
	private ArrayList<PropiedadesVO> propiedades;
	private ComponentesVO componenteVO;

	// constructor de una clase
	public ConfigParamVO(String cdParametro) {
		this.cdParametro = cdParametro;
	}

	public ConfigParamVO() {
		super();
	}

	public String getNbParametro() {
		return nbParametro;
	}

	public void setNbParametro(String nbParametro) {
		this.nbParametro = nbParametro;
	}

	public String getNbEtiquetaParam() {
		return nbEtiquetaParam;
	}

	public void setNbEtiquetaParam(String nbEtiquetaParam) {
		this.nbEtiquetaParam = nbEtiquetaParam;
	}

	public TipoParametroVO getTipoParametroVO() {
		return tipoParametroVO;
	}

	public void setTipoParametroVO(TipoParametroVO tipoParametroVO) {
		this.tipoParametroVO = tipoParametroVO;
	}

	public ArrayList<PropiedadesVO> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(ArrayList<PropiedadesVO> propiedades) {
		this.propiedades = propiedades;
	}

	public ComponentesVO getComponenteVO() {
		return componenteVO;
	}

	public void setComponenteVO(ComponentesVO componenteVO) {
		this.componenteVO = componenteVO;
	}

	public String getCdParametro() {
		return cdParametro;
	}

	public void setCdParametro(String cdParametro) {
		this.cdParametro = cdParametro;
	}

}
