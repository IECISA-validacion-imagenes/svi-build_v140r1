package mx.com.teclo.svideskwsw.persistencia.hibernate.dto.catVehiculos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCI017C_PT_MARCAS")
public class PtMarcasDTO implements Serializable{
private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PT_MARCA")
    private Long idPtMarca;
    
    @Basic(optional = false)
    @Column(name = "CD_PT_MARCA")
    private String cdPtMarca;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtMarca", fetch = FetchType.LAZY)
    private List<PtSubmarcasDTO> tci018cPtSubmarcaList;

	/**
	 * @return the idPtMarca
	 */
	public Long getIdPtMarca() {
		return idPtMarca;
	}

	/**
	 * @param idPtMarca the idPtMarca to set
	 */
	public void setIdPtMarca(Long idPtMarca) {
		this.idPtMarca = idPtMarca;
	}

	/**
	 * @return the cdPtMarca
	 */
	public String getCdPtMarca() {
		return cdPtMarca;
	}

	/**
	 * @param cdPtMarca the cdPtMarca to set
	 */
	public void setCdPtMarca(String cdPtMarca) {
		this.cdPtMarca = cdPtMarca;
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
	 * @return the tci018cPtSubmarcaList
	 */
	public List<PtSubmarcasDTO> getTci018cPtSubmarcaList() {
		return tci018cPtSubmarcaList;
	}

	/**
	 * @param tci018cPtSubmarcaList the tci018cPtSubmarcaList to set
	 */
	public void setTci018cPtSubmarcaList(List<PtSubmarcasDTO> tci018cPtSubmarcaList) {
		this.tci018cPtSubmarcaList = tci018cPtSubmarcaList;
	}
}

