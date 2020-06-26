package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

import java.util.List;

public class PuntoTacticoVO {
	
	private boolean isNameValid;
	private String name;
	private List<ImagenesVO> imagenes;
	private List<SiluetasVO> siluetas;
	private List<ArchivoCSVVO> archivos;
	
	public boolean isNameValid() {
		return isNameValid;
	}

	public void setNameValid(boolean isNameValid) {
		this.isNameValid = isNameValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ImagenesVO> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenesVO> imagenes) {
		this.imagenes = imagenes;
	}

	public List<SiluetasVO> getSiluetas() {
		return siluetas;
	}

	public void setSiluetas(List<SiluetasVO> siluetas) {
		this.siluetas = siluetas;
	}

	public List<ArchivoCSVVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoCSVVO> archivos) {
		this.archivos = archivos;
	}
	
	public boolean isImagenesValid(){
		boolean somethingWrong = true;
		
		if(imagenes != null){
			if(!imagenes.isEmpty()){
				for(ImagenesVO iVO: imagenes){
					if(!iVO.isNameValid()){
						somethingWrong = false;
						break;
					}
				}
			}else{
				somethingWrong = false;
			}
		}else{
			somethingWrong = false;
		}
		return somethingWrong;
	}
	
	public boolean isCSVValid(){
		boolean somethingWrong = true;
		
		if(archivos != null){
			if(!archivos.isEmpty()){
				for(ArchivoCSVVO aCSVVO: archivos){
					if(!aCSVVO.isNameValid()){
						somethingWrong = false;
						break;
					}
				}
			}else{
				somethingWrong = false;
			}
		}else{
			somethingWrong = false;
		}
		return somethingWrong;
	}

	public boolean isPuntoTactivoValid(){
		boolean somethingWrong = true;
		
		somethingWrong = isCSVValid();
		
		somethingWrong = isImagenesValid();
		
		if(siluetas != null){
			for(SiluetasVO sVO: siluetas){
				if(!sVO.isNameValid()){
					somethingWrong = false;
					break;
				}
			}
		}
		
		return somethingWrong;
	}
	
	@Override
	public String toString(){
		return "[isNameValid: "+isNameValid+", name: "+name+"]";
	}
}
