package mx.com.teclo.svi.negocio.vo.catalogo;

import java.io.Serializable;

public class PtPerfilesVO {
	
	private long idPtPerfiles;
	private String cdIdentificacion;
	private Serializable lbPerfil;
	private String txDescripcion;
	/**
	 * @return the idPtPerfiles
	 */
	public long getIdPtPerfiles() {
		return idPtPerfiles;
	}
	/**
	 * @param idPtPerfiles the idPtPerfiles to set
	 */
	public void setIdPtPerfiles(long idPtPerfiles) {
		this.idPtPerfiles = idPtPerfiles;
	}
	/**
	 * @return the cdIdentificacion
	 */
	public String getCdIdentificacion() {
		return cdIdentificacion;
	}
	/**
	 * @param cdIdentificacion the cdIdentificacion to set
	 */
	public void setCdIdentificacion(String cdIdentificacion) {
		this.cdIdentificacion = cdIdentificacion;
	}
	/**
	 * @return the lbPerfil
	 */
	public Serializable getLbPerfil() {
		return lbPerfil;
	}
	/**
	 * @param lbPerfil the lbPerfil to set
	 */
	public void setLbPerfil(Serializable lbPerfil) {
		this.lbPerfil = lbPerfil;
	}
	/**
	 * @return the txDescripcion
	 */
	public String getTxDescripcion() {
		return txDescripcion;
	}
	/**
	 * @param txDescripcion the txDescripcion to set
	 */
	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}
	
}
