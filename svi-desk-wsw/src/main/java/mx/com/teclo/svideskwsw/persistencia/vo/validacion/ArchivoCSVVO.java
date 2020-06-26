package mx.com.teclo.svideskwsw.persistencia.vo.validacion;

public class ArchivoCSVVO {

	private boolean isNameValid;
	private String name;
	
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
		return "[isNameValid: "+isNameValid +", name: "+name+"]";
	}
}
