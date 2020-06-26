package mx.com.teclo.svi.negocio.vo.entrega;

import java.util.Date;

public class EntregaVO {
	
    private Long idEntrega;
    private String nbEntrega;
    private Date fhRecepcion;
    private Date fhEntrega;
    private Long nuCantidadCsv;
    private Long nuTotalRegCsv;
    private Long nuTotalRegImg;
    private Long nuTotalRegSil;
    private String txEntrega;
    private short stActivo;
    private Date fhCreacion;
    private long idUsrCreacion;
    private Date fhModificacion;
    private long idUsrModifica;
    
	public Long getIdEntrega() {
		return idEntrega;
	}
	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}
	public String getNbEntrega() {
		return nbEntrega;
	}
	public void setNbEntrega(String nbEntrega) {
		this.nbEntrega = nbEntrega;
	}
	public Date getFhRecepcion() {
		return fhRecepcion;
	}
	public void setFhRecepcion(Date fhRecepcion) {
		this.fhRecepcion = fhRecepcion;
	}
	public Date getFhEntrega() {
		return fhEntrega;
	}
	public void setFhEntrega(Date fhEntrega) {
		this.fhEntrega = fhEntrega;
	}
	public Long getNuCantidadCsv() {
		return nuCantidadCsv;
	}
	public void setNuCantidadCsv(Long nuCantidadCsv) {
		this.nuCantidadCsv = nuCantidadCsv;
	}
	public Long getNuTotalRegCsv() {
		return nuTotalRegCsv;
	}
	public void setNuTotalRegCsv(Long nuTotalRegCsv) {
		this.nuTotalRegCsv = nuTotalRegCsv;
	}
	public Long getNuTotalRegImg() {
		return nuTotalRegImg;
	}
	public void setNuTotalRegImg(Long nuTotalRegImg) {
		this.nuTotalRegImg = nuTotalRegImg;
	}
	public Long getNuTotalRegSil() {
		return nuTotalRegSil;
	}
	public void setNuTotalRegSil(Long nuTotalRegSil) {
		this.nuTotalRegSil = nuTotalRegSil;
	}
	public String getTxEntrega() {
		return txEntrega;
	}
	public void setTxEntrega(String txEntrega) {
		this.txEntrega = txEntrega;
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
