package mx.com.teclo.svi.persistencia.vo.asignacionarchivos;

import java.util.Date;

public class ArchivoCsvDetallesVO {

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
	private int totAsignacionInicial;
	
	
	private Long idRegistroCsv;
	private Long nodoVpn;	
	private Long nuCarril;
	private String cdPlacaDelantera;
	private String txtPlacaDelantera;
	private String cdEntidadDelantera;
	private String cdPlacaTrasera;
	private String txtPlacaTrasera;
	private String cdEntidadTrasera;
	private String nbImagenPlacaDelantera;
	private String nbImagenPlacaTrasera;
	private String nbImagenConductor;
	private String nbImagenAmbiental;
	private String cdPerfil;
	private String nbImagenPerfil;
	private Long idPtClasifExpe;
	private Long idCatValidacion;
	
	public int getTotAsignacionInicial() {
		return totAsignacionInicial;
	}
	public void setTotAsignacionInicial(int totAsignacionInicial) {
		this.totAsignacionInicial = totAsignacionInicial;
	}
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
	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}
	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}
	public Long getNodoVpn() {
		return nodoVpn;
	}
	public void setNodoVpn(Long nodoVpn) {
		this.nodoVpn = nodoVpn;
	}
	public Long getNuCarril() {
		return nuCarril;
	}
	public void setNuCarril(Long nuCarril) {
		this.nuCarril = nuCarril;
	}
	public String getCdPlacaDelantera() {
		return cdPlacaDelantera;
	}
	public void setCdPlacaDelantera(String cdPlacaDelantera) {
		this.cdPlacaDelantera = cdPlacaDelantera;
	}
	public String getTxtPlacaDelantera() {
		return txtPlacaDelantera;
	}
	public void setTxtPlacaDelantera(String txtPlacaDelantera) {
		this.txtPlacaDelantera = txtPlacaDelantera;
	}
	public String getCdEntidadDelantera() {
		return cdEntidadDelantera;
	}
	public void setCdEntidadDelantera(String cdEntidadDelantera) {
		this.cdEntidadDelantera = cdEntidadDelantera;
	}
	public String getCdPlacaTrasera() {
		return cdPlacaTrasera;
	}
	public void setCdPlacaTrasera(String cdPlacaTrasera) {
		this.cdPlacaTrasera = cdPlacaTrasera;
	}
	public String getTxtPlacaTrasera() {
		return txtPlacaTrasera;
	}
	public void setTxtPlacaTrasera(String txtPlacaTrasera) {
		this.txtPlacaTrasera = txtPlacaTrasera;
	}
	public String getCdEntidadTrasera() {
		return cdEntidadTrasera;
	}
	public void setCdEntidadTrasera(String cdEntidadTrasera) {
		this.cdEntidadTrasera = cdEntidadTrasera;
	}
	public String getNbImagenPlacaDelantera() {
		return nbImagenPlacaDelantera;
	}
	public void setNbImagenPlacaDelantera(String nbImagenPlacaDelantera) {
		this.nbImagenPlacaDelantera = nbImagenPlacaDelantera;
	}
	public String getNbImagenPlacaTrasera() {
		return nbImagenPlacaTrasera;
	}
	public void setNbImagenPlacaTrasera(String nbImagenPlacaTrasera) {
		this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
	}
	public String getNbImagenConductor() {
		return nbImagenConductor;
	}
	public void setNbImagenConductor(String nbImagenConductor) {
		this.nbImagenConductor = nbImagenConductor;
	}
	public String getNbImagenAmbiental() {
		return nbImagenAmbiental;
	}
	public void setNbImagenAmbiental(String nbImagenAmbiental) {
		this.nbImagenAmbiental = nbImagenAmbiental;
	}
	public String getCdPerfil() {
		return cdPerfil;
	}
	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}
	public String getNbImagenPerfil() {
		return nbImagenPerfil;
	}
	public void setNbImagenPerfil(String nbImagenPerfil) {
		this.nbImagenPerfil = nbImagenPerfil;
	}
	public Long getIdPtClasifExpe() {
		return idPtClasifExpe;
	}
	public void setIdPtClasifExpe(Long idPtClasifExpe) {
		this.idPtClasifExpe = idPtClasifExpe;
	}
	public Long getIdCatValidacion() {
		return idCatValidacion;
	}
	public void setIdCatValidacion(Long idCatValidacion) {
		this.idCatValidacion = idCatValidacion;
	}
	
	
	
	
	
	

}
