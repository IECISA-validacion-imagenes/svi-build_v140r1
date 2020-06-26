package mx.com.teclo.svi.negocio.vo.reporte;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportesTaqVO {

	private Long idReporte;
	private TipoReporteVO tipoReporte;
	@JsonIgnore
	private TipoTituloVO tipoTitulo;
	@JsonIgnore
	private long aplicacion;
	private String cdReporte;
	private String nbReporte;
	private String txReporte;
	private String url;
	private String scriptSelect;
	private String scritFrom;
	private String scriptWhere;
	private Integer stActivo;
	private String queryFull;
	@JsonIgnore
	private List<ParametrosVO> parametros;
	@JsonIgnore
	private List<ReportesFormatosVO> reporteFormato;
	private List<ParametrosVO> parametrosAux;
	@JsonIgnore
	private List<ReportesFormatosVO> reporteFormatoAux;
	private String txUrlDinamic;
	private RestriccionesQueryVO restriccion;
	private String txLayout;
	public Long getIdReporte() {
		return idReporte;
	}
	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}
	public TipoReporteVO getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(TipoReporteVO tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	public TipoTituloVO getTipoTitulo() {
		return tipoTitulo;
	}
	public void setTipoTitulo(TipoTituloVO tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}
	public long getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(long aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getCdReporte() {
		return cdReporte;
	}
	public void setCdReporte(String cdReporte) {
		this.cdReporte = cdReporte;
	}
	public String getNbReporte() {
		return nbReporte;
	}
	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}
	public String getTxReporte() {
		return txReporte;
	}
	public void setTxReporte(String txReporte) {
		this.txReporte = txReporte;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScriptSelect() {
		return scriptSelect;
	}
	public void setScriptSelect(String scriptSelect) {
		this.scriptSelect = scriptSelect;
	}
	public String getScritFrom() {
		return scritFrom;
	}
	public void setScritFrom(String scritFrom) {
		this.scritFrom = scritFrom;
	}
	public String getScriptWhere() {
		return scriptWhere;
	}
	public void setScriptWhere(String scriptWhere) {
		this.scriptWhere = scriptWhere;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	public String getQueryFull() {
		return queryFull;
	}
	public void setQueryFull(String queryFull) {
		this.queryFull = queryFull;
	}
	public List<ParametrosVO> getParametros() {
		return parametros;
	}
	public void setParametros(List<ParametrosVO> parametros) {
		this.parametros = parametros;
	}
	public List<ReportesFormatosVO> getReporteFormato() {
		return reporteFormato;
	}
	public void setReporteFormato(List<ReportesFormatosVO> reporteFormato) {
		this.reporteFormato = reporteFormato;
	}
	public List<ParametrosVO> getParametrosAux() {
		return parametrosAux;
	}
	public void setParametrosAux(List<ParametrosVO> parametrosAux) {
		this.parametrosAux = parametrosAux;
	}
	public List<ReportesFormatosVO> getReporteFormatoAux() {
		return reporteFormatoAux;
	}
	public void setReporteFormatoAux(List<ReportesFormatosVO> reporteFormatoAux) {
		this.reporteFormatoAux = reporteFormatoAux;
	}
	public String getTxUrlDinamic() {
		return txUrlDinamic;
	}
	public void setTxUrlDinamic(String txUrlDinamic) {
		this.txUrlDinamic = txUrlDinamic;
	}
	public RestriccionesQueryVO getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(RestriccionesQueryVO restriccion) {
		this.restriccion = restriccion;
	}
	public String getTxLayout() {
		return txLayout;
	}
	public void setTxLayout(String txLayout) {
		this.txLayout = txLayout;
	}

	
}
