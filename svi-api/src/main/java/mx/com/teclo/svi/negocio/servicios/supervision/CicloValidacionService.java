package mx.com.teclo.svi.negocio.servicios.supervision;

import java.util.List;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;

public interface CicloValidacionService {

	/**
	 * Verifica que el id o conjunto de ids de archivos csv (PT) no hayan sido
	 * asignados por ciclo vigente Y verifica que el ciclo anterior al vigente
	 * est&eacute; validado al 100%
	 * 
	 * @param arregloIdsCsv
	 * @param idArchivoCsv
	 * @return
	 * @throws BusinessException
	 */
	Boolean isValidaReasignacion(List<Long> arregloIdsCsv, Long idArchivoCsv) throws BusinessException;
	
	Boolean isCicloAnteriorCerrado() throws BusinessException;
}
