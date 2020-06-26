package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoCsvDTO;

public interface ArchivoCsvDAO extends BaseDAO<ArchivoCsvDTO>{
	
	//public Long getSequenceNextVal();
	
	public List<ArchivoCsvDTO>  obtenerArchivoPTSCV();
	
	/* Metodo para insertar metodo */
	public void editarDetalleTCI003D (ArchivoCsvDTO elemento);
	
	
	/* Metodo para consultar  ID_ARCHIVO_CSV ID_PT_LOTE*/
	public ArchivoCsvDTO consultarArchivoPT(Long IdArchivoSCV, Long IdLotePT);
	
	public void guardarDTO(ArchivoCsvDTO DTO);
	
	/*@Author Maverick
	 *@param Long IdPT
	 *@Param String nombreCSV(Carril)
	 *@Return Long IdArchivoCSV  */
	public Long getIdCSVxIdPTyNomCSV(Long IdPtLote, String nbArchivoCSV);
	
	/*@Author Maverick
	 *@param Long IdPT
	 *@Return ArchivoCsvDTO */
	public ArchivoCsvDTO BuscarDTOxId(Long IdPtLote);

}
