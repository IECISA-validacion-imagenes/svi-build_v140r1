package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

import mx.com.teclo.svi.persistencia.hibernate.dto.PtMarcasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;

public class ConsultaDetalleIncidenciaCSVVO {

	private Long idRegistroCsv;
	private String nuExpediente;
	private String nuCarril;
	private String placaDelantera;
	private String entidadDelantera;
	private String placaTrasera;
	private String entidadTrasera;
	private String perfil;
	private Boolean validacion;
	private Boolean stPreSeleccion;
	private List<IncidenciaVO> inconsistencia;
	private ClasificaExpedienteVO clasificacion;
	private Long idMarca;
	private Long idSubmarca;
	private Long idPerfil;
	
	
	
	
	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	public Long getIdSubmarca() {
		return idSubmarca;
	}
	public void setIdSubmarca(Long idSubmarca) {
		this.idSubmarca = idSubmarca;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}
	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}
	public String getNuExpediente() {
		return nuExpediente;
	}
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	public String getNuCarril() {
		return nuCarril;
	}
	public void setNuCarril(String nuCarril) {
		this.nuCarril = nuCarril;
	}
	public String getPlacaDelantera() {
		return placaDelantera;
	}
	public void setPlacaDelantera(String placaDelantera) {
		this.placaDelantera = placaDelantera;
	}
	public String getEntidadDelantera() {
		return entidadDelantera;
	}
	public void setEntidadDelantera(String entidadDelantera) {
		this.entidadDelantera = entidadDelantera;
	}
	public String getPlacaTrasera() {
		return placaTrasera;
	}
	public void setPlacaTrasera(String placaTrasera) {
		this.placaTrasera = placaTrasera;
	}
	public String getEntidadTrasera() {
		return entidadTrasera;
	}
	public void setEntidadTrasera(String entidadTrasera) {
		this.entidadTrasera = entidadTrasera;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Boolean getValidacion() {
		return validacion;
	}
	public void setValidacion(Boolean validacion) {
		this.validacion = validacion;
	}
	public List<IncidenciaVO> getInconsistencia() {
		return inconsistencia;
	}
	public void setInconsistencia(List<IncidenciaVO> inconsistencia) {
		this.inconsistencia = inconsistencia;
	}
	public ClasificaExpedienteVO getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(ClasificaExpedienteVO clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Boolean getStPreSeleccion() {
		return stPreSeleccion;
	}
	public void setStPreSeleccion(Boolean stPreSeleccion) {
		this.stPreSeleccion = stPreSeleccion;
	}
	
}
