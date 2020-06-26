package mx.com.teclo.svi.negocio.vo.reporte.reportesalta;
import java.util.List;

public class SqlTestResultaVO {

	private String mensaje;
	private Object causa;
	private Boolean stResult;
	private List<Object> columnsMetadataTest;// retornar el metadata cuando se
												// ejecute la consulta de prueba

	public SqlTestResultaVO(String mensaje, Object causa, Boolean stResult, List<Object> metadata) {
		this.mensaje = mensaje;
		this.causa = causa;
		this.stResult = stResult;
		this.columnsMetadataTest = metadata;
	}

	public SqlTestResultaVO() {
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getCausa() {
		return causa;
	}

	public void setCausa(Object causa) {
		this.causa = causa;
	}

	public Boolean isStResult() {
		return stResult;
	}

	public void setStResult(Boolean stResult) {
		this.stResult = stResult;
	}

	public List<Object> getColumnsMetadataTest() {
		return columnsMetadataTest;
	}

	public void setColumnsMetadataTest(List<Object> columnsMetadataTest) {
		this.columnsMetadataTest = columnsMetadataTest;
	}

	@Override
	public String toString() {
		return "SqlTestResultaVO [mensaje:" + mensaje + ", causa:" + causa + ", stResult:" + stResult + "]";
	}

}
