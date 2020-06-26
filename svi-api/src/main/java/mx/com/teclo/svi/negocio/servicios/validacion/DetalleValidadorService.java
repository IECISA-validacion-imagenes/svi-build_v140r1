package mx.com.teclo.svi.negocio.servicios.validacion;

public interface DetalleValidadorService {

	/**
	 * Registra una nueva validaci&oacute;n y su detalle
	 * 
	 * @param idRegistroCsv
	 * @return
	 */
	Long saveDetalleValidador(Long idRegistroCsv);

}
