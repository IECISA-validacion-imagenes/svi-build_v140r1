package mx.com.teclo.svideskwsw.persistencia.vo.carga;

public class reduccionElementosVO {
	private Long IdPtLote;
	private Long IdArchivoscv;
	private int  totFilasCSV;
	private Long totImagenes;
	private Long totSiluetas;
	/**
	 * @return the idPtLote
	 */
	public Long getIdPtLote() {
		return IdPtLote;
	}
	/**
	 * @param idPtLote the idPtLote to set
	 */
	public void setIdPtLote(Long idPtLote) {
		IdPtLote = idPtLote;
	}
	/**
	 * @return the idArchivoscv
	 */
	public Long getIdArchivoscv() {
		return IdArchivoscv;
	}
	/**
	 * @param idArchivoscv the idArchivoscv to set
	 */
	public void setIdArchivoscv(Long idArchivoscv) {
		IdArchivoscv = idArchivoscv;
	}
	/**
	 * @return the totFilasCSV
	 */
	public int getTotFilasCSV() {
		return totFilasCSV;
	}
	/**
	 * @param totFilasCSV the totFilasCSV to set
	 */
	public void setTotFilasCSV(int totFilasCSV) {
		this.totFilasCSV = totFilasCSV;
	}
	/**
	 * @return the totImagenes
	 */
	public Long getTotImagenes() {
		return totImagenes;
	}
	/**
	 * @param totImagenes the totImagenes to set
	 */
	public void setTotImagenes(Long totImagenes) {
		this.totImagenes = totImagenes;
	}
	/**
	 * @return the totSiluetas
	 */
	public Long getTotSiluetas() {
		return totSiluetas;
	}
	/**
	 * @param totSiluetas the totSiluetas to set
	 */
	public void setTotSiluetas(Long totSiluetas) {
		this.totSiluetas = totSiluetas;
	}

	

}
