package mx.com.teclo.svi.negocio.vo.reporte.admireporte;

import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoTituloVO;

public class ReporteVO {
	
	private Long idReporte;
	private TipoReporteVO idTipoReporte;
	private TipoTituloVO idTipoTitulo;
	private String cdReporte;
	private String nbReporte;
	private String txReporte;
	private String txUrl;
	private String scriptSelect;
	private String scriptFrom;
	private String scriptWhere;
	
	public Long getIdReporte() {
		return idReporte;
	}
	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}


	public TipoReporteVO getIdTipoReporte() {
		return idTipoReporte;
	}
	public void setIdTipoReporte(TipoReporteVO idTipoReporte) {
		this.idTipoReporte = idTipoReporte;
	}
	public TipoTituloVO getIdTipoTitulo() {
		return idTipoTitulo;
	}
	public void setIdTipoTitulo(TipoTituloVO idTipoTitulo) {
		this.idTipoTitulo = idTipoTitulo;
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
	public String getTxUrl() {
		return txUrl;
	}
	public void setTxUrl(String txUrl) {
		this.txUrl = txUrl;
	}
	public String getScriptSelect() {
		return scriptSelect;
	}
	public void setScriptSelect(String scriptSelect) {
		this.scriptSelect = scriptSelect;
	}
	public String getScriptFrom() {
		return scriptFrom;
	}
	public void setScriptFrom(String scriptFrom) {
		this.scriptFrom = scriptFrom;
	}
	public String getScriptWhere() {
		return scriptWhere;
	}
	public void setScriptWhere(String scriptWhere) {
		this.scriptWhere = scriptWhere;
	}
	
	
	public String toString(){
		return "ReporteVO [tipoReporte=" + idTipoReporte +", idTipoTitulo="+ idTipoTitulo +", cdReporte=" + cdReporte + ", nbReporte=" + nbReporte +", txReporte="+ txReporte + ", txUrl="+ txUrl 
				+", scriptSelect="+ scriptSelect +", scriptFrom="+ scriptFrom +", scriptWhere=" + scriptWhere +"]";
	}
	


}
