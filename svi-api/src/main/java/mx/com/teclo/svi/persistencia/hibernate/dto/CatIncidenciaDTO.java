package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TCI020C_PT_INCIDENCIA")
public class CatIncidenciaDTO implements Serializable{
	
	private static final long serialVersionUID = 7078244648189729088L;
	
	@Id
	@Column(name="ID_INCIDENCIA")
	private Long idIncidencia;
	@Column(name="CD_INCIDENCIA")
	private String cdIncidencia;
	@Column(name="TX_INCIDENCIA")
	private String txIncidencia;
	@Column(name="ST_ACTIVO")
	private Boolean stActivo;
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	@Column(name="ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name="FH_MODIFICACION")
	private Date fhModificacion;
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;
	
	public Long getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getCdIncidencia() {
		return cdIncidencia;
	}
	public void setCdIncidencia(String cdIncidencia) {
		this.cdIncidencia = cdIncidencia;
	}
	public String getTxIncidencia() {
		return txIncidencia;
	}
	public void setTxIncidencia(String txIncidencia) {
		this.txIncidencia = txIncidencia;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
}
