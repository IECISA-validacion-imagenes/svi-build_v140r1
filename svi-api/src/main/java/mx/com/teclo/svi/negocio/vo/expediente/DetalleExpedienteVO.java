package mx.com.teclo.svi.negocio.vo.expediente;

import java.util.List;

public class DetalleExpedienteVO {
	
	private Long idRegistroCsv;
	private Long idEntrega;
	private String nbEntrega;
	private Long idPtLote;
	private String nbPtLote;
	private Long idArchivoCsv;
	private String nbArchivoCsv;
	private Long nodoVpn;
	private Long nuCarril;
	private String nuExpediente;
	private String cdPlacaDelantera;
	private String cdEntidadDelantera;
	private String cdPlacaTrasera;
	private String cdEntidadTrasera;
	private String txDescEntidadDelantera;
	private String txDescEntidadTrasera;
	private String nbImagenPlacaDelantera;
	private String nbImagenPlacaTrasera;
	private String nbImagenConductor;
	private String nbImagenAmbiental;
	private String cdPerfil;
	private Long idPerfil;
	private String perfilSeleccionado;
	private String nbImagenPerfil;	
	private Long idPtClasifExpe;
	private String txClasifExpe;
	private String txSilueta;	
	private String txPerfil;
	private String txMarca;
	private String txSubMarca;
	private String txRutaStorage;
	private String txCarpetaSil;
	private String txCarpetaImg;
	private Boolean stValidacion;
	private Boolean stValEntidadDelantera;
	private Boolean stValEntidadTrasera;
	private Boolean stValImagenMal;
	private Boolean stValPerfil;
	private Boolean stValPlacaDelantera;
	private Boolean stValPlacaTrasera;
	private Boolean stPlacaOfiDelantera;
	private Boolean stPlacaOfiTrasera;
	private Boolean stPochomovil;
	private Boolean stDoblePlaca;
	private Boolean stPreSeleccion;
	private List<DetalleExpedienteHistoricoVO> detalleHistorico;
	private List<Long> listaNavegacion;
	private FiltroExpedienteVO filtroAplicado;
	
	
	public Long getIdRegistroCsv() {
		return idRegistroCsv;
	}
	public void setIdRegistroCsv(Long idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}
	public Long getIdEntrega() {
		return idEntrega;
	}
	public void setIdEntrega(Long idEntrega) {
		this.idEntrega = idEntrega;
	}
	public String getNbEntrega() {
		return nbEntrega;
	}
	public void setNbEntrega(String nbEntrega) {
		this.nbEntrega = nbEntrega;
	}
	public Long getIdPtLote() {
		return idPtLote;
	}
	public void setIdPtLote(Long idPtLote) {
		this.idPtLote = idPtLote;
	}
	public String getNbPtLote() {
		return nbPtLote;
	}
	public void setNbPtLote(String nbPtLote) {
		this.nbPtLote = nbPtLote;
	}
	public Long getIdArchivoCsv() {
		return idArchivoCsv;
	}
	public void setIdArchivoCsv(Long idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}
	public String getNbArchivoCsv() {
		return nbArchivoCsv;
	}
	public void setNbArchivoCsv(String nbArchivoCsv) {
		this.nbArchivoCsv = nbArchivoCsv;
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
	public String getNuExpediente() {
		return nuExpediente;
	}
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	public String getCdPlacaDelantera() {
		return cdPlacaDelantera;
	}
	public void setCdPlacaDelantera(String cdPlacaDelantera) {
		this.cdPlacaDelantera = cdPlacaDelantera;
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
	public String getCdEntidadTrasera() {
		return cdEntidadTrasera;
	}
	public void setCdEntidadTrasera(String cdEntidadTrasera) {
		this.cdEntidadTrasera = cdEntidadTrasera;
	}
	
	public String getTxDescEntidadDelantera() {
		return txDescEntidadDelantera;
	}
	public void setTxDescEntidadDelantera(String txDescEntidadDelantera) {
		this.txDescEntidadDelantera = txDescEntidadDelantera;
	}
	public String getTxDescEntidadTrasera() {
		return txDescEntidadTrasera;
	}
	public void setTxDescEntidadTrasera(String txDescEntidadTrasera) {
		this.txDescEntidadTrasera = txDescEntidadTrasera;
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
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}	
	public String getPerfilSeleccionado() {
		return perfilSeleccionado;
	}
	public void setPerfilSeleccionado(String perfilSeleccionado) {
		this.perfilSeleccionado = perfilSeleccionado;
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
	public String getTxClasifExpe() {
		return txClasifExpe;
	}
	public void setTxClasifExpe(String txClasifExpe) {
		this.txClasifExpe = txClasifExpe;
	}
	
	public String getTxSilueta() {
		return txSilueta;
	}
	public void setTxSilueta(String txSilueta) {
		this.txSilueta = txSilueta;
	}
	
	public String getTxPerfil() {
		return txPerfil;
	}
	public void setTxPerfil(String txPerfil) {
		this.txPerfil = txPerfil;
	}	
	
	public String getTxMarca() {
		return txMarca;
	}
	public void setTxMarca(String txMarca) {
		this.txMarca = txMarca;
	}
	public String getTxSubMarca() {
		return txSubMarca;
	}
	public void setTxSubMarca(String txSubMarca) {
		this.txSubMarca = txSubMarca;
	}
	public String getTxRutaStorage() {
		return txRutaStorage;
	}
	public void setTxRutaStorage(String txRutaStorage) {
		this.txRutaStorage = txRutaStorage;
	}
	public String getTxCarpetaSil() {
		return txCarpetaSil;
	}
	public void setTxCarpetaSil(String txCarpetaSil) {
		this.txCarpetaSil = txCarpetaSil;
	}
	public String getTxCarpetaImg() {
		return txCarpetaImg;
	}
	public void setTxCarpetaImg(String txCarpetaImg) {
		this.txCarpetaImg = txCarpetaImg;
	}
	public Boolean getStValidacion() {
		return stValidacion;
	}
	public void setStValidacion(Boolean stValidacion) {
		this.stValidacion = stValidacion;
	}
	public Boolean getStValEntidadDelantera() {
		return stValEntidadDelantera;
	}
	public void setStValEntidadDelantera(Boolean stValEntidadDelantera) {
		this.stValEntidadDelantera = stValEntidadDelantera;
	}
	public Boolean getStValEntidadTrasera() {
		return stValEntidadTrasera;
	}
	public void setStValEntidadTrasera(Boolean stValEntidadTrasera) {
		this.stValEntidadTrasera = stValEntidadTrasera;
	}
	public Boolean getStValImagenMal() {
		return stValImagenMal;
	}
	public void setStValImagenMal(Boolean stValImagenMal) {
		this.stValImagenMal = stValImagenMal;
	}
	public Boolean getStValPerfil() {
		return stValPerfil;
	}
	public void setStValPerfil(Boolean stValPerfil) {
		this.stValPerfil = stValPerfil;
	}
	public Boolean getStValPlacaDelantera() {
		return stValPlacaDelantera;
	}
	public void setStValPlacaDelantera(Boolean stValPlacaDelantera) {
		this.stValPlacaDelantera = stValPlacaDelantera;
	}
	public Boolean getStValPlacaTrasera() {
		return stValPlacaTrasera;
	}
	public void setStValPlacaTrasera(Boolean stValPlacaTrasera) {
		this.stValPlacaTrasera = stValPlacaTrasera;
	}
	public Boolean getStPlacaOfiDelantera() {
		return stPlacaOfiDelantera;
	}
	public void setStPlacaOfiDelantera(Boolean stPlacaOfiDelantera) {
		this.stPlacaOfiDelantera = stPlacaOfiDelantera;
	}
	public Boolean getStPlacaOfiTrasera() {
		return stPlacaOfiTrasera;
	}
	public void setStPlacaOfiTrasera(Boolean stPlacaOfiTrasera) {
		this.stPlacaOfiTrasera = stPlacaOfiTrasera;
	}
	public Boolean getStPochomovil() {
		return stPochomovil;
	}
	public void setStPochomovil(Boolean stPochomovil) {
		this.stPochomovil = stPochomovil;
	}
	public Boolean getStDoblePlaca() {
		return stDoblePlaca;
	}
	public void setStDoblePlaca(Boolean stDoblePlaca) {
		this.stDoblePlaca = stDoblePlaca;
	}
	/**
	 * @return the detalleHistorico
	 */
	public List<DetalleExpedienteHistoricoVO> getDetalleHistorico() {
		return detalleHistorico;
	}
	/**
	 * @param detalleHistorico the detalleHistorico to set
	 */
	public void setDetalleHistorico(List<DetalleExpedienteHistoricoVO> detalleHistorico) {
		this.detalleHistorico = detalleHistorico;
	}
	public List<Long> getListaNavegacion() {
		return listaNavegacion;
	}
	public void setListaNavegacion(List<Long> listaNavegacion) {
		this.listaNavegacion = listaNavegacion;
	}
	public FiltroExpedienteVO getFiltroAplicado() {
		return filtroAplicado;
	}
	public void setFiltroAplicado(FiltroExpedienteVO filtroAplicado) {
		this.filtroAplicado = filtroAplicado;
	}
	public Boolean getStPreSeleccion() {
		return stPreSeleccion;
	}
	public void setStPreSeleccion(Boolean stPreSeleccion) {
		this.stPreSeleccion = stPreSeleccion;
	}
	
	
	
}
