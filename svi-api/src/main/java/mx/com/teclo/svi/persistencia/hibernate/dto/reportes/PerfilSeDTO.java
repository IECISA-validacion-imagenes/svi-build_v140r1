package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAQ026C_SE_PERFILES")
public class PerfilSeDTO  implements Serializable{
	
	private static final long serialVersionUID = 2073543093107067611L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_PERFIL", unique = true, nullable = false)
	private Long perfilId;
	
	@Column(name = "ID_APLICACION")
	private Long idAplicacion;
	
	@Column(name = "CD_PERFIL")
	private String cdPerfil;
	
	@Column(name = "NB_PERFIL")
	private String nbPerfil;
	
	@Column(name = "TX_PERFIL")
	private String txPerfil;
	
	@Column(name = "ST_ACTIVO")
	private Long stActivo;

	@Column(name = "ID_USR_CREACION")
	private Long idUserCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long idUserModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date FHMODIFICACION;

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Long getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public String getNbPerfil() {
		return nbPerfil;
	}

	public void setNbPerfil(String nbPerfil) {
		this.nbPerfil = nbPerfil;
	}

	public String getTxPerfil() {
		return txPerfil;
	}

	public void setTxPerfil(String txPerfil) {
		this.txPerfil = txPerfil;
	}

	public Long getStActivo() {
		return stActivo;
	}

	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUserModifica() {
		return idUserModifica;
	}

	public void setIdUserModifica(Long idUserModifica) {
		this.idUserModifica = idUserModifica;
	}

//	public Date getFhModificacion() {
//		return fhModificacion;
//	}
//
//	public void setFhModificacion(Date fhModificacion) {
//		this.fhModificacion = fhModificacion;
//	}
	
	

}
