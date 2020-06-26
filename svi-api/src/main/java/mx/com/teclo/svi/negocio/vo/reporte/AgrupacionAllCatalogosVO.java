package mx.com.teclo.svi.negocio.vo.reporte;


import java.io.Serializable;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.CaracteresVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.TipoOrdenamientoVO;

/**
 * @author Jorge Luis
 * */

public class AgrupacionAllCatalogosVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5534762048054666688L;

	private List<AgrupacionHojasVO> agrupacionHojasVO;
	private List<TipoTituloVO> tipoTituloVO;
	private List<TipoReporteVO> tipoReporteVO;
	private List<FormatoDescargaVO> formatoDescargaVO;
	private List<TipoParametroVO> tipoParametroVO;
	private List<TipoParamCompVO> tipoParamCompVO;
	private List<ComponentesVO> componentesVO;
	private List<PropiedadesVO> propiedadesVO;
	private List<PropiedadesCompVO> propiedadesCompVO;
	private List<TablasVO> tablasVO;
	private List<ColumnasVO> columnasVO;
	private List<TipoOperadorVO> tipoOperadorVO;
	private List<TipoOrdenamientoVO> tipoOrdenamientoVO;
	private List<CaracteresVO> caracteresVO;

	public List<AgrupacionHojasVO> getAgrupacionHojasVO() {
		return agrupacionHojasVO;
	}

	public void setAgrupacionHojasVO(List<AgrupacionHojasVO> agrupacionHojasVO) {
		this.agrupacionHojasVO = agrupacionHojasVO;
	}

	public List<TipoTituloVO> getTipoTituloVO() {
		return tipoTituloVO;
	}

	public void setTipoTituloVO(List<TipoTituloVO> tipoTituloVO) {
		this.tipoTituloVO = tipoTituloVO;
	}

	public List<TipoReporteVO> getTipoReporteVO() {
		return tipoReporteVO;
	}

	public void setTipoReporteVO(List<TipoReporteVO> tipoReporteVO) {
		this.tipoReporteVO = tipoReporteVO;
	}

	public List<FormatoDescargaVO> getFormatoDescargaVO() {
		return formatoDescargaVO;
	}

	public void setFormatoDescargaVO(List<FormatoDescargaVO> formatoDescargaVO) {
		this.formatoDescargaVO = formatoDescargaVO;
	}

	public List<TipoParametroVO> getTipoParametroVO() {
		return tipoParametroVO;
	}

	public void setTipoParametroVO(List<TipoParametroVO> tipoParametroVO) {
		this.tipoParametroVO = tipoParametroVO;
	}

	public List<TipoParamCompVO> getTipoParamCompVO() {
		return tipoParamCompVO;
	}

	public void setTipoParamCompVO(List<TipoParamCompVO> tipoParamCompVO) {
		this.tipoParamCompVO = tipoParamCompVO;
	}

	public List<ComponentesVO> getComponentesVO() {
		return componentesVO;
	}

	public void setComponentesVO(List<ComponentesVO> componentesVO) {
		this.componentesVO = componentesVO;
	}

	public List<PropiedadesVO> getPropiedadesVO() {
		return propiedadesVO;
	}

	public void setPropiedadesVO(List<PropiedadesVO> propiedadesVO) {
		this.propiedadesVO = propiedadesVO;
	}

	public List<PropiedadesCompVO> getPropiedadesCompVO() {
		return propiedadesCompVO;
	}

	public void setPropiedadesCompVO(List<PropiedadesCompVO> propiedadesCompVO) {
		this.propiedadesCompVO = propiedadesCompVO;
	}

	public List<TablasVO> getTablasVO() {
		return tablasVO;
	}

	public void setTablasVO(List<TablasVO> tablasVO) {
		this.tablasVO = tablasVO;
	}

	public List<ColumnasVO> getColumnasVO() {
		return columnasVO;
	}

	public void setColumnasVO(List<ColumnasVO> columnasVO) {
		this.columnasVO = columnasVO;
	}

	public List<TipoOperadorVO> getTipoOperadorVO() {
		return tipoOperadorVO;
	}

	public void setTipoOperadorVO(List<TipoOperadorVO> tipoOperadorVO) {
		this.tipoOperadorVO = tipoOperadorVO;
	}

	public List<TipoOrdenamientoVO> getTipoOrdenamientoVO() {
		return tipoOrdenamientoVO;
	}

	public void setTipoOrdenamientoVO(List<TipoOrdenamientoVO> tipoOrdenamientoVO) {
		this.tipoOrdenamientoVO = tipoOrdenamientoVO;
	}

	public List<CaracteresVO> getCaracteresVO() {
		return caracteresVO;
	}

	public void setCaracteresVO(List<CaracteresVO> caracteresVO) {
		this.caracteresVO = caracteresVO;
	}

}
