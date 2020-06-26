package mx.com.teclo.svi.persistencia.hibernate.dto.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ017C_AR_FORMATO_DESCARGAS")
public class FormatoDescargaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8875646058841713145L;

	@Id
	@SequenceGenerator(name = "SQAQ017C", sequenceName="SQAQ017C_AR_FORMAT_DESCAR", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ017C")
	@Column(name = "ID_FORMATO_DESCARGA", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idFormatoDescarga;

	@Column(name = "CD_FORMATO_DESCARGA", nullable = false, length = 10)
	private String cdFormato;

	@Column(name = "NB_FORMATO_DESCARGA", nullable = false, length = 50)
	private String nbFormato;

	@Column(name = "TX_FORMATO_DESCARGA", nullable = false, length = 300)
	private String txDescripcion;

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

	@Column(name = "CD_EXTENSION", nullable = false, length = 20)
	private String cdExtension;

	public Long getIdFormatoDescarga() {
		return idFormatoDescarga;
	}

	public void setIdFormatoDescarga(Long idFormatoDescarga) {
		this.idFormatoDescarga = idFormatoDescarga;
	}

	public String getCdFormato() {
		return cdFormato;
	}

	public void setCdFormato(String cdFormato) {
		this.cdFormato = cdFormato;
	}

	public String getNbFormato() {
		return nbFormato;
	}

	public void setNbFormato(String nbFormato) {
		this.nbFormato = nbFormato;
	}

	public String getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public String getCdExtension() {
		return cdExtension;
	}

	public void setCdExtension(String cdExtension) {
		this.cdExtension = cdExtension;
	}

}
