package mx.com.teclo.svi.negocio.vo.catalogo;

public class CatCsvVO {

	private Long idArchivoCsv;
	private Long idPt;
	private String nbPt;
	private String nbNombre;
	private Long nuRegistrosCsv;
	
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public Long getIdPt() {
		return idPt;
	}
	public void setIdPt(Long idPt) {
		this.idPt = idPt;
	}
	public String getNbPt() {
		return nbPt;
	}
	public void setNbPt(String nbPt) {
		this.nbPt = nbPt;
	}
	public String getNbNombre() {
		return nbNombre;
	}
	public void setNbNombre(String nbNombre) {
		this.nbNombre = nbNombre;
	}
	public Long getNuRegistrosCsv() {
		return nuRegistrosCsv;
	}
	public void setNuRegistrosCsv(Long nuRegistrosCsv) {
		this.nuRegistrosCsv = nuRegistrosCsv;
	}
	
}
