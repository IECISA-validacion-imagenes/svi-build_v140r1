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
@Table(name = "TAQ039C_AR_TIPO_OPERADOR")
public class TipoOperadorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7825194445332631774L;

	@Id
	@SequenceGenerator(name = "SQAQ039C", sequenceName="SQAQ039C_AR_TIP_OPERA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ039C")
	@Column(name = "ID_TIPO_OPERADOR", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idTipoOperador;

	@Column(name = "CD_OPERADOR", nullable = false, length = 15)
	private String cdOperador;

	@Column(name = "TX_OPERADOR", nullable = false, length = 100)
	private String txOperador;

	@Column(name = "NB_OPERADOR", nullable = false, length = 20)
	private String nbOperador;

	@Column(name = "TX_VALOR", nullable = false, length = 50)
	private String txValor;

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

	@Column(name = "ST_VALOR_REQUERIDO", nullable = false, precision = 1, scale = 0)
	private Long stValorRequerido;

	public Long getIdTipoOperador() {
		return idTipoOperador;
	}

	public void setIdTipoOperador(Long idTipoOperador) {
		this.idTipoOperador = idTipoOperador;
	}

	public String getCdOperador() {
		return cdOperador;
	}

	public void setCdOperador(String cdOperador) {
		this.cdOperador = cdOperador;
	}

	public String getTxOperador() {
		return txOperador;
	}

	public void setTxOperador(String txOperador) {
		this.txOperador = txOperador;
	}

	public String getNbOperador() {
		return nbOperador;
	}

	public void setNbOperador(String nbOperador) {
		this.nbOperador = nbOperador;
	}

	public String getTxValor() {
		return txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
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

	public Long getStValorRequerido() {
		return stValorRequerido;
	}

	public void setStValorRequerido(Long stValorRequerido) {
		this.stValorRequerido = stValorRequerido;
	}

}
