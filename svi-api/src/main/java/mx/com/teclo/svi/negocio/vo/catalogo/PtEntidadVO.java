package mx.com.teclo.svi.negocio.vo.catalogo;

import java.util.Date;

public class PtEntidadVO {

	 private Long idPtCatalogoEntidad;
	 private String cdEntidad;
	 private String txDescEntidad;
	 
	
	 /**
	 * @return the idPtCatalogoEntidad
	 */
	public Long getIdPtCatalogoEntidad() {
		return idPtCatalogoEntidad;
	}
	/**
	 * @param idPtCatalogoEntidad the idPtCatalogoEntidad to set
	 */
	public void setIdPtCatalogoEntidad(Long idPtCatalogoEntidad) {
		this.idPtCatalogoEntidad = idPtCatalogoEntidad;
	}
	/**
	 * @return the cdEntidad
	 */
	public String getCdEntidad() {
		return cdEntidad;
	}
	/**
	 * @param cdEntidad the cdEntidad to set
	 */
	public void setCdEntidad(String cdEntidad) {
		this.cdEntidad = cdEntidad;
	}
	/**
	 * @return the txDescEntidad
	 */
	public String getTxDescEntidad() {
		return txDescEntidad;
	}
	/**
	 * @param txDescEntidad the txDescEntidad to set
	 */
	public void setTxDescEntidad(String txDescEntidad) {
		this.txDescEntidad = txDescEntidad;
	}

	 
}
