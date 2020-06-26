package mx.com.teclo.svi.negocio.vo.supervision;

public class ValidadorReasignaVO {
	private Long idArchivoCsv;
	private String nbArchivoCsv;
	private ValidadorVO validador;
	
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
	public ValidadorVO getValidador() {
		return validador;
	}
	public void setValidador(ValidadorVO validador) {
		this.validador = validador;
	}
}
