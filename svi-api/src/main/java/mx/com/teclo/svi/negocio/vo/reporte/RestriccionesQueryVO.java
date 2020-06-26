package mx.com.teclo.svi.negocio.vo.reporte;

public class RestriccionesQueryVO {

	private Long idRestriccion;
	private String cdRestriccion;
	private String txRestriccion;
	private String txValor;

	public Long getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(Long idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public String getCdRestriccion() {
		return cdRestriccion;
	}

	public void setCdRestriccion(String cdRestriccion) {
		this.cdRestriccion = cdRestriccion;
	}

	public String getTxRestriccion() {
		return txRestriccion;
	}

	public void setTxRestriccion(String txRestriccion) {
		this.txRestriccion = txRestriccion;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

}
