package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;

public class DetalleAsignacionVO {

	private Long idPtLote;
	private String nbLote;
	private Long idPtCsv;
	private String nbCsv;
	private Long idEtiqueta;
	private String nbEtiqueta;
	private Long idMotivoSeleccionado;
	private String estado;
	private List<ItemAsignacionVO> asignaciones;
	private FiltroExpedienteVO filtroAplicado;

	public Long getIdPtLote() {
		return idPtLote;
	}

	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}

	public String getNbLote() {
		return nbLote;
	}

	public void setNbLote(String nbLote) {
		this.nbLote = nbLote;
	}

	public Long getIdPtCsv() {
		return idPtCsv;
	}

	public void setIdPtCsv(Long idPtCsv) {
		this.idPtCsv = idPtCsv;
	}

	public String getNbCsv() {
		return nbCsv;
	}

	public void setNbCsv(String nbCsv) {
		this.nbCsv = nbCsv;
	}

	public Long getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(Long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public String getNbEtiqueta() {
		return nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public Long getIdMotivoSeleccionado() {
		return idMotivoSeleccionado;
	}

	public void setIdMotivoSeleccionado(Long idMotivoSeleccionado) {
		this.idMotivoSeleccionado = idMotivoSeleccionado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ItemAsignacionVO> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(List<ItemAsignacionVO> asignaciones) {
		this.asignaciones = asignaciones;
	}

	public FiltroExpedienteVO getFiltroAplicado() {
		return filtroAplicado;
	}

	public void setFiltroAplicado(FiltroExpedienteVO filtroAplicado) {
		this.filtroAplicado = filtroAplicado;
	}
	

}
