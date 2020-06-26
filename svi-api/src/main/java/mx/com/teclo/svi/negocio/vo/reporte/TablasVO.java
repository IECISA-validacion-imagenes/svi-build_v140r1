package mx.com.teclo.svi.negocio.vo.reporte;

public class TablasVO {

	private Long idTabla;
	private String cdTabla;
	private String nbAlias;
	private String nbReal;
	private String txTabla;

	public Long getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(Long idTabla) {
		this.idTabla = idTabla;
	}

	public String getCdTabla() {
		return cdTabla;
	}

	public void setCdTabla(String cdTabla) {
		this.cdTabla = cdTabla;
	}

	public String getNbAlias() {
		return nbAlias;
	}

	public void setNbAlias(String nbAlias) {
		this.nbAlias = nbAlias;
	}

	public String getNbReal() {
		return nbReal;
	}

	public void setNbReal(String nbReal) {
		this.nbReal = nbReal;
	}

	public String getTxTabla() {
		return txTabla;
	}

	public void setTxTabla(String txTabla) {
		this.txTabla = txTabla;
	}
}
