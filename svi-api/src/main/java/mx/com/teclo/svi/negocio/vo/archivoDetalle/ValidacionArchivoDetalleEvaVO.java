package mx.com.teclo.svi.negocio.vo.archivoDetalle;


public class ValidacionArchivoDetalleEvaVO {
	 
	 private Long idRegistroCsv;//
	
	 private String cdPlacaDelantera;//
	 private String cdEntidadDelantera;//
	 private String nbImagenPlacaDelantera; //
	 private String cdPlacaTrasera;//
	 private String cdEntidadTrasera;//
	 private String nbImagenPlacaTrasera;// 
	 private String nbImagenConductor; //
	 
	 private String nbImagenAmbiental; //
	 private String cdPerfil; //
	 private String nbImagenPerfil; //
	 private Long idArchivoCsv;//
	 private Long idPtClasifExpe;//
	 private Long idCatValidacion;//
	 
	 private Boolean StValPlacaDelantera;
	 private Boolean StValEntidadDelantera;
	 private Boolean StValPlacaTrasera;
	 private Boolean StValEntidadTrasera;
	 private Boolean StPochomovil;
	 private Boolean StPlacaOfiDelantera;
	 private Boolean StPlacaOfiTrasera;
	 private Boolean StDifPerfil;
	 private Boolean StDoblePlaca;
	
	 private Long idPerfil;//
	 private Long idMarca;//
	 private Long idSubMarca;//
	 
	 
	 
	 
	 
	 public Boolean getStDoblePlaca() {
		return StDoblePlaca;
	}
	public void setStDoblePlaca(Boolean stDoblePlaca) {
		StDoblePlaca = stDoblePlaca;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	public Long getIdSubMarca() {
		return idSubMarca;
	}
	public void setIdSubMarca(Long idSubMarca) {
		this.idSubMarca = idSubMarca;
	}
	public Boolean getStValPlacaDelantera() {
		return StValPlacaDelantera;
	}
	public void setStValPlacaDelantera(Boolean stValPlacaDelantera) {
		StValPlacaDelantera = stValPlacaDelantera;
	}
	public Boolean getStValEntidadDelantera() {
		return StValEntidadDelantera;
	}
	public void setStValEntidadDelantera(Boolean stValEntidadDelantera) {
		StValEntidadDelantera = stValEntidadDelantera;
	}
	public Boolean getStValPlacaTrasera() {
		return StValPlacaTrasera;
	}
	public void setStValPlacaTrasera(Boolean stValPlacaTrasera) {
		StValPlacaTrasera = stValPlacaTrasera;
	}
	public Boolean getStValEntidadTrasera() {
		return StValEntidadTrasera;
	}
	public void setStValEntidadTrasera(Boolean stValEntidadTrasera) {
		StValEntidadTrasera = stValEntidadTrasera;
	}
	public Boolean getStPochomovil() {
		return StPochomovil;
	}
	public void setStPochomovil(Boolean stPochomovil) {
		StPochomovil = stPochomovil;
	}
	public Boolean getStPlacaOfiDelantera() {
		return StPlacaOfiDelantera;
	}
	public void setStPlacaOfiDelantera(Boolean stPlacaOfiDelantera) {
		StPlacaOfiDelantera = stPlacaOfiDelantera;
	}
	public Boolean getStPlacaOfiTrasera() {
		return StPlacaOfiTrasera;
	}
	public void setStPlacaOfiTrasera(Boolean stPlacaOfiTrasera) {
		StPlacaOfiTrasera = stPlacaOfiTrasera;
	}
	public Boolean getStDifPerfil() {
		return StDifPerfil;
	}
	public void setStDifPerfil(Boolean stDifPerfil) {
		StDifPerfil = stDifPerfil;
	}
	public Long getIdCatValidacion() {
		return idCatValidacion;
	}
	public void setIdCatValidacion(Long idCatValidacion) {
		this.idCatValidacion = idCatValidacion;
	}
	public Long getIdPtClasifExpe() {
		return idPtClasifExpe;
	}
	public void setIdPtClasifExpe(Long idPtClasifExpe) {
		this.idPtClasifExpe = idPtClasifExpe;
	}
	
	/**
	 * @return the idRegistroCsv
	 */
	 
	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}
	/**
	 * @param idRegistroCsv the idRegistroCsv to set
	 */
	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}
	/**
	 * @return the cdPlacaDelantera
	 */
	public String getCdPlacaDelantera() {
		return cdPlacaDelantera;
	}
	/**
	 * @param cdPlacaDelantera the cdPlacaDelantera to set
	 */
	public void setCdPlacaDelantera(String cdPlacaDelantera) {
		this.cdPlacaDelantera = cdPlacaDelantera;
	}
	/**
	 * @return the cdEntidadDelantera
	 */
	public String getCdEntidadDelantera() {
		return cdEntidadDelantera;
	}
	/**
	 * @param cdEntidadDelantera the cdEntidadDelantera to set
	 */
	public void setCdEntidadDelantera(String cdEntidadDelantera) {
		this.cdEntidadDelantera = cdEntidadDelantera;
	}
	/**
	 * @return the nbImagenPlacaDelantera
	 */
	public String getNbImagenPlacaDelantera() {
		return nbImagenPlacaDelantera;
	}
	/**
	 * @param nbImagenPlacaDelantera the nbImagenPlacaDelantera to set
	 */
	public void setNbImagenPlacaDelantera(String nbImagenPlacaDelantera) {
		this.nbImagenPlacaDelantera = nbImagenPlacaDelantera;
	}
	/**
	 * @return the cdPlacaTrasera
	 */
	public String getCdPlacaTrasera() {
		return cdPlacaTrasera;
	}
	/**
	 * @param cdPlacaTrasera the cdPlacaTrasera to set
	 */
	public void setCdPlacaTrasera(String cdPlacaTrasera) {
		this.cdPlacaTrasera = cdPlacaTrasera;
	}
	/**
	 * @return the cdEntidadTrasera
	 */
	public String getCdEntidadTrasera() {
		return cdEntidadTrasera;
	}
	/**
	 * @param cdEntidadTrasera the cdEntidadTrasera to set
	 */
	public void setCdEntidadTrasera(String cdEntidadTrasera) {
		this.cdEntidadTrasera = cdEntidadTrasera;
	}
	/**
	 * @return the nbImagenPlacaTrasera
	 */
	public String getNbImagenPlacaTrasera() {
		return nbImagenPlacaTrasera;
	}
	/**
	 * @param nbImagenPlacaTrasera the nbImagenPlacaTrasera to set
	 */
	public void setNbImagenPlacaTrasera(String nbImagenPlacaTrasera) {
		this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
	}
	/**
	 * @return the nbImagenConductor
	 */
	public String getNbImagenConductor() {
		return nbImagenConductor;
	}
	/**
	 * @param nbImagenConductor the nbImagenConductor to set
	 */
	public void setNbImagenConductor(String nbImagenConductor) {
		this.nbImagenConductor = nbImagenConductor;
	}
	/**
	 * @return the nbImagenAmbiental
	 */
	public String getNbImagenAmbiental() {
		return nbImagenAmbiental;
	}
	/**
	 * @param nbImagenAmbiental the nbImagenAmbiental to set
	 */
	public void setNbImagenAmbiental(String nbImagenAmbiental) {
		this.nbImagenAmbiental = nbImagenAmbiental;
	}
	/**
	 * @return the cdPerfil
	 */
	public String getCdPerfil() {
		return cdPerfil;
	}
	/**
	 * @param cdPerfil the cdPerfil to set
	 */
	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}
	/**
	 * @return the nbImagenPerfil
	 */
	public String getNbImagenPerfil() {
		return nbImagenPerfil;
	}
	/**
	 * @param nbImagenPerfil the nbImagenPerfil to set
	 */
	public void setNbImagenPerfil(String nbImagenPerfil) {
		this.nbImagenPerfil = nbImagenPerfil;
	}
	/**
	 * @return the idArchivoCsv
	 */
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	/**
	 * @param idArchivoCsv the idArchivoCsv to set
	 */
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	 
	 
		 
	 
	 
	 
}
