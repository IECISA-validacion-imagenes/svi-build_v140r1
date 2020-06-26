/**
 * 
 */
package mx.com.teclo.svideskwsw.persistencia.vo.carga;

import java.util.Date;

public class DetalleArchivoPTVO {
	
	 private long idArchivoCsv;
	 private long nodoVpn;
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
	    private boolean stActivo;
	    private Date fhCreacion;
	    private long idUsrCreacion;
	    
	    
	    
	    
//	    public DetalleArchivoPTVO(long idArchivoCsv,long nodoVpn, String nuExpediente, String fhGeneracion,long nuCarril,String cdPlacaDelantera,String cdEntidadDelantera,
//	    		String cdPlacaTrasera, String cdEntidadTrasera,String nbImagenPlacaDelantera,String nbImagenPlacaTrasera,String nbImagenConductor,String nbImagenAmbiental,
//	    		String cdPerfil,String nbImagenPerfil,long stActivo, Date fhCreacion, long idUsrCreacion){
//	    	
//	    	this.idArchivoCsv = idArchivoCsv;
//	    	this.nodoVpn = nodoVpn;
//	    	this.nuExpediente = nuExpediente;
//	    	this.fhGeneracion = fhGeneracion;
//	    	this.nuCarril = nuCarril;
//	    	this.cdPlacaDelantera = cdPlacaDelantera;
//	    	this.cdEntidadDelantera = cdEntidadDelantera;
//	    	this.cdPlacaTrasera = cdPlacaTrasera;
//	    	this.cdEntidadTrasera = cdEntidadTrasera;
//	    	this.nbImagenPlacaDelantera  =nbImagenPlacaDelantera;
//	    	this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
//	    	this.nbImagenConductor = nbImagenConductor;
//	    	this.nbImagenAmbiental = nbImagenAmbiental;
//	    	this.cdPerfil = cdPerfil;
//	    	this.nbImagenPerfil = nbImagenPerfil;
//	    	this.stActivo = stActivo;
//	    	this.fhCreacion = fhCreacion;
//	    	this.idUsrCreacion = idUsrCreacion;
//	    }

		/**
		 * @return the idArchivoCsv
		 */
		public long getIdArchivoCsv() {
			return idArchivoCsv;
		}

		/**
		 * @param idArchivoCsv the idArchivoCsv to set
		 */
		public void setIdArchivoCsv(long idArchivoCsv) {
			this.idArchivoCsv = idArchivoCsv;
		}

		/**
		 * @param nodoVpn the nodoVpn to set
		 */
		public void setNodoVpn(long nodoVpn) {
			this.nodoVpn = nodoVpn;
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
		 * @return the stActivo
		 */
		public boolean getStActivo() {
			return stActivo;
		}
		/**
		 * @param stActivo the stActivo to set
		 */
		public void setStActivo(boolean stActivo) {
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
	    
	

}
