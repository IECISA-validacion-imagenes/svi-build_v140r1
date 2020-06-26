package mx.com.teclo.svi.negocio.vo.lote;

import java.util.Date;

public class LoteVO {
	
	 private Long idPtLote;
	 private String nbPtLote;
	 private short stEntrega;
	 private short stValidacion;
	 private short stActivo;
	 private long nuCarpetasImg;
	 private long nuTotalRegImg;
	 private long nuArchivosCsv;
	 private long nuTotalRegCsv;
	 private long nuCarpetasSil;
	 private long nuTotalRegSil;
	 private Date fhCreacion;
	 private long idUsrCreacion;
	 private Date fhModificacion;
	 private long idUsrModifica;
	 
	public Long getIdPtLote() {
		return idPtLote;
	}
	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}
	public String getNbPtLote() {
		return nbPtLote;
	}
	public void setNbPtLote(String nbPtLote) {
		this.nbPtLote = nbPtLote;
	}
	public short getStEntrega() {
		return stEntrega;
	}
	public void setStEntrega(short stEntrega) {
		this.stEntrega = stEntrega;
	}
	public short getStValidacion() {
		return stValidacion;
	}
	public void setStValidacion(short stValidacion) {
		this.stValidacion = stValidacion;
	}
	public short getStActivo() {
		return stActivo;
	}
	public void setStActivo(short stActivo) {
		this.stActivo = stActivo;
	}
	public long getNuCarpetasImg() {
		return nuCarpetasImg;
	}
	public void setNuCarpetasImg(long nuCarpetasImg) {
		this.nuCarpetasImg = nuCarpetasImg;
	}
	public long getNuTotalRegImg() {
		return nuTotalRegImg;
	}
	public void setNuTotalRegImg(long nuTotalRegImg) {
		this.nuTotalRegImg = nuTotalRegImg;
	}
	public long getNuArchivosCsv() {
		return nuArchivosCsv;
	}
	public void setNuArchivosCsv(long nuArchivosCsv) {
		this.nuArchivosCsv = nuArchivosCsv;
	}
	public long getNuTotalRegCsv() {
		return nuTotalRegCsv;
	}
	public void setNuTotalRegCsv(long nuTotalRegCsv) {
		this.nuTotalRegCsv = nuTotalRegCsv;
	}
	public long getNuCarpetasSil() {
		return nuCarpetasSil;
	}
	public void setNuCarpetasSil(long nuCarpetasSil) {
		this.nuCarpetasSil = nuCarpetasSil;
	}
	public long getNuTotalRegSil() {
		return nuTotalRegSil;
	}
	public void setNuTotalRegSil(long nuTotalRegSil) {
		this.nuTotalRegSil = nuTotalRegSil;
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
