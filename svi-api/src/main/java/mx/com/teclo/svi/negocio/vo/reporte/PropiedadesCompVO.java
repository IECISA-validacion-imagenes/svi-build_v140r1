package mx.com.teclo.svi.negocio.vo.reporte;

public class PropiedadesCompVO {

	private long idPropiedadComp;
	private PropiedadesVO propiedad;
	private ComponentesVO componente;
	private long idPropiedad;
	private long idComponente;

	public PropiedadesCompVO(long idPropiedadComp, long idPropiedad, long idComponente) {
		this.idPropiedadComp = idPropiedadComp;
		this.idPropiedad = idPropiedad;
		this.idComponente = idComponente;
	}

	public PropiedadesCompVO() {
	}

	public Long getIdPropiedadComp() {
		return idPropiedadComp;
	}

	public void setIdPropiedadComp(Long idPropiedadComp) {
		this.idPropiedadComp = idPropiedadComp;
	}

	public PropiedadesVO getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadesVO propiedad) {
		this.propiedad = propiedad;
	}

	public ComponentesVO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesVO componente) {
		this.componente = componente;
	}

	public long getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(long idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public long getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(long idComponente) {
		this.idComponente = idComponente;
	}

}
