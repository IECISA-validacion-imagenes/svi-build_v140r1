package mx.com.teclo.svi.negocio.vo.catalogo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CsvExpedienteVO {
	private Long idEntrega;
	private String nbEntrega;
	private Long idPtLote;
	private String nbPtLote;
	private Long idArchivoCsv;
	private String nbArchivoCsv;

	public Long getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}

	public String getNbEntrega() {
		return nbEntrega;
	}

	public void setNbEntrega(String nbEntrega) {
		this.nbEntrega = nbEntrega;
	}

	public Long getIdPtLote() {
		return idPtLote;
	}

	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}

	public String getNbPtLote() {
		return nbPtLote;
	}

	public void setNbPtLote(String nbPtLote) {
		this.nbPtLote = nbPtLote;
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
