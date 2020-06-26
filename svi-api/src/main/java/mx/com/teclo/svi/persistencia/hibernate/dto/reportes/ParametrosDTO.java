package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ012D_AR_PARAMETROS")
public class ParametrosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5859077737908705919L;

	@Id
	@SequenceGenerator(name = "SQAQ012D", sequenceName="SQAQ012D_AR_PARAMETROS", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ012D")
	@Column(name = "ID_PARAMETRO", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idParamtro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REPORTE", nullable = false)
	private ReportesDTO reporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_PARAMETRO", nullable = false)
	private TipoParametroDTO tipoParametro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COMPONENTE", nullable = false)
	private ComponentesDTO componente;

	@Column(name = "CD_PARAMETRO", nullable = false, length = 50)
	private String cdParametro;

	@Column(name = "NB_PARAMETRO", nullable = false, length = 50)
	private String nbParametro;

	@Column(name = "TX_VALOR", nullable = false, length = 50)
	private String txValor;

	@Column(name = "TX_PARAMETRO", nullable = false, length = 70)
	private String txParametro;

	@Column(name = "ST_IS_CATALOGO", nullable = false, length = 1, scale = 0)
	private Integer stIsCatalogo;

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

	@Column(name = "ST_MULTIPLE_VALORES", nullable = false, precision = 1, scale = 0)
	private Integer stMultipleValores;

	@Column(name = "NU_ORDEN", nullable = false, precision = 11, scale = 0)
	private Long nuOrden;

	@Column(name = "TX_AYUDA", nullable = true, length = 1000)
	private String txAyuda;

	public Long getIdParamtro() {
		return idParamtro;
	}

	public void setIdParamtro(Long idParamtro) {
		this.idParamtro = idParamtro;
	}

	public ReportesDTO getReporte() {
		return reporte;
	}

	public void setReporte(ReportesDTO reporte) {
		this.reporte = reporte;
	}

	public TipoParametroDTO getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroDTO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public ComponentesDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesDTO componente) {
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

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
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

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
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

	public String getTxAyuda() {
		return txAyuda;
	}

	public void setTxAyuda(String txAyuda) {
		this.txAyuda = txAyuda;
	}

}
