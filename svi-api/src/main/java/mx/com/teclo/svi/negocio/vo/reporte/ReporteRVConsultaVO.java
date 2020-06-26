package mx.com.teclo.svi.negocio.vo.reporte;

public class ReporteRVConsultaVO {

	private Long idEntrega;
	private String nbEntrega;
	private String nbLote;
	private Long idLote;
	private int nuArchivos;
	private int nuRegistros;
	private int nuValidaciones;
	private int pendientes;
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
	public String getNbLote() {
		return nbLote;
	}
	public void setNbLote(String nbLote) {
		this.nbLote = nbLote;
	}
	public Long getIdLote() {
		return idLote;
	}
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	public int getNuArchivos() {
		return nuArchivos;
	}
	public void setNuArchivos(int nuArchivos) {
		this.nuArchivos = nuArchivos;
	}
	public int getNuRegistros() {
		return nuRegistros;
	}
	public void setNuRegistros(int nuRegistros) {
		this.nuRegistros = nuRegistros;
	}
	public int getNuValidaciones() {
		return nuValidaciones;
	}
	public void setNuValidaciones(int nuValidaciones) {
		this.nuValidaciones = nuValidaciones;
	}
	public int getPendientes() {
		return pendientes;
	}
	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}
	
	
	
}
