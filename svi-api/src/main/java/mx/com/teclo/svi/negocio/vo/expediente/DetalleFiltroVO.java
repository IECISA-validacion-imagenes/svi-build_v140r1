package mx.com.teclo.svi.negocio.vo.expediente;

import java.util.Date;
import java.util.List;

public class DetalleFiltroVO {
	private Long idFiltro;
	private String nbEtiqueta;	
	private Date fhCreacion;
	private String autor;
	private String txFiltro;
	private List<ItemFiltroExpedienteVO> detalle;

	public DetalleFiltroVO(Long idFiltro, String nbEtiqueta, Date fhCreacion, String autor, String txFiltro) {
		this.idFiltro = idFiltro;
		this.nbEtiqueta = nbEtiqueta;
		this.fhCreacion = fhCreacion;
		this.autor = autor;
		this.txFiltro = txFiltro;
	}
	
	public Long getIdFiltro() {
		return idFiltro;
	}

	public void setIdFiltro(Long idFiltro) {
		this.idFiltro = idFiltro;
	}

	public String getNbEtiqueta() {
		return nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public List<ItemFiltroExpedienteVO> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<ItemFiltroExpedienteVO> detalle) {
		this.detalle = detalle;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTxFiltro() {
		return txFiltro;
	}

	public void setTxFiltro(String txFiltro) {
		this.txFiltro = txFiltro;
	}

}
