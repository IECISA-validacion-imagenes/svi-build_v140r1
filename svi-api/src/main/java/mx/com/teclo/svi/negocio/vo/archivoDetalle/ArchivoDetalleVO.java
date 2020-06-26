package mx.com.teclo.svi.negocio.vo.archivoDetalle;


import java.util.Collection;
import java.util.Date;

import mx.com.teclo.svi.persistencia.hibernate.dto.ArchDetBitEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;

public class ArchivoDetalleVO {
	
	private Long idRegistroCsv;
	private Long nodoVpn;
	private String nuExpediente;
	private String fhGeneracion;
	private Long nuCarril;
	private String cdPlacaDelantera;
	private String cdEntidadDelantera;
	private String cdPlacaTrasera;
	private String cdEntidadTrasera;
	private String nbImagenPlacaDelantera;
	private String nbImagenPlacaTrasera;
	private String nbImagenConductor;
	private String nbImagenAmbiental;
	private String cdPerfil;
	private String nbImagenPerfil;
	private short stActivo;
	private Date fhCreacion;
	private long idUsrCreacion;
	
	private Collection<ArchDetBitEvaDTO> archivoDetBitEvaCollection;
	private ArchivoCsvDTO idArchivoCsv;
	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}
	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}
	public Long getNodoVpn() {
		return nodoVpn;
	}
	public void setNodoVpn(Long nodoVpn) {
		this.nodoVpn = nodoVpn;
	}
	public String getNuExpediente() {
		return nuExpediente;
	}
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	public String getFhGeneracion() {
		return fhGeneracion;
	}
	public void setFhGeneracion(String fhGeneracion) {
		this.fhGeneracion = fhGeneracion;
	}
	public Long getNuCarril() {
		return nuCarril;
	}
	public void setNuCarril(Long nuCarril) {
		this.nuCarril = nuCarril;
	}
	public String getCdPlacaDelantera() {
		return cdPlacaDelantera;
	}
	public void setCdPlacaDelantera(String cdPlacaDelantera) {
		this.cdPlacaDelantera = cdPlacaDelantera;
	}
	public String getCdEntidadDelantera() {
		return cdEntidadDelantera;
	}
	public void setCdEntidadDelantera(String cdEntidadDelantera) {
		this.cdEntidadDelantera = cdEntidadDelantera;
	}
	public String getCdPlacaTrasera() {
		return cdPlacaTrasera;
	}
	public void setCdPlacaTrasera(String cdPlacaTrasera) {
		this.cdPlacaTrasera = cdPlacaTrasera;
	}
	public String getCdEntidadTrasera() {
		return cdEntidadTrasera;
	}
	public void setCdEntidadTrasera(String cdEntidadTrasera) {
		this.cdEntidadTrasera = cdEntidadTrasera;
	}
	public String getNbImagenPlacaDelantera() {
		return nbImagenPlacaDelantera;
	}
	public void setNbImagenPlacaDelantera(String nbImagenPlacaDelantera) {
		this.nbImagenPlacaDelantera = nbImagenPlacaDelantera;
	}
	public String getNbImagenPlacaTrasera() {
		return nbImagenPlacaTrasera;
	}
	public void setNbImagenPlacaTrasera(String nbImagenPlacaTrasera) {
		this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
	}
	public String getNbImagenConductor() {
		return nbImagenConductor;
	}
	public void setNbImagenConductor(String nbImagenConductor) {
		this.nbImagenConductor = nbImagenConductor;
	}
	public String getNbImagenAmbiental() {
		return nbImagenAmbiental;
	}
	public void setNbImagenAmbiental(String nbImagenAmbiental) {
		this.nbImagenAmbiental = nbImagenAmbiental;
	}
	public String getCdPerfil() {
		return cdPerfil;
	}
	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}
	public String getNbImagenPerfil() {
		return nbImagenPerfil;
	}
	public void setNbImagenPerfil(String nbImagenPerfil) {
		this.nbImagenPerfil = nbImagenPerfil;
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
	public Collection<ArchDetBitEvaDTO> getArchivoDetBitEvaCollection() {
		return archivoDetBitEvaCollection;
	}
	public void setArchivoDetBitEvaCollection(Collection<ArchDetBitEvaDTO> archivoDetBitEvaCollection) {
		this.archivoDetBitEvaCollection = archivoDetBitEvaCollection;
	}
	public ArchivoCsvDTO getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	
	
	 
}
