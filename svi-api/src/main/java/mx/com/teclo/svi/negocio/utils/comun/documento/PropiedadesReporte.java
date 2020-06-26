package mx.com.teclo.svi.negocio.utils.comun.documento;


import java.io.InputStream;
import java.util.List;

public class PropiedadesReporte {

	private String nbRutaArchivo;
	private String txExtension;
	private String nbReporte;
	private String txTituloExcel;
	private String fhInicio;
	private String fhFin;
	private InputStream imgReporte;
	private int nuHojas;
	private List<String> subtitulos;
	private String nbHoja;
	private String nbCompania;
	private String txColumnaPaginacion;
	private boolean stMultiplehoja;

	public String getNbCompania() {
		return nbCompania;
	}

	public void setNbCompania(String nbCompania) {
		this.nbCompania = nbCompania;
	}

	public String getNbRutaArchivo() {
		return nbRutaArchivo;
	}

	public void setNbRutaArchivo(String nbRutaArchivo) {
		this.nbRutaArchivo = nbRutaArchivo;
	}

	public String getTxExtension() {
		return txExtension;
	}

	public void setTxExtension(String txExtension) {
		this.txExtension = txExtension;
	}

	public String getNbReporte() {
		return nbReporte;
	}

	public void setNbReporte(String nbReporte) {
		this.nbReporte = nbReporte;
	}

	public String getTxTituloExcel() {
		return txTituloExcel;
	}

	public void setTxTituloExcel(String txTituloExcel) {
		this.txTituloExcel = txTituloExcel;
	}

	public String getFhInicio() {
		return fhInicio;
	}

	public void setFhInicio(String fhInicio) {
		this.fhInicio = fhInicio;
	}

	public String getFhFin() {
		return fhFin;
	}

	public void setFhFin(String fhFin) {
		this.fhFin = fhFin;
	}

	public InputStream getImgReporte() {
		return imgReporte;
	}

	public void setImgReporte(InputStream imgReporte) {
		this.imgReporte = imgReporte;
	}

	public int getNuHojas() {
		return nuHojas;
	}

	public void setNuHojas(int nuHojas) {
		this.nuHojas = nuHojas;
	}

	public List<String> getSubtitulos() {
		return subtitulos;
	}

	public void setSubtitulos(List<String> subtitulos) {
		this.subtitulos = subtitulos;
	}

	public String getNbHoja() {
		return nbHoja;
	}

	public void setNbHoja(String nbHoja) {
		this.nbHoja = nbHoja;
	}

	public String getTxColumnaPaginacion() {
		return txColumnaPaginacion;
	}

	public void setTxColumnaPaginacion(String txColumnaPaginacion) {
		this.txColumnaPaginacion = txColumnaPaginacion;
	}

	public boolean isStMultiplehoja() {
		return stMultiplehoja;
	}

	public void setStMultiplehoja(boolean stMultiplehoja) {
		this.stMultiplehoja = stMultiplehoja;
	}

	
	
}
