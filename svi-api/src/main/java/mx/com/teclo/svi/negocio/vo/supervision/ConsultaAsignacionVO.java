package mx.com.teclo.svi.negocio.vo.supervision;

public class ConsultaAsignacionVO {

	private Long idAsignaValidacion;
	private String nbUsuario;
	private String nbNombre;
	private String nbLote;
	private String nbArchivo;
	private Boolean stValidacion;
	private Boolean stActivo;
	private String fhAsignacion;
	private String fhUltimaValidacion;
	
	public Long getIdAsignaValidacion() {
		return idAsignaValidacion;
	}
	public void setIdAsignaValidacion(Long idAsignaValidacion) {
		this.idAsignaValidacion = idAsignaValidacion;
	}
	public String getNbUsuario() {
		return nbUsuario;
	}
	public void setNbUsuario(String nbUsuario) {
		this.nbUsuario = nbUsuario;
	}
	public String getNbNombre() {
		return nbNombre;
	}
	public void setNbNombre(String nbNombre) {
		this.nbNombre = nbNombre;
	}
	public String getNbLote() {
		return nbLote;
	}
	public void setNbLote(String nbLote) {
		this.nbLote = nbLote;
	}
	public String getNbArchivo() {
		return nbArchivo;
	}
	public void setNbArchivo(String nbArchivo) {
		this.nbArchivo = nbArchivo;
	}
	public Boolean getStValidacion() {
		return stValidacion;
	}
	public void setStValidacion(Boolean stValidacion) {
		this.stValidacion = stValidacion;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public String getFhAsignacion() {
		return fhAsignacion;
	}
	public void setFhAsignacion(String fhAsignacion) {
		this.fhAsignacion = fhAsignacion;
	}
	public String getFhUltimaValidacion() {
		return fhUltimaValidacion;
	}
	public void setFhUltimaValidacion(String fhUltimaValidacion) {
		this.fhUltimaValidacion = fhUltimaValidacion;
	}
}
