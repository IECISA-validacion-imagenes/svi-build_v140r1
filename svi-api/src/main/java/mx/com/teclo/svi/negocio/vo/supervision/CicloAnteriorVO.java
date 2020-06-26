package mx.com.teclo.svi.negocio.vo.supervision;

public class CicloAnteriorVO {
	private Long idCicloValidacion;
	private Long totalAsignaciones;
	private Long totalReasignaciones;
	private Long totalValidaciones;

	public Long getIdCicloValidacion() {
		return idCicloValidacion;
	}

	public void setIdCicloValidacion(Long idCicloValidacion) {
		this.idCicloValidacion = idCicloValidacion;
	}

	public Long getTotalAsignaciones() {
		return totalAsignaciones;
	}

	public void setTotalAsignaciones(Long totalAsignaciones) {
		this.totalAsignaciones = totalAsignaciones;
	}	

	public Long getTotalReasignaciones() {
		return totalReasignaciones;
	}

	public void setTotalReasignaciones(Long totalReasignaciones) {
		this.totalReasignaciones = totalReasignaciones;
	}

	public Long getTotalValidaciones() {
		return totalValidaciones;
	}

	public void setTotalValidaciones(Long totalValidaciones) {
		this.totalValidaciones = totalValidaciones;
	}

}
