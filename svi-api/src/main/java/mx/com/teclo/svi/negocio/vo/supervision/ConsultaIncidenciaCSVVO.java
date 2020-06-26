package mx.com.teclo.svi.negocio.vo.supervision;

public class ConsultaIncidenciaCSVVO {

	private Long idArchivoCsv;
	private String periodo;
	private String lote;
	private String archivoCsv;
	private Boolean stValidacion;
	private Boolean stRevalidacion;
	private Boolean stPtValidacion;
	private Long totalRegistro;
	private Long totalValidado;
	private Long totalIncidencia;
	private Long idArchivoCsv_v2;
	
	
	
	
	
	public Long getIdArchivoCsv_v2() {
		return idArchivoCsv_v2;
	}
	public void setIdArchivoCsv_v2(Long idArchivoCsv_v2) {
		this.idArchivoCsv_v2 = idArchivoCsv_v2;
	}
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getArchivoCsv() {
		return archivoCsv;
	}
	public void setArchivoCsv(String archivoCsv) {
		this.archivoCsv = archivoCsv;
	}
	public Boolean getStValidacion() {
		return stValidacion;
	}
	public void setStValidacion(Boolean stValidacion) {
		this.stValidacion = stValidacion;
	}
	public Boolean getStPtValidacion() {
		return stPtValidacion;
	}
	public void setStPtValidacion(Boolean stPtValidacion) {
		this.stPtValidacion = stPtValidacion;
	}
	public Boolean getStRevalidacion() {
		return stRevalidacion;
	}
	public void setStRevalidacion(Boolean stRevalidacion) {
		this.stRevalidacion = stRevalidacion;
	}
	public Long getTotalRegistro() {
		return totalRegistro;
	}
	public void setTotalRegistro(Long totalRegistro) {
		this.totalRegistro = totalRegistro;
	}
	public Long getTotalValidado() {
		return totalValidado;
	}
	public void setTotalValidado(Long totalValidado) {
		this.totalValidado = totalValidado;
	}
	public Long getTotalIncidencia() {
		return totalIncidencia;
	}
	public void setTotalIncidencia(Long totalIncidencia) {
		this.totalIncidencia = totalIncidencia;
	}
}
