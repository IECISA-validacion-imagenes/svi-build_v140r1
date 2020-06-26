package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.List;

import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.DependenciasSelectVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;

public interface GuadaColumnasParamService {

	/**
	 * DescripciÃ³n: MÃ©todo para guardar las columnas de serÃ¡n utiles
	 * para la recuperaciÃ³n de la informaciÃ³n en el catÃ¡logo
	 * @author Jorge Luis
	 * @param List<ColumnasVO>
	 * @param ParametrosTablasDTO
	 * @return Boolean
	 * */
	public Boolean guardarRestriccion(List<ColumnasVO> restriccion, ParametrosTablasDTO parametrosTablasDTO);
	
	
	/**
	 * DescripciÃ³n: MÃ©todo para guardar las columnas de serÃ¡n utiles
	 * para la recuperaciÃ³n de la informaciÃ³n en el catÃ¡logo
	 * @author Jorge Luis
	 * @param List<ColumnasVO>
	 * @param ParametrosTablasDTO
	 * @return Boolean
	 * */
	public Boolean guardarDescripcion(List<ColumnasVO> descripcion, ParametrosTablasDTO parametrosTablasDTO);
	
	
	/**
	 * DescripciÃ³n: MÃ©todo para guardar las columnas de serÃ¡n utiles
	 * para la recuperaciÃ³n de la informaciÃ³n en el catÃ¡logo
	 * @author Jorge Luis
	 * @param List<ColumnasVO>
	 * @param ParametrosTablasDTO
	 * @return Boolean
	 * */
	public Boolean guardarIdentificador(List<ColumnasVO> identificador, ParametrosTablasDTO parametrosTablasDTO);
	
	
	/**
	 * DescripciÃ³n: MÃ©todo para guardar las columnas de serÃ¡n utiles
	 * para la recuperaciÃ³n de la informaciÃ³n en el catÃ¡logo
	 * @author Jorge Luis
	 * @param List<ColumnasVO>
	 * @param ParametrosTablasDTO
	 * @return Boolean
	 * */
	public Boolean guardarOrden(List<ColumnasVO> orden, ParametrosTablasDTO parametrosTablasDTO);
	
	/**
	 * Descripción: Método para predefinir los datos de bitácora
	 * @author Jorge Luis
	 * @return ParametrosColumnDTO
	 * */
	public ParametrosColumnDTO setDatosBitacora();
	
	/**
	 * Descripción: Método para obtener el objeto que servirá 
	 * como el padre del hijo dependiente
	 * @author Jorge Luis
	 * @return ParametrosTablasDTO
	 * */
	public ParametrosTablasDTO getParamPadreDependencia(List<ParametrosTablasDTO> dtos, String cdParametro);
	
	/**
	 * Descripción: Método para guardar las las 
	 * restricciones con dependencias
	 * @author Jorge Luis
	 * @param List<ParametrosTablasDTO>
	 * @param List<DependenciasSelectVO>
	 * @return Boolean
	 * */
	public Boolean guardaRestriccionDependiente (List<ParametrosTablasDTO> paramsTabs, List<DependenciasSelectVO> dependencias);
}
