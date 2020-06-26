package mx.com.teclo.svideskwsw.persistencia.vo.carga;

import java.util.Date;


public class DetalleLoteVO {

	private long idPtLote;
	private EntregaVO idEntrega;
	private String nbPtLote;
	private Boolean stEntrega;
	private Boolean stValidacion;
	private Boolean stActivo;
	private Long nuCarpetasImg;
	private Long nuTotalRegImg;
	private Long nuArchivosCsv;
	private Long nuTotalRegCsv;
	private Long nuCarpetasSil;
	private Long nuTotalRegSil;
	private Date fhCreacion;
	private Long idUsrCreacion;
	private Date fhModificacion;
	private Long idUsrModifica;
	
	//*Datos para completar ruta */
	private String nbEntrega;
	
	
	/**
	 * @return the idPtLote
	 */
	public long getIdPtLote() {
		return idPtLote;
	}
	/**
	 * @param idPtLote the idPtLote to set
	 */
	public void setIdPtLote(long idPtLote) {
		this.idPtLote = idPtLote;
	}
	/**
	 * @return the nbPtLote
	 */
	public String getNbPtLote() {
		return nbPtLote;
	}
	/**
	 * @param nbPtLote the nbPtLote to set
	 */
	public void setNbPtLote(String nbPtLote) {
		this.nbPtLote = nbPtLote;
	}
	/**
	 * @return the stEntrega
	 */
	public Boolean getStEntrega() {
		return stEntrega;
	}
	/**
	 * @param stEntrega the stEntrega to set
	 */
	public void setStEntrega(Boolean stEntrega) {
		this.stEntrega = stEntrega;
	}
	/**
	 * @return the stValidacion
	 */
	public Boolean getStValidacion() {
		return stValidacion;
	}
	/**
	 * @param stValidacion the stValidacion to set
	 */
	public void setStValidacion(Boolean stValidacion) {
		this.stValidacion = stValidacion;
	}
	/**
	 * @return the stActivo
	 */
	public Boolean getStActivo() {
		return stActivo;
	}
	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	/**
	 * @return the nuCarpetasImg
	 */
	public Long getNuCarpetasImg() {
		return nuCarpetasImg;
	}
	/**
	 * @param nuCarpetasImg the nuCarpetasImg to set
	 */
	public void setNuCarpetasImg(Long nuCarpetasImg) {
		this.nuCarpetasImg = nuCarpetasImg;
	}
	/**
	 * @return the nuTotalRegImg
	 */
	public Long getNuTotalRegImg() {
		return nuTotalRegImg;
	}
	/**
	 * @param nuTotalRegImg the nuTotalRegImg to set
	 */
	public void setNuTotalRegImg(Long nuTotalRegImg) {
		this.nuTotalRegImg = nuTotalRegImg;
	}
	/**
	 * @return the nuArchivosCsv
	 */
	public Long getNuArchivosCsv() {
		return nuArchivosCsv;
	}
	/**
	 * @param nuArchivosCsv the nuArchivosCsv to set
	 */
	public void setNuArchivosCsv(Long nuArchivosCsv) {
		this.nuArchivosCsv = nuArchivosCsv;
	}
	/**
	 * @return the nuTotalRegCsv
	 */
	public Long getNuTotalRegCsv() {
		return nuTotalRegCsv;
	}
	/**
	 * @param nuTotalRegCsv the nuTotalRegCsv to set
	 */
	public void setNuTotalRegCsv(Long nuTotalRegCsv) {
		this.nuTotalRegCsv = nuTotalRegCsv;
	}
	/**
	 * @return the nuCarpetasSil
	 */
	public Long getNuCarpetasSil() {
		return nuCarpetasSil;
	}
	/**
	 * @param nuCarpetasSil the nuCarpetasSil to set
	 */
	public void setNuCarpetasSil(Long nuCarpetasSil) {
		this.nuCarpetasSil = nuCarpetasSil;
	}
	/**
	 * @return the nuTotalRegSil
	 */
	public Long getNuTotalRegSil() {
		return nuTotalRegSil;
	}
	/**
	 * @param nuTotalRegSil the nuTotalRegSil to set
	 */
	public void setNuTotalRegSil(Long nuTotalRegSil) {
		this.nuTotalRegSil = nuTotalRegSil;
	}
	/**
	 * @return the fhCreacion
	 */
	public Date getFhCreacion() {
		return fhCreacion;
	}
	/**
	 * @param fhCreacion the fhCreacion to set
	 */
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	/**
	 * @return the fhModificacion
	 */
	public Date getFhModificacion() {
		return fhModificacion;
	}
	/**
	 * @param fhModificacion the fhModificacion to set
	 */
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	/**
	 * @return the idUsrModifica
	 */
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	/**
	 * @return the idEntrega
	 */
	public EntregaVO getIdEntrega() {
		return idEntrega;
	}
	/**
	 * @param idEntrega the idEntrega to set
	 */
	public void setIdEntrega(EntregaVO idEntrega) {
		this.idEntrega = idEntrega;
	}
	/**
	 * @return the nbEntrega
	 */
	public String getNbEntrega() {
		return nbEntrega;
	}
	/**
	 * @param nbEntrega the nbEntrega to set
	 */
	public void setNbEntrega(String nbEntrega) {
		this.nbEntrega = nbEntrega;
	}
	
	
}
