package mx.com.teclo.svi.negocio.vo.supervision;

import java.util.List;

public class AsignaRevalidacionVO {
	private Long idPtLote;
	private Long idMotivoGeneralLote;
	private Long idMotivoDetalleLote;
	private Long idValidadorLote;
	private Long idTiporeval;
	private String filtro;
	private List<ArchivoRevalidacionVO> listaArchivosCsv;
	
	public Long getIdPtLote() {
		return idPtLote;
	}
	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}
	public Long getIdMotivoGeneralLote() {
		return idMotivoGeneralLote;
	}
	public void setIdMotivoGeneralLote(Long idMotivoGeneralLote) {
		this.idMotivoGeneralLote = idMotivoGeneralLote;
	}
	public Long getIdMotivoDetalleLote() {
		return idMotivoDetalleLote;
	}
	public void setIdMotivoDetalleLote(Long idMotivoDetalleLote) {
		this.idMotivoDetalleLote = idMotivoDetalleLote;
	}
	public Long getIdValidadorLote() {
		return idValidadorLote;
	}
	public void setIdValidadorLote(Long idValidadorLote) {
		this.idValidadorLote = idValidadorLote;
	}
	public Long getIdTiporeval() {
		return idTiporeval;
	}
	public void setIdTiporeval(Long idTiporeval) {
		this.idTiporeval = idTiporeval;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<ArchivoRevalidacionVO> getListaArchivosCsv() {
		return listaArchivosCsv;
	}
	public void setListaArchivosCsv(List<ArchivoRevalidacionVO> listaArchivosCsv) {
		this.listaArchivosCsv = listaArchivosCsv;
	}
	
	

}
