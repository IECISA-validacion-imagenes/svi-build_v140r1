package mx.com.teclo.svi.negocio.vo.validacion;

public class RevalidacionArchivoDetEvaVO {
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
	 /* Constaodores */
	 private Long totalRegistros;
	 private Long regValidados;
	 private Long Revalidacion;
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
	/**
	 * @return the idPtClasifExpe
	 */
	public Long getIdPtClasifExpe() {
		return idPtClasifExpe;
	}
	/**
	 * @param idPtClasifExpe the idPtClasifExpe to set
	 */
	public void setIdPtClasifExpe(Long idPtClasifExpe) {
		this.idPtClasifExpe = idPtClasifExpe;
	}
	/**
	 * @return the idCatValidacion
	 */
	public Long getIdCatValidacion() {
		return idCatValidacion;
	}
	/**
	 * @param idCatValidacion the idCatValidacion to set
	 */
	public void setIdCatValidacion(Long idCatValidacion) {
		this.idCatValidacion = idCatValidacion;
	}
	/**
	 * @return the stValPlacaDelantera
	 */
	public Boolean getStValPlacaDelantera() {
		return StValPlacaDelantera;
	}
	/**
	 * @param stValPlacaDelantera the stValPlacaDelantera to set
	 */
	public void setStValPlacaDelantera(Boolean stValPlacaDelantera) {
		StValPlacaDelantera = stValPlacaDelantera;
	}
	/**
	 * @return the stValEntidadDelantera
	 */
	public Boolean getStValEntidadDelantera() {
		return StValEntidadDelantera;
	}
	/**
	 * @param stValEntidadDelantera the stValEntidadDelantera to set
	 */
	public void setStValEntidadDelantera(Boolean stValEntidadDelantera) {
		StValEntidadDelantera = stValEntidadDelantera;
	}
	/**
	 * @return the stValPlacaTrasera
	 */
	public Boolean getStValPlacaTrasera() {
		return StValPlacaTrasera;
	}
	/**
	 * @param stValPlacaTrasera the stValPlacaTrasera to set
	 */
	public void setStValPlacaTrasera(Boolean stValPlacaTrasera) {
		StValPlacaTrasera = stValPlacaTrasera;
	}
	/**
	 * @return the stValEntidadTrasera
	 */
	public Boolean getStValEntidadTrasera() {
		return StValEntidadTrasera;
	}
	/**
	 * @param stValEntidadTrasera the stValEntidadTrasera to set
	 */
	public void setStValEntidadTrasera(Boolean stValEntidadTrasera) {
		StValEntidadTrasera = stValEntidadTrasera;
	}
	/**
	 * @return the stPochomovil
	 */
	public Boolean getStPochomovil() {
		return StPochomovil;
	}
	/**
	 * @param stPochomovil the stPochomovil to set
	 */
	public void setStPochomovil(Boolean stPochomovil) {
		StPochomovil = stPochomovil;
	}
	/**
	 * @return the stPlacaOfiDelantera
	 */
	public Boolean getStPlacaOfiDelantera() {
		return StPlacaOfiDelantera;
	}
	/**
	 * @param stPlacaOfiDelantera the stPlacaOfiDelantera to set
	 */
	public void setStPlacaOfiDelantera(Boolean stPlacaOfiDelantera) {
		StPlacaOfiDelantera = stPlacaOfiDelantera;
	}
	/**
	 * @return the stPlacaOfiTrasera
	 */
	public Boolean getStPlacaOfiTrasera() {
		return StPlacaOfiTrasera;
	}
	/**
	 * @param stPlacaOfiTrasera the stPlacaOfiTrasera to set
	 */
	public void setStPlacaOfiTrasera(Boolean stPlacaOfiTrasera) {
		StPlacaOfiTrasera = stPlacaOfiTrasera;
	}
	/**
	 * @return the stDifPerfil
	 */
	public Boolean getStDifPerfil() {
		return StDifPerfil;
	}
	/**
	 * @param stDifPerfil the stDifPerfil to set
	 */
	public void setStDifPerfil(Boolean stDifPerfil) {
		StDifPerfil = stDifPerfil;
	}
	/**
	 * @return the stDoblePlaca
	 */
	public Boolean getStDoblePlaca() {
		return StDoblePlaca;
	}
	/**
	 * @param stDoblePlaca the stDoblePlaca to set
	 */
	public void setStDoblePlaca(Boolean stDoblePlaca) {
		StDoblePlaca = stDoblePlaca;
	}
	/**
	 * @return the idPerfil
	 */
	public Long getIdPerfil() {
		return idPerfil;
	}
	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	/**
	 * @return the idMarca
	 */
	public Long getIdMarca() {
		return idMarca;
	}
	/**
	 * @param idMarca the idMarca to set
	 */
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	/**
	 * @return the idSubMarca
	 */
	public Long getIdSubMarca() {
		return idSubMarca;
	}
	/**
	 * @param idSubMarca the idSubMarca to set
	 */
	public void setIdSubMarca(Long idSubMarca) {
		this.idSubMarca = idSubMarca;
	}
	/**
	 * @return the totalRegistros
	 */
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * @return the regValidados
	 */
	public Long getRegValidados() {
		return regValidados;
	}
	/**
	 * @param regValidados the regValidados to set
	 */
	public void setRegValidados(Long regValidados) {
		this.regValidados = regValidados;
	}
	/**
	 * @return the revalidacion
	 */
	public Long getRevalidacion() {
		return Revalidacion;
	}
	/**
	 * @param revalidacion the revalidacion to set
	 */
	public void setRevalidacion(Long revalidacion) {
		Revalidacion = revalidacion;
	}
	 
	 
}
