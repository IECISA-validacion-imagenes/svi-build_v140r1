package mx.com.teclo.svi.negocio.vo.validadores;

import java.util.Date;

public class ValidadoresConfigVO {
	
	private Long idValidadorConfig;
	private short stActivo;
	private Date fhCreacion;
	private long idUsrCreacion;
	private Date fhModificacion;
	private long idUsrModifica;
	
	public ValidadoresConfigVO(Long idValidadorConfig, short stActivo, Date fhCreacion, long idUsrCreacion,
			Date fhModificacion, long idUsrModifica) {
		super();
		this.idValidadorConfig = idValidadorConfig;
		this.stActivo = stActivo;
		this.fhCreacion = fhCreacion;
		this.idUsrCreacion = idUsrCreacion;
		this.fhModificacion = fhModificacion;
		this.idUsrModifica = idUsrModifica;
	}
	
	public Long getIdValidadorConfig() {
		return idValidadorConfig;
	}
	public void setIdValidadorConfig(Long idValidadorConfig) {
		this.idValidadorConfig = idValidadorConfig;
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
