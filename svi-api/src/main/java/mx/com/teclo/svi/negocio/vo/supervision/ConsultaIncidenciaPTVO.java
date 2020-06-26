package mx.com.teclo.svi.negocio.vo.supervision;

public class ConsultaIncidenciaPTVO {

	private Long idPtLote;
	private String nbPtLote;
	private String nbEntrega;
	private Long nuCsv;
	private Long nuValidados;
	private Long nuFaltantes;
	private Boolean stValidacion;
	private Boolean stRevalidacion;
	private Long nuRegistros;
	
	
	
	public Long getNuRegistros() {
		return nuRegistros;
	}
	public void setNuRegistros(Long nuRegistros) {
		this.nuRegistros = nuRegistros;
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
	public String getNbEntrega() {
		return nbEntrega;
	}
	public void setNbEntrega(String nbEntrega) {
		this.nbEntrega = nbEntrega;
	}
	public Long getNuCsv() {
		return nuCsv;
	}
	public void setNuCsv(Long nuCsv) {
		this.nuCsv = nuCsv;
	}
	public Long getNuValidados() {
		return nuValidados;
	}
	public void setNuValidados(Long nuValidados) {
		this.nuValidados = nuValidados;
	}
	public Long getNuFaltantes() {
		return nuFaltantes;
	}
	public void setNuFaltantes(Long nuFaltantes) {
		this.nuFaltantes = nuFaltantes;
	}
	public Boolean getStValidacion() {
		return stValidacion;
	}
	public void setStValidacion(Boolean stValidacion) {
		this.stValidacion = stValidacion;
	}
	public Boolean getStRevalidacion() {
		return stRevalidacion;
	}
	public void setStRevalidacion(Boolean stRevalidacion) {
		this.stRevalidacion = stRevalidacion;
	}
}
