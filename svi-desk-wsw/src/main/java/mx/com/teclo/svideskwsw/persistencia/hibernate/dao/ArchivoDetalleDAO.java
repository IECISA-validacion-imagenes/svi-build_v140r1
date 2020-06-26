package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleDTO;

public interface ArchivoDetalleDAO extends BaseDAO<ArchivoDetalleDTO> {
	
	public void cargarDetalleCSVDTO (ArchivoDetalleDTO detalleCSV );
	
	
	/*
	 * @author: fernando (Maverick) 
	 * @param:  Long IdARchivoCSV
	 * @return: Long ultimoRegistro*/
	public int getUltimoRegistro(Long idArchivoCSV);
	
	/*
	 * @author: fernando (Maverick) 
	 * @param:  ArchivoDetalleDTO detalleCSV 
	 * metodo para actualizar o guardar registro dif */
	public void updateOrGuardar(ArchivoDetalleDTO detalleCSV);
	
	
	public ArchivoDetalleDTO getDTObyNumExpediente(String expediente,Long ArchivoCSV);
	

}
