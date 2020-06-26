package mx.com.teclo.svi.negocio.vo.reporte;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ParametrosVO {

	private Long idParamtro;
	private Long idReporte;
	@JsonIgnore
	private ReportesTaqVO reporte;
	@JsonIgnore
	private TipoParametroVO tipoParametro;
	private ComponentesVO componente;
	private String cdParametro;
	private String nbParametro;
	private String txValor;
	private String txParametro;
	private Integer stIsCatalogo;
	private Integer stMultipleValores;
	private Long nuOrden;
	private String txAyuda;
	private List<ParametrosPropVO> parametrosPropAux;
	// private List<ObjectCollectionVO> catValues;
	private List<DependenciasVO> dependencias;
	private List<DependenciasVO> padres;
	private List<LinkedHashMap<Object, Object>> catValues;
	/*
	 * @JsonIgnore private List<ParametrosColumnVO> paramColumnDependency;
	 */

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	
	public ParametrosVO() {
	}

	public ParametrosVO(String cdParametro) {
		this.cdParametro = cdParametro;
	}

	public Long getIdParamtro() {
		return idParamtro;
	}

	public void setIdParamtro(Long idParamtro) {
		this.idParamtro = idParamtro;
	}

	public ReportesTaqVO getReporte() {
		return reporte;
	}

	public void setReporte(ReportesTaqVO reporte) {
		this.reporte = reporte;
	}

	public TipoParametroVO getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroVO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public ComponentesVO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesVO componente) {
		this.componente = componente;
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

	/*
	 * public String getTxValor() { return txValor; }
	 * 
	 * public void setTxValor(String txValor) { this.txValor = txValor; }
	 */

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

	public List<ParametrosPropVO> getParametrosPropAux() {
		return parametrosPropAux;
	}

	public void setParametrosPropAux(List<ParametrosPropVO> parametrosPropAux) {
		this.parametrosPropAux = parametrosPropAux;
	}

	public List<DependenciasVO> getDependencias() {
		return dependencias;
	}

	public void setDependencias(List<DependenciasVO> dependencias) {
		this.dependencias = dependencias;
	}

	public List<LinkedHashMap<Object, Object>> getCatValues() {
		return catValues;
	}

	public void setCatValues(List<LinkedHashMap<Object, Object>> catValues) {
		this.catValues = catValues;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public List<DependenciasVO> getPadres() {
		return padres;
	}

	public void setPadres(List<DependenciasVO> padres) {
		this.padres = padres;
	}

	public String getTxAyuda() {
		return txAyuda;
	}

	public void setTxAyuda(String txAyuda) {
		this.txAyuda = txAyuda;
	}

}
