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
@Table(name = "TAQ016D_AR_PARAMETROS_COLUMN")
public class ParametrosColumnDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6125797297853177244L;

	@Id
	@SequenceGenerator(name = "SQAQ016D", sequenceName="SQAQ016D_AR_PARAM_COLUM", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAQ016D")
	@Column(name = "ID_PARAMETRO_COLUMN", unique = true, nullable = false, precision = 11, scale = 0)
	private Long idParamtetroColumn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COLUMNA", nullable = false)
	private ColumnasDTO columna;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PARAMETRO_TABLA", nullable = false)
	private ParametrosTablasDTO parametroTabla;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_OPERADOR", nullable = true)
	private TipoOperadorDTO tipoOperador;

	@Column(name = "ST_IS_KEY", nullable = false, precision = 1, scale = 0)
	private Integer stIsKey;

	@Column(name = "ST_IS_DESC", nullable = false, precision = 1, scale = 0)
	private Integer stIsDesc;

	@Column(name = "ST_IS_WHERE", nullable = false, precision = 1, scale = 0)
	private Integer stIsWhere;

	@Column(name = "ST_IS_ORDER", nullable = false, precision = 1, scale = 0)
	private Integer stIsOrder;

	@Column(name = "TX_VALOR_WHERE", nullable = false, length = 50)
	private String txValorWhere;

	@Column(name = "ST_ACTIVO", nullable = false, precision = 1, scale = 0)
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION", nullable = false, length = 7)
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICACION", nullable = false, precision = 11, scale = 0)
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION", nullable = false, length = 7)
	private Date fhModificacion;

	@Column(name = "TP_ORDER", nullable = true, length = 20)
	private String tpOrder;

	@Column(name = "ID_PARAM_TAB_DEPENDECY", nullable = true, precision = 11, scale = 0)
	private Long idParamTabDependency;

	@Column(name = "NU_ORDEN", nullable = false, precision = 2, scale = 0)
	private Long nuOrden;

	public Long getIdParamtetroColumn() {
		return idParamtetroColumn;
	}

	public void setIdParamtetroColumn(Long idParamtetroColumn) {
		this.idParamtetroColumn = idParamtetroColumn;
	}

	public ColumnasDTO getColumna() {
		return columna;
	}

	public void setColumna(ColumnasDTO columna) {
		this.columna = columna;
	}

	public ParametrosTablasDTO getParametroTabla() {
		return parametroTabla;
	}

	public void setParametroTabla(ParametrosTablasDTO parametroTabla) {
		this.parametroTabla = parametroTabla;
	}

	public Integer getStIsKey() {
		return stIsKey;
	}

	public void setStIsKey(Integer stIsKey) {
		this.stIsKey = stIsKey;
	}

	public Integer getStIsDesc() {
		return stIsDesc;
	}

	public void setStIsDesc(Integer stIsDesc) {
		this.stIsDesc = stIsDesc;
	}

	public Integer getStIsWhere() {
		return stIsWhere;
	}

	public void setStIsWhere(Integer stIsWhere) {
		this.stIsWhere = stIsWhere;
	}

	public String getTxValorWhere() {
		return txValorWhere;
	}

	public void setTxValorWhere(String txValorWhere) {
		this.txValorWhere = txValorWhere;
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

	public TipoOperadorDTO getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(TipoOperadorDTO tipoOperador) {
		this.tipoOperador = tipoOperador;
	}

	public Integer getStIsOrder() {
		return stIsOrder;
	}

	public void setStIsOrder(Integer stIsOrder) {
		this.stIsOrder = stIsOrder;
	}

	public String getTpOrder() {
		return tpOrder;
	}

	public void setTpOrder(String tpOrder) {
		this.tpOrder = tpOrder;
	}

	public Long getIdParamTabDependency() {
		return idParamTabDependency;
	}

	public void setIdParamTabDependency(Long idParamTabDependency) {
		this.idParamTabDependency = idParamTabDependency;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

}
