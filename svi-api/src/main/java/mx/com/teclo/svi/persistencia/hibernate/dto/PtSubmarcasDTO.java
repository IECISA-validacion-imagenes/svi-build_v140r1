package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCI018C_PT_SUBMARCA")
public class PtSubmarcasDTO implements Serializable{


	 
	    /**
	 * 
	 */
	private static final long serialVersionUID = 8129400927078246395L;
		@Id
	    @Basic(optional = false)
	    @Column(name = "ID_PT_SUBMARCA")
	    private Long idPtSubmarca;
	    @Basic(optional = false)
	    @Column(name = "CD_PT_SUBMARCA")
	    private String cdPtSubmarca;
	    @Basic(optional = false)
	    @Column(name = "TXT_MARCA")
	    private String txtMarca;
	    @Basic(optional = false)
	    @Column(name = "ST_ACTIVO")
	    private short stActivo;
	    @Basic(optional = false)
	    @Column(name = "FH_CREACION")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date fhCreacion;
	    @Basic(optional = false)
	    @Column(name = "ID_USR_CREACION")
	    private long idUsrCreacion;
	    @Basic(optional = false)
	    @Column(name = "FH_MODIFICACION")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date fhModificacion;
	    @Column(name = "ID_USR_MODIFICA")
	    private Long idUsrModifica;
	    
	    @JoinColumn(name = "ID_PT_PERFIL", referencedColumnName = "ID_PT_PERFILES")
	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    private PtPerfilesDTO idPtPerfil;
	    
	    @JoinColumn(name = "ID_PT_MARCA", referencedColumnName = "ID_PT_MARCA")
	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    private PtMarcasDTO idPtMarca;

		/**
		 * @return the idPtSubmarca
		 */
		public Long getIdPtSubmarca() {
			return idPtSubmarca;
		}

		/**
		 * @param idPtSubmarca the idPtSubmarca to set
		 */
		public void setIdPtSubmarca(Long idPtSubmarca) {
			this.idPtSubmarca = idPtSubmarca;
		}

		/**
		 * @return the cdPtSubmarca
		 */
		public String getCdPtSubmarca() {
			return cdPtSubmarca;
		}

		/**
		 * @param cdPtSubmarca the cdPtSubmarca to set
		 */
		public void setCdPtSubmarca(String cdPtSubmarca) {
			this.cdPtSubmarca = cdPtSubmarca;
		}

		/**
		 * @return the txtMarca
		 */
		public String getTxtMarca() {
			return txtMarca;
		}

		/**
		 * @param txtMarca the txtMarca to set
		 */
		public void setTxtMarca(String txtMarca) {
			this.txtMarca = txtMarca;
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
		 * @return the idPtPerfil
		 */
		public PtPerfilesDTO getIdPtPerfil() {
			return idPtPerfil;
		}

		/**
		 * @param idPtPerfil the idPtPerfil to set
		 */
		public void setIdPtPerfil(PtPerfilesDTO idPtPerfil) {
			this.idPtPerfil = idPtPerfil;
		}

		/**
		 * @return the idPtMarca
		 */
		public PtMarcasDTO getIdPtMarca() {
			return idPtMarca;
		}

		/**
		 * @param idPtMarca the idPtMarca to set
		 */
		public void setIdPtMarca(PtMarcasDTO idPtMarca) {
			this.idPtMarca = idPtMarca;
		}
	    
	    
	    
}
