package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;

public interface LoteDAO extends BaseDAO<LoteDTO>{
	
	public Long getSequenceNextVal();
	
	/* metodo para consultar el valor completo de la entrega */
	public  LoteDTO actualizarLote(long idPtLote);
	
	public Boolean validaSiExisteLote(String nbLote, EntregaDTO eDTO);
	
	public void modificarEntraga(LoteDTO lote);
	
	/* REgresar informacion detalle del lote */
	public List<LoteDTO> getLoteCargar(String tipo);

	/* Traer bandera si existe el lote PT */ 
	public Boolean existePT(Long idLote);
	
	
}
