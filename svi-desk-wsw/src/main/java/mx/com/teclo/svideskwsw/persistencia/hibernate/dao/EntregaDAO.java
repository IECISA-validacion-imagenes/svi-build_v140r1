package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;

public interface EntregaDAO extends BaseDAO <EntregaDTO> {
	
	public Long getSequenceNextVal();
	public EntregaDTO buscarPosibleEntregable(String nbEntregable, Long idUsuario);
	
	public void  actualizar(EntregaDTO elemento); 
	public EntregaDTO consultarEntregaDTO(long idEntrega);
	
	
	public List<EntregaDTO> getValoresTxtRuta();
}
