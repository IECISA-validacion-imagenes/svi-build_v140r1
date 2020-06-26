package mx.com.teclo.svi.negocio.vo.reporte;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ColumnasVO {

	private Long idColumna;
	@JsonIgnore
	private TablasVO tabla;
	private String nbAlias;
	private String nbReal;
	private Long idTabla;
	private Long nuOrden;
	private String tipoOrdenamiento;
	private String valor;
	private TipoOperadorVO tipoOperador;

	public ColumnasVO() {
	}

	public ColumnasVO(Long idColumna, String nbAlias, String nbReal, Long idTabla) {
		this.idColumna = idColumna;
		this.nbAlias = nbAlias;
		this.nbReal = nbReal;
		this.idTabla = idTabla;
	}

	public Long getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(Long idColumna) {
		this.idColumna = idColumna;
	}

	public TablasVO getTabla() {
		return tabla;
	}

	public void setTabla(TablasVO tabla) {
		this.tabla = tabla;
	}

	public String getNbAlias() {
		return nbAlias;
	}

	public void setNbAlias(String nbAlias) {
		this.nbAlias = nbAlias;
	}

	public String getNbReal() {
		return nbReal;
	}

	public void setNbReal(String nbReal) {
		this.nbReal = nbReal;
	}

	public Long getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(Long idTabla) {
		this.idTabla = idTabla;
	}

	public Long getNuOrden() {
		return nuOrden;
	}

	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}

	public String getTipoOrdenamiento() {
		return tipoOrdenamiento;
	}

	public void setTipoOrdenamiento(String tipoOrdenamiento) {
		this.tipoOrdenamiento = tipoOrdenamiento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoOperadorVO getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(TipoOperadorVO tipoOperador) {
		this.tipoOperador = tipoOperador;
	}

}
