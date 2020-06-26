package mx.com.teclo.svi.negocio.vo.supervision;

public class IncidenciaVO {

	private String idIncidencia;
	private String txIncidencia;
	
	public IncidenciaVO(String idIncidencia, String txIncidencia){
		this.idIncidencia = idIncidencia;
		this.txIncidencia = txIncidencia;
	}
	
	public String getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(String idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getTxIncidencia() {
		return txIncidencia;
	}
	public void setTxIncidencia(String txIncidencia) {
		this.txIncidencia = txIncidencia;
	}
}
