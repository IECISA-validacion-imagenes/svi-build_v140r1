package mx.com.teclo.svi.negocio.vo.reporte;

public class ComponentesVO {

	private Long idComponente;
	private String cdComponente;
	private String nbComponente;
	private String txComponente;
	private Integer stIsCatalogo;
	//private List<PropiedadesCompVO> propiedadesCompVO;

	public Long getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}

	public String getCdComponente() {
		return cdComponente;
	}

	public void setCdComponente(String cdComponente) {
		this.cdComponente = cdComponente;
	}

	public String getNbComponente() {
		return nbComponente;
	}

	public void setNbComponente(String nbComponente) {
		this.nbComponente = nbComponente;
	}

	public String getTxComponente() {
		return txComponente;
	}

	public void setTxComponente(String txComponente) {
		this.txComponente = txComponente;
	}

	public Integer getStIsCatalogo() {
		return stIsCatalogo;
	}

	public void setStIsCatalogo(Integer stIsCatalogo) {
		this.stIsCatalogo = stIsCatalogo;
	}
}
