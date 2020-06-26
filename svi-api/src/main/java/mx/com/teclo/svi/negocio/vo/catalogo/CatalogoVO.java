package mx.com.teclo.svi.negocio.vo.catalogo;

import java.math.BigDecimal;

public class CatalogoVO {

	private Long idCat;
	private String nameCat;
	private BigDecimal nuCantidad;
	
	public Long getIdCat() {
		return idCat;
	}
	public void setIdCat(Long idCat) {
		this.idCat = idCat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public BigDecimal getNuCantidad() {
		return nuCantidad;
	}
	public void setNuCantidad(BigDecimal nuCantidad) {
		this.nuCantidad = nuCantidad;
	}

	
}
