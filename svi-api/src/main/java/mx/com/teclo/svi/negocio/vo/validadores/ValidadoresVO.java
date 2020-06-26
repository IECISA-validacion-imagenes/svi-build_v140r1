package mx.com.teclo.svi.negocio.vo.validadores;

import java.util.Date;

import mx.com.teclo.svi.persistencia.hibernate.dto.UsuarioDTO;

public class ValidadoresVO {

	private Long idValidador;
	private short stValidadorActivo;
	private short stActivo;
	private Date fhCreacion;
	private long idUsrCreacion;
	private Date fhModificacion;
	private long idUsrModifica;
	private UsuarioDTO idUsuario;
	
	
	
	public ValidadoresVO(Long idValidador, short stValidadorActivo, short stActivo, Date fhCreacion, long idUsrCreacion,
			Date fhModificacion, long idUsrModifica, UsuarioDTO idUsuario) {
		super();
		this.idValidador = idValidador;
		this.stValidadorActivo = stValidadorActivo;
		this.stActivo = stActivo;
		this.fhCreacion = fhCreacion;
		this.idUsrCreacion = idUsrCreacion;
		this.fhModificacion = fhModificacion;
		this.idUsrModifica = idUsrModifica;
		this.idUsuario = idUsuario;
	}
	public Long getIdValidador() {
		return idValidador;
	}
	public void setIdValidador(Long idValidador) {
		this.idValidador = idValidador;
	}
	public short getStValidadorActivo() {
		return stValidadorActivo;
	}
	public void setStValidadorActivo(short stValidadorActivo) {
		this.stValidadorActivo = stValidadorActivo;
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
	public UsuarioDTO getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(UsuarioDTO idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
