package mx.com.teclo.svi.negocio.vo.reporte;

public class ParametrosColumnVO {

	private Long idParamtetroColumn;
	private ColumnasVO columna;
	private ParametrosTablasVO parametroTabla;
	private TipoOperadorVO tipoOperador;
	private Integer stIsKey;
	private Integer stIsDesc;
	private Integer stIsWhere;
	private Integer stIsOrder;
	private String txValorWhere;
	private String tpOrder;
	private Long idParamTabDependency;

	public Long getIdParamtetroColumn() {
		return idParamtetroColumn;
	}

	public void setIdParamtetroColumn(Long idParamtetroColumn) {
		this.idParamtetroColumn = idParamtetroColumn;
	}

	public ColumnasVO getColumna() {
		return columna;
	}

	public void setColumna(ColumnasVO columna) {
		this.columna = columna;
	}

	public ParametrosTablasVO getParametroTabla() {
		return parametroTabla;
	}

	public void setParametroTabla(ParametrosTablasVO parametroTabla) {
		this.parametroTabla = parametroTabla;
	}

	public TipoOperadorVO getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(TipoOperadorVO tipoOperador) {
		this.tipoOperador = tipoOperador;
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

	public Integer getStIsOrder() {
		return stIsOrder;
	}

	public void setStIsOrder(Integer stIsOrder) {
		this.stIsOrder = stIsOrder;
	}

	public String getTxValorWhere() {
		return txValorWhere;
	}

	public void setTxValorWhere(String txValorWhere) {
		this.txValorWhere = txValorWhere;
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

}
