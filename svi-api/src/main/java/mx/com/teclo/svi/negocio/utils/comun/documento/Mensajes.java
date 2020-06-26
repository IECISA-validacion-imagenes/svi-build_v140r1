package mx.com.teclo.svi.negocio.utils.comun.documento;

public enum Mensajes {

	TOTAL_REGISTROS("Total de Registros:  "), 
	TITULO_PERIODO("Reporte del periodo:  "), 
	TITULO_GENERICO("Reporte General del: "), 
	PREPOSICION_PERIODO(" al ");

	private String mensaje;

	private Mensajes(String mensaje) {
		this.mensaje = mensaje;

	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	static Mensajes getArchivoTipo(String x) {
		for (Mensajes currentType : Mensajes.values()) {
			if (x.equals(currentType.getMensaje())) {
				return currentType;
			}
		}
		return null;
	}
}
