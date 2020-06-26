package mx.com.teclo.svi.negocio.enumerable;

import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;

public enum EnumTipoBusqReporteRV {
	PT(1L, "Punto Tactico"), CSV(2L, "Archivo CSV"), REGISTRO(3L, "Registro Expediente");
	
	private Long id;
	private String nombre;
	
	private  EnumTipoBusqReporteRV(Long id, String nombre){
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
