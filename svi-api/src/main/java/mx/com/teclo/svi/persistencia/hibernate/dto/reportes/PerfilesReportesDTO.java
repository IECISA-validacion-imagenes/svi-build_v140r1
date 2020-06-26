package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAQ005D_AR_PERFILES_REPORTES")
public class PerfilesReportesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SQAQ005D", sequenceName="SQAQ005D_AR_PERFIL_REP", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ005D")
	@Column(name = "ID_PERFIL_REPORTE", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idPerfilReporte;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID_PERFIL", nullable = false)
//	private PerfilDTO perfil;
	
	@Column(name = "ID_PERFIL", nullable = false)
	private Long perfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_REPORTE", nullable = false)
	private ReportesDTO reporte;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	/**
	 * @return the idPerfilReporte
	 */
	public Long getIdPerfilReporte() {
		return idPerfilReporte;
	}

	/**
	 * @param idPerfilReporte the idPerfilReporte to set
	 */
	public void setIdPerfilReporte(Long idPerfilReporte) {
		this.idPerfilReporte = idPerfilReporte;
	}

	/**
	 * @return the perfil
	 */
	public Long getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return the reporte
	 */
	public ReportesDTO getReporte() {
		return reporte;
	}

	/**
	 * @param reporte the reporte to set
	 */
	public void setReporte(ReportesDTO reporte) {
		this.reporte = reporte;
	}

	/**
	 * @return the stActivo
	 */
	public Integer getStActivo() {
		return stActivo;
	}

	/**
	 * @param stActivo the stActivo to set
	 */
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	/**
	 * @return the idUsrCreacion
	 */
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	/**
	 * @param idUsrCreacion the idUsrCreacion to set
	 */
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
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



	
}
