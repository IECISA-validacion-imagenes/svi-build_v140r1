package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;

public interface ArchivoDetalleEvaDAO extends BaseDAO<ArchivoDetalleEvaDTO>{
	
	/*
	 * @author: fernando 
	 * @param:ArchivoDetalleEvaDTO
	 * @return: insercion de elemento en Detalle eva */
	public void guardarDetalleEva(ArchivoDetalleEvaDTO elementoEVA);
	
	/*
	 * @author: fernando (Maverick) 
	 * @param:  Long IdARchivoCSV
	 * @return: Long ultimoRegistro*/
	public int getUltimoRegistro(Long idArchivoCSV);

}
