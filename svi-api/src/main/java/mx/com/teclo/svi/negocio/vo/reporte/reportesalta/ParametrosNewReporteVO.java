package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.ComponentesVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParametroVO;

public class ParametrosNewReporteVO {

	private Long idParamtro;
	private ComponentesVO componente;
	private TipoParametroVO tipoParametro;
	private String cdParametro;
	private String nbParametro;
	private String txParametro;
	private Integer stIsCatalogo;
	private Integer stMultipleValores;
	private Long nuOrden;
	private String txAyuda;
	private List<PropiedadesVO> propieades;
	private ParametroTipoCatConfigVO configParamTipoCatVO;
	private String txValor;
	

	public Long getIdParamtro() {
		return idParamtro;
	}

	public void setIdParamtro(Long idParamtro) {
		this.idParamtro = idParamtro;
	}

	public ComponentesVO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesVO componente) {
		this.componente = componente;
	}

	public TipoParametroVO getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroVO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getCdParametro() {
		return cdParametro;
	}

	public void setCdParametro(String cdParametro) {
		this.cdParametro = cdParametro;
	}

	public String getNbParametro() {
		return nbParametro;
	}

	public void setNbParametro(String nbParametro) {
		this.nbParametro = nbParametro;
	}

	public String getTxParametro() {
		return txParametro;
	}

	public void setTxParametro(String txParametro) {
		this.txParametro = txParametro;
	}

	public Integer getStIsCatalogo() {
		return stIsCatalogo;
	}

	public void setStIsCatalogo(Integer stIsCatalogo) {
		this.stIsCatalogo = stIsCatalogo;
	}

	public Integer getStMultipleValores() {
		return stMultipleValores;
	}

	public void setStMultipleValores(Integer stMultipleValores) {
		this.stMultipleValores = stMultipleValores;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public List<PropiedadesVO> getPropieades() {
		return propieades;
	}

	public void setPropieades(List<PropiedadesVO> propieades) {
		this.propieades = propieades;
	}

	public ParametroTipoCatConfigVO getConfigParamTipoCatVO() {
		return configParamTipoCatVO;
	}

	public void setConfigParamTipoCatVO(ParametroTipoCatConfigVO configParamTipoCatVO) {
		this.configParamTipoCatVO = configParamTipoCatVO;
	}

	public String getTxAyuda() {
		return txAyuda;
	}

	public void setTxAyuda(String txAyuda) {
		this.txAyuda = txAyuda;
	}

	/**
	 * @return the txValor
	 */
	public String getTxValor() {
		return txValor;
	}

	/**
	 * @param txValor the txValor to set
	 */
	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

}
