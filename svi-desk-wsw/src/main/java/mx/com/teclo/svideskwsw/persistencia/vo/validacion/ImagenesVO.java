package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

public class ImagenesVO {

	private boolean doesExist;
	private boolean isNameValid;
	private String name;
	
	public boolean isDoesExist() {
		return doesExist;
	}
	public void setDoesExist(boolean doesExist) {
		this.doesExist = doesExist;
	}
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
	
	@Override
	public String toString(){
		return "[isNameValid: "+isNameValid +",doesExist: "+doesExist+", name: "+name+"]";
	}
}
