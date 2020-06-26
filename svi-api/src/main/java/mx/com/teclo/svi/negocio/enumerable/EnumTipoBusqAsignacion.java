package mx.com.teclo.svi.negocio.enumerable;

import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;

public enum EnumTipoBusqAsignacion {
	TODO(1L, "Todos"), USUARIO(2L, "Usuario"), NOMBRE(3L, "Nombre del usuario"), APELLIDOPAT(4L, "Apellido Paterno") ,
	MOTIVO_SOLCLIENTE(1L, "Revalidación por solicitud del cliente"),
	MOTIVO_SUPERVISION(2L, "Revalidación por supervisión"),
	MOTIVO_CSV(1L, "Motivo para CSV"),
	MOTIVO_EXPEDIENTES(2L, "Motivo para Expedientes");;
	
	private Long id;
	private String nombre;
	
	private  EnumTipoBusqAsignacion(Long id, String nombre){
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
		EnumTipoBusqAsignacion[] cat =EnumTipoBusqAsignacion.values();
		List<CatalogoVO> lista = new ArrayList<CatalogoVO>();
		for(EnumTipoBusqAsignacion etbrVO: cat){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(etbrVO.getId());
			cVO.setNameCat(etbrVO.getNombre());
			lista.add(cVO);
		}
		return lista;
	}
}
