package mx.com.teclo.svi.negocio.vo.reporte;
public class PerfilesReportesVO {

	private Long idPerfilReporte;
	private Long perfil;
	private ReportesTaqVO reporte;
	private Integer stActivo;
	private PerfilReporteIdVO id;
	public Long getIdPerfilReporte() {
		return idPerfilReporte;
	}
	public void setIdPerfilReporte(Long idPerfilReporte) {
		this.idPerfilReporte = idPerfilReporte;
	}
	public Long getPerfil() {
		return perfil;
	}
	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}
	public ReportesTaqVO getReporte() {
		return reporte;
	}
	public void setReporte(ReportesTaqVO reporte) {
		this.reporte = reporte;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	public PerfilReporteIdVO getId() {
		return id;
	}
	public void setId(PerfilReporteIdVO id) {
		this.id = id;
	}

	
}
