package mx.com.teclo.svi.negocio.vo.evareportes;

public class EvaReportesVO {
	private Long carril=0L;
	private int totalRegistros=0;
	private int totalPlacaCorrecta=0;
	private int totalExcepcion=0;
	private int totalPerfil=0;
	private String totalEfectividad=null;
	
	
	public Long getCarril() {
		return carril;
	}
	public void setCarril(Long carril) {
		this.carril = carril;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public int getTotalPlacaCorrecta() {
		return totalPlacaCorrecta;
	}
	public void setTotalPlacaCorrecta(int totalPlacaCorrecta) {
		this.totalPlacaCorrecta = totalPlacaCorrecta;
	}
	public int getTotalExcepcion() {
		return totalExcepcion;
	}
	public void setTotalExcepcion(int totalExcepcion) {
		this.totalExcepcion = totalExcepcion;
	}
	public int getTotalPerfil() {
		return totalPerfil;
	}
	public void setTotalPerfil(int totalPerfil) {
		this.totalPerfil = totalPerfil;
	}
	public String getTotalEfectividad() {
		return totalEfectividad;
	}
	public void setTotalEfectividad(String totalEfectividad) {
		this.totalEfectividad = totalEfectividad;
	}
	
	

}
