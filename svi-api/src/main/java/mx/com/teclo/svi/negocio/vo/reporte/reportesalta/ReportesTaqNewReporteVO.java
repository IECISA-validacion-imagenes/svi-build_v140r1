package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

import java.util.Date;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesFormatosVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoTituloVO;

public class ReportesTaqNewReporteVO {

	private Long idReporte;
	private TipoReporteVO tipoReporte;
	private TipoTituloVO tipoTitulo;
	private String cdReporte;
	private String nbReporte;
	private String txReporte;
	private String url;
	private String scriptSelect;
	private String scritFrom;
	private String scriptWhere;
	private Integer stActivo;
	private String queryFull;
	private List<ParametrosNewReporteVO> parametros;
	private List<FormatoDescargaVO> reporteFormato;
	private String txUrlDinamic;
	private String txColumnaPaginacionHojas;
	private AgrupacionHojasVO tipoAgrupacion;
	private List<DependenciasSelectVO> dependenciasSelect;
	private List<ReportesFormatosVO> listaReportesFormatos;
	private Date fhCreacion;
	private String txLayout;

	public ReportesTaqNewReporteVO() {
	}

	public ReportesTaqNewReporteVO(Long idReporte, String cdReporte, TipoReporteVO tipoReporte, TipoTituloVO tipoTitulo,
			String nbReporte, String txReporte, String scriptSelect, String scritFrom, String scriptWhere,
			String txUrlDinamic, List<ReportesFormatosVO> listaReportesFormatos) {
		this.idReporte = idReporte;
		this.cdReporte = cdReporte;
		this.tipoReporte = tipoReporte;
		this.tipoTitulo = tipoTitulo;
		this.nbReporte = nbReporte;
		this.txReporte = txReporte;
		this.scriptSelect = scriptSelect;
		this.scritFrom = scritFrom;
		this.scriptWhere = scriptWhere;
		this.txUrlDinamic = txUrlDinamic;
		this.listaReportesFormatos = listaReportesFormatos;

	}

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

	public List<ParametrosNewReporteVO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametrosNewReporteVO> parametros) {
		this.parametros = parametros;
	}

	public List<FormatoDescargaVO> getReporteFormato() {
		return reporteFormato;
	}

	public void setReporteFormato(List<FormatoDescargaVO> reporteFormato) {
		this.reporteFormato = reporteFormato;
	}

	public String getTxUrlDinamic() {
		return txUrlDinamic;
	}

	public void setTxUrlDinamic(String txUrlDinamic) {
		this.txUrlDinamic = txUrlDinamic;
	}

	public String getTxColumnaPaginacionHojas() {
		return txColumnaPaginacionHojas;
	}

	public void setTxColumnaPaginacionHojas(String txColumnaPaginacionHojas) {
		this.txColumnaPaginacionHojas = txColumnaPaginacionHojas;
	}

	public AgrupacionHojasVO getTipoAgrupacion() {
		return tipoAgrupacion;
	}

	public void setTipoAgrupacion(AgrupacionHojasVO tipoAgrupacion) {
		this.tipoAgrupacion = tipoAgrupacion;
	}

	public List<DependenciasSelectVO> getDependenciasSelect() {
		return dependenciasSelect;
	}

	public void setDependenciasSelect(List<DependenciasSelectVO> dependenciasSelect) {
		this.dependenciasSelect = dependenciasSelect;
	}

	public List<ReportesFormatosVO> getListaReportesFormatos() {
		return listaReportesFormatos;
	}

	public void setListaReportesFormatos(List<ReportesFormatosVO> listaReportesFormatos) {
		this.listaReportesFormatos = listaReportesFormatos;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public String getTxLayout() {
		return txLayout;
	}

	public void setTxLayout(String txLayout) {
		this.txLayout = txLayout;
	}

}
