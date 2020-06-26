package mx.com.teclo.svi.negocio.vo.supervision;

public class ConsultaDetalleIncidenciaPTVO {

	private Long idArchivoCsv;
	private String nbArchivoCsv;
	private Long nuRegistros;
	
	
	
	public Long getNuRegistros() {
		return nuRegistros;
	}
	public void setNuRegistros(Long nuRegistros) {
		this.nuRegistros = nuRegistros;
	}
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public String getNbArchivoCsv() {
		return nbArchivoCsv;
	}
	public void setNbArchivoCsv(String nbArchivoCsv) {
		this.nbArchivoCsv = nbArchivoCsv;
	}
}
