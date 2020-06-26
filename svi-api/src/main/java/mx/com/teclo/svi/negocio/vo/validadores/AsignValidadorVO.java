package mx.com.teclo.svi.negocio.vo.validadores;

import java.util.Date;

public class AsignValidadorVO {
	
	private Long idAsignValidador;
	private Long idPtLote;
	private Long idArchivoCsv;
	private Short idValidacion;
	private short stActivo;
	private Date fhCreacion;
	private long idUsrCreacion;
	private Date fhModificacion;
	private long idUsrModifica;
	
	
	
	public AsignValidadorVO(Long idAsignValidador, Long idPtLote, Long idArchivoCsv, Short idValidacion, short stActivo,
			Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
		super();
		this.idAsignValidador = idAsignValidador;
		this.idPtLote = idPtLote;
		this.idArchivoCsv = idArchivoCsv;
		this.idValidacion = idValidacion;
		this.stActivo = stActivo;
		this.fhCreacion = fhCreacion;
		this.idUsrCreacion = idUsrCreacion;
		this.fhModificacion = fhModificacion;
		this.idUsrModifica = idUsrModifica;
	}   
	
	public Long getIdAsignValidador() {
		return idAsignValidador;
	}
	public void setIdAsignValidador(Long idAsignValidador) {
		this.idAsignValidador = idAsignValidador;
	}
	public Long getIdPtLote() {
		return idPtLote;
	}
	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public Short getIdValidacion() {
		return idValidacion;
	}
	public void setIdValidacion(Short idValidacion) {
		this.idValidacion = idValidacion;
	}
	public short getStActivo() {
		return stActivo;
	}
	public void setStActivo(short stActivo) {
		this.stActivo = stActivo;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	public long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

}
