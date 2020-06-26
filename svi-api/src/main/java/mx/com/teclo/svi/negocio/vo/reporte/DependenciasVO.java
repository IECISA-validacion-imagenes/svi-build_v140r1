package mx.com.teclo.svi.negocio.vo.reporte;

public class DependenciasVO {

	private Object idParent;
	private Object cdParam;
	private Object nbColumnaFiltro;

	public DependenciasVO() {
	}

	public DependenciasVO(Object idParent, Object cdParam, Object nbColumnaFiltro) {
		this.idParent = idParent;
		this.cdParam = cdParam;
		this.nbColumnaFiltro = nbColumnaFiltro;
	}

	public Object getIdParent() {
		return idParent;
	}

	public void setIdParent(Object idParent) {
		this.idParent = idParent;
	}

	public Object getCdParam() {
		return cdParam;
	}

	public void setCdParam(Object cdParam) {
		this.cdParam = cdParam;
	}

	public Object getNbColumnaFiltro() {
		return nbColumnaFiltro;
	}

	public void setNbColumnaFiltro(Object nbColumnaFiltro) {
		this.nbColumnaFiltro = nbColumnaFiltro;
	}

	@Override
	public String toString() {
		return "DependenciasVO: [idParent:" + idParent + ", cdParam:" + cdParam + ", nbColumnaFiltro: "
				+ nbColumnaFiltro + "]";
	}

}
