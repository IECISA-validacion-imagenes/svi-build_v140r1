package mx.com.teclo.svi.negocio.enumerable;

import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;

public enum EnumValidacion {
	Grupo5(5L, "5 - No se puede clasificar el expediente (Placa, entidad y perfil de vehículo)");
	
	private Long id;
	private String nombre;
	
	private  EnumValidacion(Long id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static List<CatalogoVO> getCatalog(){
		EnumTipoBusqReporteRV[] cat =EnumTipoBusqReporteRV.values();
		List<CatalogoVO> lista = new ArrayList<CatalogoVO>();
		for(EnumTipoBusqReporteRV etbrVO: cat){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(etbrVO.getId());
			cVO.setNameCat(etbrVO.getNombre());
			lista.add(cVO);
		}
		return lista;
	}
}
