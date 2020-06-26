package mx.com.teclo.svi.negocio.vo.archivoDetalle;

import java.util.Date;

import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;

public class ArchivoDetalleEvaVO {
	 
	 private Long idRegistroCsv;
	 private Long nodoVpn;
	 private String nuExpediente;
	 private String fhGeneracion;
	 private Long nuCarril;
	 private String cdPlacaDelantera;
	 private Short stDifPlacaDelantera;
	 private String cdEntidadDelantera;
	 private Short stDifEntidadDelantera;
	 private String nbImagenPlacaDelantera;
	 private String cdPlacaTrasera;
	 private Short stDifPlacaTrasera;
	 private String cdEntidadTrasera;
	 private Short stDifEntidadTrasera;
	 private String nbImagenPlacaTrasera;
	 private String nbImagenConductor;
	 private String nbImagenAmbiental;
	 private String cdPerfil;
	 private Short stDifPerfil;
	 private String nbImagenPerfil;
	 private long stValidacion;
	 private short st2daValidacion;
	 private short stInconsistencia;
	 private short stActivo;
	 private Date fhCreacion;
	 private long idUsrCreacion;
	 private Date fhModificacion;
	 private long idUsrModifica;
	 private Boolean stValPlacaDelantera;
	 private Boolean stValEntidadDelantera;
	 private Boolean stValPlacaTrasera;
	 private Boolean stValEntidadTrasera;
	 private Boolean stPochomovil;
	 private Boolean stPlacaOfiDelantera;
	 private Boolean stPlacaOfiTrasera;
	 private ArchivoCsvDTO idArchivoCsv;
	 private PtSiluetasDTO idPtSilueta;
	 private PtClasifExpedientesDTO idPTClasifExpe;
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
	 * @return the nodoVpn
	 */
	public Long getNodoVpn() {
		return nodoVpn;
	}
	/**
	 * @param nodoVpn the nodoVpn to set
	 */
	public void setNodoVpn(Long nodoVpn) {
		this.nodoVpn = nodoVpn;
	}
	/**
	 * @return the nuExpediente
	 */
	public String getNuExpediente() {
		return nuExpediente;
	}
	/**
	 * @param nuExpediente the nuExpediente to set
	 */
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	/**
	 * @return the fhGeneracion
	 */
	public String getFhGeneracion() {
		return fhGeneracion;
	}
	/**
	 * @param fhGeneracion the fhGeneracion to set
	 */
	public void setFhGeneracion(String fhGeneracion) {
		this.fhGeneracion = fhGeneracion;
	}
	/**
	 * @return the nuCarril
	 */
	public Long getNuCarril() {
		return nuCarril;
	}
	/**
	 * @param nuCarril the nuCarril to set
	 */
	public void setNuCarril(Long nuCarril) {
		this.nuCarril = nuCarril;
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
	 * @return the stDifPlacaDelantera
	 */
	public Short getStDifPlacaDelantera() {
		return stDifPlacaDelantera;
	}
	/**
	 * @param stDifPlacaDelantera the stDifPlacaDelantera to set
	 */
	public void setStDifPlacaDelantera(Short stDifPlacaDelantera) {
		this.stDifPlacaDelantera = stDifPlacaDelantera;
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
	 * @return the stDifEntidadDelantera
	 */
	public Short getStDifEntidadDelantera() {
		return stDifEntidadDelantera;
	}
	/**
	 * @param stDifEntidadDelantera the stDifEntidadDelantera to set
	 */
	public void setStDifEntidadDelantera(Short stDifEntidadDelantera) {
		this.stDifEntidadDelantera = stDifEntidadDelantera;
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
	 * @return the stDifPlacaTrasera
	 */
	public Short getStDifPlacaTrasera() {
		return stDifPlacaTrasera;
	}
	/**
	 * @param stDifPlacaTrasera the stDifPlacaTrasera to set
	 */
	public void setStDifPlacaTrasera(Short stDifPlacaTrasera) {
		this.stDifPlacaTrasera = stDifPlacaTrasera;
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
	 * @return the stDifEntidadTrasera
	 */
	public Short getStDifEntidadTrasera() {
		return stDifEntidadTrasera;
	}
	/**
	 * @param stDifEntidadTrasera the stDifEntidadTrasera to set
	 */
	public void setStDifEntidadTrasera(Short stDifEntidadTrasera) {
		this.stDifEntidadTrasera = stDifEntidadTrasera;
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
	 * @return the stDifPerfil
	 */
	public Short getStDifPerfil() {
		return stDifPerfil;
	}
	/**
	 * @param stDifPerfil the stDifPerfil to set
	 */
	public void setStDifPerfil(Short stDifPerfil) {
		this.stDifPerfil = stDifPerfil;
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
	 * @return the stValidacion
	 */
	public long getStValidacion() {
		return stValidacion;
	}
	/**
	 * @param stValidacion the stValidacion to set
	 */
	public void setStValidacion(long stValidacion) {
		this.stValidacion = stValidacion;
	}
	/**
	 * @return the st2daValidacion
	 */
	public short getSt2daValidacion() {
		return st2daValidacion;
	}
	/**
	 * @param st2daValidacion the st2daValidacion to set
	 */
	public void setSt2daValidacion(short st2daValidacion) {
		this.st2daValidacion = st2daValidacion;
	}
	/**
	 * @return the stInconsistencia
	 */
	public short getStInconsistencia() {
		return stInconsistencia;
	}
	/**
	 * @param stInconsistencia the stInconsistencia to set
	 */
	public void setStInconsistencia(short stInconsistencia) {
		this.stInconsistencia = stInconsistencia;
	}
	/**
	 * @return the stActivo
	 */
	public short getStActivo() {
		return stActivo;
	}
	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(short stActivo) {
		this.stActivo = stActivo;
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
	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(long idUsrCreacion) {
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
	public long getIdUsrModifica() {
		return idUsrModifica;
	}
	/**
	 * @param idUsrModifica the idUsrModifica to set
	 */
	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	
	/**
	 * @return the stValPlacaDelantera
	 */
	public Boolean getStValPlacaDelantera() {
		return stValPlacaDelantera;
	}
	/**
	 * @param stValPlacaDelantera the stValPlacaDelantera to set
	 */
	public void setStValPlacaDelantera(Boolean stValPlacaDelantera) {
		this.stValPlacaDelantera = stValPlacaDelantera;
	}
	/**
	 * @return the stValEntidadDelantera
	 */
	public Boolean getStValEntidadDelantera() {
		return stValEntidadDelantera;
	}
	/**
	 * @param stValEntidadDelantera the stValEntidadDelantera to set
	 */
	public void setStValEntidadDelantera(Boolean stValEntidadDelantera) {
		this.stValEntidadDelantera = stValEntidadDelantera;
	}
	/**
	 * @return the stValPlacaTrasera
	 */
	public Boolean getStValPlacaTrasera() {
		return stValPlacaTrasera;
	}
	/**
	 * @param stValPlacaTrasera the stValPlacaTrasera to set
	 */
	public void setStValPlacaTrasera(Boolean stValPlacaTrasera) {
		this.stValPlacaTrasera = stValPlacaTrasera;
	}
	/**
	 * @return the stValEntidadTrasera
	 */
	public Boolean getStValEntidadTrasera() {
		return stValEntidadTrasera;
	}
	/**
	 * @param stValEntidadTrasera the stValEntidadTrasera to set
	 */
	public void setStValEntidadTrasera(Boolean stValEntidadTrasera) {
		this.stValEntidadTrasera = stValEntidadTrasera;
	}

	/**
	 * @return the stPochomovil
	 */
	public Boolean getStPochomovil() {
		return stPochomovil;
	}
	/**
	 * @param stPochomovil the stPochomovil to set
	 */
	public void setStPochomovil(Boolean stPochomovil) {
		this.stPochomovil = stPochomovil;
	}
	/**
	 * @return the stPlacaOfiDelantera
	 */
	public Boolean getStPlacaOfiDelantera() {
		return stPlacaOfiDelantera;
	}
	/**
	 * @param stPlacaOfiDelantera the stPlacaOfiDelantera to set
	 */
	public void setStPlacaOfiDelantera(Boolean stPlacaOfiDelantera) {
		this.stPlacaOfiDelantera = stPlacaOfiDelantera;
	}
	/**
	 * @return the stPlacaOfiTrasera
	 */
	public Boolean getStPlacaOfiTrasera() {
		return stPlacaOfiTrasera;
	}
	/**
	 * @param stPlacaOfiTrasera the stPlacaOfiTrasera to set
	 */
	public void setStPlacaOfiTrasera(Boolean stPlacaOfiTrasera) {
		this.stPlacaOfiTrasera = stPlacaOfiTrasera;
	}
	/**
	 * @return the idArchivoCsv
	 */
	public ArchivoCsvDTO getIdArchivoCsv() {
		return idArchivoCsv;
	}
	/**
	 * @param idArchivoCsv the idArchivoCsv to set
	 */
	public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	/**
	 * @return the idPtSilueta
	 */
	public PtSiluetasDTO getIdPtSilueta() {
		return idPtSilueta;
	}
	/**
	 * @param idPtSilueta the idPtSilueta to set
	 */
	public void setIdPtSilueta(PtSiluetasDTO idPtSilueta) {
		this.idPtSilueta = idPtSilueta;
	}
	/**
	 * @return the idPTClasifExpe
	 */
	public PtClasifExpedientesDTO getIdPTClasifExpe() {
		return idPTClasifExpe;
	}
	/**
	 * @param idPTClasifExpe the idPTClasifExpe to set
	 */
	public void setIdPTClasifExpe(PtClasifExpedientesDTO idPTClasifExpe) {
		this.idPTClasifExpe = idPTClasifExpe;
	}
	 
 
}
