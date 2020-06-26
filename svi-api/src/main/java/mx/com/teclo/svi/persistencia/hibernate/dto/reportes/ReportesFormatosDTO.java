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
@Table(name = "TAQ018D_AR_REPORTES_FORMATOS")
public class ReportesFormatosDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7691986629025169830L;

	@Id
	@SequenceGenerator(name = "SQAQ018D", sequenceName="SQAQ018D_AR_REPOR_FORMA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ018D")
	@Column(name = "ID_REPORTE_FORMATO", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idReporteFormato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FORMATO_DESCARGA", nullable = false)
	private FormatoDescargaDTO formatoDescarga;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REPORTE", nullable = false)
	private ReportesDTO reporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_AGRUPACION", nullable = false)
	private AgrupacionHojasDTO agrupacionHojas;

	@Column(name = "NB_COLUMNA_AGRUPACION", nullable = true, length = 50)
	private String nbColumnaAgrupacion;

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

	public Long getIdReporteFormato() {
		return idReporteFormato;
	}

	public void setIdReporteFormato(Long idReporteFormato) {
		this.idReporteFormato = idReporteFormato;
	}

	public FormatoDescargaDTO getFormatoDescarga() {
		return formatoDescarga;
	}

	public void setFormatoDescarga(FormatoDescargaDTO formatoDescarga) {
		this.formatoDescarga = formatoDescarga;
	}

	public ReportesDTO getReporte() {
		return reporte;
	}

	public void setReporte(ReportesDTO reporte) {
		this.reporte = reporte;
	}

	public AgrupacionHojasDTO getAgrupacionHojas() {
		return agrupacionHojas;
	}

	public void setAgrupacionHojas(AgrupacionHojasDTO agrupacionHojas) {
		this.agrupacionHojas = agrupacionHojas;
	}

	public String getNbColumnaAgrupacion() {
		return nbColumnaAgrupacion;
	}

	public void setNbColumnaAgrupacion(String nbColumnaAgrupacion) {
		this.nbColumnaAgrupacion = nbColumnaAgrupacion;
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

}
