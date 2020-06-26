package mx.com.teclo.svi.negocio.vo.reporte;

public class ReportesFormatosVO {

	private Long idReporteFormato;
	private FormatoDescargaVO formatoDescarga;
	private ReportesTaqVO reporte;
	private AgrupacionHojasVO agrupacionHojas;
	private String nbColumnaAgrupacion;

	public Long getIdReporteFormato() {
		return idReporteFormato;
	}

	public void setIdReporteFormato(Long idReporteFormato) {
		this.idReporteFormato = idReporteFormato;
	}

	public FormatoDescargaVO getFormatoDescarga() {
		return formatoDescarga;
	}

	public void setFormatoDescarga(FormatoDescargaVO formatoDescarga) {
		this.formatoDescarga = formatoDescarga;
	}

	public ReportesTaqVO getReporte() {
		return reporte;
	}

	public void setReporte(ReportesTaqVO reporte) {
		this.reporte = reporte;
	}

	public AgrupacionHojasVO getAgrupacionHojas() {
		return agrupacionHojas;
	}

	public void setAgrupacionHojas(AgrupacionHojasVO agrupacionHojas) {
		this.agrupacionHojas = agrupacionHojas;
	}

	public String getNbColumnaAgrupacion() {
		return nbColumnaAgrupacion;
	}

	public void setNbColumnaAgrupacion(String nbColumnaAgrupacion) {
		this.nbColumnaAgrupacion = nbColumnaAgrupacion;
	}

}
