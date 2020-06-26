package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name = "TAQ004D_AR_REPORTES")
public class ReportesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030485256231002698L;

	@Id
	@SequenceGenerator(name = "SQAQ004D", sequenceName="SQAQ004D_AR_REPORTES", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ004D")	
	@Column(name = "ID_REPORTE", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idReporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_REPORTE", nullable = false)
	private TipoReportesDTO tipoReporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_TITULO", nullable = false)
	private TipoTitulosDTO tipoTitulo;

	@Column(name = "ID_APLICACION", nullable = false)
	private Long aplicacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporte")
	@OrderBy("nuOrden ASC")
	@Where(clause = "ST_ACTIVO = 1")
	private List<ParametrosDTO> parametros = new ArrayList<ParametrosDTO>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporte")
	@Where(clause = "ST_ACTIVO = 1")
	private List<ReportesFormatosDTO> reporteFormato = new ArrayList<ReportesFormatosDTO>();

	@Column(name = "CD_REPORTE", nullable = false, length = 10)
	private String cdReporte;

	@Column(name = "NB_REPORTE", nullable = false, length = 100)
	private String nbReporte;

	@Column(name = "TX_REPORTE", nullable = false, length = 300)
	private String txReporte;

	@Column(name = "TX_URL", nullable = false, length = 200)
	private String url;

	@Column(name = "TX_URL_DINAMIC", nullable = false, length = 50)
	private String txUrlDinamic;

	@Column(name = "SCRIPT_SELECT", nullable = false)
	private String scriptSelect;

	@Column(name = "SCRIPT_FROM", nullable = false)
	private String scritFrom;

	@Column(name = "SCRIPT_WHERE", nullable = false)
	private String scriptWhere;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@Column(name = "TX_LAYOUT", nullable = false, length = 1000)
	private String txLayout;

	/**
	 * @return the idReporte
	 */
	public Long getIdReporte() {
		return idReporte;
	}

	public String getTxLayout() {
		return txLayout;
	}

	public void setTxLayout(String txLayout) {
		this.txLayout = txLayout;
	}

	/**
	 * @param idReporte the idReporte to set
	 */
	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public TipoReportesDTO getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(TipoReportesDTO tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the tipoTitulo
	 */
	public TipoTitulosDTO getTipoTitulo() {
		return tipoTitulo;
	}

	/**
	 * @param tipoTitulo the tipoTitulo to set
	 */
	public void setTipoTitulo(TipoTitulosDTO tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}

	/**
	 * @return the aplicacion
	 */
	public Long getAplicacion() {
		return aplicacion;
	}

	/**
	 * @param aplicacion the aplicacion to set
	 */
	public void setAplicacion(Long aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * @return the parametros
	 */
	public List<ParametrosDTO> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<ParametrosDTO> parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the reporteFormato
	 */
	public List<ReportesFormatosDTO> getReporteFormato() {
		return reporteFormato;
	}

	/**
	 * @param reporteFormato the reporteFormato to set
	 */
	public void setReporteFormato(List<ReportesFormatosDTO> reporteFormato) {
		this.reporteFormato = reporteFormato;
	}

	/**
	 * @return the cdReporte
	 */
	public String getCdReporte() {
		return cdReporte;
	}

	/**
	 * @param cdReporte the cdReporte to set
	 */
	public void setCdReporte(String cdReporte) {
		this.cdReporte = cdReporte;
	}

	/**
	 * @return the nbReporte
	 */
	public String getNbReporte() {
		return nbReporte;
	}

	/**
	 * @param nbReporte the nbReporte to set
	 */
	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}

	/**
	 * @return the txReporte
	 */
	public String getTxReporte() {
		return txReporte;
	}

	/**
	 * @param txReporte the txReporte to set
	 */
	public void setTxReporte(String txReporte) {
		this.txReporte = txReporte;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the txUrlDinamic
	 */
	public String getTxUrlDinamic() {
		return txUrlDinamic;
	}

	/**
	 * @param txUrlDinamic the txUrlDinamic to set
	 */
	public void setTxUrlDinamic(String txUrlDinamic) {
		this.txUrlDinamic = txUrlDinamic;
	}

	/**
	 * @return the scriptSelect
	 */
	public String getScriptSelect() {
		return scriptSelect;
	}

	/**
	 * @param scriptSelect the scriptSelect to set
	 */
	public void setScriptSelect(String scriptSelect) {
		this.scriptSelect = scriptSelect;
	}

	/**
	 * @return the scritFrom
	 */
	public String getScritFrom() {
		return scritFrom;
	}

	/**
	 * @param scritFrom the scritFrom to set
	 */
	public void setScritFrom(String scritFrom) {
		this.scritFrom = scritFrom;
	}

	/**
	 * @return the scriptWhere
	 */
	public String getScriptWhere() {
		return scriptWhere;
	}

	/**
	 * @param scriptWhere the scriptWhere to set
	 */
	public void setScriptWhere(String scriptWhere) {
		this.scriptWhere = scriptWhere;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}

	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}

	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

//	/**
//	 * @return the txLayout
//	 */
//	public String getTxLayout() {
//		return txLayout;
//	}
//
//	/**
//	 * @param txLayout the txLayout to set
//	 */
//	public void setTxLayout(String txLayout) {
//		this.txLayout = txLayout;
//	}

	
}
