package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;

import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;

public class DependenciasSelectVO {

	private String cdParametroPadre;
	private String cdParametroHijo;
	private ColumnasVO columnaPadre;
	private ColumnasVO columnaHijo;
	private Long idPadreDependency;
	private Long idParametroRegistroTabla;

	public DependenciasSelectVO() {
	}

	public DependenciasSelectVO(String cdParametroPadre, String cdParametroHijo, ColumnasVO columnaPadre,
			ColumnasVO columnaHijo, Long idPadreDependency, Long idParametroRegistroTabla) {
		this.cdParametroPadre = cdParametroPadre;
		this.cdParametroHijo = cdParametroHijo;
		this.columnaPadre = columnaPadre;
		this.columnaHijo = columnaHijo;
		this.idPadreDependency = idPadreDependency;
		this.idParametroRegistroTabla = idParametroRegistroTabla;
	}

	public String getCdParametroPadre() {
		return cdParametroPadre;
	}

	public void setCdParametroPadre(String cdParametroPadre) {
		this.cdParametroPadre = cdParametroPadre;
	}

	public String getCdParametroHijo() {
		return cdParametroHijo;
	}

	public void setCdParametroHijo(String cdParametroHijo) {
		this.cdParametroHijo = cdParametroHijo;
	}

	public ColumnasVO getColumnaPadre() {
		return columnaPadre;
	}

	public void setColumnaPadre(ColumnasVO columnaPadre) {
		this.columnaPadre = columnaPadre;
	}

	public ColumnasVO getColumnaHijo() {
		return columnaHijo;
	}

	public void setColumnaHijo(ColumnasVO columnaHijo) {
		this.columnaHijo = columnaHijo;
	}

	public Long getIdPadreDependency() {
		return idPadreDependency;
	}

	public void setIdPadreDependency(Long idPadreDependency) {
		this.idPadreDependency = idPadreDependency;
	}

	public Long getIdParametroRegistroTabla() {
		return idParametroRegistroTabla;
	}

	public void setIdParametroRegistroTabla(Long idParametroRegistroTabla) {
		this.idParametroRegistroTabla = idParametroRegistroTabla;
	}

}
