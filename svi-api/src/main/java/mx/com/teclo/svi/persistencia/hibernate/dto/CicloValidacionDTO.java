package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCI028C_PT_CICLO_VALIDACION")
public class CicloValidacionDTO implements Serializable{
	
	private static final long serialVersionUID = 1073143193117067611L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_CICLO_VALIDACION")
    private Long idCicloValidacion;
	
	@Basic(optional = false)
    @Column(name = "CD_CICLO_VALIDACION")
    private String cdCicloValidacion;
	
	@Basic(optional = false)
    @Column(name = "TXT_CICLO_VALIDACION")
    private String txtCicloValidacion;
	
	@Basic(optional = false)
    @Column(name = "NU_CICLO_VALIDACION")
    private Long nuCicloValidacion;
	
	@Basic(optional = false)
    @Column(name = "ST_VIGENTE")
    private Long stVigente;

	
	@Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private Long stActivo;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    private Date fechaCreacion ;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private Long idUserCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    private Date fechaModificacion ;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private Long idUserModifica ;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCicloValidacion")
    private Collection<DetalleRevalidaDTO> detReasignCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCicloValidacion")
    private Collection<DetalleValidadorDTO> detValidacionesCollection;
    
    public Long getIdCicloValidacion() {
		return idCicloValidacion;
	}

	public void setIdCicloValidacion(Long idCicloValidacion) {
		this.idCicloValidacion = idCicloValidacion;
	}

	public String getCdCicloValidacion() {
		return cdCicloValidacion;
	}

	public void setCdCicloValidacion(String cdCicloValidacion) {
		this.cdCicloValidacion = cdCicloValidacion;
	}

	public String getTxtCicloValidacion() {
		return txtCicloValidacion;
	}

	public void setTxtCicloValidacion(String txtCicloValidacion) {
		this.txtCicloValidacion = txtCicloValidacion;
	}

	public Long getNuCicloValidacion() {
		return nuCicloValidacion;
	}

	public void setNuCicloValidacion(Long nuCicloValidacion) {
		this.nuCicloValidacion = nuCicloValidacion;
	}

	public Long getStVigente() {
		return stVigente;
	}

	public void setStVigente(Long stVigente) {
		this.stVigente = stVigente;
	}

	public Long getStActivo() {
		return stActivo;
	}

	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getIdUserModifica() {
		return idUserModifica;
	}

	public void setIdUserModifica(Long idUserModifica) {
		this.idUserModifica = idUserModifica;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Collection<DetalleRevalidaDTO> getDetReasignCollection() {
		return detReasignCollection;
	}

	public void setDetReasignCollection(Collection<DetalleRevalidaDTO> detReasignCollection) {
		this.detReasignCollection = detReasignCollection;
	}

	public Collection<DetalleValidadorDTO> getDetValidacionesCollection() {
		return detValidacionesCollection;
	}

	public void setDetValidacionesCollection(Collection<DetalleValidadorDTO> detValidacionesCollection) {
		this.detValidacionesCollection = detValidacionesCollection;
	}
 

}
