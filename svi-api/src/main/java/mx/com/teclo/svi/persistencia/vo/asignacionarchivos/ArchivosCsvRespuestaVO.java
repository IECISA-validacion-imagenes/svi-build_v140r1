package mx.com.teclo.svi.persistencia.vo.asignacionarchivos;

import java.util.Date;
import java.util.List;

public class ArchivosCsvRespuestaVO {

	private Long idArchivoCsv;
	private Long idPtLote;
	private String nbArchivoCsv;
	private Long nuRegistrosCsv;
	private String nbCarpetaImg;
	private String txCarpetaImg;
	private Long nuImagenes;
	private String nbCarpetaSil;
	private String txCarpetaSil;
	private Long nuSiluetas;
	private short stValidacion;
	private short stActivo;
	private String txRutaStorage;
	
	private List<ArchivoCsvDetallesVO> archivodetalle;

	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}

	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}

	public Long getIdPtLote() {
		return idPtLote;
	}

	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}

	public String getNbArchivoCsv() {
		return nbArchivoCsv;
	}

	public void setNbArchivoCsv(String nbArchivoCsv) {
		this.nbArchivoCsv = nbArchivoCsv;
	}

	public Long getNuRegistrosCsv() {
		return nuRegistrosCsv;
	}

	public void setNuRegistrosCsv(Long nuRegistrosCsv) {
		this.nuRegistrosCsv = nuRegistrosCsv;
	}

	public String getNbCarpetaImg() {
		return nbCarpetaImg;
	}

	public void setNbCarpetaImg(String nbCarpetaImg) {
		this.nbCarpetaImg = nbCarpetaImg;
	}

	public String getTxCarpetaImg() {
		return txCarpetaImg;
	}

	public void setTxCarpetaImg(String txCarpetaImg) {
		this.txCarpetaImg = txCarpetaImg;
	}

	public Long getNuImagenes() {
		return nuImagenes;
	}

	public void setNuImagenes(Long nuImagenes) {
		this.nuImagenes = nuImagenes;
	}

	public String getNbCarpetaSil() {
		return nbCarpetaSil;
	}

	public void setNbCarpetaSil(String nbCarpetaSil) {
		this.nbCarpetaSil = nbCarpetaSil;
	}

	public String getTxCarpetaSil() {
		return txCarpetaSil;
	}

	public void setTxCarpetaSil(String txCarpetaSil) {
		this.txCarpetaSil = txCarpetaSil;
	}

	public Long getNuSiluetas() {
		return nuSiluetas;
	}

	public void setNuSiluetas(Long nuSiluetas) {
		this.nuSiluetas = nuSiluetas;
	}

	public short getStValidacion() {
		return stValidacion;
	}

	public void setStValidacion(short stValidacion) {
		this.stValidacion = stValidacion;
	}

	public short getStActivo() {
		return stActivo;
	}

	public void setStActivo(short stActivo) {
		this.stActivo = stActivo;
	}

	public String getTxRutaStorage() {
		return txRutaStorage;
	}

	public void setTxRutaStorage(String txRutaStorage) {
		this.txRutaStorage = txRutaStorage;
	}

	public List<ArchivoCsvDetallesVO> getArchivodetalle() {
		return archivodetalle;
	}

	public void setArchivodetalle(List<ArchivoCsvDetallesVO> archivodetalle) {
		this.archivodetalle = archivodetalle;
	}
	
	
	
	
	
	  
}
