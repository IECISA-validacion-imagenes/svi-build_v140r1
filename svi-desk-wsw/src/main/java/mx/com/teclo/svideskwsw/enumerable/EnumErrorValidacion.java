package mx.com.teclo.svideskwsw.enumerable;

public enum EnumErrorValidacion {

	NAME(1L, "El nombre esta incoscistente"), 
	NOT_FOUND_FOLDER(2L, "No se encontro su carpeta correspondiente"),
	EMPTY_FOLDER(3L, "La carpeta esta vacia"),
	RELATION(4L, "El archivo no cuenta con X"),
	FILES_CSV(5L, "Inconsistencias en los archivos CSV"),
	IMAGES(6L, "Inconsistencias en las carpetas de imagenes");
	
	private long idError;
	private String mensajeError;
	
	private EnumErrorValidacion(long idError, String mensajeError){
		this.idError = idError;
		this.mensajeError = mensajeError;
	}

	public long getIdError() {
		return idError;
	}

	public void setIdError(long idError) {
		this.idError = idError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
