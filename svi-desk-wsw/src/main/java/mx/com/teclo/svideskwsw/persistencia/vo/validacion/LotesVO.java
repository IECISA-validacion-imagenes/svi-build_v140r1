package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

import java.util.List;

public class LotesVO {

	private Long idUsuario;
	private String nbEntregable;
	private List<PuntoTacticoVO> puntosTacticos;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNbEntregable() {
		return nbEntregable;
	}
	public void setNbEntregable(String nbEntregable) {
		this.nbEntregable = nbEntregable;
	}
	public List<PuntoTacticoVO> getPuntosTacticos() {
		return puntosTacticos;
	}
	public void setPuntosTacticos(List<PuntoTacticoVO> puntosTacticos) {
		this.puntosTacticos = puntosTacticos;
	}
}
