package mx.com.teclo.svi.negocio.vo.reporte;

public class FormatoDescargaVO {

	private Long idFormatoDescarga;
	private String cdFormato;
	private String nbFormato;
	private String txDescripcion;
	private String cdExtension;

	public Long getIdFormatoDescarga() {
		return idFormatoDescarga;
	}

	public void setIdFormatoDescarga(Long idFormatoDescarga) {
		this.idFormatoDescarga = idFormatoDescarga;
	}

	public String getCdFormato() {
		return cdFormato;
	}

	public void setCdFormato(String cdFormato) {
		this.cdFormato = cdFormato;
	}

	public String getNbFormato() {
		return nbFormato;
	}

	public void setNbFormato(String nbFormato) {
		this.nbFormato = nbFormato;
	}

	public String getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}

	public String getCdExtension() {
		return cdExtension;
	}

	public void setCdExtension(String cdExtension) {
		this.cdExtension = cdExtension;
	}

}
