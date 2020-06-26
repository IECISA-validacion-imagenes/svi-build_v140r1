package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;
import mx.com.teclo.svi.negocio.vo.reporte.TablasVO;

public class ParametroTipoCatConfigVO {

	private TablasVO tablaActual;
	private List<ColumnasVO> identificador;
	private List<ColumnasVO> descripciones;
	private List<ColumnasVO> restricciones;
	private List<ColumnasVO> ordenes;

	public TablasVO getTablaActual() {
		return tablaActual;
	}

	public void setTablaActual(TablasVO tablaActual) {
		this.tablaActual = tablaActual;
	}

	public List<ColumnasVO> getDescripciones() {
		return descripciones;
	}

	public void setDescripciones(List<ColumnasVO> descripciones) {
		this.descripciones = descripciones;
	}

	public List<ColumnasVO> getIdentificador() {
		return identificador;
	}

	public void setIdentificador(List<ColumnasVO> identificador) {
		this.identificador = identificador;
	}

	public List<ColumnasVO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<ColumnasVO> ordenes) {
		this.ordenes = ordenes;
	}

	public List<ColumnasVO> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(List<ColumnasVO> restricciones) {
		this.restricciones = restricciones;
	}

}
